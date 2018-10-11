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
package com.woodare.template.jpa.model.data;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: EnumMercCategory
 * 
 * @description
 * @author Luke
 * @Date Dec 30, 2017
 */
public enum EnumMercCategory {
	// 网关&银联在线
	NONE("默认", new EnumProductType[] { EnumProductType.ONLINEBANK_DEBIT, EnumProductType.UNIONPAY_DEBIT }),
	// 网关
	GATEWAY("网关支付", new EnumProductType[] { EnumProductType.ONLINEBANK_DEBIT }),
	// 银联在线
	UNIONPAY("银联在线", new EnumProductType[] { EnumProductType.UNIONPAY_DEBIT }),

	///////////////////////////////////////////////////
	// 银联码
	UNIONQR("银联码", new EnumProductType[] { EnumProductType.UNIONQR_SCAN, EnumProductType.UNIONQR_PAY }),
	// 银联码
	UNIONQRSCAN("银联主扫", new EnumProductType[] { EnumProductType.UNIONQR_SCAN }),
	// 银联码
	UNIONQRPAY("银联付款码", new EnumProductType[] { EnumProductType.UNIONQR_PAY }),
	// 转账码
	DIRECTQR("转账码", new EnumProductType[] { EnumProductType.QRCODE_DEBIT }),
	// QQ钱包
	QQQRSCAN("QQ扫码", new EnumProductType[] { EnumProductType.QQQR_PAY }),
	// 支付宝扫码
	ALIQRSCAN("支付宝扫码", new EnumProductType[] { EnumProductType.ALIQR_PAY }),
	// 支付宝H5
	ALIQRH5("支付宝H5", new EnumProductType[] { EnumProductType.ALIH5_PAY }),
	// 微信H5
	WXQRH5("微信H5", new EnumProductType[] { EnumProductType.WXH5_PAY }),

	///////////////////////////////////////////////////
	// 同名进出
	SAMEPAY("同名进出", new EnumProductType[] { EnumProductType.QUICKPAY_CREDIT_SCORE }),

	;

	EnumMercCategory(String name, EnumProductType[] supportProducts) {
		this.name = name;
		this.supportProducts = supportProducts;
	}

	/**
	 * @param productType
	 * @return
	 */
	public boolean contains(EnumProductType productType) {
		boolean ret = false;
		if (productType != null) {
			for (EnumProductType tmp : this.getSupportProducts()) {
				if (tmp.equals(productType)) {
					ret = true;
					break;
				}
			}
		}
		return ret;
	}
	
	public static final List<EnumMercCategory> allItems = new ArrayList<EnumMercCategory>();
	static {
		// Initialize map from constant name to enum constant
		for (EnumMercCategory blah : values()) {
			allItems.add(blah);
		}
	}

	private String name;

	private EnumProductType[] supportProducts;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the name
	 */
	public String getCode() {
		return this.toString();
	}

	/**
	 * @return the supportProducts
	 */
	public EnumProductType[] getSupportProducts() {
		return supportProducts;
	}

}
