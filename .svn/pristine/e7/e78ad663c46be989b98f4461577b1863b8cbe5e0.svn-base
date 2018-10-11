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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumDspChannel;
import com.woodare.template.jpa.model.data.EnumMercCategory;

/**
 * ClassName: DownDspInvoiceRoute
 * 
 * @description
 * @author Luke
 * @Date Sep 15, 2018
 */
@Entity
@EntityDescriptor(name = "验签交易路由", category = "content")
public class DownDspInvoiceRoute extends AbstractBusiModel {
	private static final long serialVersionUID = 1156051373825532962L;

	/* 商户No. */
	@Column(length = 32)
	private String mchNo;

	/* 上游通道 */
	@Enumerated(EnumType.STRING)
	private EnumDspChannel channel;

	/* 通道商户编码 */
	@Column(length = 512)
	private String channelMercNo;

	/* 通道商户名称 */
	@Column(length = 512)
	private String channelMercName;

	/* 支持的验证类别 */
	@Column(length = 128)
	private String supportDspModes;

	/* 商户类别 */
	@Enumerated(EnumType.STRING)
	private EnumMercCategory mercCategory;

	/* 优先级 */
	private Integer priority;

	/* 开始日期 */
	@Column(length = 8)
	private String startDate;

	/* 结束日期 */
	@Column(length = 8)
	private String endDate;

	/* 开始时间 */
	@Column(length = 6)
	private String startTime;

	/* 开始时间 */
	@Column(length = 6)
	private String endTime;

	/**
	 * @return the supportDspModes
	 */
	public String getSupportDspModes() {
		return supportDspModes;
	}

	/**
	 * @param supportDspModes
	 *            the supportDspModes to set
	 */
	public void setSupportDspModes(String supportDspModes) {
		this.supportDspModes = supportDspModes;
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
	 * @return the channel
	 */
	public EnumDspChannel getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(EnumDspChannel channel) {
		this.channel = channel;
	}

	/**
	 * @return the channelMercNo
	 */
	public String getChannelMercNo() {
		return channelMercNo;
	}

	/**
	 * @param channelMercNo
	 *            the channelMercNo to set
	 */
	public void setChannelMercNo(String channelMercNo) {
		this.channelMercNo = channelMercNo;
	}

	/**
	 * @return the channelMercName
	 */
	public String getChannelMercName() {
		return channelMercName;
	}

	/**
	 * @param channelMercName
	 *            the channelMercName to set
	 */
	public void setChannelMercName(String channelMercName) {
		this.channelMercName = channelMercName;
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
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
