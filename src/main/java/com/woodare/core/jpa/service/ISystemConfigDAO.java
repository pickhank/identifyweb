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

import com.woodare.core.jpa.data.systemconfig.SearchSystemConfigData;
import com.woodare.core.jpa.model.SystemConfig;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.ISimpleDAO;

/**
 * 
 * ClassName: ISystemConfigDAO
 *
 * @description
 * @author city_maven_auto_generate
 * @Date Aug 31, 2013
 *
 */
public interface ISystemConfigDAO extends ISimpleDAO<SystemConfig> {

	/**
	 * @param searchData
	 * @return
	 */
	IPagedList<SystemConfig> searchSystemConfigs(SearchSystemConfigData searchData);
}

