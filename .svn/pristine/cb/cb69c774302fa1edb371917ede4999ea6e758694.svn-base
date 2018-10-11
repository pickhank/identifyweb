package com.woodare.template.jpa.model.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luke
 */
public enum EnumDspMode {
	// 交易类型
	// 2102-个人身份二要素验证（姓名+身份证）根据姓名和身份证验证个人身份。
	C102("身份二要素（姓名+身份证）", Arrays.asList(new EnumDspFieldType[] { EnumDspFieldType.Name, EnumDspFieldType.IdentifyCode })),

	// 2103-个人身份二要素验证（姓名+身份证）根据姓名和身份证验证个人身份，并返回身份证照片。
	// C103("身份二要素（返）"),

	// 2104-个人身份二要素验证（姓名+身份证，含业务响应信息）根据姓名和身份证验证个人身份。返回报文包含业务响应信息（ResponseCode、 ResponseMessage） 。
	// C104("身份二要素（准）"),

	// 2112-银行卡二要素验证（姓名+银行卡号）根据姓名+银行卡号验证个人身份。
	C112("银行卡二要素（姓名+银行卡号）", Arrays.asList(new EnumDspFieldType[] { EnumDspFieldType.Name, EnumDspFieldType.CardNo })),

	// 2113-银行卡三要素验证[精准]（姓名+身份证+银行卡号）根据姓名+身份证+银行卡号验证个人身份。
	// C113("银行卡三要素（准）"),

	// 2114-银行卡四要素验证[精准]（姓名+身份证+手机号+银行卡号）根据姓名+身份证+手机号银行卡号验证个人身份。
	// C114("银行卡四要素（准）"),

	// 2115-银行卡三要素验证[非精准]（姓名+身份证+银行卡号）根据姓名+身份证+银行卡号验证个人身份。中国金融认证中心China Financial Certification Authority CFCA 数据产品服务规范
	C115("银行卡三要素（姓名+身份证+银行卡号）", Arrays.asList(new EnumDspFieldType[] { EnumDspFieldType.Name, EnumDspFieldType.IdentifyCode, EnumDspFieldType.CardNo })),

	// 2116-银行卡四要素验证[非精准]（姓名+身份证+手机号+银行卡号）根据姓名+身份证+手机号银行卡号验证个人身份。
	C116("银行卡四要素（姓名+身份证+手机号+银行卡号）", Arrays.asList(new EnumDspFieldType[] { EnumDspFieldType.Name, EnumDspFieldType.Mobile, EnumDspFieldType.IdentifyCode, EnumDspFieldType.CardNo })),

	// 2117-银行卡三要素验证[返卡类型、发卡行]（姓名+身份证+银行卡号）银行卡三要素验证（姓名+身份证+银行卡号） 并返回卡类型、发卡行信息。
	// C117("银行卡三要素（返）"),

	// 2118-银行卡四要素验证[返卡类型、发卡行]（姓名+身份证+银行卡号+预留手机号）银行卡三要素验证（姓名+身份证+银行卡号） 并返回卡类型、发卡行信息。
	// C118("银行卡四要素（返）"),

	// 2123-运营商三要素验证（姓名+身份证+手机号）根据姓名+身份证+手机号验证个人身份。
	C123("运营商三要素（姓名+身份证+手机号）", Arrays.asList(new EnumDspFieldType[] { EnumDspFieldType.Name, EnumDspFieldType.Mobile, EnumDspFieldType.IdentifyCode })),

	// 2124-运营商三要素验证（姓名+身份证+手机号，含业务响应信息）根据姓名+身份证+手机号验证个人身份。返回信息包含业务响应信息。
	// C124("运营商三要素（返）"),

	// 2301-活体验证 ......................................................................................................................25
	C301("活体验证（姓名+身份证号+采集图片）", Arrays.asList(new EnumDspFieldType[] { EnumDspFieldType.Name, EnumDspFieldType.Photos, EnumDspFieldType.IdentifyCode })),
	
	//
	;

	private String desc;
	private List<EnumDspFieldType> fields;

	EnumDspMode(String desc, List<EnumDspFieldType> fields) {
		this.desc = desc;
		this.fields = fields;
	}

	public String getDesc() {
		return desc;
	}

	/**
	 * @param fieldType
	 * @return
	 */
	public boolean isRequired(EnumDspFieldType fieldType) {
		return this.fields.contains(fieldType);
	}

	private static final Map<String, EnumDspMode> stringToEnum = new HashMap<String, EnumDspMode>();
	static {
		// Initialize map from constant name to enum constant
		for (EnumDspMode blah : values()) {
			stringToEnum.put(blah.toString(), blah);
		}
	}

	public static EnumDspMode fromString(String symbol) {
		return stringToEnum.get(symbol);
	}
}
