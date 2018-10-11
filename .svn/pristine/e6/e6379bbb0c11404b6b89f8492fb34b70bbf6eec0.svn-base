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

import com.woodare.core.jpa.data.dicdata.SearchDicDataData;
import com.woodare.core.jpa.model.DicData;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.ISimpleDAO;

/**
 * 
 * ClassName: IDicDataDAO
 *
 * @description
 * @author city_maven_auto_generate
 * @Date Jul 16, 2013
 *
 */
public interface IDicDataDAO extends ISimpleDAO<DicData> {

	/**
	 * 
	 * @param code
	 * @return
	 */
	DicData findByCode(String code);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean hasChildren(String id);
	
	/**
	 * @param searchData
	 * @return
	 */
	IPagedList<DicData> searchDicDatas(SearchDicDataData searchData);
}

