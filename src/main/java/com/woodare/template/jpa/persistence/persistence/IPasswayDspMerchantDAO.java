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
import com.woodare.template.jpa.model.PasswayDspMerchant;
import com.woodare.template.jpa.persistence.data.passwaydspmerchant.SearchPasswayDspMerchantData;

/**
 * 
 * ClassName: IPasswayDspMerchantDAO
 *
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/09/13
 *
 */
public interface IPasswayDspMerchantDAO extends ISimpleDAO<PasswayDspMerchant> {

	IPagedList<PasswayDspMerchant> searchItems(SearchPasswayDspMerchantData searchData);

	PasswayDspMerchant findById(String id);
}

