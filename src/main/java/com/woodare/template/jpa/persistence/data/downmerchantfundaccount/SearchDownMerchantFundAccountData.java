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
package com.woodare.template.jpa.persistence.data.downmerchantfundaccount;

import java.util.List;

import com.woodare.framework.data.impl.AbstractPageData;
import com.woodare.template.jpa.model.data.EnumFundAccountType;

/**
 * ClassName: DownMerchantFundAccountData
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/03/05
 */
public class SearchDownMerchantFundAccountData extends AbstractPageData {
	private static final long serialVersionUID = -7312844222902605851L;

	private String id;

	private List<String> ids;

	private String keywords;

	private String settleDate;

	private Boolean changedFlag;

	private EnumFundAccountType accountType;

	/**
	 * @return the accountType
	 */
	public EnumFundAccountType getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(EnumFundAccountType accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the changedFlag
	 */
	public Boolean getChangedFlag() {
		return changedFlag;
	}

	/**
	 * @param changedFlag
	 *            the changedFlag to set
	 */
	public void setChangedFlag(Boolean changedFlag) {
		this.changedFlag = changedFlag;
	}

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
