package com.thirdparty.passway.dsp.cpcn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;

import com.alibaba.fastjson.JSON;
import com.thirdparty.passway._data.PasswayDspRequestData;
import com.thirdparty.passway._data.PasswayDspResponseData;
import com.thirdparty.passway.dsp._base.IDspCardApi;
import com.thirdparty.passway.dsp.cpcn.util.CpcnDspConstant;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.template.helper.cache.PasswayDspMerchants;
import com.woodare.template.jpa.model.data.EnumDspStatus;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceData;
import com.woodare.template.jpa.persistence.data.passwaydspmerchant.PasswayDspMerchantData;

import cpcn.dsp.institution.api.codec.Base64;
import cpcn.dsp.institution.api.system.DSPInstitutionEnvironment;
import cpcn.dsp.institution.api.system.TxMessenger;
import cpcn.dsp.institution.api.tx.TxBaseResponse;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2102Request;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2102Response;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2112Request;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2112Response;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2115Request;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2115Response;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2116Request;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2116Response;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2123Request;
import cpcn.dsp.institution.api.tx.personalinfo.Tx2301Request;
import cpcn.dsp.institution.api.util.ClientDecryptionUtil;

/**
 * @author Luke
 */
public class CpcnDspApi implements IDspCardApi {
	private static final Log logger = LogFactory.getLog(CpcnDspApi.class);

	private static int connectTimeoutLimit = 2000; // 设置连接超时时间（单位ms）
	private static int readTimeoutLimit = 5000; // 设置读超时时间（单位ms）

	static {
		// 初始化中金密钥数据
		CpcnDspConstant.initMerchantCerts();
	}

	/**
	 * @return
	 */
	protected String getPasswayName() {
		return "CpcnDsp";
	}

