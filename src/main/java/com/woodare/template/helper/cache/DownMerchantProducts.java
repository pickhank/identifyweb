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
import com.woodare.template.jpa.persistence.data.downmerchantproduct.DownMerchantProductData;

/**
 * ClassName: DownMerchantProducts
 * 
 * @description
 * @author Luke
 * @Date 2018年3月8日
 */
public class DownMerchantProducts {
	private static Logger log = Logger.getLogger(DownMerchantProducts.class);

	private static Map<String, DownMerchantProductData> _cachedMap = new HashMap<String, DownMerchantProductData>();
	private static Map<String, List<DownMerchantProductData>> _cachedListMap = new HashMap<String, List<DownMerchantProductData>>();

	/**
	 * @param mchNo
	 * @param productType
	 * @return
	 */
	public static DownMerchantProductData getByMchNoAndProductType(String mchNo, EnumDspMode productType) {
		return _cachedMap.get(mchNo + "_" + productType);
	}

	/**
	 * @param mchNo
	 * @return
	 */
	public static List<DownMerchantProductData> getByMchNo(String mchNo) {
		return _cachedListMap.get(mchNo);
	}

	/**
	 * @param items
	 */
	public synchronized static void resetAll(List<DownMerchantProductData> items) {
		if (items != null && items.size() > 0) {
			Map<String, DownMerchantProductData> cachedMap = new HashMap<String, DownMerchantProductData>();
			Map<String, List<DownMerchantProductData>> cachedListMap = new HashMap<String, List<DownMerchantProductData>>();

			for (DownMerchantProductData item : items) {
				if (StringUtils.isNotEmpty(item.getMchNo()) && item.getDspMode() != null && EnumDownUserStatus.ACTIVE.equals(item.getStatus())) {
					if (!cachedListMap.containsKey(item.getMchNo())) {
						cachedListMap.put(item.getMchNo(), new ArrayList<DownMerchantProductData>());
					}
					cachedListMap.get(item.getMchNo()).add(item);
					cachedMap.put(item.getMchNo() + "_" + item.getDspMode(), item);
				}
			}
			_cachedMap = cachedMap;
			_cachedListMap = cachedListMap;
		}
		log.info("DownMerchantProducts[][Reloaded]");
	}
}
