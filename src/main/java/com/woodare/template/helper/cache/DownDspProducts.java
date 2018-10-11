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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumDspMode;
import com.woodare.template.jpa.persistence.data.downdspproduct.DownDspProductData;

/**
 * ClassName: DownDspProducts
 * 
 * @description
 * @author Luke
 * @Date 2018年3月8日
 */
public class DownDspProducts {
	private static Logger log = Logger.getLogger(DownDspProducts.class);

	private static Map<String, DownDspProductData> _cachedMap = new HashMap<String, DownDspProductData>();
	private static Map<String, List<DownDspProductData>> _cachedListMap = new HashMap<String, List<DownDspProductData>>();

	/**
	 * @param mchNo
	 * @param productType
	 * @return
	 */
	public static DownDspProductData getByMchNoAndDspMode(String mchNo, EnumDspMode mode) {
		return _cachedMap.get(mchNo + "_" + mode);
	}

	/**
	 * @param mchNo
	 * @return
	 */
	public static List<DownDspProductData> getByMchNo(String mchNo) {
		return _cachedListMap.get(mchNo);
	}

	/**
	 * @param items
	 */
	public synchronized static void resetAll(List<DownDspProductData> items) {
		if (items != null && items.size() > 0) {
			Map<String, DownDspProductData> cachedMap = new HashMap<String, DownDspProductData>();
			Map<String, List<DownDspProductData>> cachedListMap = new HashMap<String, List<DownDspProductData>>();

			for (DownDspProductData item : items) {
				if (StringUtils.isNotEmpty(item.getMchNo()) && item.getMode() != null && EnumDownUserStatus.ACTIVE.equals(item.getStatus())) {
					if (!cachedListMap.containsKey(item.getMchNo())) {
						cachedListMap.put(item.getMchNo(), new ArrayList<DownDspProductData>());
					}
					cachedListMap.get(item.getMchNo()).add(item);
					cachedMap.put(item.getMchNo() + "_" + item.getMode(), item);
				}
			}
			_cachedMap = cachedMap;
			_cachedListMap = cachedListMap;
		}
		log.info("DownDspProducts[][Reloaded]");
	}
}
