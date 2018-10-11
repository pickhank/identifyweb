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
import com.woodare.template.jpa.model.DownMerchant;
import com.woodare.template.jpa.persistence.data.downmerchant.SearchDownMerchantData;

/**
 * ClassName: IDownMerchantDAO
 *
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2016/11/11
 */
public interface IDownMerchantDAO extends ISimpleDAO<DownMerchant> {

	/**
	 * @param searchData
	 * @return
	 */
	IPagedList<DownMerchant> searchDownMerchants(SearchDownMerchantData searchData);

	DownMerchant findByMchNo(String mchNo);

	String getMaxMechNo();
	
	int finishSettle(String settleDate);
}
