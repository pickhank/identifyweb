package com.woodare.template.jpa.model.data;

/**
 * @author loven_11
 */
public enum EnumDeviceStatus {
	Online("在线"), Offine("离线");

	private String desc;

	EnumDeviceStatus(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
