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
package com.woodare.core.jpa.service;

import com.woodare.core.jpa.data.systemroleuser.SearchSystemRoleUserData;
import com.woodare.core.jpa.model.SystemRoleUser;
import com.woodare.core.jpa.model.SystemUser;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.ISimpleDAO;

/**
 * 
 * ClassName: ISystemRoleUserDAO
 *
 * @description
 * @author city_maven_auto_generate
 * @Date Jul 16, 2013
 *
 */
public interface ISystemRoleUserDAO extends ISimpleDAO<SystemRoleUser> {

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	boolean hasUserByRoleId(String roleId);
	
	/**
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	boolean hasRole(String userId, String roleId);

	/**
	 * 
	 * @param userId
	 * @param roleId
	 */
	void addRole(String userId, String roleId);
	
	/**
	 * 
	 * @param userId
	 * @param roleId
	 */
	void removeRole(String userId, String roleId);
	
	/**
	 * 
	 * @param searchData
	 * @return
	 */
	IPagedList<SystemUser> searchUsers(SearchSystemRoleUserData searchData);
	
	/**
	 * @param searchData
	 * @return
	 */
	IPagedList<SystemRoleUser> searchSystemRoleUsers(SearchSystemRoleUserData searchData);
}

