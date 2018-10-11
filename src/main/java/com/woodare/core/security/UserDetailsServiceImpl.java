package com.woodare.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.core.jpa.data.systemroleuser.SearchSystemRoleUserData;
import com.woodare.core.jpa.model.SystemRoleUser;
import com.woodare.core.jpa.model.SystemUser;
import com.woodare.core.jpa.model.data.EnumUserRole;
import com.woodare.core.jpa.service.ISystemRoleUserDAO;
import com.woodare.core.jpa.service.ISystemUserDAO;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.jpa.model.DownAgent;
import com.woodare.template.jpa.model.DownMerchant;
import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.persistence.IDownAgentDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantDAO;

/**
 * A custom {@link UserDetailsService} where user information is retrieved from
 * a JPA repository
 */
@Service("userDetailsService")
@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

	private Boolean invalidCaptcha;

	/**
	 * @return the invalidCaptcha
	 */
	public Boolean getInvalidCaptcha() {
		return invalidCaptcha;
	}

	/**
	 * @param invalidCaptcha
	 *            the invalidCaptcha to set
	 */
	public void setInvalidCaptcha(Boolean invalidCaptcha) {
		this.invalidCaptcha = invalidCaptcha;
	}

	@Autowired
	private ISystemUserDAO userDAO;

	@Autowired
	private ISystemRoleUserDAO systemRoleUserDAO;

	@Autowired
	private ISystemUserDAO systemUserDAO;

	@Autowired
	private IDownMerchantDAO downMerchantDAO;

	@Autowired
	private IDownAgentDAO downAgentDAO;

	/**
	 * Returns a populated {@link UserDetails} object. The username is first
	 * retrieved from the database and then mapped to a {@link UserDetails}
	 * object.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			SystemUser domainUser = userDAO.findByUsername(username);
			System.out.println(">>>>>>>>>> username:" + username);
			UserDetailData userData = SaftyBeanUtils.cloneTo(domainUser, UserDetailData.class);

			if (domainUser != null) {

				System.out.println(">>>>>>>>>> domainUser:" + domainUser.getUsername());
				boolean enabled = true;
				boolean accountNonExpired = true;
				boolean credentialsNonExpired = true;
				boolean accountNonLocked = true;

				List<String> strRoles = getRoles(domainUser.getId());

				List<GrantedAuthority> roles = getGrantedAuthorities(strRoles);

				UserDetailsImpl userDetail = new UserDetailsImpl(userData, roles, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked);

				userDetail.setUser(userData);

				// 代理商
				if (EnumUserRole.AGENT.equals(userData.getUserRole())) {
					DownAgent agent = downAgentDAO.findByAgentNo(userData.getUsername().substring(1));
					userDetail.setExtraData(SaftyBeanUtils.cloneTo(agent, DownAgentData.class));
					userData.setReferNo(agent.getAgentNo());
				}
				// 商户
				else if (EnumUserRole.USER.equals(userData.getUserRole())) {
					DownMerchant merchant = downMerchantDAO.findByMchNo(userData.getUsername());
					userDetail.setExtraData(SaftyBeanUtils.cloneTo(merchant, DownMerchantData.class));
					userData.setReferNo(merchant.getMchNo());
				}
				return userDetail;

			}
			else {
				throw new UsernameNotFoundException("用户名或密码错误");
			}

			// NoResultException - if there is no result
			// NonUniqueResultException - if more than one result
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("用户名或密码错误");
		} catch (NonUniqueResultException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical
	 * role
	 * 
	 * @param role
	 *            the numerical role
	 * @return a collection of {@link GrantedAuthority
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(SystemUser domainUser) {
		return getGrantedAuthorities(getRoles(domainUser.getId()));
	}

	/**
	 * Converts a numerical role to an equivalent list of roles
	 * 
	 * @param role
	 *            the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	public List<String> getRoles(String userId) {
		List<String> roles = new ArrayList<String>();
		SystemUser systemUser = systemUserDAO.findOne(userId);
		if(systemUser.getUserRole() == null
			|| EnumUserRole.SUPERVISOR.equals(systemUser.getUserRole())
			|| EnumUserRole.ADMIN.equals(systemUser.getUserRole())
			|| EnumUserRole.LIQUIDATING.equals(systemUser.getUserRole())
			|| EnumUserRole.OPERATION.equals(systemUser.getUserRole())) {
			
			roles.add("ROLE_ADMIN");
		}
		else {
			roles.add("ROLE_USER");
		}
		SearchSystemRoleUserData searchRoleUser = new SearchSystemRoleUserData();
		searchRoleUser.setPageSize(Integer.MAX_VALUE);
		searchRoleUser.setUserId(userId);
		List<SystemRoleUser> userRoles = systemRoleUserDAO.searchSystemRoleUsers(searchRoleUser);
		if (userRoles != null && userRoles.size() > 0) {
			for (SystemRoleUser item : userRoles) {
				roles.add(item.getRoleId());
			}
		}
		return roles;
	}

	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * 
	 * @param roles
	 *            {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}