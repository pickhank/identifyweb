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
package com.woodare.template.jersery.webservice;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.woodare.core.base.AbstractBusiWebService;
import com.woodare.core.util.SDFFactory;
import com.woodare.framework.exception.MessageWooException;
import com.woodare.framework.utils.SysProperties;
import com.woodare.template.constant.EnumError;
import com.woodare.template.helper.cache.DownAgents;
import com.woodare.template.helper.cache.DownDspInvoiceRoutes;
import com.woodare.template.helper.cache.DownDspProducts;
import com.woodare.template.helper.cache.DownMerchantProducts;
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.helper.cache.PasswayDspMerchants;
import com.woodare.template.jersery.servicedata.downdspinvoice.DownDspInvoiceServiceData;
import com.woodare.template.jersery.webservice.busi.DownDspInvoiceService;
import com.woodare.template.jersery.webservice.busi.base.IDownDspInvoiceService;
import com.woodare.template.jersery.webservice.utils.TransExpireSet;
import com.woodare.template.jersery.webservice.utils.ValidHelper;
import com.woodare.template.jpa.model.DownDspInvoice;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumDspFieldType;
import com.woodare.template.jpa.model.data.EnumDspMode;
import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;
import com.woodare.template.jpa.persistence.data.downdspinvoiceroute.DownDspInvoiceRouteData;
import com.woodare.template.jpa.persistence.data.downdspproduct.DownDspProductData;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.data.downmerchantproduct.DownMerchantProductData;
import com.woodare.template.jpa.persistence.persistence.IDownDspInvoiceDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;
import com.woodare.template.web.viewdata.passwaydspmerchant.PasswayDspMerchantFeeData;
import com.woodare.template.web.viewdata.passwaydspmerchant.PasswayDspMerchantViewData;

/**
 * ClassName: 鉴权交易接口
 * 
 * @author luke
 * @Date 2018/09/15
 */
@Service
@Scope("request")
public class TransDspWebService extends AbstractBusiWebService {
	private static TransExpireSet<String> uniquedIds = new TransExpireSet<String>(10);

	@Autowired
	private IDownDspInvoiceDAO downDspInvoiceDAO;

	@Autowired
	private IDownDspInvoiceService downDspInvoiceService;

	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;

