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
package com.woodare.template.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.exception.ControllerException;
import com.woodare.core.util.SDFFactory;
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.core.web.common.viewdata.ListResponseData;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.busi.service.CacheDataChangeService;
import com.woodare.template.helper.cache.DownAgents;
import com.woodare.template.helper.cache.DownMerchantProducts;
import com.woodare.template.jpa.model.DownMerchant;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.model.DownMerchantProduct;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumDspMode;
import com.woodare.template.jpa.model.data.EnumFundAccountType;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.data.downmerchantproduct.DownMerchantProductData;
import com.woodare.template.jpa.persistence.data.downmerchantproduct.SearchDownMerchantProductData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantProductDAO;
import com.woodare.template.web.viewdata.downmerchant.DownMerchantViewData;
import com.woodare.template.web.viewdata.downmerchant.SearchDownMerchantViewData;
import com.woodare.template.web.viewdata.downmerchantproduct.DownMerchantProductViewData;

/**
 * ClassName: AdminDownMerchantController
 * 
 * @description
 * @author Luke
 * @Date Feb 28, 2018
 */
@Controller
@RequestMapping("/admin/downMerchant")
public class AdminDownMerchantController extends BaseController {

	private static Logger log = Logger.getLogger(AdminDownMerchantController.class);

	@Autowired
	private IDownMerchantDAO downMerchantDAO;

	@Autowired
	private IDownMerchantProductDAO downMerchantProductDAO;

	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;

	@Autowired
	private CacheDataChangeService cacheDataChangeService;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping({ "/index", "/export" })
	public ModelAndView index(SearchDownMerchantViewData searchData, HttpServletResponse response) throws ControllerException {
		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			searchData.setPageSize(Integer.MAX_VALUE);
		}

