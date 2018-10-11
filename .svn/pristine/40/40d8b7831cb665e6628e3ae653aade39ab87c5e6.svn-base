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
package com.woodare.template.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: EnumError
 * 
 * @description
 * @author Luke
 * @Date Nov 21, 2017
 */
public enum EnumDebitError {

	ERR_110000("解密失败", 110000),
	ERR_110001("参数效验失败", 110001),
	ERR_110002("商户初始化参数异常", 110002),
	ERR_110003("卡验证失败", 110003),
	ERR_110004("无法识别卡", 110014),

	ERR_110012("版本号无法识别", 110012),
	ERR_110014("商户通道未配置或不支持", 110014),

	ERR_111000("无法识别交易模式", 111000),

	ERR_111001("预下单失败", 111001),
	ERR_111002("当前状态不允许进行的操作", 111002),
	
	ERR_111010("发送短信失败", 111010),
	ERR_111011("发送短信过于频繁", 111011),
	
	ERR_111020("确认交易失败，状态未知", 111020),
	ERR_111021("短信验证错误", 111021),
	ERR_111022("鉴权身份失效或过期", 111022),
	ERR_111023("鉴权短信未发送或已过期", 111023),
	ERR_111024("该卡已鉴权绑定", 111024),
	
	ERR_120000("原交易不存在", 120000),
	ERR_120001("原交易信息不一致", 120001),
	ERR_120002("重复订单编号", 120002),
	ERR_120003("原信息不一致", 120003),
	
	ERR_190001("不在通道服务时间或暂不受理交易", 190001),
	;
	

	private String error;
	private Integer code;

	static Map<Integer, EnumDebitError> enumMap = new HashMap<Integer, EnumDebitError>();

	static {
		for (EnumDebitError type : EnumDebitError.values()) {
			enumMap.put(type.getCode(), type);
		}
	}

	private EnumDebitError(String error, Integer code) {
		this.error = error;
		this.code = code;
	}

	public static EnumDebitError getEnum(String value) {
		return enumMap.get(value);
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static EnumDebitError getEnum(Integer code) {
		return enumMap.get(code);
	}
}
