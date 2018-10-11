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
package com.woodare.template.jpa.persistence.persistence;

import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.ISimpleDAO;
import com.woodare.template.jpa.model.DownTradeLog;
import com.woodare.template.jpa.persistence.data.downtradelog.SearchDownTradeLogData;

/**
 * ClassName: IDownTradeLogDAO
 *
 * @description
 * @author woo_maven_auto_generate
 * @Date 2017/11/08
 */
public interface IDownTradeLogDAO extends ISimpleDAO<DownTradeLog> {

	/**
	 * 
	 * @param searchData
	 * @return
	 */
	IPagedList<DownTradeLog> searchItems(SearchDownTradeLogData searchData);

	/**
	 * 删除过期数据
	 * 
	 * @param expiredDate 指定过期时间
	 * @return
	 */
    int clearByExpiredDate(String expiredDate);
}
