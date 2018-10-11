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

import com.woodare.framework.persistence.service.ISimpleDAO;
import com.woodare.framework.data.IPagedList;
import com.woodare.template.jpa.model.DownMerchantProduct;
import com.woodare.template.jpa.persistence.data.downmerchantproduct.SearchDownMerchantProductData;

/**
 * 
 * ClassName: IDownMerchantProductDAO
 *
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/03/07
 *
 */
public interface IDownMerchantProductDAO extends ISimpleDAO<DownMerchantProduct> {

	IPagedList<DownMerchantProduct> searchItems(SearchDownMerchantProductData searchData);

	DownMerchantProduct findById(String id);
	
	int updateFee(DownMerchantProduct product);
}

