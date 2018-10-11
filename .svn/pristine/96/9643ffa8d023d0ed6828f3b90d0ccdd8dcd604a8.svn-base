package com.woodare.template.helper.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.woodare.core.util.AesUtils;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumDspChannel;
import com.woodare.template.jpa.model.data.EnumDspMode;
import com.woodare.template.jpa.persistence.data.passwaydspmerchant.PasswayDspMerchantData;
import com.woodare.template.web.viewdata.passwaydspmerchant.PasswayDspMerchantFeeData;
import com.woodare.template.web.viewdata.passwaydspmerchant.PasswayDspMerchantViewData;

/**
 * 通道验证商户缓存表
 * 
 * @author Luke
 */
public class PasswayDspMerchants {
	private static Logger log = Logger.getLogger(PasswayDspMerchants.class);

	private static Map<String, PasswayDspMerchantViewData> _cachedMap = new HashMap<String, PasswayDspMerchantViewData>();
	private static Map<EnumDspChannel, List<PasswayDspMerchantViewData>> _channelMerchants = new HashMap<EnumDspChannel, List<PasswayDspMerchantViewData>>();

	public static void main(String[] args) throws Exception {
		String c = AesUtils.encrypt("JENB7ZXKeJ7DP7z7xjdgxY5zNc6X5Hsg", EnumDownNoCardChannel.AINO.getAesKey());
		System.out.println(c);
		System.out.println(AesUtils.decrypt(c, EnumDownNoCardChannel.AINO.getAesKey()));
	}

	/**
	 * @param chanal
	 * @return
	 */
	public static PasswayDspMerchantViewData get(EnumDspChannel channel, String channelNo) {
		return _cachedMap.get(channel.toString() + "_" + channelNo);
	}

	/**
	 * @param routeMerchantNo
	 * @return
	 */
	public static List<PasswayDspMerchantViewData> getByChannel(EnumDspChannel channel) {
		return _channelMerchants.get(channel);
	}

	/**
	 * @param items
	 */
	public synchronized static void resetAll(List<PasswayDspMerchantData> items) {
		if (items != null && items.size() > 0) {
			Map<String, PasswayDspMerchantViewData> cachedMap = new HashMap<String, PasswayDspMerchantViewData>();
			Map<EnumDspChannel, List<PasswayDspMerchantViewData>> channelMerchants = new HashMap<EnumDspChannel, List<PasswayDspMerchantViewData>>();
			for (PasswayDspMerchantData item : items) {
				if (item != null && item.getChannel() != null && StringUtils.isNotEmpty(item.getChannelAccNo()) && EnumDownUserStatus.ACTIVE.equals(item.getStatus())) {
					PasswayDspMerchantViewData cloneItem = SaftyBeanUtils.cloneTo(item, PasswayDspMerchantViewData.class);
					String aesKey = item.getChannel().getAesKey();
					cloneItem.setPayFees(new HashMap<String, PasswayDspMerchantFeeData>());

					if (StringUtils.isNotEmpty(aesKey)) {
						try {
							if (StringUtils.isNotEmpty(cloneItem.getEncKey())) {
								cloneItem.setEncKey(AesUtils.decrypt(cloneItem.getEncKey(), aesKey));
							}
							if (StringUtils.isNotEmpty(cloneItem.getSignKey())) {
								cloneItem.setSignKey(AesUtils.decrypt(cloneItem.getSignKey(), aesKey));
							}

							String feeText = cloneItem.getFeeText();
							if (StringUtils.isNotEmpty(feeText)) {
								String[] fees = feeText.split("\n", -1);
								for (String fee : fees) {
									String[] values = fee.split(",", -1);
									if (values != null && values.length >= 3 && EnumDspMode.fromString(values[0]) != null) {
										cloneItem.getPayFees().put(values[0], new PasswayDspMerchantFeeData(values));
									}
								}
							}
						} catch (Exception e) {
							log.error(String.format("LoadPasswayDspMerchant[%s][%s]%s", cloneItem.getChannel(), cloneItem.getChannelAccNo(), e.getMessage()), e);
						}
					}
					if (!channelMerchants.containsKey(cloneItem.getChannel())) {
						channelMerchants.put(cloneItem.getChannel(), new ArrayList<PasswayDspMerchantViewData>());
					}
					channelMerchants.get(cloneItem.getChannel()).add(cloneItem);
					cachedMap.put(item.getChannel().toString() + "_" + cloneItem.getChannelAccNo(), cloneItem);
				}
			}
			_cachedMap = cachedMap;
			_channelMerchants = channelMerchants;
		}
		log.info("PasswayDspMerchants[][Reloaded]");
	}
}
