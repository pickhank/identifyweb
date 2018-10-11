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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;

/**
 * ClassName: DownMerchants
 * 
 * @description
 * @author Luke
 * @Date 2018年3月8日
 */
public class DownMerchants {
	private static Logger log = Logger.getLogger(DownMerchants.class);
	private static Map<String, DownMerchantData> _cachedMap = new HashMap<String, DownMerchantData>();
	private static List<DownMerchantData> _cachedList = new ArrayList<DownMerchantData>();

	public static List<DownMerchantData> getAll() {
		return _cachedList;
	}

	public static DownMerchantData getByCode(String code) {
		if (code == null) {
			return null;
		}
		return _cachedMap.get(code);
	}

	/**
	 * @param items
	 */
	public synchronized static void resetAll(List<DownMerchantData> items) {
		_cachedList.clear();

		if (items != null && items.size() > 0) {
			Map<String, DownMerchantData> cachedMap = new HashMap<String, DownMerchantData>();
			for (DownMerchantData item : items) {
				cachedMap.put(item.getMchNo(), item);

				_cachedList.add(item);
			}
			_cachedMap = cachedMap;
		}
		log.info("DownMerchants[][Reloaded]");
	}
}
