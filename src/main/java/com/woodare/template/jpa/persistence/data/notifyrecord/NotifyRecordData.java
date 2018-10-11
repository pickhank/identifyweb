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

import java.util.Date;
import com.woodare.framework.data.impl.AbstractData;

import com.woodare.template.jpa.model.data.EnumNotifyRecordType;

/**
 * 
 * ClassName: NotifyRecordData
 * 
 * @description
 * @author woo_maven_auto_generate
 * 
 */
public class NotifyRecordData extends AbstractData {
	private static final long serialVersionUID = -8467342665182781094L;

	private String id;

	private Date createDate;

	private EnumNotifyRecordType notifyType;

	private String tradeNo;

	private String transNo;

	private String notifyUrl;

	private Boolean notifySuccess;

	private Integer notifyTimes;

	private Date notifyLastDate;

	private Date notifyNextDate;

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

	public EnumNotifyRecordType getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(EnumNotifyRecordType notifyType) {
		this.notifyType = notifyType;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public Boolean getNotifySuccess() {
		return notifySuccess;
	}

	public void setNotifySuccess(Boolean notifySuccess) {
		this.notifySuccess = notifySuccess;
	}

	public Integer getNotifyTimes() {
		return notifyTimes;
	}

	public void setNotifyTimes(Integer notifyTimes) {
		this.notifyTimes = notifyTimes;
	}

	public Date getNotifyLastDate() {
		return notifyLastDate;
	}

	public void setNotifyLastDate(Date notifyLastDate) {
		this.notifyLastDate = notifyLastDate;
	}

	public Date getNotifyNextDate() {
		return notifyNextDate;
	}

	public void setNotifyNextDate(Date notifyNextDate) {
		this.notifyNextDate = notifyNextDate;
	}

}
