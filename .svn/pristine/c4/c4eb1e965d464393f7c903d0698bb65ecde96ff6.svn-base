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
package com.woodare.template.jersery.webservice.busi;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.thirdparty.passway._data.PasswayDspRequestData;
import com.thirdparty.passway._data.PasswayDspResponseData;
import com.woodare.core.data.DownResponseData;
import com.woodare.core.util.AesUtils;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.utils.JsonUtils;
import com.woodare.framework.utils.Md5Utils;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.constant.EnumError;
import com.woodare.template.egw.base.IDspCardPassway;
import com.woodare.template.egw.base.PasswayEgwHelper;
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.jersery.servicedata.downdspinvoice.DownDspInvoiceServiceData;
import com.woodare.template.jersery.webservice.busi.base.IDownDspInvoiceService;
import com.woodare.template.jersery.webservice.busi.base.IDownMerchantBalanceService;
import com.woodare.template.jpa.model.DownDspInvoice;
import com.woodare.template.jpa.model.NotifyRecord;
import com.woodare.template.jpa.model.data.EnumDspMode;
import com.woodare.template.jpa.model.data.EnumDspStatus;
import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceData;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.persistence.IDownDspInvoiceDAO;

/**
 * ClassName: DownDspInvoiceService
 * 
 * @description
 * @author Luke
 * @Date Sep 15, 2018
 */
@Service(value = "downDspInvoiceService")
public class DownDspInvoiceService extends AbstractInvoiceService implements IDownDspInvoiceService {
	private Log log = LogFactory.getLog(DownDspInvoiceService.class);

	@Autowired
	private IDownDspInvoiceDAO downDspInvoiceDAO;

	@Autowired
	private IDownMerchantBalanceService downMerchantBalanceService;

	/**
	 * @param reqData
	 * @param downMerchant
	 * @param agent
	 * @return
	 * @throws Exception
	 */
	@Override
	public DownDspInvoice makeOrder(DownDspInvoiceServiceData reqData, DownMerchantData downMerchant, DownAgentData agent) throws Exception {
		DownDspInvoice model = new DownDspInvoice();

		Date nowTime = new Date();
		// 接口版本号
		model.setVersionNo(reqData.getVersionNo());
		// 鉴权类型
		model.setDspMode(reqData.getDspMode());
		// 商户号
		model.setMchNo(downMerchant.getMchNo());
		// 商户名
		model.setMchName(downMerchant.getName());
		// 平台流水号
		model.setTransNo(SDFFactory.getDateOrderNo());
		// 平台日期yyyyMMdd
		model.setTransDate(SDFFactory.DATE.format(nowTime));
		// 下游交易流水号
		model.setTradeNo(reqData.getTradeNo());
		// 订单时间, 格式：yyyyMMddHHmmss
		model.setOrderDate(reqData.getOrderDate());
		// 代理商
		model.setAgentNo(agent != null ? agent.getAgentNo() : null);
		// 通道标识
		model.setChannel(reqData.getChannel());
		// 通道账户编号
		model.setChannelAccNo(reqData.getChannelAccNo());
		// 通道账户名称
		model.setChannelAccName(reqData.getChannelAccName());
		// 单笔费，单位：分
		model.setAddFeeAmt(reqData.getAddFeeAmt());
		// 卡姓名
		model.setHolderName(reqData.getHolderName());
		// 手机号
		model.setMobile(reqData.getMobile());
		// 证件类型
		model.setIdentifyType(reqData.getIdentifyType());
		// 证件号
		model.setIdentifyNo(reqData.getIdentifyNo());
		// 卡号
		model.setCardNo(reqData.getCardNo());
		// 卡CVV
		model.setCardCvn(reqData.getCardCvn());
		// 卡有效期-MMYY
		model.setCardExpDate(reqData.getCardExpDate());
		// 备注信息
		model.setRemark(reqData.getRemark());
		// 商户交易手续费
		model.setMchPayFee(reqData.getMchPayFee());
		// 渠道交易手续费
		model.setChannelPayFee(reqData.getChannelPayFee());
		// 代理商利润
		model.setAgentProfit(reqData.getAgentProfit());
		// 平台利润
		model.setProfit(reqData.getProfit());
		// 平台利润-额外
		model.setXtraProfit(reqData.getXtraProfit());
		// 已提交上游
		model.setSentUp(false);
		// 上游流水号 - upTransNo
		// 交易状态, 00=处理成功，计费、01=处理中、02=处理失败，不计费
		model.setStatus(EnumDspStatus.PROCESSING);
		// 交易状态描述
		model.setStatusDesc("正在处理");
		// 状态更新时间
		model.setStatusChgDate(new Date());
		// 暂存下来，待用户实际访问页面时，再进行渠道下单操作
		this.downDspInvoiceDAO.saveForce(model);

		IDspCardPassway passwayEgw = PasswayEgwHelper.getService(model.getChannel(), IDspCardPassway.class);

		PasswayDspRequestData request = new PasswayDspRequestData();
		request.setInvoice(SaftyBeanUtils.cloneTo(model, DownDspInvoiceData.class));

		EnumDspMode dspMode = reqData.getDspMode();

		PasswayDspResponseData response = null;
		try {
			if (dspMode == EnumDspMode.C102) {
				response = passwayEgw.processC102(request);
			}
			else if (dspMode == EnumDspMode.C112) {
				response = passwayEgw.processC112(request);
			}
			else if (dspMode == EnumDspMode.C115) {
				response = passwayEgw.processC115(request);
			}
			else if (dspMode == EnumDspMode.C116) {
				response = passwayEgw.processC116(request);
			}
			else if (dspMode == EnumDspMode.C123) {
				response = passwayEgw.processC123(request);
			}
			else {
				// TODO: 处理成未实现异常，不可能达到的代码
				throw new MessageWooException("不支持的交易类型", EnumError.ERR_8001);
			}
		} catch (MessageWooException e) {
			response = new PasswayDspResponseData();
			response.setStatus(EnumDspStatus.FAIL);
			response.setRetCode(e.getCode().toString());
			response.setRetDesc(e.getMessage());
		} catch (Exception e) {
			log.error("UncatchException[" + reqData.getTransNo() + "]" + e.getMessage(), e);
			response = new PasswayDspResponseData();
			response.setStatus(EnumDspStatus.FAIL);
			response.setRetCode(EnumError.ERR_9003.getCode().toString());
			response.setRetDesc(e.getMessage());
		}

		model = downMerchantBalanceService.handleDspResult(model, response);

		return model;
	}

