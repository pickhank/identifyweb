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
package com.woodare.template.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;

/**
 * ClassName: Kbin
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Apr 25, 2017
 */
@Entity
@EntityDescriptor(name = "卡bin", category = "content")
public class Kbin extends AbstractBusiModel {

	private static final long serialVersionUID = -2526533917106160144L;

	/* 首数字 */
	@Column(length = 24)
	private String startNum;

	/* 长度 */
	private Integer cardLength;

	/* 银行名称 */
	@Column(length = 64)
	private String bankName;

	/* 银行编码 */
	@Column(length = 64)
	private String bankCode;

	/* 银行联行号 */
	@Column(length = 64)
	private String bankNo;

	/* 银行名称 */
	@Column(length = 16)
	private String bankNameAbbr;

	/* 银行卡类别，1借记卡；2贷记卡；3 */
	@Column(length = 2)
	private String cardAttr;

	/* 用于EPAY自定义的银行编码 */
	@Column(length = 10)
	private String cardEpayNo;

	/**
	 * @return the cardEpayNo
	 */
	public String getCardEpayNo() {
		return cardEpayNo;
	}

	/**
	 * @param cardEpayNo
	 *            the cardEpayNo to set
	 */
	public void setCardEpayNo(String cardEpayNo) {
		this.cardEpayNo = cardEpayNo;
	}

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

}
