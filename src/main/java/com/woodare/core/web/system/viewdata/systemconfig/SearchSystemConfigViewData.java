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
package com.woodare.core.web.system.viewdata.systemconfig;

import com.woodare.core.jpa.data.systemconfig.SearchSystemConfigData;
import com.woodare.framework.data.ISearchData;

/**
 * 
 * ClassName: SearchSystemConfigViewData
 * 
 * @description
 * @author 
 * @Date 2014/10/08
 * 
 */
public class SearchSystemConfigViewData extends SearchSystemConfigData implements ISearchData {
	private static final long serialVersionUID = 6577287406178696277L;

	@Override
	public String getSearchFields() {
		// TODO: to be replaced
		return null;
	}
}

