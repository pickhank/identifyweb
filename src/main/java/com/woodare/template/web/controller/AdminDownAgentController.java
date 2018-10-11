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
import com.woodare.template.helper.cache.DownMerchantProducts;
import com.woodare.template.jpa.model.DownAgent;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.model.DownMerchantProduct;
import com.woodare.template.jpa.model.data.EnumDownUserStatus;
import com.woodare.template.jpa.model.data.EnumDspMode;
import com.woodare.template.jpa.model.data.EnumFundAccountType;
import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;
import com.woodare.template.jpa.persistence.data.downmerchantproduct.DownMerchantProductData;
import com.woodare.template.jpa.persistence.data.downmerchantproduct.SearchDownMerchantProductData;
import com.woodare.template.jpa.persistence.persistence.IDownAgentDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantProductDAO;
import com.woodare.template.web.viewdata.downagent.DownAgentViewData;
import com.woodare.template.web.viewdata.downagent.SearchDownAgentViewData;
import com.woodare.template.web.viewdata.downmerchantproduct.DownMerchantProductViewData;

/**
 * ClassName: AdminUserController
 * 
 * @description
 * @author Xinxing Jiang
 * @Date May 12, 2015
 */
@Controller
@RequestMapping("/admin/downAgent")
public class AdminDownAgentController extends BaseController {

	private static Logger log = Logger.getLogger(AdminDownAgentController.class);

	@Autowired
	private IDownAgentDAO downAgentDAO;

	@Autowired
	private IDownMerchantProductDAO downMerchantProductDAO;

	@Autowired
	private CacheDataChangeService cacheDataChangeService;

	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping({ "/index", "/export" })
	public ModelAndView index(SearchDownAgentViewData searchData, HttpServletResponse response) throws ControllerException {
		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			searchData.setPageSize(Integer.MAX_VALUE);
		}

		IPagedList<DownAgent> items = downAgentDAO.searchDownAgents(searchData);

		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			String responName = "agent-" + SDFFactory.DATETIME.format(new Date()) + ".csv";
			return this.exportToResponse(response, responName, formatString(items));
		}

		ModelAndView mav = new ModelAndView(getTemplate("/admin/downAgent/index"));
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(DownAgentViewData data) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/admin/downAgent/add"));
		DownAgent model = null;

		List<DownMerchantProduct> existedProducts = null;
		if (StringUtils.isNotEmpty(data.getId())) {
			model = downAgentDAO.findOne(data.getId());

			SearchDownMerchantProductData searchProduct = new SearchDownMerchantProductData();
			searchProduct.setMchNo(model.getAgentNo());
			existedProducts = downMerchantProductDAO.searchItems(searchProduct);

		}
		mav.addObject("item", convertDetails(model));
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
	public ModelAndView addP(DownAgentViewData data) throws ControllerException {
		List<DownMerchantProduct> existedProducts = new ArrayList<DownMerchantProduct>();
		if (data.getProductTypeArr() != null) {
			List<String> selProductTypes = data.getSelProductTypeArr() != null ? Arrays.asList(data.getSelProductTypeArr()) : new ArrayList<String>();

			for (int i = 0; i < data.getProductTypeArr().length; i++) {
				DownMerchantProduct product = new DownMerchantProduct();
				product.setMchNo(data.getAgentNo());
				product.setDspMode(EnumDspMode.valueOf(data.getProductTypeArr()[i]));

				product.setMchName(data.getName());
				// product.setAddDrawFeeAmt(StringUtils.isNotEmpty(data.getAddDrawFeeAmtArr()[i]) ? new BigDecimal(data.getAddDrawFeeAmtArr()[i]) : new BigDecimal("-1"));
				product.setAddFeeAmt(StringUtils.isNotEmpty(data.getAddFeeAmtArr()[i]) ? new BigDecimal(data.getAddFeeAmtArr()[i]) : new BigDecimal("-1"));
				// product.setFeeRatio(StringUtils.isNotEmpty(data.getFeeRatioArr()[i]) ? new BigDecimal(data.getFeeRatioArr()[i]) : new BigDecimal("-1"));
				// product.setDrawFeeRatio(StringUtils.isNotEmpty(data.getDrawFeeRatioArr()[i]) ? new BigDecimal(data.getDrawFeeRatioArr()[i]) : new BigDecimal("-1"));

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
			DownAgent model = null;
			if (StringUtils.isNotEmpty(data.getId())) {
				model = downAgentDAO.findOne(data.getId());
			}
			boolean update = true;
			if (model == null) {
				model = new DownAgent();
				update = false;
			}

			SaftyBeanUtils.copyProperties(data, model, new String[] { "id", "agentNo" });
			if (update) {
				downAgentDAO.update(model);
			}
			else {
				model.setAgentNo(data.getAgentNo());
				downAgentDAO.save(model);
			}

			// 生成账户余额表数据
			DownMerchantFundAccount fundAccount = downMerchantFundAccountDAO.findByMchNo(model.getAgentNo());
			if (fundAccount == null) {
				fundAccount = new DownMerchantFundAccount();
				fundAccount.setAccountType(EnumFundAccountType.Agent);
				fundAccount.setMchNo(model.getAgentNo());
				fundAccount.setMchName(model.getName());
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

			cacheDataChangeService.notifyOthers(DownAgentData.class);
			cacheDataChangeService.notifyOthers(DownMerchantProductData.class);

			return alertSuccess(getTemplate("/admin/downAgent/add"), convertDetails(model));
		}
		else {
			return alertFailed(getTemplate("/admin/downAgent/add"), data, error);
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
				DownAgent model = downAgentDAO.findOne(itemId);
				if (model == null) {
					message = "数据已被删除，请重新刷新画面！";
				}
				else {
					downAgentDAO.delete(model);

					cacheDataChangeService.notifyOthers(DownAgentData.class);

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

	private String formatString(List<DownAgent> items) {
		StringBuffer sb = new StringBuffer();
		sb.append("代理商编号,姓名,电话,结算卡银行,结算卡卡号,状态\n");
		if (items != null) {
			for (DownAgent item : items) {
				sb.append(formatValue(item.getAgentNo(), true));
				sb.append(formatValue(item.getName()));
				sb.append(formatValue(item.getPhone(), true));
				sb.append(formatValue(item.getBankName()));
				sb.append(formatValue(item.getCardNo(), true));
				if (item.getStatus() == EnumDownUserStatus.ACTIVE) {
					sb.append(formatValue("使用中"));
				}
				else if (item.getStatus() == EnumDownUserStatus.PENDING) {
					sb.append(formatValue("未使用"));
				}
				else if (item.getStatus() == EnumDownUserStatus.HOLD) {
					sb.append(formatValue("暂停使用"));
				}
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	private ListResponseData<DownAgentViewData> convertToList(IPagedList<DownAgent> items) {
		ListResponseData<DownAgentViewData> response = new ListResponseData<DownAgentViewData>();
		if (items != null) {
			for (DownAgent item : items) {
				response.addItem(convert(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private DownAgentViewData convert(DownAgent item) {
		DownAgentViewData viewData = SaftyBeanUtils.cloneTo(item, DownAgentViewData.class);

		if (item != null && StringUtils.isNotEmpty(item.getAgentNo())) {
			List<DownMerchantProductData> products = DownMerchantProducts.getByMchNo(item.getAgentNo());
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

	private DownAgentViewData convertDetails(DownAgent item) {
		DownAgentViewData viewData = convert(item);
		return viewData;
	}

	private String validError(DownAgentViewData data) {
		String error = "";
		return error;
	}
}
