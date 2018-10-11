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
package com.woodare.template.web.viewdata.chargedetail;

import java.math.BigDecimal;

import com.woodare.template.jpa.persistence.data.chargedetail.ChargeDetailData;

/**
 * ClassName: ChargeDetailViewData
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/09/29
 */
public class ChargeDetailViewData extends ChargeDetailData {
	private static final long serialVersionUID = -920203691251156286L;

	// View - Only Display view
	private String mode;

	private BigDecimal rearMoneyYuan;

	/**
	 * @return the rearMoneyYuan
	 */
	public BigDecimal getRearMoneyYuan() {
		return rearMoneyYuan;
	}

	/**
	 * @param rearMoneyYuan
	 *            the rearMoneyYuan to set
	 */
	public void setRearMoneyYuan(BigDecimal rearMoneyYuan) {
		this.rearMoneyYuan = rearMoneyYuan;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

}
