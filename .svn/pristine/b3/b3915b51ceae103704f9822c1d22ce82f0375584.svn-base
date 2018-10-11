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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumNotifyRecordType;

/**
 * ClassName: NotifyRecord
 * 
 * @description
 * @author Luke
 * @Date 2018/02/28
 */
@Entity
@EntityDescriptor(name = "机构通知交易", category = "content")
public class NotifyRecord extends AbstractBusiModel {
	private static final long serialVersionUID = -8467342665182781094L;

	/** 异步通知类型 */
	private EnumNotifyRecordType notifyType;

	/** 机构交易主键 */
	@Column(length = 64)
	private String tradeNo;

	/** 内部流水号 */
	@Column(length = 64)
	private String transNo;

	/** 通知地址 */
	@Column(length = 512)
	private String notifyUrl;

	/** 异步通知是否成功 */
	private Boolean notifySuccess;

	/** 异步通知次数 */
	private Integer notifyTimes = 0;

	/** 末次异步通知时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date notifyLastDate;

	/** 下次异步通知时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date notifyNextDate;

	/**
	 * @return the notifyType
	 */
	public EnumNotifyRecordType getNotifyType() {
		return notifyType;
	}

	/**
	 * @param notifyType
	 *            the notifyType to set
	 */
	public void setNotifyType(EnumNotifyRecordType notifyType) {
		this.notifyType = notifyType;
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
	 * @return the transNo
	 */
	public String getTransNo() {
		return transNo;
	}

	/**
	 * @param transNo
	 *            the transNo to set
	 */
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	/**
	 * @return the notifyUrl
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}

	/**
	 * @param notifyUrl
	 *            the notifyUrl to set
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	/**
	 * @return the notifySuccess
	 */
	public Boolean getNotifySuccess() {
		return notifySuccess;
	}

	/**
	 * @param notifySuccess
	 *            the notifySuccess to set
	 */
	public void setNotifySuccess(Boolean notifySuccess) {
		this.notifySuccess = notifySuccess;
	}

	/**
	 * @return the notifyTimes
	 */
	public Integer getNotifyTimes() {
		return notifyTimes;
	}

	/**
	 * @param notifyTimes
	 *            the notifyTimes to set
	 */
	public void setNotifyTimes(Integer notifyTimes) {
		this.notifyTimes = notifyTimes;
	}

	/**
	 * @return the notifyLastDate
	 */
	public Date getNotifyLastDate() {
		return notifyLastDate;
	}

	/**
	 * @param notifyLastDate
	 *            the notifyLastDate to set
	 */
	public void setNotifyLastDate(Date notifyLastDate) {
		this.notifyLastDate = notifyLastDate;
	}

	/**
	 * @return the notifyNextDate
	 */
	public Date getNotifyNextDate() {
		return notifyNextDate;
	}

	/**
	 * @param notifyNextDate
	 *            the notifyNextDate to set
	 */
	public void setNotifyNextDate(Date notifyNextDate) {
		this.notifyNextDate = notifyNextDate;
	}
}