		IPagedList<DownMerchant> items = downMerchantDAO.searchDownMerchants(searchData);

		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			String responName = "merchant-" + SDFFactory.DATETIME.format(new Date()) + ".csv";
			return this.exportToResponse(response, responName, formatString(items));
		}

		ModelAndView mav = new ModelAndView(getTemplate("/admin/downMerchant/index"));
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(DownMerchantViewData data) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/admin/downMerchant/add"));

		DownMerchantViewData item = null;

		List<DownMerchantProduct> existedProducts = null;
		if (StringUtils.isNotEmpty(data.getId())) {
			DownMerchant model = downMerchantDAO.findOne(data.getId());
			item = convertDetails(model);

			SearchDownMerchantProductData searchProduct = new SearchDownMerchantProductData();
			searchProduct.setMchNo(model.getMchNo());
			existedProducts = downMerchantProductDAO.searchItems(searchProduct);
		}
		else {
			item = new DownMerchantViewData();
			item.setCreditRatio(70l);
		}

		mav.addObject("item", item);
		// 代理列表
		mav.addObject("agents", DownAgents.getAll());
		// 产品列表
		mav.addObject("products", getProducts(existedProducts));

		return mav;
	}

	/**
	 * @param existedProducts
	 * @return
	 */
	private List<DownMerchantProductViewData> getProducts(List<DownMerchantProduct> existedProducts) {
		List<DownMerchantProductViewData> products = new ArrayList<DownMerchantProductViewData>();
		for (EnumDspMode typ : EnumDspMode.values()) {
			DownMerchantProductViewData product = null;
			DownMerchantProduct existProduct = null;
			if (existedProducts != null) {
				for (DownMerchantProduct tmpProduct : existedProducts) {
					if (typ.equals(tmpProduct.getDspMode())) {
						existProduct = tmpProduct;
						break;
					}
				}
			}
			if (existProduct != null) {
				product = SaftyBeanUtils.cloneTo(existProduct, DownMerchantProductViewData.class);
			}
			else {
				product = new DownMerchantProductViewData();
				product.setDspMode(typ);
			}
			if (product.getDrawFeeRatio() == null || new BigDecimal("-1").compareTo(product.getDrawFeeRatio()) == 0) {
				product.setDrawFeeRatio(new BigDecimal("0"));
			}
			if (product.getAddFeeAmt() == null || new BigDecimal("-1").compareTo(product.getAddFeeAmt()) == 0) {
				product.setAddFeeAmt(new BigDecimal("0"));
			}

			if (product.getMinPerAmt() == null) {
				product.setMinPerAmt(new BigDecimal("0"));
			}
			if (product.getMaxPerAmt() == null) {
				product.setMaxPerAmt(new BigDecimal("0"));
			}
			if (product.getMaxTotalAmt() == null) {
				product.setMaxTotalAmt(new BigDecimal("0"));
			}

			if (EnumDownUserStatus.PENDING.equals(product.getStatus())) {
				if (product.getFeeRatio() != null && new BigDecimal("-1").compareTo(product.getFeeRatio()) == 0) {
					product.setFeeRatio(null);
				}
				if (product.getAddDrawFeeAmt() != null && new BigDecimal("-1").compareTo(product.getAddDrawFeeAmt()) == 0) {
					product.setAddDrawFeeAmt(null);
				}
			}
			else if (product.getStatus() == null) {
				product.setStatus(EnumDownUserStatus.PENDING);
			}
			products.add(product);
		}
		return products;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addP(DownMerchantViewData data) throws ControllerException {
		List<DownMerchantProduct> existedProducts = new ArrayList<DownMerchantProduct>();
		if (data.getProductTypeArr() != null) {
			List<String> selProductTypes = data.getSelProductTypeArr() != null ? Arrays.asList(data.getSelProductTypeArr()) : new ArrayList<String>();

			for (int i = 0; i < data.getProductTypeArr().length; i++) {
				DownMerchantProduct product = new DownMerchantProduct();
				product.setMchNo(data.getMchNo());
				product.setDspMode(EnumDspMode.valueOf(data.getProductTypeArr()[i]));

				product.setMchName(data.getName());
				// product.setAddDrawFeeAmt(StringUtils.isNotEmpty(data.getAddDrawFeeAmtArr()[i]) ? new BigDecimal(data.getAddDrawFeeAmtArr()[i]) : new BigDecimal("-1"));
				product.setAddFeeAmt(StringUtils.isNotEmpty(data.getAddFeeAmtArr()[i]) ? new BigDecimal(data.getAddFeeAmtArr()[i]) : new BigDecimal("-1"));
				// product.setFeeRatio(StringUtils.isNotEmpty(data.getFeeRatioArr()[i]) ? new BigDecimal(data.getFeeRatioArr()[i]) : new BigDecimal("-1"));
				// product.setDrawFeeRatio(StringUtils.isNotEmpty(data.getDrawFeeRatioArr()[i]) ? new BigDecimal(data.getDrawFeeRatioArr()[i]) : new BigDecimal("-1"));

				// product.setMinPerAmt(StringUtils.isNotEmpty(data.getMinPerAmtArr()[i]) ? new BigDecimal(data.getMinPerAmtArr()[i]) : new BigDecimal("0"));
				// product.setMaxPerAmt(StringUtils.isNotEmpty(data.getMaxPerAmtArr()[i]) ? new BigDecimal(data.getMaxPerAmtArr()[i]) : new BigDecimal("0"));
				// product.setMaxTotalAmt(StringUtils.isNotEmpty(data.getMaxTotalAmtArr()[i]) ? new BigDecimal(data.getMaxTotalAmtArr()[i]) : new BigDecimal("0"));

				if (selProductTypes.contains(data.getProductTypeArr()[i])) {
					product.setStatus(EnumDownUserStatus.ACTIVE);
				}
				else {
					product.setStatus(EnumDownUserStatus.PENDING);
				}
				existedProducts.add(product);
			}
		}

		String error = validError(data);
		if (StringUtils.isEmpty(error)) {
			DownMerchant model = null;
			if (StringUtils.isNotEmpty(data.getId())) {
				model = downMerchantDAO.findOne(data.getId());
			}
			boolean update = true;
			if (model == null) {
				model = new DownMerchant();
				model.setMchNo(downMerchantDAO.getMaxMechNo());
				update = false;
			}
			SaftyBeanUtils.copyProperties(data, model, new String[] { "id", "mchNo", "settleDate" });
			if (model.getCreditRatio() == null) {
				model.setCreditRatio(0l);
			}
			if (update) {
				downMerchantDAO.update(model);
			}
			else {
				model.setSettleDate(SDFFactory.DATE.format(new Date()));
				downMerchantDAO.save(model);
			}

			// 生成账户余额表数据
			DownMerchantFundAccount fundAccount = downMerchantFundAccountDAO.findByMchNo(model.getMchNo());
			if (fundAccount == null) {
				fundAccount = new DownMerchantFundAccount();
				fundAccount.setMchNo(model.getMchNo());
				fundAccount.setMchName(model.getName());
				fundAccount.setAccountType(EnumFundAccountType.Merchant);
				fundAccount.setSettleDate(SDFFactory.DATE.format(new Date()));
				fundAccount.setCurInAmt(0l);
				fundAccount.setCurOutAmt(0l);
				fundAccount.setSettleInAmt(0l);
				fundAccount.setSettleOutAmt(0l);
				fundAccount.setLastSettleAmt(0l);
				fundAccount.setFrozenAmt(0l);
				downMerchantFundAccountDAO.save(fundAccount);
			}

			// 保存机构开通产品费率信息
			for (int i = 0; i < existedProducts.size(); i++) {
				DownMerchantProduct product = existedProducts.get(i);
				int cnt = downMerchantProductDAO.updateFee(product);
				// 首次创建
				if (cnt == 0) {
					product.setT1BalanceAmt(0l);
					product.setT0BalanceAmt(0l);
					downMerchantProductDAO.save(product);
				}
			}

			cacheDataChangeService.notifyOthers(DownMerchantData.class);
			cacheDataChangeService.notifyOthers(DownMerchantProductData.class);

			return new ModelAndView("redirect:/admin/downMerchant/add?id=" + model.getId());
		}
		else {
			ModelAndView mav = alertFailed(getTemplate("/admin/downMerchant/add"), data, error);
			// 初始化代理列表
			mav.addObject("agents", DownAgents.getAll());

			// 初始化产品列表
			mav.addObject("products", getProducts(existedProducts));

			// 保持列表数据
			if (data.getProductTypeArr() != null) {

			}
			return mav;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/delete/{itemId}", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseData<Boolean> delete(@PathVariable String itemId) {
		AjaxResponseData<Boolean> ret = new AjaxResponseData<Boolean>(false);
		ret.setState(EResponseState.CustomMsg);
		String message = "";
		try {
			if (StringUtils.isNotEmpty(itemId)) {
				DownMerchant model = downMerchantDAO.findOne(itemId);
				if (model == null) {
					message = "数据已被删除，请重新刷新画面！";
				}
				else {
					downMerchantDAO.delete(model);

					cacheDataChangeService.notifyOthers(DownMerchantData.class);

					message = "删除成功！";
					ret.setState(EResponseState.Successful);
				}
			}
			else {
				message = "数据已被删除，请重新刷新画面！";
			}
		} catch (Exception e) {
			log.error(e, e);
			message = "删除失败！ " + e.getMessage();
		}
		ret.setMessage(message);
		return ret;
	}

	private ListResponseData<DownMerchantViewData> convertToList(IPagedList<DownMerchant> items) {
		ListResponseData<DownMerchantViewData> response = new ListResponseData<DownMerchantViewData>();
		if (items != null) {
			for (DownMerchant item : items) {
				response.addItem(convert(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private DownMerchantViewData convertDetails(DownMerchant item) {
		DownMerchantViewData viewData = convert(item);
		return viewData;
	}

	private DownMerchantViewData convert(DownMerchant item) {
		DownMerchantViewData viewData = SaftyBeanUtils.cloneTo(item, DownMerchantViewData.class);

		if (item != null && StringUtils.isNotEmpty(item.getMchNo())) {
			List<DownMerchantProductData> products = DownMerchantProducts.getByMchNo(item.getMchNo());
			String openProducts = "-";
			if (products != null && products.size() > 0) {
				openProducts = "";
				for (DownMerchantProductData product : products) {
					openProducts += String.format("<span class='lbl-summary'>%s</span> = %s", product.getDspMode().getDesc(), product.getAddFeeAmt() + "元") + "<br>";
				}
			}
			viewData.setProductTypeSummary(openProducts);
		}

		return viewData;
	}

	private String validError(DownMerchantViewData data) {
		String error = "";

		return error;
	}

	/**
	 * @param items
	 * @return
	 */
	private String formatString(List<DownMerchant> items) {
		StringBuffer sb = new StringBuffer();
		sb.append("机构类别,");
		sb.append("机构编号,");
		sb.append("机构名称,");
		sb.append("联系电话,");
		sb.append("结算户名,");
		sb.append("结算银行,");
		sb.append("结算卡号,");
		sb.append("状态,");
		sb.append("代理商ID,");
		/*
		 * sb.append("交易费率,");
		 * sb.append("每笔交易费,");
		 */
		sb.append("代付费率,");
		sb.append("每笔代付费,");
		sb.append("\n");
		if (items != null) {
			for (DownMerchant item : items) {
				sb.append(formatValue(item.getMercCategory()));
				sb.append(formatValue(item.getMchNo(), true));
				sb.append(formatValue(item.getName()));
				sb.append(formatValue(item.getPhone(), true));
				sb.append(formatValue(item.getAccCardHolder()));
				sb.append(formatValue(item.getAccBankName()));
				sb.append(formatValue(item.getAccCardNo()));
				if (item.getStatus() == EnumDownUserStatus.ACTIVE) {
					sb.append(formatValue("使用中"));
				}
				else if (item.getStatus() == EnumDownUserStatus.PENDING) {
					sb.append(formatValue("未使用"));
				}
				else if (item.getStatus() == EnumDownUserStatus.HOLD) {
					sb.append(formatValue("暂停使用"));
				}
				sb.append(formatValue(item.getAgentId()));
				sb.append(formatValue(item.getDrawFeeRatio(), true));
				sb.append(formatValue(item.getAddDrawFeeAmt(), true));
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}
