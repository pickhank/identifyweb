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

import com.woodare.core.jpa.data.systemproperties.SearchSystemPropertiesData;
import com.woodare.core.jpa.model.SystemProperties;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.ISimpleDAO;

/**
 * 
 * ClassName: ISystemPropertiesDAO
 *
 * @description
 * @author city_maven_auto_generate
 * @Date Sep 28, 2013
 *
 */
public interface ISystemPropertiesDAO extends ISimpleDAO<SystemProperties> {

	/**
	 * @param searchData
	 * @return
	 */
	IPagedList<SystemProperties> searchSystemPropertiess(SearchSystemPropertiesData searchData);
	
	SystemProperties searchByPcode(String pcode);
}

