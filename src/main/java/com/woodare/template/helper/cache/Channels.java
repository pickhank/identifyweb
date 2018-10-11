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
package com.woodare.template.helper.cache;

import java.util.concurrent.ConcurrentHashMap;

import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;

/**
 * ClassName: Channels
 * 
 * @description
 * @author Xinxing Jiang
 * @Date 2017年12月12日
 */
public class Channels {
	private static ConcurrentHashMap<String, EnumDownNoCardChannel> cacheMap = new ConcurrentHashMap<String, EnumDownNoCardChannel>();
	static {
		setByCode("00", EnumDownNoCardChannel.AINO);
	}

	public static EnumDownNoCardChannel getByCode(String code) {
		if (code == null) {
			return null;
		}
		return cacheMap.get(code);
	}

	public static void setByCode(String code, EnumDownNoCardChannel value) {
		cacheMap.put(code, value);
	}

	public static void reset() {
		cacheMap.clear();
	}
}