	/**
	 * 鉴权接口
	 * 
	 * @param reqData
	 * @return
	 * @throws Exception
	 */
	public DownDspInvoiceServiceData makeOrder(DownDspInvoiceServiceData reqData) throws Exception {
		// 【1】基本参数信息效验
		ValidHelper.notNull(reqData, "解密失败，请求参数为空", EnumError.ERR_9001);

		ValidHelper.notNull(reqData.getVersionNo(), "版本号[version]不能为空", EnumError.ERR_2001);
		if (reqData.getVersionNo() != 1) {
			throw new MessageWooException("传入版本号[version]信息有误", EnumError.ERR_2001);
		}
		ValidHelper.notEmpty(reqData.getMchNo(), "机构号[mchNo]不能为空", EnumError.ERR_2001);
		if (reqData.getMchNo().length() > 20) {
			throw new MessageWooException("机构号[mchNo]值非法", EnumError.ERR_2001);
		}
		ValidHelper.notEmpty(reqData.getTradeNo(), "订单号[tradeNo]不能为空", EnumError.ERR_2001);
		if (reqData.getTradeNo().length() > 50) {
			throw new MessageWooException("订单号[tradeNo]超过允许最大长度50", EnumError.ERR_2001);
		}
		ValidHelper.notEmpty(reqData.getOrderDate(), "下单时间[orderDate]不能为空， 格式为yyyyMMddHHmmss", EnumError.ERR_2001);
		ValidHelper.validDate(reqData.getOrderDate(), SDFFactory.DATETIME, "下单时间[orderDate]格式不正确， 格式为yyyyMMddHHmmss", EnumError.ERR_2001);

		// 验证模式类型
		EnumDspMode dspMode = reqData.getDspMode();
		if (dspMode.isRequired(EnumDspFieldType.Name)) {
			ValidHelper.notEmpty(reqData.getHolderName(), "姓名[holderName]不能为空 ", EnumError.ERR_2001);
			if (!reqData.getHolderName().equals(reqData.getHolderName().trim())) {
				throw new MessageWooException("姓名[holderName]格式错误，如空格", EnumError.ERR_2001);
			}
			else if (reqData.getHolderName().length() > 20) {
				throw new MessageWooException("姓名[holderName]超过允许最大长度20", EnumError.ERR_2001);
			}
		}
		if (dspMode.isRequired(EnumDspFieldType.CardNo)) {
			ValidHelper.notEmpty(reqData.getCardNo(), "卡号[cardNo]不能为空 ", EnumError.ERR_2001);
			if (!reqData.getCardNo().equals(reqData.getCardNo().trim())) {
				throw new MessageWooException("卡号[cardNo]格式错误，含空格", EnumError.ERR_2001);
			}
			else if (!reqData.getCardNo().equals(reqData.getCardNo().replaceAll("[^0-9]", ""))) {
				throw new MessageWooException("卡号[cardNo]格式错误，含非法字符", EnumError.ERR_2001);
			}
			else if (reqData.getCardNo().length() > 30) {
				throw new MessageWooException("卡号[cardNo]超过允许最大长度30", EnumError.ERR_2001);
			}
		}
		if (dspMode.isRequired(EnumDspFieldType.Mobile)) {
			ValidHelper.notEmpty(reqData.getMobile(), "手机号[mobile]不能为空 ", EnumError.ERR_2001);
			if (!reqData.getMobile().equals(reqData.getMobile().trim())) {
				throw new MessageWooException("手机号[mobile]格式错误，含空格", EnumError.ERR_2001);
			}
			else if (!reqData.getMobile().equals(reqData.getMobile().replaceAll("[^0-9]", "")) || reqData.getMobile().length() != 11) {
				throw new MessageWooException("手机号[mobile]格式错误，含非法字符", EnumError.ERR_2001);
			}
		}
		if (dspMode.isRequired(EnumDspFieldType.IdentifyCode)) {
			ValidHelper.notEmpty(reqData.getIdentifyType(), "证件类型[identifyType]不能为空 ", EnumError.ERR_2001);

			ValidHelper.notEmpty(reqData.getIdentifyNo(), "证件号[identifyNo]不能为空 ", EnumError.ERR_2001);
			if (!reqData.getIdentifyNo().equals(reqData.getIdentifyNo().trim())) {
				throw new MessageWooException("证件号[identifyNo]格式错误，含空格", EnumError.ERR_2001);
			}
			else if (!reqData.getIdentifyNo().equals(reqData.getIdentifyNo().replaceAll("[^0-9Xx]", ""))) {
				throw new MessageWooException("证件号[identifyNo]格式错误，含非法字符", EnumError.ERR_2001);
			}
			else if (reqData.getIdentifyNo().length() > 40) {
				throw new MessageWooException("证件号[identifyNo]超过允许最大长度40", EnumError.ERR_2001);
			}
		}
		if (dspMode.isRequired(EnumDspFieldType.CVV)) {
			ValidHelper.notEmpty(reqData.getCardCvn(), "CVV[cardCvn]不能为空 ", EnumError.ERR_2001);
			if (!reqData.getCardCvn().equals(reqData.getCardCvn().trim())) {
				throw new MessageWooException("CVV[cardCvn]格式错误，含空格", EnumError.ERR_2001);
			}
			else if (!reqData.getCardCvn().equals(reqData.getCardCvn().replaceAll("[^0-9]", ""))) {
				throw new MessageWooException("CVV[cardCvn]格式错误，含非法字符", EnumError.ERR_2001);
			}
			else if (reqData.getCardCvn().length() > 10) {
				throw new MessageWooException("CVV[cardCvn]超过允许最大长度10", EnumError.ERR_2001);
			}
		}
		if (dspMode.isRequired(EnumDspFieldType.ExpiredDate)) {
			ValidHelper.notEmpty(reqData.getCardExpDate(), "卡有效期[cardExpDate]不能为空 ", EnumError.ERR_2001);
			if (!reqData.getCardExpDate().equals(reqData.getCardExpDate().trim())) {
				throw new MessageWooException("卡有效期[cardExpDate]格式错误，含空格", EnumError.ERR_2001);
			}
			else if (!reqData.getCardExpDate().equals(reqData.getCardExpDate().replaceAll("[^0-9]", ""))) {
				throw new MessageWooException("卡有效期[cardExpDate]格式错误，含非法字符", EnumError.ERR_2001);
			}
			else if (reqData.getCardExpDate().length() != 4) {
				throw new MessageWooException("卡有效期[cardExpDate]长度异常", EnumError.ERR_2001);
			}
			else {
				// 卡有效期-MMYY
				String month = reqData.getCardExpDate().substring(0, 2);
				String year = reqData.getCardExpDate().substring(2);

				if (month.compareTo("01") < 0 || month.compareTo("12") > 0) {
					throw new MessageWooException("卡有效期[cardExpDate]传入月份数据异常", EnumError.ERR_2001);
				}
				else {
					String nowYearMonth = SDFFactory.PARTMONTH.format(new Date());
					if (nowYearMonth.compareTo(year + month) < 0) {
						throw new MessageWooException("卡有效期[cardExpDate]已过期", EnumError.ERR_2001);
					}
				}
			}
		}
		if (reqData.getRemark() != null && reqData.getRemark().length() > 100) {
			throw new MessageWooException("描述[remark]超过允许最大长度100", EnumError.ERR_2001);
		}

		if (!"dev".equals(SysProperties.getInstance().getProperty("deploy.mode"))) {
			if (uniquedIds.containsKey(reqData.getTradeNo() + reqData.getMchNo())) {
				throw new MessageWooException("相同订单号[tradeNo]并发请求，请确认后再试", EnumError.ERR_4003);
			}
		}

		// 【2】关联的机构权限信息效验
		DownMerchantData downMerchant = DownMerchants.getByCode(reqData.getMchNo());
		if (downMerchant == null) {
			throw new MessageWooException("机构号[mchNo]不存在或已停用", EnumError.ERR_3001);
		}
		else if (downMerchant.getStatus() != EnumDownUserStatus.ACTIVE) {
			throw new MessageWooException("机构号[mchNo]未启用", EnumError.ERR_3001);
		}

		if (!SDFFactory.DATE.format(new Date()).equals(downMerchant.getSettleDate())) {
			throw new MessageWooException("正在进行日终跑批，请稍后再试", EnumError.ERR_8003);
		}

		// 配置了支持的产品集合
		DownMerchantProductData productData = DownMerchantProducts.getByMchNoAndProductType(downMerchant.getMchNo(), dspMode);
		if (productData == null) {
			throw new MessageWooException("机构未开通[" + dspMode.getDesc() + "]支付方式", EnumError.ERR_3001);
		}

		// 交易手续费，向上取整
		BigDecimal addFeeAmt = productData.getAddFeeAmt().setScale(2, BigDecimal.ROUND_CEILING);
		// 交易手续费
		reqData.setMchPayFee(addFeeAmt);
		reqData.setAddFeeAmt(addFeeAmt);

		// 代理商利润
		BigDecimal agtProfit = new BigDecimal("0");
		BigDecimal agentPayFee = addFeeAmt;
		DownAgentData agent = downMerchant.getAgentId() != null ? DownAgents.getByCode(downMerchant.getAgentId()) : null;
		if (agent != null) {
			DownDspProductData agentProductData = DownDspProducts.getByMchNoAndDspMode(agent.getAgentNo(), dspMode);
			if (agentProductData != null) {
				agentPayFee = reqData.getAddFeeAmt().setScale(2, BigDecimal.ROUND_CEILING);
				agtProfit = addFeeAmt.subtract(agentPayFee);
			}
		}
		reqData.setAgentProfit(agtProfit.compareTo(new BigDecimal("0")) < 0 ? new BigDecimal("0") : agtProfit);

		// 【3】交易路由信息效验
		DownDspInvoiceRouteData route = DownDspInvoiceRoutes.getByMerchantAndDspMode(downMerchant, dspMode);
		if (route != null) {
		}
		else {
			throw new MessageWooException("无可用路由配置", EnumError.ERR_8001);
		}
		reqData.setChannel(route.getChannel());

		PasswayDspMerchantViewData routeMerchant = PasswayDspMerchants.get(route.getChannel(), route.getChannelMercNo());
		if (routeMerchant == null || !routeMerchant.getStatus().equals(EnumDownUserStatus.ACTIVE)) {
			throw new MessageWooException("渠道为空或未启用", EnumError.ERR_8001);
		}
		reqData.setChannelAccName(routeMerchant.getChannelAccName());
		reqData.setChannelAccNo(routeMerchant.getChannelAccNo());

		// 平台利润, 默认为代理商的支付成本
		PasswayDspMerchantFeeData routeMerchantFee = routeMerchant.getPayFees().get(dspMode.toString());
		if (routeMerchantFee == null) {
			throw new MessageWooException("手续费参数未定义完全", EnumError.ERR_8001);
		}
		
		BigDecimal passwayFeeAmt = routeMerchantFee.getAddFeeAmt();
		reqData.setChannelPayFee(passwayFeeAmt);
		reqData.setProfit(agentPayFee.subtract(passwayFeeAmt));

		BigDecimal feedFeeAmt = routeMerchantFee.getFeedFeeAmt();
		reqData.setXtraProfit(passwayFeeAmt.subtract(feedFeeAmt));

		// 设置并发拦截
		if (!"dev".equals(SysProperties.getInstance().getProperty("deploy.mode"))) {
			uniquedIds.add(reqData.getTradeNo() + reqData.getMchNo());
		}
		// 【验证原交易是否已存在】
		DownDspInvoice existedModel = this.downDspInvoiceDAO.findByTradeNoAndMchNo(reqData.getTradeNo(), reqData.getMchNo());
		if (existedModel != null) {
			throw new MessageWooException("订单号已存在，请确认是否重复下单", EnumError.ERR_4002);
		}

		// 效验累计交易额度
		DownMerchantFundAccount fundAccount = downMerchantFundAccountDAO.findByMchNo(reqData.getMchNo());

		// 针对未开启预授信权限的账户，进行余额检查
		if (downMerchant.getPreAuthDspFlg() == null || !downMerchant.getPreAuthDspFlg()) {
			if (fundAccount.getSettleInAmt() - fundAccount.getSettleOutAmt() - fundAccount.getFrozenAmt() < addFeeAmt.multiply(new BigDecimal("100")).longValue()) {
				throw new MessageWooException("账户余额不足", EnumError.ERR_2001);
			}
		}

		DownDspInvoice model = downDspInvoiceService.makeOrder(reqData, downMerchant, agent);

		return DownDspInvoiceService.toServiceData(reqData, model);
	}
}
