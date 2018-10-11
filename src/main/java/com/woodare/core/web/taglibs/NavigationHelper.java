package com.woodare.core.web.taglibs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.woodare.core.constant.CommonConstant;
import com.woodare.core.jpa.data.systemmenu.SystemMenuData;
import com.woodare.core.jpa.data.systemroleright.SystemRoleRightData;
import com.woodare.framework.component.SystemCache;
import com.woodare.framework.model.data.EnumSystemMenuType;

/**
 * 
 * @author lu_feng
 *
 */
public class NavigationHelper {

	/**
	 * 
	 * @return
	 */
	public static Map<String, Object> getAccessNavigation() {
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		List<SystemMenuData> navigationLst = new ArrayList<SystemMenuData>();
		Map<String, List<SystemMenuData>> subNavigationMaps = new HashMap<String, List<SystemMenuData>>();
		Map<String, List<SystemMenuData>> actionMaps = new HashMap<String, List<SystemMenuData>>();
		
		retMap.put("navigations", navigationLst);
		retMap.put("subNavigations", subNavigationMaps);
		retMap.put("actions", actionMaps);
		
		List<SystemRoleRightData> roleRights = SystemCache.getItems(SystemRoleRightData.class);
		List<SystemMenuData> menuLst = SystemCache.getItems(SystemMenuData.class);
		
		menuLst = BusiCommonUtils.sortForMenuManage(menuLst);
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		if(authorities != null) {
			List<String> roleIds = new ArrayList<String>();
			for(GrantedAuthority authority : authorities) {
				roleIds.add(authority.getAuthority());
			}
			if(roleIds.contains(CommonConstant.SYSTEM_ROLE.SUPER_USER)) {
				for(SystemMenuData menu : menuLst) {
					// First navigation
					if(menu.getMenuType() == EnumSystemMenuType.NAVIGATION) {
						navigationLst.add(menu);
					}
					// Sub
					else if(menu.getMenuType() == EnumSystemMenuType.SUB_NAVIGATION) {
						if(!subNavigationMaps.containsKey(menu.getParentMenuId())) {
							subNavigationMaps.put(menu.getParentMenuId(), new ArrayList<SystemMenuData>());
						}
						List<SystemMenuData> subNavigations = subNavigationMaps.get(menu.getParentMenuId());
						subNavigations.add(menu);
					}
					// Action button
					else if(menu.getMenuType() == EnumSystemMenuType.ACTION) {
						if(!actionMaps.containsKey(menu.getParentMenuId())) {
							actionMaps.put(menu.getParentMenuId(), new ArrayList<SystemMenuData>());
						}
						List<SystemMenuData> actions = actionMaps.get(menu.getParentMenuId());
						actions.add(menu);
					}
				}
			}
			else {
				List<String> userRoleRightMenuIds = new ArrayList<String>();
				for(SystemRoleRightData roleRight : roleRights) {
					String roleId = roleRight.getRoleId();
					if(roleIds.contains(roleId)) {
						userRoleRightMenuIds.add(roleRight.getMenuId());
					}
				}
				
				if(userRoleRightMenuIds != null && userRoleRightMenuIds.size() > 0) {
					for(SystemMenuData menu : menuLst) {
						// Has access
						if(userRoleRightMenuIds.contains(menu.getId())) {
							// First navigation
							if(menu.getMenuType() == EnumSystemMenuType.NAVIGATION) {
								navigationLst.add(menu);
							}
							// Sub
							else if(menu.getMenuType() == EnumSystemMenuType.SUB_NAVIGATION) {
								if(!subNavigationMaps.containsKey(menu.getParentMenuId())) {
									subNavigationMaps.put(menu.getParentMenuId(), new ArrayList<SystemMenuData>());
								}
								List<SystemMenuData> subNavigations = subNavigationMaps.get(menu.getParentMenuId());
								subNavigations.add(menu);
							}
							// Action button
							else if(menu.getMenuType() == EnumSystemMenuType.ACTION) {
								if(!actionMaps.containsKey(menu.getParentMenuId())) {
									actionMaps.put(menu.getParentMenuId(), new ArrayList<SystemMenuData>());
								}
								List<SystemMenuData> actions = actionMaps.get(menu.getParentMenuId());
								actions.add(menu);
							}
						}
					}
				}
			}
		}
		
		return retMap;
	}
}
