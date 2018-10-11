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
import com.woodare.template.jpa.model.data.EnumDspMode;

/**
 * ClassName: DownMerchantProduct
 * 唯一索引： mchNo + productType
 * 
 * @description
 * @author Luke
 * @Date Mar 7, 2018
 */
@Entity
@EntityDescriptor(name = "下游机构产品信息", category = "content")
public class DownMerchantProduct extends AbstractBusiModel {
	private static final long serialVersionUID = 3041502520100457874L;

	/** 机构编号 */
	@Column(length = 20)
	private String mchNo;

	/** 姓名 */
	@Column(length = 100)
	private String mchName;

	/** 产品类型 */
	@Enumerated(EnumType.STRING)
	private EnumDspMode dspMode;

	/** T1账户金额, 单位：分 */
	private Long t1BalanceAmt;

	/** T0账户金额 , 单位：分 */
	private Long t0BalanceAmt;

	/** 交易费率，单位：千分之 */
	@Column(scale = 2, precision = 5)
	private BigDecimal feeRatio;

	/** 单笔交易加收费，单位：元 */
	@Column(scale = 2, precision = 5)
	private BigDecimal addFeeAmt;

	/** 代付费率，单位：千分之 */
	@Column(scale = 3, precision = 6)
	private BigDecimal drawFeeRatio;

	/** 单笔代付加收费，单位：元 */
	@Column(scale = 2, precision = 5)
	private BigDecimal addDrawFeeAmt;

	/** 单笔最小金额, 单位：元 */
	@Column(scale = 2, precision = 6)
	private BigDecimal minPerAmt;

	/** 单笔最大金额, 单位：元 */
	@Column(scale = 2, precision = 6)
	private BigDecimal maxPerAmt;

	/** 日交易限额, 单位：元 */
	@Column(scale = 2, precision = 11)
	private BigDecimal maxTotalAmt;

	/** 状态 */
	@Enumerated(EnumType.STRING)
	private EnumDownUserStatus status;

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
	 * @return the t1BalanceAmt
	 */
	public Long getT1BalanceAmt() {
		return t1BalanceAmt;
	}

	/**
	 * @param t1BalanceAmt
	 *            the t1BalanceAmt to set
	 */
	public void setT1BalanceAmt(Long t1BalanceAmt) {
		this.t1BalanceAmt = t1BalanceAmt;
	}

	/**
	 * @return the t0BalanceAmt
	 */
	public Long getT0BalanceAmt() {
		return t0BalanceAmt;
	}

	/**
	 * @param t0BalanceAmt
	 *            the t0BalanceAmt to set
	 */
	public void setT0BalanceAmt(Long t0BalanceAmt) {
		this.t0BalanceAmt = t0BalanceAmt;
	}

	/**
	 * @return the feeRatio
	 */
	public BigDecimal getFeeRatio() {
		return feeRatio;
	}

	/**
	 * @param feeRatio
	 *            the feeRatio to set
	 */
	public void setFeeRatio(BigDecimal feeRatio) {
		this.feeRatio = feeRatio;
	}

	/**
	 * @return the addFeeAmt
	 */
	public BigDecimal getAddFeeAmt() {
		return addFeeAmt;
	}

	/**
	 * @param addFeeAmt
	 *            the addFeeAmt to set
	 */
	public void setAddFeeAmt(BigDecimal addFeeAmt) {
		this.addFeeAmt = addFeeAmt;
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
	 * @return the minPerAmt
	 */
	public BigDecimal getMinPerAmt() {
		return minPerAmt;
	}

	/**
	 * @param minPerAmt
	 *            the minPerAmt to set
	 */
	public void setMinPerAmt(BigDecimal minPerAmt) {
		this.minPerAmt = minPerAmt;
	}

	/**
	 * @return the maxPerAmt
	 */
	public BigDecimal getMaxPerAmt() {
		return maxPerAmt;
	}

	/**
	 * @param maxPerAmt
	 *            the maxPerAmt to set
	 */
	public void setMaxPerAmt(BigDecimal maxPerAmt) {
		this.maxPerAmt = maxPerAmt;
	}

	/**
	 * @return the maxTotalAmt
	 */
	public BigDecimal getMaxTotalAmt() {
		return maxTotalAmt;
	}

	/**
	 * @param maxTotalAmt
	 *            the maxTotalAmt to set
	 */
	public void setMaxTotalAmt(BigDecimal maxTotalAmt) {
		this.maxTotalAmt = maxTotalAmt;
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

}
