package com.woodare.template.jpa.model.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.woodare.core.util.SDFFactory;

/**
 * 产品类型
 * 
 * @author loven_11
 */
public enum EnumProductType {
	// 网银，借记卡
	ONLINEBANK_DEBIT("01", "网银-借"),

	// 银联在线，借记卡
	UNIONPAY_DEBIT("02", "银联在线-借"),

	// 扫码付，借记卡
	QRCODE_DEBIT("03", "扫码付-借"),

	// 银联码-主扫
	UNIONQR_SCAN("10", "银联码-扫码"),

	// 银联码-被扫
	UNIONQR_PAY("11", "银联码-被扫"),

	// QQ-钱包
	QQQR_PAY("12", "QQ钱包"),

	// 支付宝扫码
	ALIQR_PAY("13", "支付宝扫码"),

	// 支付宝H5
	ALIH5_PAY("14", "支付宝H5"),

	// 微信H5
	WXH5_PAY("15", "微信H5"),

	// 同名交易，积分类
	QUICKPAY_CREDIT_SCORE("21", "同名快捷"),

	;

	private String mode;
	private String desc;

	EnumProductType(String mode, String desc) {
		this.mode = mode;
		this.desc = desc;
	}

	public String getMode() {
		return mode;
	}

	public String getDesc() {
		return desc;
	}

	private static Map<String, EnumProductType> _map = new HashMap<String, EnumProductType>();
	static {
		for (EnumProductType product : EnumProductType.values()) {
			_map.put(product.getMode(), product);
		}
	}

	/**
	 * @param mode
	 * @return
	 */
	public static EnumProductType getByMode(String mode) {
		if (mode != null && _map.containsKey(mode)) {
			return _map.get(mode);
		}
		return null;
	}

	/**
	 * @return
	 */
	public static String getUniTransNo(String payType) {
		String transNo = SDFFactory.getOrderNo();
		// 网银
		if ("01".equals(payType)) {
			transNo = "OB" + transNo;
		}
		// 银联在线
		else if ("02".equals(payType)) {
			transNo = "UN" + transNo;
		}
		// 转账码
		else if ("03".equals(payType)) {
			transNo = "DR" + transNo;
		}
		// 银联扫码
		else if ("10".equals(payType)) {
			transNo = "SN" + transNo;
		}
		// 银联付款码
		else if ("11".equals(payType)) {
			transNo = "PN" + transNo;
		}
		// QQ钱包
		else if ("12".equals(payType)) {
			transNo = "SQ" + transNo;
		}
		// 同名进出
		else if ("21".equals(payType)) {
			transNo = "SP" + transNo;
		}
		else {
			transNo = "XX" + transNo;
		}

		return transNo;
	}

	/**
	 * @return
	 */
	public static String unshortenTransNo(String payType, String shortenTransNo) {
		String yearMonth = SDFFactory.DATE.format(new Date()).substring(2, 6);
		String transNo = shortenTransNo.substring(0, 1) + yearMonth + shortenTransNo.substring(1);
		// 网银
		if ("01".equals(payType)) {
			transNo = "OB" + transNo;
		}
		// 银联在线
		else if ("02".equals(payType)) {
			transNo = "UN" + transNo;
		}
		// 转账码
		else if ("03".equals(payType)) {
			transNo = "DR" + transNo;
		}
		// 银联扫码
		else if ("10".equals(payType)) {
			transNo = "SN" + transNo;
		}
		// 银联付款码
		else if ("11".equals(payType)) {
			transNo = "PN" + transNo;
		}
		// QQ钱包
		else if ("12".equals(payType)) {
			transNo = "SQ" + transNo;
		}
		// 同名进出
		else if ("21".equals(payType)) {
			transNo = "SP" + transNo;
		}
		else {
			transNo = "XX" + transNo;
		}

		return transNo;
	}

	/**
	 * @return
	 */
	public static String shortenTransNo(String uniTransNo) {
		return uniTransNo.substring(2, 3) + uniTransNo.substring(7);
	}

	public static void main(String[] args) {
		String shortenId = shortenTransNo("DRD1806051055067421000");
		System.out.println(shortenId);
		System.out.println(unshortenTransNo("03", "A051455457651005"));
	}
}
