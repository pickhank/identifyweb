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

/**
 * ClassName: MessageCodeWooException
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Jul 25, 2016
 * 
 */
public class MessageCodeWooException extends AbstractWooException {

	private static final long serialVersionUID = 7094241939415266676L;

	private Integer code;

	/**
	 * @param message
	 * @param cause
	 */
	public MessageCodeWooException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public MessageCodeWooException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MessageCodeWooException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	public MessageCodeWooException() {
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MessageCodeWooException(String message, Throwable cause, Integer code) {
		this(message, cause);
		this.code = code;
	}

	/**
	 * @param message
	 */
	public MessageCodeWooException(String message, Integer code) {
		this(message);
		this.code = code;
	}

	/**
	 * @param cause
	 */
	public MessageCodeWooException(Throwable cause, Integer code) {
		this(cause);
		this.code = code;
	}

	/**
	 * @param message
	 */
	public MessageCodeWooException(Integer code) {
		this();
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
