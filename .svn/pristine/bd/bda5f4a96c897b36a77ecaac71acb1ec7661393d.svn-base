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

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;

/**
 * ClassName: DownTradeLog
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Apr 25, 2017
 */
@Entity
@EntityDescriptor(name = "下游交易日志", category = "content")
public class DownTradeLog extends AbstractBusiModel {

	private static final long serialVersionUID = -2526533917106160144L;

	/* 记录日期 */
	@Column(length = 8)
	private String recordDt;

	@Column(length = 6)
	private String receiveDtime;

	@Column(length = 6)
	private String replyDtime;

	@Column(length = 20)
	private String errCode;

	@Column(length = 128)
	private String errMsg;

	@Column(length = 256)
	private String mchNo;

	@Column(length = 256)
	private String mchName;

	@Column(length = 64)
	private String tradeNo;

	@Column(length = 50)
	private String accIdCard;

	@Column(length = 30)
	private String accCardNo;

	@Column(length = 80)
	private String accBankName;

	@Column(length = 30)
	private String payCardNo;

	@Column(length = 80)
	private String payBankName;

	@Column(length = 20)
	private String channel;

	@Column(length = 200)
	private String extra1;

	@Column(length = 200)
	private String extra2;

	/** 交易金额 */
	@Column(scale = 2, precision = 9)
	private BigDecimal price;

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * @return the extra1
	 */
	public String getExtra1() {
		return extra1;
	}

	/**
	 * @param extra1
	 *            the extra1 to set
	 */
	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	/**
	 * @return the extra2
	 */
	public String getExtra2() {
		return extra2;
	}

	/**
	 * @param extra2
	 *            the extra2 to set
	 */
	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}

	/**
	 * @return the recordDt
	 */
	public String getRecordDt() {
		return recordDt;
	}

	/**
	 * @param recordDt
	 *            the recordDt to set
	 */
	public void setRecordDt(String recordDt) {
		this.recordDt = recordDt;
	}

	/**
	 * @return the receiveDtime
	 */
	public String getReceiveDtime() {
		return receiveDtime;
	}

	/**
	 * @param receiveDtime
	 *            the receiveDtime to set
	 */
	public void setReceiveDtime(String receiveDtime) {
		this.receiveDtime = receiveDtime;
	}

	/**
	 * @return the replyDtime
	 */
	public String getReplyDtime() {
		return replyDtime;
	}

	/**
	 * @param replyDtime
	 *            the replyDtime to set
	 */
	public void setReplyDtime(String replyDtime) {
		this.replyDtime = replyDtime;
	}

	/**
	 * @return the errCode
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * @param errCode
	 *            the errCode to set
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * @return the errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * @param errMsg
	 *            the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * @return the mchNo
	 */
	public String getMchNo() {
		return mchNo;
	}

	/**
	 * @param mchNo
	 *            the mchNo to set
	 */
	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}

	/**
	 * @return the mchName
	 */
	public String getMchName() {
		return mchName;
	}

	/**
	 * @param mchName
	 *            the mchName to set
	 */
	public void setMchName(String mchName) {
		this.mchName = mchName;
	}

	/**
	 * @return the tradeNo
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * @param tradeNo
	 *            the tradeNo to set
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/**
	 * @return the accIdCard
	 */
	public String getAccIdCard() {
		return accIdCard;
	}

	/**
	 * @param accIdCard
	 *            the accIdCard to set
	 */
	public void setAccIdCard(String accIdCard) {
		this.accIdCard = accIdCard;
	}

	/**
	 * @return the accCardNo
	 */
	public String getAccCardNo() {
		return accCardNo;
	}

	/**
	 * @param accCardNo
	 *            the accCardNo to set
	 */
	public void setAccCardNo(String accCardNo) {
		this.accCardNo = accCardNo;
	}

	/**
	 * @return the accBankName
	 */
	public String getAccBankName() {
		return accBankName;
	}

	/**
	 * @param accBankName
	 *            the accBankName to set
	 */
	public void setAccBankName(String accBankName) {
		this.accBankName = accBankName;
	}

	/**
	 * @return the payCardNo
	 */
	public String getPayCardNo() {
		return payCardNo;
	}

	/**
	 * @param payCardNo
	 *            the payCardNo to set
	 */
	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	/**
	 * @return the payBankName
	 */
	public String getPayBankName() {
		return payBankName;
	}

	/**
	 * @param payBankName
	 *            the payBankName to set
	 */
	public void setPayBankName(String payBankName) {
		this.payBankName = payBankName;
	}
}
