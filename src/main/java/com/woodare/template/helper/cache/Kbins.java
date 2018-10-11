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

import com.woodare.framework.utils.StringUtils;
import com.woodare.template.jpa.persistence.data.kbin.KbinData;

/**
 * ClassName: Kbins
 * 
 * @description
 * @author Xinxing Jiang
 * @Date 2017年11月21日
 */
public class Kbins {
	private static ConcurrentHashMap<Integer, List<KbinData>> cacheMap = new ConcurrentHashMap<Integer, List<KbinData>>();

	public static KbinData getByCardNo(String cardNo) {
		if (StringUtils.isNotEmpty(cardNo)) {
			List<KbinData> tmp = cacheMap.get(cardNo.length());
			if (tmp != null) {
				for (KbinData kbin : tmp) {
					if (cardNo.startsWith(kbin.getStartNum())) {
						return kbin;
					}
				}
			}
		}
		return null;
	}

	public static void setValues(KbinData value) {
		List<KbinData> tmp = cacheMap.get(value.getCardLength());
		if (tmp == null) {
			tmp = new ArrayList<KbinData>();
			cacheMap.put(value.getCardLength(), tmp);
		}
		tmp.add(value);
	}

	public static void reset() {
		cacheMap.clear();
		cacheMap = new ConcurrentHashMap<Integer, List<KbinData>>();
	}
}
