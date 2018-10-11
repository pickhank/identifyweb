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

import com.woodare.framework.data.EResponseState;
import com.woodare.framework.data.impl.AbstractData;

/**
 * @author lu_feng
 */
public class AppResponseData extends AbstractData {
	private static final long serialVersionUID = -1603230307978873069L;

	private String payload;

	private EResponseState state;

	private String message;

	private String sign;

	private String userName;

	private String respTime;

	private Integer code;

	/**
	 * @return the payload
	 */
	public String getPayload() {
		return payload;
	}

	/**
	 * @param payload
	 *            the payload to set
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}

	/**
	 * @return the state
	 */
	public EResponseState getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(EResponseState state) {
		this.state = state;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign
	 *            the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
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

	/**
	 * @return the respTime
	 */
	public String getRespTime() {
		return respTime;
	}

	/**
	 * @param respTime
	 *            the respTime to set
	 */
	public void setRespTime(String respTime) {
		this.respTime = respTime;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

}