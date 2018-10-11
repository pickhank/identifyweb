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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.annotation.EntityDescriptor;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumMercCategory;

/**
 * ClassName: DownMerchant
 * 
 * @description
 * @author Luke
 * @Date Feb 26, 2018
 */
@Entity
@EntityDescriptor(name = "下游机构", category = "content")
public class DownMerchant extends AbstractBusiModel {
	private static final long serialVersionUID = -5303765049019581524L;

	/** 类别 */
	@Enumerated(EnumType.STRING)
	private EnumMercCategory mercCategory;

	/** 机构编号 */
	@Column(length = 20, unique = true)
	private String mchNo;

	/** 姓名 */
	@Column(length = 100)
	private String name;

	/** 电话 */
	@Column(length = 20)
	private String phone;

	/** 结算卡卡号 */
	@Column(length = 30)
	private String accCardNo;

	/** 结算卡户名 */
	@Column(length = 60)
	private String accCardHolder;

	/** 结算银行名称 */
	@Column(length = 50)
	private String accBankName;

	/** 代理商ID */
	@Column(length = 64)
	private String agentId;

	/** 加密Key */
	@Column(length = 64)
	private String encKey;

	/** 签名Key */
	@Column(length = 64)
	private String signKey;

	/** 代付KEY */
	@Column(length = 64)
	private String payKey;

	/** 信任IP */
	@Column(length = 256)
	private String limitIps;

	/** 默认代付费率，单位：千分之 */
	@Column(scale = 3, precision = 6)
	private BigDecimal drawFeeRatio;

	/** 默认单笔代付加收费，单位：元 */
	@Column(scale = 2, precision = 5)
	private BigDecimal addDrawFeeAmt;

	/** 通知消息 */
	private Boolean enableNotify;

	/** 开启资金明细 */
	private Boolean enableBalChgFlg;
	//
	// /** 产品类型，00网银、01银联在线 */
	// @Column(length = 50)
	// private String supportPayType;
	//
	// /** 默认产品类型，00网银、01银联在线 */
	// @Column(length = 2)
	// private String payType;

	/** 状态 */
	@Enumerated(EnumType.STRING)
	private EnumDownUserStatus status;

	/** 鉴权余额预授权模式 */
	private Boolean preAuthDspFlg;

	/** T0授信资金比，单位：百分之 */
	private Long creditRatio;

	/** 下一笔结算日期(由系统自动维护) */
	private String settleDate;;

	/**
	 * @return the preAuthDspFlg
	 */
	public Boolean getPreAuthDspFlg() {
		return preAuthDspFlg;
	}

	/**
	 * @param preAuthDspFlg
	 *            the preAuthDspFlg to set
	 */
	public void setPreAuthDspFlg(Boolean preAuthDspFlg) {
		this.preAuthDspFlg = preAuthDspFlg;
	}

	/**
	 * @return the enableBalChgFlg
	 */
	public Boolean getEnableBalChgFlg() {
		return enableBalChgFlg;
	}

	/**
	 * @param enableBalChgFlg
	 *            the enableBalChgFlg to set
	 */
	public void setEnableBalChgFlg(Boolean enableBalChgFlg) {
		this.enableBalChgFlg = enableBalChgFlg;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the accCardHolder
	 */
	public String getAccCardHolder() {
		return accCardHolder;
	}

	/**
	 * @param accCardHolder
	 *            the accCardHolder to set
	 */
	public void setAccCardHolder(String accCardHolder) {
		this.accCardHolder = accCardHolder;
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
	 * @return the agentId
	 */
	public String getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId
	 *            the agentId to set
	 */
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	/**
	 * @return the encKey
	 */
	public String getEncKey() {
		return encKey;
	}

	/**
	 * @param encKey
	 *            the encKey to set
	 */
	public void setEncKey(String encKey) {
		this.encKey = encKey;
	}

	/**
	 * @return the signKey
	 */
	public String getSignKey() {
		return signKey;
	}

	/**
	 * @param signKey
	 *            the signKey to set
	 */
	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	/**
	 * @return the payKey
	 */
	public String getPayKey() {
		return payKey;
	}

	/**
	 * @param payKey
	 *            the payKey to set
	 */
	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}

	/**
	 * @return the limitIps
	 */
	public String getLimitIps() {
		return limitIps;
	}

	/**
	 * @param limitIps
	 *            the limitIps to set
	 */
	public void setLimitIps(String limitIps) {
		this.limitIps = limitIps;
	}

	/**
	 * @return the drawFeeRatio
	 */
	public BigDecimal getDrawFeeRatio() {
		return drawFeeRatio;
	}

	/**
	 * @param drawFeeRatio
	 *            the drawFeeRatio to set
	 */
	public void setDrawFeeRatio(BigDecimal drawFeeRatio) {
		this.drawFeeRatio = drawFeeRatio;
	}

	/**
	 * @return the addDrawFeeAmt
	 */
	public BigDecimal getAddDrawFeeAmt() {
		return addDrawFeeAmt;
	}

	/**
	 * @param addDrawFeeAmt
	 *            the addDrawFeeAmt to set
	 */
	public void setAddDrawFeeAmt(BigDecimal addDrawFeeAmt) {
		this.addDrawFeeAmt = addDrawFeeAmt;
	}

	/**
	 * @return the enableNotify
	 */
	public Boolean getEnableNotify() {
		return enableNotify;
	}

	/**
	 * @param enableNotify
	 *            the enableNotify to set
	 */
	public void setEnableNotify(Boolean enableNotify) {
		this.enableNotify = enableNotify;
	}

	/**
	 * @return the status
	 */
	public EnumDownUserStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(EnumDownUserStatus status) {
		this.status = status;
	}

	/**
	 * @return the creditRatio
	 */
	public Long getCreditRatio() {
		return creditRatio;
	}

	/**
	 * @param creditRatio
	 *            the creditRatio to set
	 */
	public void setCreditRatio(Long creditRatio) {
		this.creditRatio = creditRatio;
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

}