	/**
	 * 个人身份二要素验证（姓名+身份证）根据姓名和身份证验证个人身份。
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	public PasswayDspResponseData processC102(PasswayDspRequestData reqData) {
		DownDspInvoiceData dspInv = reqData.getInvoice();

		PasswayDspMerchantData setting = PasswayDspMerchants.get(dspInv.getChannel(), dspInv.getChannelAccNo());

		PasswayDspResponseData respData = new PasswayDspResponseData();
		try {
			// 1. 交易请求对象
			Tx2102Request request = new Tx2102Request();
			request.setInstitutionID(setting.getChannelAccNo());
			request.setTxSN(dspInv.getTransNo());
			request.setIdentificationType(dspInv.getIdentifyType());
			// 需加密
			request.setIdentificationNumber(dspInv.getIdentifyNo());
			request.setName(dspInv.getHolderName());
			request.setRemark(dspInv.getRemark());
			// System.out.println(JSON.toJSONString(request));

			// 2.
			String tagId = System.currentTimeMillis() + "|" + dspInv.getTransNo();
			String txCode = "2102";
			logger.info("[" + getPasswayName() + txCode + "ReqePlain][" + tagId + "]" + JSON.toJSONString(request));

			// 3. 执行报文处理
			request.setIdentificationNumber(ClientDecryptionUtil.encrypt(dspInv.getIdentifyNo()));
			request.process();
			logger.info("[" + getPasswayName() + txCode + "Reqe][" + tagId + "]" + request.getRequestPlainText());

			Tx2102Response response = this.doPost(tagId, txCode, request.getRequestMessage(), request.getRequestSignature(), "20", Tx2102Response.class);

			// 0001 参数校验失败
			// 2000 SUCCESS
			// 2001 系统内部错误
			// 2002 验证签名失败
			// 2003 解析报文错误
			// 2004 获取通道信息出错
			// 2005 无可用通道
			// 2006 机构交易无效
			// 2007 交易流水号重复
			// 2008 请求通道出错
			// 2009 交易异常
			// 3001 请求报文格式不正确
			// 3002 非法校验不通过
			// 3004 交易类型无效
			// 3005 未查询到结果
			// 4004 不正确的访问
			String code = response.getCode();
			String message = response.getMessage();

			respData.setRetCode(code);
			respData.setRetDesc(message);

			if ("2000".equals(code)) {
				respData.setStatus(EnumDspStatus.SUCCESS);
				respData.setPasswayOrderId(response.getTraceNo());

				// 验证状态: 20=匹配, 30=不匹配
				String verification = response.getVerification();
				if ("20".equals(verification)) {
					respData.setVerifyStatus("00");
				}
				else {
					respData.setVerifyStatus("02");
				}
			}
			else {
				respData.setStatus(EnumDspStatus.FAIL);
			}
		} catch (InterruptedIOException e) {
			respData.setStatus(EnumDspStatus.FAIL);
			String kindMessage = "通讯处理异常，状态未知";
			if (e instanceof ConnectionPoolTimeoutException) {
				kindMessage = "线路繁忙，请重新发起交易";
				respData.setRetCode("E001");
			}
			else if (e instanceof ConnectTimeoutException) {
				kindMessage = "网络繁忙，请重新发起交易";
				respData.setRetCode("E002");
			}
			else if (e instanceof SocketTimeoutException) {
				kindMessage = "通讯响应超时，状态未知";
				respData.setRetCode("E003");
			}
			else {
				kindMessage = "未知网络异常";
				respData.setRetCode("E004");
			}
			respData.setRetDesc(kindMessage);
		} catch (Exception e) {
			logger.error(e, e);
			respData.setStatus(EnumDspStatus.FAIL);
			respData.setRetCode("E900");
			respData.setRetDesc("未知系统异常，" + e.getMessage());
		}
		return respData;
	}

	/**
	 * 个人身份二要素验证（姓名+身份证）根据姓名和身份证验证个人身份。
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	public PasswayDspResponseData processC112(PasswayDspRequestData reqData) {
		DownDspInvoiceData dspInv = reqData.getInvoice();

		PasswayDspMerchantData setting = PasswayDspMerchants.get(dspInv.getChannel(), dspInv.getChannelAccNo());

		PasswayDspResponseData respData = new PasswayDspResponseData();
		try {
			// 1. 交易请求对象
			Tx2112Request request = new Tx2112Request();

			request.setInstitutionID(setting.getChannelAccNo());
			request.setTxSN(dspInv.getTransNo());
			request.setName(dspInv.getHolderName());
			request.setAccountNumber(dspInv.getCardNo());
			request.setRemark(dspInv.getRemark());

			// 2.
			String tagId = System.currentTimeMillis() + "|" + dspInv.getTransNo();
			String txCode = "2112";
			logger.info("[" + getPasswayName() + txCode + "ReqePlain][" + tagId + "]" + JSON.toJSONString(request));

			// 3.
			request.setAccountNumber(ClientDecryptionUtil.encrypt(dspInv.getIdentifyNo()));
			request.process();
			logger.info("[" + getPasswayName() + txCode + "Reqe][" + tagId + "]" + request.getRequestPlainText());

			Tx2112Response response = this.doPost(tagId, txCode, request.getRequestMessage(), request.getRequestSignature(), "20", Tx2112Response.class);

			// 0001 参数校验失败
			// 2000 SUCCESS
			// 2001 系统内部错误
			// 2002 验证签名失败
			// 2003 解析报文错误
			// 2004 获取通道信息出错
			// 2005 无可用通道
			// 2006 机构交易无效
			// 2007 交易流水号重复
			// 2008 请求通道出错
			// 2009 交易异常
			// 3001 请求报文格式不正确
			// 3002 非法校验不通过
			// 3004 交易类型无效
			// 3005 未查询到结果
			// 4004 不正确的访问
			String code = response.getCode();
			String message = response.getMessage();

			respData.setRetCode(code);
			respData.setRetDesc(message);

			if ("2000".equals(code)) {
				respData.setStatus(EnumDspStatus.SUCCESS);
				respData.setPasswayOrderId(response.getTraceNo());

				// 验证状态: 20=匹配, 30=不匹配
				String verification = response.getVerification();
				if ("20".equals(verification)) {
					respData.setVerifyStatus("00");
				}
				else {
					respData.setVerifyStatus("02");
				}
			}
			else {
				respData.setStatus(EnumDspStatus.FAIL);
			}
		} catch (InterruptedIOException e) {
			respData.setStatus(EnumDspStatus.FAIL);
			String kindMessage = "通讯处理异常，状态未知";
			if (e instanceof ConnectionPoolTimeoutException) {
				kindMessage = "线路繁忙，请重新发起交易";
				respData.setRetCode("E001");
			}
			else if (e instanceof ConnectTimeoutException) {
				kindMessage = "网络繁忙，请重新发起交易";
				respData.setRetCode("E002");
			}
			else if (e instanceof SocketTimeoutException) {
				kindMessage = "通讯响应超时，状态未知";
				respData.setRetCode("E003");
			}
			else {
				kindMessage = "未知网络异常";
				respData.setRetCode("E004");
			}
			respData.setRetDesc(kindMessage);
		} catch (Exception e) {
			logger.error(e, e);
			respData.setStatus(EnumDspStatus.FAIL);
			respData.setRetCode("E900");
			respData.setRetDesc("未知系统异常，" + e.getMessage());
		}
		return respData;
	}

	/**
	 * 银行卡三要素验证[非精准]（姓名+身份证+银行卡号）根据姓名+身份证+银行卡号验证个人身份。中国金融认证中心China Financial Certification Authority CFCA 数据产品服务规范
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	public PasswayDspResponseData processC115(PasswayDspRequestData reqData) {
		DownDspInvoiceData dspInv = reqData.getInvoice();

		PasswayDspMerchantData setting = PasswayDspMerchants.get(dspInv.getChannel(), dspInv.getChannelAccNo());

		PasswayDspResponseData respData = new PasswayDspResponseData();
		try {
			// 1. 交易请求对象
			Tx2115Request request = new Tx2115Request();

			request.setInstitutionID(setting.getChannelAccNo());
			request.setTxSN(dspInv.getTransNo());
			request.setName(dspInv.getHolderName());
			request.setIdentificationType(dspInv.getIdentifyType());
			request.setIdentificationNumber(dspInv.getIdentifyNo());
			request.setAccountNumber(dspInv.getCardNo());
			request.setRemark(dspInv.getRemark());

			String tagId = System.currentTimeMillis() + "|" + dspInv.getTransNo();
			String txCode = "2115";
			logger.info("[" + getPasswayName() + txCode + "ReqePlain][" + tagId + "]" + JSON.toJSONString(request));

			// 2. 执行报文处理
			request.setIdentificationNumber(ClientDecryptionUtil.encrypt(dspInv.getIdentifyNo()));
			request.setAccountNumber(ClientDecryptionUtil.encrypt(dspInv.getCardNo()));
			request.process();

			logger.info("[" + getPasswayName() + txCode + "Reqe][" + tagId + "]" + request.getRequestPlainText());
			Tx2115Response response = this.doPost(tagId, txCode, request.getRequestMessage(), request.getRequestSignature(), "", Tx2115Response.class);

			// 0001 参数校验失败
			// 2000 SUCCESS
			// 2001 系统内部错误
			// 2002 验证签名失败
			// 2003 解析报文错误
			// 2004 获取通道信息出错
			// 2005 无可用通道
			// 2006 机构交易无效
			// 2007 交易流水号重复
			// 2008 请求通道出错
			// 2009 交易异常
			// 3001 请求报文格式不正确
			// 3002 非法校验不通过
			// 3004 交易类型无效
			// 3005 未查询到结果
			// 4004 不正确的访问
			String code = response.getCode();
			String message = response.getMessage();

			respData.setRetCode(code);
			respData.setRetDesc(message);

			if ("2000".equals(code)) {
				respData.setStatus(EnumDspStatus.SUCCESS);
				respData.setPasswayOrderId(response.getTraceNo());

				// 验证状态: 20=匹配, 30=不匹配
				String verification = response.getVerification();
				if ("20".equals(verification)) {
					respData.setVerifyStatus("00");
				}
				else {
					respData.setVerifyStatus("02");
				}
			}
			else {
				respData.setStatus(EnumDspStatus.FAIL);
			}
		} catch (InterruptedIOException e) {
			respData.setStatus(EnumDspStatus.FAIL);
			String kindMessage = "通讯处理异常，状态未知";
			if (e instanceof ConnectionPoolTimeoutException) {
				kindMessage = "线路繁忙，请重新发起交易";
				respData.setRetCode("E001");
			}
			else if (e instanceof ConnectTimeoutException) {
				kindMessage = "网络繁忙，请重新发起交易";
				respData.setRetCode("E002");
			}
			else if (e instanceof SocketTimeoutException) {
				kindMessage = "通讯响应超时，状态未知";
				respData.setRetCode("E003");
			}
			else {
				kindMessage = "未知网络异常";
				respData.setRetCode("E004");
			}
			respData.setRetDesc(kindMessage);
		} catch (Exception e) {
			logger.error(e, e);
			respData.setStatus(EnumDspStatus.FAIL);
			respData.setRetCode("E900");
			respData.setRetDesc("未知系统异常，" + e.getMessage());
		}
		return respData;
	}

	/**
	 * 银行卡四要素验证[非精准]（姓名+身份证+手机号+银行卡号）根据姓名+身份证+手机号银行卡号验证个人身份。
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	public PasswayDspResponseData processC116(PasswayDspRequestData reqData) {
		DownDspInvoiceData dspInv = reqData.getInvoice();

		PasswayDspMerchantData setting = PasswayDspMerchants.get(dspInv.getChannel(), dspInv.getChannelAccNo());

		PasswayDspResponseData respData = new PasswayDspResponseData();
		try {
			// 1. 交易请求对象
			Tx2116Request request = new Tx2116Request();

			request.setInstitutionID(setting.getChannelAccNo());
			request.setTxSN(dspInv.getTransNo());
			request.setName(dspInv.getHolderName());
			request.setIdentificationType(dspInv.getIdentifyType());
			request.setIdentificationNumber(dspInv.getIdentifyNo());
			request.setAccountNumber(dspInv.getCardNo());
			request.setPhoneNumber(dspInv.getMobile());
			request.setRemark(dspInv.getRemark());

			String tagId = System.currentTimeMillis() + "|" + dspInv.getTransNo();
			String txCode = "2116";
			logger.info("[" + getPasswayName() + txCode + "ReqePlain][" + tagId + "]" + JSON.toJSONString(request));

			// 2. 执行报文处理
			request.setIdentificationNumber(ClientDecryptionUtil.encrypt(dspInv.getIdentifyNo()));
			request.setAccountNumber(ClientDecryptionUtil.encrypt(dspInv.getCardNo()));
			request.setPhoneNumber(ClientDecryptionUtil.encrypt(dspInv.getMobile()));
			request.process();

			logger.info("[" + getPasswayName() + txCode + "Reqe][" + tagId + "]" + request.getRequestPlainText());
			Tx2116Response response = this.doPost(tagId, txCode, request.getRequestMessage(), request.getRequestSignature(), "", Tx2116Response.class);

			// 0001 参数校验失败
			// 2000 SUCCESS
			// 2001 系统内部错误
			// 2002 验证签名失败
			// 2003 解析报文错误
			// 2004 获取通道信息出错
			// 2005 无可用通道
			// 2006 机构交易无效
			// 2007 交易流水号重复
			// 2008 请求通道出错
			// 2009 交易异常
			// 3001 请求报文格式不正确
			// 3002 非法校验不通过
			// 3004 交易类型无效
			// 3005 未查询到结果
			// 4004 不正确的访问
			String code = response.getCode();
			String message = response.getMessage();

			respData.setRetCode(code);
			respData.setRetDesc(message);

			if ("2000".equals(code)) {
				respData.setStatus(EnumDspStatus.SUCCESS);
				respData.setPasswayOrderId(response.getTraceNo());

				// 验证状态: 20=匹配, 30=不匹配
				String verification = response.getVerification();
				if ("20".equals(verification)) {
					respData.setVerifyStatus("00");
				}
				else {
					respData.setVerifyStatus("02");
				}
			}
			else {
				respData.setStatus(EnumDspStatus.FAIL);
			}
		} catch (InterruptedIOException e) {
			respData.setStatus(EnumDspStatus.FAIL);
			String kindMessage = "通讯处理异常，状态未知";
			if (e instanceof ConnectionPoolTimeoutException) {
				kindMessage = "线路繁忙，请重新发起交易";
				respData.setRetCode("E001");
			}
			else if (e instanceof ConnectTimeoutException) {
				kindMessage = "网络繁忙，请重新发起交易";
				respData.setRetCode("E002");
			}
			else if (e instanceof SocketTimeoutException) {
				kindMessage = "通讯响应超时，状态未知";
				respData.setRetCode("E003");
			}
			else {
				kindMessage = "未知网络异常";
				respData.setRetCode("E004");
			}
			respData.setRetDesc(kindMessage);
		} catch (Exception e) {
			logger.error(e, e);
			respData.setStatus(EnumDspStatus.FAIL);
			respData.setRetCode("E900");
			respData.setRetDesc("未知系统异常，" + e.getMessage());
		}
		return respData;
	}

	/**
	 * 运营商三要素验证（姓名+身份证+手机号）。
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	public PasswayDspResponseData processC123(PasswayDspRequestData reqData) {
		DownDspInvoiceData dspInv = reqData.getInvoice();

		PasswayDspMerchantData setting = PasswayDspMerchants.get(dspInv.getChannel(), dspInv.getChannelAccNo());

		PasswayDspResponseData respData = new PasswayDspResponseData();
		try {
			// 1. 交易请求对象
			Tx2123Request request = new Tx2123Request();

			request.setInstitutionID(setting.getChannelAccNo());
			request.setTxSN(dspInv.getTransNo());
			request.setName(dspInv.getHolderName());
			request.setIdentificationType(dspInv.getIdentifyType());
			request.setIdentificationNumber(dspInv.getIdentifyNo());
			request.setPhoneNumber(dspInv.getMobile());
			request.setRemark(dspInv.getRemark());

			String tagId = System.currentTimeMillis() + "|" + dspInv.getTransNo();
			String txCode = "2123";
			logger.info("[" + getPasswayName() + txCode + "ReqePlain][" + tagId + "]" + JSON.toJSONString(request));

			// 2. 执行报文处理
			request.setIdentificationNumber(ClientDecryptionUtil.encrypt(dspInv.getIdentifyNo()));
			request.setPhoneNumber(ClientDecryptionUtil.encrypt(dspInv.getMobile()));
			request.process();

			logger.info("[" + getPasswayName() + txCode + "Reqe][" + tagId + "]" + request.getRequestPlainText());
			Tx2116Response response = this.doPost(tagId, txCode, request.getRequestMessage(), request.getRequestSignature(), "", Tx2116Response.class);

			// 0001 参数校验失败
			// 2000 SUCCESS
			// 2001 系统内部错误
			// 2002 验证签名失败
			// 2003 解析报文错误
			// 2004 获取通道信息出错
			// 2005 无可用通道
			// 2006 机构交易无效
			// 2007 交易流水号重复
			// 2008 请求通道出错
			// 2009 交易异常
			// 3001 请求报文格式不正确
			// 3002 非法校验不通过
			// 3004 交易类型无效
			// 3005 未查询到结果
			// 4004 不正确的访问
			String code = response.getCode();
			String message = response.getMessage();

			respData.setRetCode(code);
			respData.setRetDesc(message);

			if ("2000".equals(code)) {
				respData.setStatus(EnumDspStatus.SUCCESS);
				respData.setPasswayOrderId(response.getTraceNo());

				// 验证状态: 20=匹配, 30=不匹配
				String verification = response.getVerification();
				if ("20".equals(verification)) {
					respData.setVerifyStatus("00");
				}
				else {
					respData.setVerifyStatus("02");
				}
			}
			else {
				respData.setStatus(EnumDspStatus.FAIL);
			}
		} catch (InterruptedIOException e) {
			respData.setStatus(EnumDspStatus.FAIL);
			String kindMessage = "通讯处理异常，状态未知";
			if (e instanceof ConnectionPoolTimeoutException) {
				kindMessage = "线路繁忙，请重新发起交易";
				respData.setRetCode("E001");
			}
			else if (e instanceof ConnectTimeoutException) {
				kindMessage = "网络繁忙，请重新发起交易";
				respData.setRetCode("E002");
			}
			else if (e instanceof SocketTimeoutException) {
				kindMessage = "通讯响应超时，状态未知";
				respData.setRetCode("E003");
			}
			else {
				kindMessage = "未知网络异常";
				respData.setRetCode("E004");
			}
			respData.setRetDesc(kindMessage);
		} catch (Exception e) {
			logger.error(e, e);
			respData.setStatus(EnumDspStatus.FAIL);
			respData.setRetCode("E900");
			respData.setRetDesc("未知系统异常，" + e.getMessage());
		}
		return respData;
	}

	/**
	 * 活体验证（姓名+身份证+图集）。
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	public PasswayDspResponseData processC301(PasswayDspRequestData reqData) {
		DownDspInvoiceData dspInv = reqData.getInvoice();

		PasswayDspMerchantData setting = PasswayDspMerchants.get(dspInv.getChannel(), dspInv.getChannelAccNo());

		PasswayDspResponseData respData = new PasswayDspResponseData();
		try {
			// 1. 交易请求对象
			Tx2301Request request = new Tx2301Request();

			request.setInstitutionID(setting.getChannelAccNo());
			request.setTxSN(dspInv.getTransNo());
			request.setName(dspInv.getHolderName());
			request.setIdentificationNumber(dspInv.getIdentifyNo());
			request.setRemark(dspInv.getRemark());
			request.setDelta(dspInv.getResv1());

			String[] resv2Arr = dspInv.getResv2().split(",");
			// ImageBest R NOT NULL 质量最佳图片文件 ID（先通过接口 3201 上传图片，获取文件 ID）
			request.setImageBest(resv2Arr[0]);
			// ImageEnv R NOT NULL 假脸判定图片文件 ID（先通过接口3201 上传图片，获取文件 ID）
			request.setImageEnv(resv2Arr[1]);

			String tagId = System.currentTimeMillis() + "|" + dspInv.getTransNo();
			String txCode = "2301";
			logger.info("[" + getPasswayName() + txCode + "ReqePlain][" + tagId + "]" + JSON.toJSONString(request));

			// 2. 执行报文处理
			request.setIdentificationNumber(ClientDecryptionUtil.encrypt(dspInv.getIdentifyNo()));
			request.process();

			logger.info("[" + getPasswayName() + txCode + "Reqe][" + tagId + "]" + request.getRequestPlainText());
			Tx2116Response response = this.doPost(tagId, txCode, request.getRequestMessage(), request.getRequestSignature(), "", Tx2116Response.class);

			// 0001 参数校验失败
			// 2000 SUCCESS
			// 2001 系统内部错误
			// 2002 验证签名失败
			// 2003 解析报文错误
			// 2004 获取通道信息出错
			// 2005 无可用通道
			// 2006 机构交易无效
			// 2007 交易流水号重复
			// 2008 请求通道出错
			// 2009 交易异常
			// 3001 请求报文格式不正确
			// 3002 非法校验不通过
			// 3004 交易类型无效
			// 3005 未查询到结果
			// 4004 不正确的访问
			String code = response.getCode();
			String message = response.getMessage();

			respData.setRetCode(code);
			respData.setRetDesc(message);

			if ("2000".equals(code)) {
				respData.setStatus(EnumDspStatus.SUCCESS);
				respData.setPasswayOrderId(response.getTraceNo());

				// 验证状态: 20=匹配, 30=不匹配
				String verification = response.getVerification();
				if ("20".equals(verification)) {
					respData.setVerifyStatus("00");
				}
				else {
					respData.setVerifyStatus("02");
				}
			}
			else {
				respData.setStatus(EnumDspStatus.FAIL);
			}
		} catch (InterruptedIOException e) {
			respData.setStatus(EnumDspStatus.FAIL);
			String kindMessage = "通讯处理异常，状态未知";
			if (e instanceof ConnectionPoolTimeoutException) {
				kindMessage = "线路繁忙，请重新发起交易";
				respData.setRetCode("E001");
			}
			else if (e instanceof ConnectTimeoutException) {
				kindMessage = "网络繁忙，请重新发起交易";
				respData.setRetCode("E002");
			}
			else if (e instanceof SocketTimeoutException) {
				kindMessage = "通讯响应超时，状态未知";
				respData.setRetCode("E003");
			}
			else {
				kindMessage = "未知网络异常";
				respData.setRetCode("E004");
			}
			respData.setRetDesc(kindMessage);
		} catch (Exception e) {
			logger.error(e, e);
			respData.setStatus(EnumDspStatus.FAIL);
			respData.setRetCode("E900");
			respData.setRetDesc("未知系统异常，" + e.getMessage());
		}
		return respData;
	}

	private <T extends TxBaseResponse> T doPost(String tagId, String txCode, String message, String signature, String flag, Class<T> respClass) throws IOException {
		Date startTime = new Date();

		T respObj = null;
		try {
			TxMessenger txMessenger = new TxMessenger();
			String[] respMsg = null;

			// Flag=10:cmb, 20:paymentAccount
			if ("10".equals(flag)) {
				respMsg = txMessenger.send(message, signature, connectTimeoutLimit, readTimeoutLimit, DSPInstitutionEnvironment.txURL);
			}
			//
			else if ("20".equals(flag)) {
				respMsg = txMessenger.send(message, signature, connectTimeoutLimit, readTimeoutLimit, DSPInstitutionEnvironment.fileURL);
			}
			//
			else {
				respMsg = txMessenger.send(message, signature, connectTimeoutLimit, readTimeoutLimit);
			}
			System.out.println(JSON.toJSONString(respMsg));
			String plainText = new String(Base64.decode(respMsg[0]), "UTF-8");
			logger.info("[" + getPasswayName() + txCode + "Resp][" + tagId + "][" + ((int) ((new Date().getTime() - startTime.getTime()) / 1000.0)) + "]" + plainText + "[<->]" + respMsg[1]);

			respObj = respClass.getConstructor(String.class, String.class).newInstance(respMsg[0], respMsg[1]);

			return respObj;

		} catch (IOException e) {
			logger.warn("[" + getPasswayName() + txCode + "Err][" + tagId + "][" + ((int) ((new Date().getTime() - startTime.getTime()) / 1000.0)) + "]" + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("[" + getPasswayName() + txCode + "Err][" + tagId + "][" + ((int) ((new Date().getTime() - startTime.getTime()) / 1000.0)) + "]" + e.getMessage(), e);
			throw new IOException(e);
		}
	}
}
