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

import com.woodare.core.jpa.data.systemroleright.SearchSystemRoleRightData;
import com.woodare.core.jpa.model.SystemRoleRight;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.ISimpleDAO;

/**
 * 
 * ClassName: ISystemRoleRightDAO
 *
 * @description
 * @author city_maven_auto_generate
 * @Date Jul 16, 2013
 *
 */
public interface ISystemRoleRightDAO extends ISimpleDAO<SystemRoleRight> {

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	boolean hasRightByRoleId(String roleId);
	
	/**
	 * 
	 * @param roleId
	 */
	void deleteAllByRoleId(String roleId);
	
	/**
	 * @param searchData
	 * @return
	 */
	IPagedList<SystemRoleRight> searchSystemRoleRights(SearchSystemRoleRightData searchData);
}

