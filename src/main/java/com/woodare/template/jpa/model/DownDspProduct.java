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
 * ClassName: DownDspProduct
 * 唯一索引： mchNo + productType
 * 
 * @description
 * @author Luke
 * @Date Mar 7, 2018
 */
@Entity
@EntityDescriptor(name = "鉴权产品类型", category = "content")
public class DownDspProduct extends AbstractBusiModel {
	private static final long serialVersionUID = 3041502520100457874L;

	/** 机构编号 */
	@Column(length = 20)
	private String mchNo;

	/** 机构名称 */
	@Column(length = 100)
	private String mchName;

	/** 产品类型 */
	@Enumerated(EnumType.STRING)
	private EnumDspMode mode;

	/** 单笔交易费，单位：元 */
	@Column(scale = 2, precision = 5)
	private BigDecimal addFeeAmt;

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
	 * @return the mode
	 */
	public EnumDspMode getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(EnumDspMode mode) {
		this.mode = mode;
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
