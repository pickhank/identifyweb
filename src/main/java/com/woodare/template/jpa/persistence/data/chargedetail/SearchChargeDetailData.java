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
package com.woodare.template.jpa.persistence.data.chargedetail;

import java.util.List;

import com.woodare.framework.data.impl.AbstractPageData;

/**
 * ClassName: ChargeDetailData
 *
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/09/29
 */
public class SearchChargeDetailData extends AbstractPageData {
	private static final long serialVersionUID = -1460361072012866099L;

	private String id;

	private List<String> ids;

	private String keywords;

	private String startDate;

	private String endDate;

	// 过滤状态
	private Integer status;

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

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
