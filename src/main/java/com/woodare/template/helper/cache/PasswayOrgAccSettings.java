package com.woodare.template.helper.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.woodare.template.jpa.persistence.data.passwayorgaccsetting.PasswayOrgAccSettingData;

/**
 * @author Luke
 */
public class PasswayOrgAccSettings {
	// 根据渠道code和账户获取配置
	private static ConcurrentHashMap<String, PasswayOrgAccSettingData> keyMap = new ConcurrentHashMap<String, PasswayOrgAccSettingData>();

	// 根据原Channel作为唯一键获取渠道账户配置
	private static ConcurrentHashMap<String, PasswayOrgAccSettingData> channelMap = new ConcurrentHashMap<String, PasswayOrgAccSettingData>();

	// 全部集合
	private static List<PasswayOrgAccSettingData> items = new ArrayList<PasswayOrgAccSettingData>();

	/**
	 * @param channel
	 * @return
	 */
	public static PasswayOrgAccSettingData getByChannel(String channel) {
		return channelMap.get(channel);
	}

	/**
	 * 
	 * @return
	 */
	public static List<PasswayOrgAccSettingData> listItems() {
		return items;
	}

	/**
	 * @param channel
	 * @return
	 */
	public static PasswayOrgAccSettingData getByKey(String code, String mercId) {
		return keyMap.get(code + "_" + mercId);
	}

	public static void add(PasswayOrgAccSettingData value) {
		keyMap.put(value.getEgwName() + "_" + value.getEgwMercId(), value);
		channelMap.put(value.getChannelKey(), value);
		items.add(value);
	}

	public static void reset() {
		items = new ArrayList<PasswayOrgAccSettingData>();
		keyMap.clear();
		channelMap.clear();
	}
}
