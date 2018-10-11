/******************************************************************************
 *                                                                             
 *                      Woodare PROPRIETARY INFORMATION                        
 *                                                                             
 *          The information contained herein is proprietary to Woodare         
 *           and shall not be reproduced or disclosed in whole or in part      
 *                    or used for any design or manufacture                    
 *              without direct written authorization from FengDa.              
 *                                                                             
 *            Copyright (c) 2013 by Woodare.  All rights reserved.             
 *                                                                             
 *****************************************************************************/
package com.woodare.core.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author lu_feng
 */
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 6398167714172164576L;

	private String userId;

	private UserDetailData user;
	private List<GrantedAuthority> roles;

	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;

	private Object extraData;

	public UserDetailsImpl(UserDetailData user, List<GrantedAuthority> roles) {
		this.user = user;
		this.roles = roles;

		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;

		this.userId = this.user.getId();
	}

	public UserDetailsImpl(UserDetailData user, List<GrantedAuthority> roles, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked) {
		this.user = user;
		this.roles = roles;

		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;

		this.userId = this.user.getId();
	}

	/**
	 * @return the extraData
	 */
	public Object getExtraData() {
		return extraData;
	}

	/**
	 * @param extraData
	 *            the extraData to set
	 */
	public void setExtraData(Object extraData) {
		this.extraData = extraData;
	}

	public String getUserId() {
		return this.userId;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return roles;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public UserDetailData getUser() {
		return user;
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	public List<GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(List<GrantedAuthority> roles) {
		this.roles = roles;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUser(UserDetailData user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}
}
