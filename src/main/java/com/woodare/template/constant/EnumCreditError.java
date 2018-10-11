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
 * @Date 2017/12/11
 */
public enum EnumCreditError {

	/** 解密失败 */
	ERR_110000("解密失败", 110000),

	// 参数效验失败
	/** 入参效验失败 */
	ERR_110010("入参效验失败", 110010),
	/** 身份效验失败 */
	ERR_110011("身份效验失败", 110011),

	// 用户信息相关
	/** 用户不存在 */
	ERR_110020("用户不存在", 110020),
	/** 用户重复注册 */
	ERR_110021("用户重复注册", 110021),
	/** 用户费率参数不合法 */
	ERR_110022("用户费率参数不合法", 110022),
	/** 用户注册失败 */
	ERR_110029("用户注册失败", 110029),

	// 绑卡签约相关
	/** 签约信息不存在 */
	ERR_110030("签约信息不存在", 110030),
	/** 签约信息重复注册 */
	ERR_110031("签约信息重复注册", 110031),
	/** 签约信息状态有误 */
	ERR_110032("签约信息状态有误", 110032),
	/** 签约信息状态有误 */
	ERR_110033("解绑操作失败", 110033),
	/** 签约卡与用户信息不符 */
	ERR_110034("签约卡与用户信息不符", 110034),
	/** 签约卡非信用卡 */
	ERR_110035("签约卡非信用卡", 110035),
	/** 签约信息重复注册 */
	ERR_110039("签约信息注册失败", 110039),

	// 支付交易相关
	/** 支付流水号已存在 */
	ERR_110040("交易流水号重复", 110040),
	/** 原交易流水号不存在 */
	ERR_110041("原交易流水号不存在", 110041),
	/** 原交易流水号不存在 */
	ERR_110042("交易费率与入网信息不一致", 110042),
	/** 原交易状态已改变，请查询确认 */
	ERR_110043("原交易状态已改变，请查询确认", 110043),
	/** 账户余额入账异常，查询确认 */
	ERR_110044("账户余额入账异常，请查询确认", 110044),

	// 提现交易相关
	/** 提现流水号重复 */
	ERR_110050("提现流水号重复", 110050),
	/** 原提现流水号不存在 */
	ERR_110051("原提现流水号不存在", 110051),
	/** 提现费率与入网信息不一致 */
	ERR_110052("提现费率与入网信息不一致", 110052),
	/** 原交易状态已变化，请重新查询 */
	ERR_110053("原交易状态已变化，请重新查询", 110053),
	/** 账户余额出账异常，查询确认 */
	ERR_110054("账户余额出账异常，请查询确认", 110054),
	/** 用户账户余额不足 */
	ERR_110055("用户账户余额不足", 110055),
	
	
	/** 请求过于频繁，请稍后提交 */
	ERR_120000("请求过于频繁，请稍后再试", 120000),
	;

	public String error;
	public Integer code;

	static Map<Integer, EnumCreditError> enumMap = new HashMap<Integer, EnumCreditError>();

	static {
		for (EnumCreditError type : EnumCreditError.values()) {
			enumMap.put(type.code, type);
		}
	}

	private EnumCreditError(String error, Integer code) {
		this.error = error;
		this.code = code;
	}

	public static EnumCreditError getEnum(String value) {
		return enumMap.get(value);
	}

	public static EnumCreditError getEnum(Integer code) {
		return enumMap.get(code);
	}
}
