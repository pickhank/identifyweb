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
package com.woodare.template.jpa.persistence.data.kbin;

import java.util.List;
import com.woodare.framework.data.impl.AbstractPageData;

/**
 * ClassName: KbinData
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2017/04/25
 */
public class SearchKbinData extends AbstractPageData {
	private static final long serialVersionUID = -2526533917106160144L;

	/** ID */
	private String id;

	/** IDs */
	private List<String> ids;

	private String startNum;

	private Integer cardLength;

	private String bankName;

	private String bankCode;

	private String bankNo;

	private String bankNameAbbr;

	private String keywords;

	private String cardAttr;

	/**
	 * @return the cardAttr
	 */
	public String getCardAttr() {
		return cardAttr;
	}

	/**
	 * @param cardAttr
	 *            the cardAttr to set
	 */
	public void setCardAttr(String cardAttr) {
		this.cardAttr = cardAttr;
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

	public String getStartNum() {
		return startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}

	public Integer getCardLength() {
		return cardLength;
	}

	public void setCardLength(Integer cardLength) {
		this.cardLength = cardLength;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankNameAbbr() {
		return bankNameAbbr;
	}

	public void setBankNameAbbr(String bankNameAbbr) {
		this.bankNameAbbr = bankNameAbbr;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

}
