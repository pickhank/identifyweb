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

import com.woodare.core.jpa.data.systemmenu.SearchSystemMenuData;
import com.woodare.core.jpa.model.SystemMenu;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.ISimpleDAO;

/**
 * 
 * ClassName: ISystemMenuDAO
 *
 * @description
 * @author city_maven_auto_generate
 * @Date Jul 16, 2013
 *
 */
public interface ISystemMenuDAO extends ISimpleDAO<SystemMenu> {

	/**
	 * @param searchData
	 * @return
	 */
	IPagedList<SystemMenu> searchSystemMenus(SearchSystemMenuData searchData);
	
	/**
	 * 
	 * @param parentMenuId
	 * @return
	 */
	boolean hasChildMenus(String parentMenuId);
}

