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
 */
public class AppRequestData extends AbstractData {
	private static final long serialVersionUID = 4150629323608181249L;

	private String userName;

	private String reqTime;

	private String payload;

	private String sign;

	
	/**
	 * @return the reqTime
	 */
	public String getReqTime() {
		return reqTime;
	}

	/**
	 * @param reqTime the reqTime to set
	 */
	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
