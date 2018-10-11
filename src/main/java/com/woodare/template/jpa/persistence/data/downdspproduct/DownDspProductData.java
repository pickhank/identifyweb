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
package com.woodare.template.jpa.persistence.data.downdspproduct;

import java.util.Date;
import com.woodare.framework.data.impl.AbstractData;

import java.math.BigDecimal;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumDspMode;

/**
 * 
 * ClassName: DownDspProductData
 * 
 * @description
 * @author woo_maven_auto_generate
 * 
 */
public class DownDspProductData extends AbstractData {
	private static final long serialVersionUID = 3041502520100457874L;

	private String id;

	private Date createDate;

	private String mchNo;

	private String mchName;

	private EnumDspMode mode;

	private BigDecimal addFeeAmt;

	private EnumDownUserStatus status;

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

	public EnumDspMode getMode() {
		return mode;
	}

	public void setMode(EnumDspMode mode) {
		this.mode = mode;
	}

	public BigDecimal getAddFeeAmt() {
		return addFeeAmt;
	}

	public void setAddFeeAmt(BigDecimal addFeeAmt) {
		this.addFeeAmt = addFeeAmt;
	}

	public EnumDownUserStatus getStatus() {
		return status;
	}

	public void setStatus(EnumDownUserStatus status) {
		this.status = status;
	}

}
