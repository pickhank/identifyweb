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
package com.woodare.template.web.viewdata.downagent;

import com.woodare.framework.data.ISearchData;
import com.woodare.template.jpa.persistence.data.downagent.SearchDownAgentData;

/**
 * 
 * ClassName: SearchDownAgentViewData
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2016/11/11
 * 
 */
public class SearchDownAgentViewData extends SearchDownAgentData implements ISearchData {
	private static final long serialVersionUID = -1838815663502921782L;

	@Override
	public String getSearchFields() {
		return "keywords";
	}

}
