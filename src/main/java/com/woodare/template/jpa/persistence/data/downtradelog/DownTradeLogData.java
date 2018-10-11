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
package com.woodare.template.jpa.persistence.data.downtradelog;

import java.util.Date;
import com.woodare.framework.data.impl.AbstractData;

import java.math.BigDecimal;

/**
 * 
 * ClassName: DownTradeLogData
 * 
 * @description
 * @author woo_maven_auto_generate
 * 
 */
public class DownTradeLogData extends AbstractData {
	private static final long serialVersionUID = -2526533917106160144L;

	private String id;

	private Date createDate;

	private String recordDt;

	private String receiveDtime;

	private String replyDtime;

	private String errCode;

	private String errMsg;

	private String mchNo;

	private String mchName;

	private String tradeNo;

	private String accIdCard;

	private String accCardNo;

	private String accBankName;

	private String payCardNo;

	private String payBankName;

	private String channel;

	private String extra1;

	private String extra2;

	private BigDecimal price;

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

	public String getRecordDt() {
		return recordDt;
	}

	public void setRecordDt(String recordDt) {
		this.recordDt = recordDt;
	}

	public String getReceiveDtime() {
		return receiveDtime;
	}

	public void setReceiveDtime(String receiveDtime) {
		this.receiveDtime = receiveDtime;
	}

	public String getReplyDtime() {
		return replyDtime;
	}

	public void setReplyDtime(String replyDtime) {
		this.replyDtime = replyDtime;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getMchNo() {
		return mchNo;
	}

	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}

	public String getMchName() {
		return mchName;
	}

	public void setMchName(String mchName) {
		this.mchName = mchName;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getAccIdCard() {
		return accIdCard;
	}

	public void setAccIdCard(String accIdCard) {
		this.accIdCard = accIdCard;
	}

	public String getAccCardNo() {
		return accCardNo;
	}

	public void setAccCardNo(String accCardNo) {
		this.accCardNo = accCardNo;
	}

	public String getAccBankName() {
		return accBankName;
	}

	public void setAccBankName(String accBankName) {
		this.accBankName = accBankName;
	}

	public String getPayCardNo() {
		return payCardNo;
	}

	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	public String getPayBankName() {
		return payBankName;
	}

	public void setPayBankName(String payBankName) {
		this.payBankName = payBankName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getExtra1() {
		return extra1;
	}

	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	public String getExtra2() {
		return extra2;
	}

	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
