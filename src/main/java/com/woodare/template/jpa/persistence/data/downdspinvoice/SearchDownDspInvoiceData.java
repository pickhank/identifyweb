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
package com.woodare.template.jpa.persistence.data.downdspinvoice;

import java.util.Date;
import java.util.List;

import com.woodare.framework.data.impl.AbstractPageData;
import com.woodare.template.jpa.model.data.EnumDspChannel;
import com.woodare.template.jpa.model.data.EnumDspMode;

/**
 * ClassName: DownDspInvoiceData
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/09/13
 */
public class SearchDownDspInvoiceData extends AbstractPageData {
	private static final long serialVersionUID = 4644858587558158916L;

	private String id;

	private List<String> ids;

	private String keywords;

	private String startTime;

	private String endTime;

	private Date startDate;

	private Date endDate;

	private String mchNo;

	private String dateStr;

	private String transNo;

	private String tradeNo;

	private String status;
	private String statusDesc;

	private String orderDate;

	private EnumDspMode dspMode;
	private EnumDspChannel channel;

	private Date startDateSuccess;
	private Date endDateSuccess;

	private String cardNo;

	private Boolean withDspModeFlg;

	/**
	 * @return the withDspModeFlg
	 */
	public Boolean getWithDspModeFlg() {
		return withDspModeFlg;
	}

	/**
	 * @param withDspModeFlg
	 *            the withDspModeFlg to set
	 */
	public void setWithDspModeFlg(Boolean withDspModeFlg) {
		this.withDspModeFlg = withDspModeFlg;
	}

	/**
	 * @return the dspMode
	 */
	public EnumDspMode getDspMode() {
		return dspMode;
	}

	/**
	 * @param dspMode
	 *            the dspMode to set
	 */
	public void setDspMode(EnumDspMode dspMode) {
		this.dspMode = dspMode;
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

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @return the dateStr
	 */
	public String getDateStr() {
		return dateStr;
	}

	/**
	 * @param dateStr
	 *            the dateStr to set
	 */
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the statusDesc
	 */
	public String getStatusDesc() {
		return statusDesc;
	}

	/**
	 * @param statusDesc
	 *            the statusDesc to set
	 */
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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
	 * @return the startDateSuccess
	 */
	public Date getStartDateSuccess() {
		return startDateSuccess;
	}

	/**
	 * @param startDateSuccess
	 *            the startDateSuccess to set
	 */
	public void setStartDateSuccess(Date startDateSuccess) {
		this.startDateSuccess = startDateSuccess;
	}

	/**
	 * @return the endDateSuccess
	 */
	public Date getEndDateSuccess() {
		return endDateSuccess;
	}

	/**
	 * @param endDateSuccess
	 *            the endDateSuccess to set
	 */
	public void setEndDateSuccess(Date endDateSuccess) {
		this.endDateSuccess = endDateSuccess;
	}

	/**
	 * @return the cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo
	 *            the cardNo to set
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
