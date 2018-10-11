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

/**
 * ClassName: EnumQifenType
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Feb 6, 2017
 */
public enum EnumSettleType {
	Local("本地清算"),   // 本地清算
	Channel("渠道直清"),  // 渠道直清
	;

	EnumSettleType(String desc) {
		this.desc = desc;
	}

	private String desc;

	public String getDesc() {
		return desc;
	}
}
