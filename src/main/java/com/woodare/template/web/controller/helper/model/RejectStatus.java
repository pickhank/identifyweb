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
package com.woodare.template.web.controller.helper.model;

import com.woodare.template.jpa.model.data.EnumAccountStatus;

/**
 * ClassName: LoginResponse
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Aug 30, 2016
 * 
 */
public class RejectStatus extends POSPResult {
	private EnumAccountStatus status;

	public EnumAccountStatus getStatus() {
		return status;
	}

	public void setStatus(EnumAccountStatus status) {
		this.status = status;
	}

}