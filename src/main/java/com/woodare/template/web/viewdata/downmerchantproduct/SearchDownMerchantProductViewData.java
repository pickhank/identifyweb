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
package com.woodare.template.web.viewdata.downmerchantproduct;

import com.woodare.framework.data.ISearchData;
import com.woodare.template.jpa.persistence.data.downmerchantproduct.SearchDownMerchantProductData;

/**
 * 
 * ClassName: SearchDownMerchantProductViewData
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2017/03/07
 * 
 */
public class SearchDownMerchantProductViewData extends SearchDownMerchantProductData implements ISearchData {
	private static final long serialVersionUID = -5303765049019581524L;

	@Override
	public String getSearchFields() {
		return "keywords";
	}
}
