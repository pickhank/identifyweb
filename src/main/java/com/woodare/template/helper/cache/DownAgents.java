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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;

/**
 * ClassName: Channels
 * 
 * @description
 * @author Xinxing Jiang
 * @Date 2017年10月23日
 */
public class DownAgents {
	private static ConcurrentHashMap<String, DownAgentData> cacheMap = new ConcurrentHashMap<String, DownAgentData>();

	public static DownAgentData getByCode(String code) {
		return cacheMap.get(code);
	}
	
	public static List<DownAgentData> getAll() {
		return new ArrayList<DownAgentData>(cacheMap.values());
	}

	public static void setByCode(String code, DownAgentData value) {
		cacheMap.put(code, value);
	}

	public static void reset() {
		cacheMap.clear();
	}
}
