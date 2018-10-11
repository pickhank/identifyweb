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
 * ClassName: EnumDownNoCardChannel
 * 
 * @description
 * @author Luke
 * @Date Feb 26, 2018
 */
public enum EnumDspChannel {
	CPCN("cpcnDsp", "0102030405060708"),  // CFCA

	BANK("bankDsp", "0102030405060708"),  // 人行

	;
	private String egwName;
	private String aeskey;

	/**
	 * @param egwName
	 *            渠道适配器
	 * @param aeskey
	 * @param extra
	 */
	EnumDspChannel(String egwName, String aeskey) {
		this.egwName = egwName;
		this.aeskey = aeskey;
	}

	public String getEgwName() {
		return this.egwName;
	}

	public String getAesKey() {
		return this.aeskey;
	}

	private static final Map<String, EnumDspChannel> stringToEnum = new HashMap<String, EnumDspChannel>();
	static {
		// Initialize map from constant name to enum constant
		for (EnumDspChannel blah : values()) {
			stringToEnum.put(blah.toString(), blah);
		}
	}

	public static EnumDspChannel fromString(String symbol) {
		return stringToEnum.get(symbol);
	}

}
