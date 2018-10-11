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
package com.woodare.template.web.viewdata.kbin;

import com.woodare.framework.data.ISearchData;
import com.woodare.template.jpa.persistence.data.kbin.SearchKbinData;

/**
 * 
 * ClassName: SearchMeavViewData
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2017/04/25
 * 
 */
public class SearchKbinViewData extends SearchKbinData implements ISearchData {
	private static final long serialVersionUID = -2526533917106160144L;

	private String keywords;

	@Override
	public String getSearchFields() {
		return "keywords";
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
