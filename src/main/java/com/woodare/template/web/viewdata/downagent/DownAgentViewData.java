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
package com.woodare.template.web.viewdata.downagent;

import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;

/**
 * ClassName: DownAgentViewData
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2016/11/11
 */
public class DownAgentViewData extends DownAgentData {
	private static final long serialVersionUID = -1838815663502921782L;

	private String productTypeSummary;

	/** 生效产品类别 */
	private String[] selProductTypeArr;

	/** 产品类别 */
	private String[] productTypeArr;

	/** 交易费率，单位：千分之 */
	private String[] feeRatioArr;

	/** 单笔交易加收费，单位：元 */
	private String[] addFeeAmtArr;

	/** 代付费率，单位：千分之 */
	private String[] drawFeeRatioArr;

	/** 单笔代付加收费，单位：元 */
	private String[] addDrawFeeAmtArr;

	/**
	 * @return the productTypeSummary
	 */
	public String getProductTypeSummary() {
		return productTypeSummary;
	}

	/**
	 * @param productTypeSummary
	 *            the productTypeSummary to set
	 */
	public void setProductTypeSummary(String productTypeSummary) {
		this.productTypeSummary = productTypeSummary;
	}

	/**
	 * @return the selProductTypeArr
	 */
	public String[] getSelProductTypeArr() {
		return selProductTypeArr;
	}

	/**
	 * @param selProductTypeArr
	 *            the selProductTypeArr to set
	 */
	public void setSelProductTypeArr(String[] selProductTypeArr) {
		this.selProductTypeArr = selProductTypeArr;
	}

	/**
	 * @return the productTypeArr
	 */
	public String[] getProductTypeArr() {
		return productTypeArr;
	}

	/**
	 * @param productTypeArr
	 *            the productTypeArr to set
	 */
	public void setProductTypeArr(String[] productTypeArr) {
		this.productTypeArr = productTypeArr;
	}

	/**
	 * @return the feeRatioArr
	 */
	public String[] getFeeRatioArr() {
		return feeRatioArr;
	}

	/**
	 * @param feeRatioArr
	 *            the feeRatioArr to set
	 */
	public void setFeeRatioArr(String[] feeRatioArr) {
		this.feeRatioArr = feeRatioArr;
	}

	/**
	 * @return the addFeeAmtArr
	 */
	public String[] getAddFeeAmtArr() {
		return addFeeAmtArr;
	}

	/**
	 * @param addFeeAmtArr
	 *            the addFeeAmtArr to set
	 */
	public void setAddFeeAmtArr(String[] addFeeAmtArr) {
		this.addFeeAmtArr = addFeeAmtArr;
	}

	/**
	 * @return the drawFeeRatioArr
	 */
	public String[] getDrawFeeRatioArr() {
		return drawFeeRatioArr;
	}

	/**
	 * @param drawFeeRatioArr
	 *            the drawFeeRatioArr to set
	 */
	public void setDrawFeeRatioArr(String[] drawFeeRatioArr) {
		this.drawFeeRatioArr = drawFeeRatioArr;
	}

	/**
	 * @return the addDrawFeeAmtArr
	 */
	public String[] getAddDrawFeeAmtArr() {
		return addDrawFeeAmtArr;
	}

	/**
	 * @param addDrawFeeAmtArr
	 *            the addDrawFeeAmtArr to set
	 */
	public void setAddDrawFeeAmtArr(String[] addDrawFeeAmtArr) {
		this.addDrawFeeAmtArr = addDrawFeeAmtArr;
	}

}
