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
package com.woodare.template.jersery.webservice.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.utils.StringUtils;
import com.woodare.framework.utils.ValidatorUtils;
import com.woodare.template.constant.EnumError;

/**
 * ClassName: ValidHelper
 * 
 * @description
 * @author Luke
 * @Date Feb 26, 2018
 */
public class ValidHelper {
	public static void verifyColumn(String tagName, String value, EnumError code, boolean required, int maxLength) throws MessageWooException {
		if (StringUtils.isEmpty(value)) {
			if (required) {
				throw new MessageWooException(tagName + "必填", code);
			}
		}
		else if (maxLength > 0 && value.length() > maxLength) {
			throw new MessageWooException(tagName + "长度超出最大允许值", code);
		}
	}

	public static void notEmpty(String value, String error, EnumError code) throws MessageWooException {
		if (StringUtils.isEmpty(value)) {
			throw new MessageWooException(error, code);
		}
	}

	public static void notNull(Object value, EnumError err) throws MessageWooException {
		if (value == null) {
			throw new MessageWooException(err.getError(), err);
		}
	}

	public static void notNull(Object value, String error, EnumError enumError) throws MessageWooException {
		if (value == null) {
			throw new MessageWooException(error, enumError);
		}
	}

	public static void validPrice(BigDecimal value, Integer min, Integer max, String pat, String error) throws MessageWooException {
		if (value == null) {
			throw new MessageWooException("金额不能为空", EnumError.ERR_2001);
		}
		else if (min != null && value.floatValue() < min) {
			throw new MessageWooException("金额需不小于" + min + "元", EnumError.ERR_2001);
		}
		else if (max != null && value.floatValue() > max) {
			throw new MessageWooException("最大交易金额为" + max + "元", EnumError.ERR_2001);
		}
		else if (StringUtils.isNotEmpty(pat)) {
			String pStr = String.valueOf(value.longValue());
			Pattern pattern = Pattern.compile(pat);
			Matcher m = pattern.matcher(pStr);
			if (m.matches()) {
				throw new MessageWooException(error, EnumError.ERR_2001);
			}
		}
	}

	public static void validDate(String value, SimpleDateFormat sdf, String error, EnumError code) throws MessageWooException {
		try {
			sdf.parse(value);
		} catch (Exception e) {
			throw new MessageWooException(error, code);
		}
	}

	public static Date getValidDate(String value, SimpleDateFormat sdf, String error, EnumError code) throws MessageWooException {
		try {
			return sdf.parse(value);
		} catch (Exception e) {
			throw new MessageWooException(error, code);
		}
	}

	public static void validPhone(String value, String error, EnumError code) throws MessageWooException {
		if (!ValidatorUtils.isMoblie(value)) {
			throw new MessageWooException(error, code);
		}
	}

	public static void validPhone2(String value, String error, EnumError code) throws MessageWooException {
		if (!ValidatorUtils.isMoblie2(value)) {
			throw new MessageWooException(error, code);
		}
	}

	public static void validName(String value, String error, EnumError code) throws MessageWooException {
		if (invalidName(value)) {
			throw new MessageWooException(error, code);
		}
	}

	private static String regex = ".*(微信|支付宝|支付|银行|民生|金融|收单)+.*";

	private static boolean invalidName(String name) {
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(name);
		return mat.matches();
	}
}
