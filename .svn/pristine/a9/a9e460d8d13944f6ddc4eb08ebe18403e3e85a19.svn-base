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
package com.woodare.framework.exception;

import com.woodare.template.constant.EnumError;

/**
 * ClassName: MessageWooException
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 */
public class MessageWooException extends AbstractWooException {

	private Integer code;
	private String detailMessage;
	private Object obj;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6498674930426414653L;

	/**
	 * @param message
	 * @param cause
	 */
	public MessageWooException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public MessageWooException(EnumError err) {
		super(err.getError());
		this.code = err.getCode();
	}

	/**
	 * @param message
	 */
	public MessageWooException(String message, EnumError code) {
		super(message);
		this.code = code.getCode();
	}

	/**
	 * @param message
	 */
	public MessageWooException(String message, EnumError code, String detailMessage) {
		super(message);
		this.code = code.getCode();
		this.detailMessage = detailMessage;
	}

	/**
	 * @param message
	 */
	public MessageWooException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MessageWooException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	public MessageWooException() {
	}

	/**
	 * @return the detailMessage
	 */
	public String getDetailMessage() {
		return detailMessage;
	}

	public Integer getCode() {
		return code;
	}

	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * @param obj
	 *            the obj to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}

}
