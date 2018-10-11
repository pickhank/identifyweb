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

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: EnumCreditCardChannel
 * 
 * @description
 * @author Luke
 * @Date 2018/03/23
 */
public enum EnumCreditCardChannel {
	MSAND("msand", "0102030405060708", null),  // M杉德
	
	;
	private String egwName;
	private String aeskey;
	private String extra;
	private EnumDownNoCardChannel refer;

	/**
	 * 
	 * @param egwName 渠道适配器
	 * @param aeskey 
	 * @param extra 
	 */
	EnumCreditCardChannel(String egwName, String aeskey, String extra) {
		this.egwName = egwName;
		this.aeskey = aeskey;
		this.extra = extra;
	}

	public EnumDownNoCardChannel getRefer() {
		return refer;
	}

	public String getEgwName() {
		return this.egwName;
	}

	public String getAesKey() {
		return this.aeskey;
	}

	public String getExtra() {
		return this.extra;
	}

	private static final Map<String, EnumCreditCardChannel> stringToEnum = new HashMap<String, EnumCreditCardChannel>();
	static {
		// Initialize map from constant name to enum constant
		for (EnumCreditCardChannel blah : values()) {
			stringToEnum.put(blah.toString(), blah);
		}
	}

	public static EnumCreditCardChannel fromString(String symbol) {
		return stringToEnum.get(symbol);
	}

}
