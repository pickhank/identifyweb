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

import com.woodare.core.jpa.data.systemuser.SearchSystemUserData;
import com.woodare.core.jpa.model.SystemUser;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.ISimpleDAO;

/**
 * 
 * ClassName: ISystemUserDAO
 * 
 * @description
 * @author city_maven_auto_generate
 * @Date Jul 12, 2013
 * 
 */
public interface ISystemUserDAO extends ISimpleDAO<SystemUser> {

	/**
	 * 
	 * @param searchData
	 * @return
	 */
	Long countUsers(SearchSystemUserData searchData);

	/**
	 * @param searchData
	 * @return
	 */
	IPagedList<SystemUser> searchUsers(SearchSystemUserData searchData);

	/**
	 * 
	 * @param username
	 * @return
	 */
	SystemUser findByUsername(String username);

	/**
	 * 
	 * @param email
	 * @return
	 */
	SystemUser findByEmail(String email);
}
