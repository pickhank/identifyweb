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

import java.util.Date;
import com.woodare.framework.data.impl.AbstractData;


/**
 * 
 * ClassName: KbinData
 * 
 * @description
 * @author woo_maven_auto_generate
 * 
 */
public class KbinData extends AbstractData {
	private static final long serialVersionUID = -2526533917106160144L;

	private String id;

	private Date createDate;

	private String startNum;

	private Integer cardLength;

	private String bankName;

	private String bankCode;

	private String bankNo;

	private String bankNameAbbr;

	private String cardAttr;

	private String cardEpayNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public String getCardAttr() {
		return cardAttr;
	}

	public void setCardAttr(String cardAttr) {
		this.cardAttr = cardAttr;
	}

	public String getCardEpayNo() {
		return cardEpayNo;
	}

	public void setCardEpayNo(String cardEpayNo) {
		this.cardEpayNo = cardEpayNo;
	}

}
