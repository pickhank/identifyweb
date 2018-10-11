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
import com.woodare.template.jpa.model.data.EnumFundAccountType;

/**
 * ClassName: DownMerchantFundAccount
 * 唯一索引：mchNo + settleDate
 * 索引：settleDate
 * 
 * @description
 * @author Luke
 * @Date Mar 5, 2018
 */
@Entity
@EntityDescriptor(name = "下游机构账户", category = "content")
public class DownMerchantFundAccount extends AbstractBusiModel {
	private static final long serialVersionUID = -7312844222902605851L;

	/** 机构编号 */
	@Column(length = 20)
	private String mchNo;

	/** 姓名 */
	@Column(length = 100)
	private String mchName;

	/** 账户类别 */
	@Enumerated(EnumType.STRING)
	private EnumFundAccountType accountType;

	/** 结算记账批次号 */
	private String settleBalanceNo;

	/** 结算日期（标识本次） */
	private String settleDate;

	/** 资金变动状态 */
	private boolean changedFlag;

	/** 期初余额, 单位：分 */
	private Long lastSettleAmt;

	/** T1账户余额, 单位：分 */
	private Long settleInAmt;

	/** 已清算金额, 单位：分 */
	private Long settleOutAmt;

	/** 当日入账额 , 单位：分 */
	private Long curInAmt;

	/** T0提现金额, 单位：分 */
	private Long curOutAmt;

	/** 冻结金额, 单位：分 */
	private Long frozenAmt;

	/** 上日验证账户余额, 单位：分 */
	private Long lastDspBalAmt;

	/** 当前验证账户余额, 单位：分 */
	private Long curDspBalAmt;

	/**
	 * @return the lastDspBalAmt
	 */
	public Long getLastDspBalAmt() {
		return lastDspBalAmt;
	}

	/**
	 * @param lastDspBalAmt
	 *            the lastDspBalAmt to set
	 */
	public void setLastDspBalAmt(Long lastDspBalAmt) {
		this.lastDspBalAmt = lastDspBalAmt;
	}

	/**
	 * @return the curDspBalAmt
	 */
	public Long getCurDspBalAmt() {
		return curDspBalAmt;
	}

	/**
	 * @param curDspBalAmt
	 *            the curDspBalAmt to set
	 */
	public void setCurDspBalAmt(Long curDspBalAmt) {
		this.curDspBalAmt = curDspBalAmt;
	}

	/**
	 * @return the frozenAmt
	 */
	public Long getFrozenAmt() {
		return frozenAmt;
	}

	/**
	 * @param frozenAmt
	 *            the frozenAmt to set
	 */
	public void setFrozenAmt(Long frozenAmt) {
		this.frozenAmt = frozenAmt;
	}

	/**
	 * @return the changedFlag
	 */
	public boolean isChangedFlag() {
		return changedFlag;
	}

	/**
	 * @param changedFlag
	 *            the changedFlag to set
	 */
	public void setChangedFlag(boolean changedFlag) {
		this.changedFlag = changedFlag;
	}

	/**
	 * @return the lastSettleAmt
	 */
	public Long getLastSettleAmt() {
		return lastSettleAmt;
	}

	/**
	 * @param lastSettleAmt
	 *            the lastSettleAmt to set
	 */
	public void setLastSettleAmt(Long lastSettleAmt) {
		this.lastSettleAmt = lastSettleAmt;
	}

	/**
	 * @return the accountType
	 */
	public EnumFundAccountType getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(EnumFundAccountType accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the settleBalanceNo
	 */
	public String getSettleBalanceNo() {
		return settleBalanceNo;
	}

	/**
	 * @param settleBalanceNo
	 *            the settleBalanceNo to set
	 */
	public void setSettleBalanceNo(String settleBalanceNo) {
		this.settleBalanceNo = settleBalanceNo;
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

	/**
	 * @return the settleInAmt
	 */
	public Long getSettleInAmt() {
		return settleInAmt;
	}

	/**
	 * @param settleInAmt
	 *            the settleInAmt to set
	 */
	public void setSettleInAmt(Long settleInAmt) {
		this.settleInAmt = settleInAmt;
	}

	/**
	 * @return the settleOutAmt
	 */
	public Long getSettleOutAmt() {
		return settleOutAmt;
	}

	/**
	 * @param settleOutAmt
	 *            the settleOutAmt to set
	 */
	public void setSettleOutAmt(Long settleOutAmt) {
		this.settleOutAmt = settleOutAmt;
	}

	/**
	 * @return the curInAmt
	 */
	public Long getCurInAmt() {
		return curInAmt;
	}

	/**
	 * @param curInAmt
	 *            the curInAmt to set
	 */
	public void setCurInAmt(Long curInAmt) {
		this.curInAmt = curInAmt;
	}

	/**
	 * @return the curOutAmt
	 */
	public Long getCurOutAmt() {
		return curOutAmt;
	}

	/**
	 * @param curOutAmt
	 *            the curOutAmt to set
	 */
	public void setCurOutAmt(Long curOutAmt) {
		this.curOutAmt = curOutAmt;
	}
}
