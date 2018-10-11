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
package com.woodare.core.data;

import com.woodare.framework.data.impl.AbstractData;

/**
 * ClassName: DownRequestData
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Nov 11, 2016
 * 
 */
public class DownRequestData extends AbstractData {
	private static final long serialVersionUID = 4150629323608181249L;

	private String mchNo;

	private String payload;

	private String sign;

	public String getMchNo() {
		return mchNo;
	}

	public void setMchNo(String mchNo) {
		this.mchNo = mchNo;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
