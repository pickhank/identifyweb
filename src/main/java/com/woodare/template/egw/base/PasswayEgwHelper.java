package com.woodare.template.egw.base;

import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.template.jpa.model.data.EnumDownNoCardChannel;
import com.woodare.template.jpa.model.data.EnumDspChannel;

/**
 * 通道外发服务器帮助类
 * 
 * @author Luke
 */
public class PasswayEgwHelper {

	/**
	 * 获取对应网关处理类
	 * 
	 * @param channel
	 * @param objClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getService(EnumDspChannel egwName, Class<T> objClass) {
		T t = (T) ApplicationContextHolder.getApplicationContext().getBean(egwName.getEgwName() + "PasswayEgw");
		return t;
	}

	/**
	 * 获取对应网关处理类
	 * 
	 * @param channel
	 * @param objClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getService(String egwName, Class<T> objClass) {
		T t = (T) ApplicationContextHolder.getApplicationContext().getBean(egwName + "PasswayEgw");
		return t;
	}

	/**
	 * 获取对应网关处理类
	 * 
	 * @param channel
	 * @param objClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getService(EnumDownNoCardChannel channel, Class<T> objClass) {
		T t = null;

		if (channel.getEgwName() != null) {
			t = (T) ApplicationContextHolder.getApplicationContext().getBean(channel.getEgwName() + "PasswayEgw");
		}

		return t;
	}
}
