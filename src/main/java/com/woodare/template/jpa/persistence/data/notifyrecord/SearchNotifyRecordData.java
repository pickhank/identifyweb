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
package com.woodare.template.jpa.persistence.data.notifyrecord;

import java.util.List;

import com.woodare.framework.data.impl.AbstractPageData;

/**
 * 
 * ClassName: NotifyRecordData
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2017/09/11
 * 
 */
public class SearchNotifyRecordData extends AbstractPageData {
	private static final long serialVersionUID = -8467342665182781094L;

	private String id;

	private List<String> ids;

	private String keywords;

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}

