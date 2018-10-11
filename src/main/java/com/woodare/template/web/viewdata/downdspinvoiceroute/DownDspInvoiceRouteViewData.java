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
package com.woodare.template.web.viewdata.downdspinvoiceroute;

import com.woodare.template.jpa.persistence.data.downdspinvoiceroute.DownDspInvoiceRouteData;

/**
 * ClassName: DownDspInvoiceRouteViewData
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/09/15
 */
public class DownDspInvoiceRouteViewData extends DownDspInvoiceRouteData {
	private static final long serialVersionUID = 1156051373825532962L;

	private String mchName;

	/* 支持的验证类别 */
	private String supportDspModeNames;

	/**
	 * @return the supportDspModeNames
	 */
	public String getSupportDspModeNames() {
		return supportDspModeNames;
	}

	/**
	 * @param supportDspModeNames
	 *            the supportDspModeNames to set
	 */
	public void setSupportDspModeNames(String supportDspModeNames) {
		this.supportDspModeNames = supportDspModeNames;
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

}
