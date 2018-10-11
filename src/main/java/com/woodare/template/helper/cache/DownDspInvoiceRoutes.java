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
import java.util.Date;
import java.util.List;

import com.woodare.core.util.SDFFactory;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumDspMode;
import com.woodare.template.jpa.persistence.data.downdspinvoiceroute.DownDspInvoiceRouteData;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.data.passwaydspmerchant.PasswayDspMerchantData;

/**
 * ClassName: DownDspInvoiceRoutes
 * 
 * @description
 * @author Luke
 * @Date 2018年09月15日
 */
public class DownDspInvoiceRoutes {
	private static List<DownDspInvoiceRouteData> cacheMapMerc = new ArrayList<DownDspInvoiceRouteData>();

	public static List<DownDspInvoiceRouteData> getAll() {
		return cacheMapMerc;
	}

	/**
	 * @param downMerchant
	 * @param productType
	 * @return
	 */
	public static DownDspInvoiceRouteData getByMerchantAndDspMode(DownMerchantData downMerchant, EnumDspMode dspMode) {
		DownDspInvoiceRouteData route = null;
		for (DownDspInvoiceRouteData data : cacheMapMerc) {
			boolean validFlg = false;

			PasswayDspMerchantData passwayMerchant = PasswayDspMerchants.get(data.getChannel(), data.getChannelMercNo());

			validFlg = passwayMerchant != null && EnumDownUserStatus.ACTIVE.equals(passwayMerchant.getStatus());

			// 限定机构标识是否一致
			if (StringUtils.isNotEmpty(data.getMchNo())) {
				validFlg = data.getMchNo().equals(downMerchant.getMchNo());
			}
			// 限制类别是否一致
			if (validFlg && data.getMercCategory() != null) {
				validFlg = data.getMercCategory().equals(downMerchant.getMercCategory());
			}
			// 限定支持的验签类型是否支持
			if (validFlg && StringUtils.isNotEmpty(data.getSupportDspModes())) {
				validFlg = ("," + data.getSupportDspModes() + ",").indexOf(dspMode.toString()) != -1;
			}
			// 限定开始日期\失效日期是否满足条件
			String curDate = SDFFactory.DATE.format(new Date());
			if (validFlg && StringUtils.isNotEmpty(data.getStartDate())) {
				validFlg = curDate.compareTo(data.getStartDate()) >= 0;
			}
			if (validFlg && StringUtils.isNotEmpty(data.getEndDate())) {
				validFlg = curDate.compareTo(data.getEndDate()) <= 0;
			}
			// 限定开始时间\失效时间是否满足条件
			String curTime = SDFFactory.TIME.format(new Date());
			if (validFlg && StringUtils.isNotEmpty(data.getStartTime()) && !"000000".equals(data.getStartTime())) {
				validFlg = curTime.compareTo(data.getStartTime()) >= 0;
			}
			if (validFlg && StringUtils.isNotEmpty(data.getEndTime()) && !"000000".equals(data.getEndTime())) {
				validFlg = curTime.compareTo(data.getEndTime()) <= 0;
			}

			if (validFlg) {
				route = data;
				break;
			}

		}
		return route;
	}

	/**
	 * @param values
	 */
	public static void setValues(List<DownDspInvoiceRouteData> values) {
		cacheMapMerc.addAll(values);
	}

	/**
	 * 
	 */
	public static void reset() {
		cacheMapMerc.clear();
	}
}
