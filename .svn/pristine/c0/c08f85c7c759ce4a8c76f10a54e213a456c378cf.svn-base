package com.thirdparty.passway.dsp.cpcn;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;

import com.alibaba.fastjson.JSON;
import com.thirdparty.passway._data.PasswayDspRequestData;
import com.thirdparty.passway._data.PasswayDspResponseData;
import com.thirdparty.passway.dsp.cpcn.util.CpcnDspConstant;
import com.woodare.framework.exception.AbstractWooException;
import com.woodare.template.helper.cache.PasswayDspMerchants;
import com.woodare.template.jersery.servicedata.downdspinvoice.DownDspImageFileData;
import com.woodare.template.jpa.model.data.EnumDspStatus;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceData;
import com.woodare.template.jpa.persistence.data.passwaydspmerchant.PasswayDspMerchantData;

import cpcn.dsp.institution.api.codec.Base64;
import cpcn.dsp.institution.api.system.DSPInstitutionEnvironment;
import cpcn.dsp.institution.api.system.TxMessenger;
import cpcn.dsp.institution.api.tx.TxBaseResponse;
import cpcn.dsp.institution.api.tx.personalinfo.Tx3201Request;
import cpcn.dsp.institution.api.tx.personalinfo.Tx3201Response;
import cpcn.dsp.institution.api.vo.FileResp;
import cpcn.dsp.institution.api.vo.ImageFile;

/**
 * @author Luke
 */
public class CpcnFileDspApi {
	private static final Log logger = LogFactory.getLog(CpcnFileDspApi.class);

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
	 * 3201-文件批量上传
	 * 
	 * @param reqData
	 * @return
	 * @throws AbstractWooException
	 */
	public PasswayDspResponseData processD3201(PasswayDspRequestData reqData) {
		DownDspInvoiceData dspInv = reqData.getInvoice();

		PasswayDspMerchantData setting = PasswayDspMerchants.get(dspInv.getChannel(), dspInv.getChannelAccNo());

		PasswayDspResponseData respData = new PasswayDspResponseData();
		try {
			// 1. 交易请求对象
			Tx3201Request request = new Tx3201Request();
			// TxCode R NOT NULL 交易类型
			request.setTxSN(dspInv.getTransNo());
			request.setInstitutionID(setting.getChannelAccNo());
			request.setRemark(dspInv.getRemark());

			List<DownDspImageFileData> localFiles = reqData.getLocalFiles();
			List<ImageFile> files = new ArrayList<ImageFile>();
			for (DownDspImageFileData localFile : localFiles) {
				ImageFile file = new ImageFile();
				file.setFileName(localFile.getFileName());
				file.setFileContent(localFile.getFileContent());
				files.add(file);
			}
			request.setFiles(files);

			// 2.
			String tagId = System.currentTimeMillis() + "|" + dspInv.getTransNo();
			String txCode = "3201";

			// 3. 执行报文处理
			request.process();
			logger.info("[" + getPasswayName() + txCode + "Reqe][" + tagId + "]" + request.getRequestPlainText());

			Tx3201Response response = this.doPost(tagId, txCode, request.getRequestMessage(), request.getRequestSignature(), "20", Tx3201Response.class);

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

				List<FileResp> respFiles = response.getFiles();
				List<DownDspImageFileData> savedLocalFiles = new ArrayList<DownDspImageFileData>();

				for (FileResp respFile : respFiles) {
					DownDspImageFileData saveLocalFile = new DownDspImageFileData();
					saveLocalFile.setFileName(respFile.getFileName());
					saveLocalFile.setFileId(respFile.getFileID());
					savedLocalFiles.add(saveLocalFile);
				}
				
				respData.setRespFiles(savedLocalFiles);
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
