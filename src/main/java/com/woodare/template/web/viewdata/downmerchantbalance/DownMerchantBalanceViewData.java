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
package com.woodare.template.web.viewdata.downmerchantbalance;

import java.math.BigDecimal;

import com.woodare.template.jpa.persistence.data.downmerchantbalance.DownMerchantBalanceData;

/**
 * ClassName: DownMerchantBalanceViewData
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2018/03/03
 */
public class DownMerchantBalanceViewData extends DownMerchantBalanceData {
	private static final long serialVersionUID = 4644858587558158916L;

	private String accTypeFormat;
	private BigDecimal lastBalAmt;

	/**
	 * @return the lastBalAmt
	 */
	public BigDecimal getLastBalAmt() {
		return lastBalAmt;
	}

	/**
	 * @param lastBalAmt
	 *            the lastBalAmt to set
	 */
	public void setLastBalAmt(BigDecimal lastBalAmt) {
		this.lastBalAmt = lastBalAmt;
	}

	/**
	 * @return the accTypeFormat
	 */
	public String getAccTypeFormat() {
		return accTypeFormat;
	}

	/**
	 * @param accTypeFormat
	 *            the accTypeFormat to set
	 */
	public void setAccTypeFormat(String accTypeFormat) {
		this.accTypeFormat = accTypeFormat;
	}

}