	/**
	 * 
	 */
	@Override
	public String getDownNotifyData(NotifyRecord record) throws Exception {
		DownDspInvoice inv = downDspInvoiceDAO.findOne(record.getId());

		DownDspInvoiceServiceData reqData = new DownDspInvoiceServiceData();
		reqData.setVersionNo(1);

		DownMerchantData downMerchant = DownMerchants.getByCode(inv.getMchNo());
		String signKey = downMerchant.getSignKey();
		String encKey = downMerchant.getEncKey();

		String resultStr = JSON.toJSONString(toServiceData(reqData, inv));

		String encodeResultStr = AesUtils.encrypt(resultStr, encKey);
		DownResponseData response = new DownResponseData();
		response.setPayload(encodeResultStr);
		response.setState(EResponseState.Successful);
		response.setCode(0);
		response.setMchNo(downMerchant.getMchNo());
		response.setSign(Md5Utils.md5(encodeResultStr + signKey, "UTF-8").toUpperCase());

		return JsonUtils.toJson(response);
	}

	/**
	 * 拼装返回数据结构
	 * 
	 * @param model
	 * @return
	 */
	public static DownDspInvoiceServiceData toServiceData(DownDspInvoiceServiceData reqData, DownDspInvoice model) {
		DownDspInvoiceServiceData isd = new DownDspInvoiceServiceData();

		isd.setVersionNo(reqData.getVersionNo());
		isd.setMchNo(model.getMchNo());
		isd.setMchName(model.getMchName());
		isd.setDspMode(model.getDspMode());
		isd.setTransNo(model.getTransNo());
		isd.setTradeNo(model.getTradeNo());
		isd.setOrderDate(model.getOrderDate());
		isd.setMchPayFee(new BigDecimal("0.00"));

		isd.setStatus(model.getStatus());
		isd.setStatusDesc(model.getStatusDesc());
		if (EnumDspStatus.SUCCESS.equals(model.getStatus())) {
			isd.setMchPayFee(model.getMchPayFee());
			isd.setVerifyStatus(model.getVerifyStatus());
		}

		return isd;
	}
}