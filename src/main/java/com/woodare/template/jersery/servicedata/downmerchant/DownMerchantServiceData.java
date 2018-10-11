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
package com.woodare.template.jersery.servicedata.downmerchant;

import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;

/**
 * ClassName: DownMerchantServiceData
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2018/03/03
 */
public class DownMerchantServiceData extends DownMerchantData {
	private static final long serialVersionUID = -5303765049019581524L;

	private int versionNo;

	private String mchName;

	/** T1结算余额, 单位：分 */
	private Long settleInAmt;

	/** T1结算余额, 单位：分 */
	private Long settleOutAmt;

	/** 当日入金额 , 单位：分 */
	private Long curInAmt;

	/** 当日授信金额 , 单位：分 */
	private Long creditLines;

	/** T0已用额度, 单位：分 */
	private Long curOutAmt;

	/** 冻结余额, 单位：分 */
	private Long frozenAmt;

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
	 * @return the creditLines
	 */
	public Long getCreditLines() {
		return creditLines;
	}

	/**
	 * @param creditLines
	 *            the creditLines to set
	 */
	public void setCreditLines(Long creditLines) {
		this.creditLines = creditLines;
	}

	/**
	 * @return the versionNo
	 */
	public int getVersionNo() {
		return versionNo;
	}

	/**
	 * @param versionNo
	 *            the versionNo to set
	 */
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
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
