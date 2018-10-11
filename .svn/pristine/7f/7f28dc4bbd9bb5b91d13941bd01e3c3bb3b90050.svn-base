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
 * @Date Feb 26, 2018
 */
public enum EnumError {
	
	/**
	 * 验签失败
	 */
	ERR_9000("验签失败", 9000),
	/**
	 * 解密失败
	 */
	ERR_9001("解密失败", 9001),
	/**
	 * 未知系统异常
	 */
	ERR_9003("未知系统异常", 9003),
	
	/**
	 * 暂无可用渠道
	 */
	ERR_8001("暂无可用渠道", 8001),
	
	/**
	 * 渠道正在维护，请稍后再试
	 */
	ERR_8002("渠道正在维护，请稍后再试", 8002),
	
	/**
	 * 正在进行日终跑批，请稍后再试
	 */
	ERR_8003("正在进行日终跑批，请稍后再试", 8003),
	
	/**
	 * 参数或数值有异常
	 */
	ERR_2001("参数或数值有异常", 2001),
	/**
	 * 订单号不能为空
	 */
	ERR_2002("订单号不能为空", 2002),
	/**
	 * 下单注册失败
	 */
	ERR_2003("下单注册失败", 2003),
	
	/**
	 * 机构账户状态停用或其他
	 */
	ERR_3001("机构账户状态停用或其他", 3001),

	/**
	 * 原订单不存在
	 */
	ERR_4001("原订单不存在", 4001),
	/**
	 * 重复请求订单
	 */
	ERR_4002("重复请求订单", 4002),
	/**
	 * 同笔订单请求过于频繁
	 */
	ERR_4003("同笔订单请求过于频繁", 4003),

	/**
	 * 账户余额不足
	 */
	ERR_4004("账户余额不足", 4004),

	;

	private String error;
	private Integer code;

	static Map<Integer, EnumError> enumMap = new HashMap<Integer, EnumError>();

	static {
		for (EnumError type : EnumError.values()) {
			enumMap.put(type.getCode(), type);
		}
	}

	private EnumError(String error, Integer code) {
		this.error = error;
		this.code = code;
	}

	public static EnumError getEnum(String value) {
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

	public static EnumError getEnum(Integer code) {
		return enumMap.get(code);
	}
}
