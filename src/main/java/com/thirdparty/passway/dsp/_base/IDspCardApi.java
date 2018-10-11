package com.thirdparty.passway.dsp._base;

import com.thirdparty.passway._data.PasswayDspRequestData;
import com.thirdparty.passway._data.PasswayDspResponseData;
import com.woodare.framework.exception.AbstractWooException;

/**
 * 鉴权通道银行卡交易处理接口
 * 
 * @author Luke
 */
public interface IDspCardApi {

	/**
	 * 个人身份二要素验证（姓名+身份证）根据姓名和身份证验证个人身份。
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	PasswayDspResponseData processC102(PasswayDspRequestData reqData);

	/**
	 * 个人身份二要素验证（姓名+身份证）根据姓名和身份证验证个人身份。
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	PasswayDspResponseData processC112(PasswayDspRequestData reqData);

	/**
	 * 银行卡三要素验证[非精准]（姓名+身份证+银行卡号）根据姓名+身份证+银行卡号验证个人身份。中国金融认证中心China Financial Certification Authority CFCA 数据产品服务规范
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	PasswayDspResponseData processC115(PasswayDspRequestData reqData);

	/**
	 * 银行卡四要素验证[非精准]（姓名+身份证+手机号+银行卡号）根据姓名+身份证+手机号银行卡号验证个人身份。
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	PasswayDspResponseData processC116(PasswayDspRequestData reqData);

	/**
	 * 运营商三要素验证（姓名+身份证+手机号）。
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	PasswayDspResponseData processC123(PasswayDspRequestData reqData);
}
