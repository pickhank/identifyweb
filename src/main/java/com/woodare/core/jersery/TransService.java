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
package com.woodare.core.jersery;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woodare.core.data.DownRequestData;
import com.woodare.core.data.DownResponseData;
import com.woodare.core.util.AesUtils;
import com.woodare.core.util.RandomUtils;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.exception.UnauthorityWooException;
import com.woodare.framework.utils.JsonUtils;
import com.woodare.framework.utils.Md5Utils;
import com.woodare.framework.utils.RequestUtil;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.constant.EnumError;
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;

/**
 * ClassName: ApiService
 * 
 * @description
 * @author luke
 * @Date 2018/02/26
 */
@Component
@Path("/trans")
public class TransService {
	private Log log = LogFactory.getLog(TransService.class);

	@Autowired
	private DownJerseyManager downJerseyManager;

	public TransService() {
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{module}/{action}")
	public Response doVerify(@Context HttpServletRequest request, @Context HttpServletResponse httpResponse, String requestString, @PathParam("module") String module, @PathParam("action") String action) {

		String oriThreadName = Thread.currentThread().getName();
		String newThreadName = SDFFactory.getLogId();
		Thread.currentThread().setName(newThreadName);

		ResponseBuilder builder = null;
		String now = String.valueOf(System.currentTimeMillis()) + RandomUtils.randomChoose(100);

		try {
			log.debug(String.format("currentThread.setName[%s]->[%s]", oriThreadName, newThreadName));
			log.info(String.format("HTTP Request with [%s] [module = %s, action = %s, requestString = %s]", now, module, action, requestString));

			DownRequestData requestData = JsonUtils.fromJson(requestString, DownRequestData.class);
			if (StringUtils.isEmpty(requestData.getMchNo())) {
				throw new MessageWooException("商户号[mchNo]不能为空", EnumError.ERR_2001);
			}
			if (StringUtils.isEmpty(requestData.getSign())) {
				throw new MessageWooException("签名[sign]不能为空", EnumError.ERR_2001);
			}
			if (StringUtils.isEmpty(requestData.getPayload())) {
				throw new MessageWooException("密文[payload]不能为空", EnumError.ERR_2001);
			}
			DownMerchantData downMerchant = DownMerchants.getByCode(requestData.getMchNo());
			if (downMerchant == null) {
				throw new MessageWooException("商户不存在或已停用", EnumError.ERR_3001);
			}
			String signKey = downMerchant.getSignKey();
			String enPayload = requestData.getPayload();
			String sign = requestData.getSign();
			String sign2 = Md5Utils.md5(enPayload + signKey, "UTF-8").toUpperCase();
			if (!sign2.equals(sign)) {
				throw new MessageWooException("验签失败", EnumError.ERR_9000);
			}
			String decodePayload = null;
			String encKey = downMerchant.getEncKey();
			try {
				decodePayload = AesUtils.decrypt(enPayload, encKey);
				log.info(String.format("HTTP Request with [%s|%s] [module = %s, action = %s, decodepayload = %s]", now, RequestUtil.getIpAddress(request), module, action, decodePayload));
			} catch (UnsupportedEncodingException e) {
				throw new MessageWooException("密文解密失败", EnumError.ERR_9001);
			}
			Object result = downJerseyManager.execute(request, "trans" + module.substring(0, 1).toUpperCase() + module.substring(1), action, decodePayload);
			String resultStr = JsonUtils.toJson(result);
			String encodeResultStr = AesUtils.encrypt(resultStr, encKey);
			DownResponseData response = new DownResponseData();
			response.setPayload(encodeResultStr);
			response.setState(EResponseState.Successful);
			response.setCode(0);
			response.setMchNo(downMerchant.getMchNo());
			response.setSign(Md5Utils.md5(encodeResultStr + signKey, "UTF-8").toUpperCase());
			builder = Response.ok(JsonUtils.toJson(response));

			log.info("HTTP Response: [" + now + "] " + JsonUtils.toJson(resultStr));
		} catch (MessageWooException e) {
			DownResponseData response = new DownResponseData();
			response.setState(EResponseState.Failed);
			Integer code = e.getCode();
			if (code == null) {
				code = EnumError.ERR_9003.getCode();
			}
			response.setCode(code);
			response.setMessage(e.getMessage());
			response.setSign("");
			builder = Response.ok(JsonUtils.toJson(response));
			if (code != 120000) {
				log.info("HTTP Response: [" + now + "] " + JsonUtils.toJson(response));
			}
		} catch (UnauthorityWooException e) {
			DownResponseData response = new DownResponseData();
			response.setState(EResponseState.Unauthority);
			response.setCode(8000);
			response.setSign("");
			builder = Response.ok(JsonUtils.toJson(response));
			log.info("HTTP Response: [" + now + "] " + JsonUtils.toJson(response));
		} catch (Exception e) {
			log.error(String.format("Execute service (%s) error.", module), e);
			DownResponseData response = new DownResponseData();
			response.setState(EResponseState.Failed);
			response.setCode(EnumError.ERR_9003.getCode());
			response.setSign("");
			response.setMessage("系统错误");
			builder = Response.ok(JsonUtils.toJson(response));
			log.info("HTTP Response: [" + now + "] " + JsonUtils.toJson(response));
		} finally {
			Thread.currentThread().setName(oriThreadName);
		}

		return builder.build();
	}
}
