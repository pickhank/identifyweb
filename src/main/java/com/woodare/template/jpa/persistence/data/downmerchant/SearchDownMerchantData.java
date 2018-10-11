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
package com.woodare.template.jpa.persistence.data.downmerchant;

import java.util.List;

import com.woodare.framework.data.impl.AbstractPageData;
import com.woodare.template.jpa.model.data.EnumMercCategory;

/**
 * ClassName: DownMerchantData
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2016/11/11
 */
public class SearchDownMerchantData extends AbstractPageData {
	private static final long serialVersionUID = -5303765049019581524L;

	/** ID */
	private String id;

	/** IDs */
	private List<String> ids;

	private String keywords;

	private String agentId;

	private EnumMercCategory mercCategory;

	private String settleDate;

	/**
	 * @return the settleDate
	 */
	public String getSettleDate() {
		return settleDate;
	}

	/**
	 * @param settleDate
	 *            the settleDate to set
	 */
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	/**
	 * @return the mercCategory
	 */
	public EnumMercCategory getMercCategory() {
		return mercCategory;
	}

	/**
	 * @param mercCategory
	 *            the mercCategory to set
	 */
	public void setMercCategory(EnumMercCategory mercCategory) {
		this.mercCategory = mercCategory;
	}

	/**
	 * @return the ids
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
}
