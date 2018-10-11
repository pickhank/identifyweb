package com.woodare.template.jpa.model.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Luke
 */
public enum EnumDspStatus {
	// 处理中
	PROCESSING("处理中"),
	// 处理成功
	SUCCESS("处理成功"),
	// 处理失败
	FAIL("处理失败"),
	//
	;

	private String desc;

	EnumDspStatus(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	private static final Map<String, EnumDspStatus> stringToEnum = new HashMap<String, EnumDspStatus>();
	static {
		// Initialize map from constant name to enum constant
		for (EnumDspStatus blah : values()) {
			stringToEnum.put(blah.toString(), blah);
		}
	}

	public static EnumDspStatus fromString(String symbol) {
		return stringToEnum.get(symbol);
	}
}
