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
package com.woodare.core.web.common.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;

/**
 * ClassName: GeneralExceptionHandler
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
@ControllerAdvice
public class GeneralExceptionHandler extends BaseController {
	private Log logger = LogFactory.getLog(GeneralExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView handleInvalidMerchantUserException(Exception ex) {
		logger.error("Catched exception: " + ex, ex);
		return alertMessage(ex.getMessage(), false);
	}

}
