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
import com.woodare.core.jpa.model.data.EnumUserRole;
import com.woodare.core.security.UserDetailData;
import com.woodare.core.util.SDFFactory;
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.core.web.common.viewdata.ListResponseData;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.jersery.webservice.busi.base.IDownMerchantBalanceService;
import com.woodare.template.jpa.model.ChargeDetail;
import com.woodare.template.jpa.model.DownMerchantBalance;
import com.woodare.template.jpa.model.data.EnumBalSourceType;
import com.woodare.template.jpa.model.data.EnumBalanceAccType;
import com.woodare.template.jpa.model.data.EnumBalanceType;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.persistence.IChargeDetailDAO;
import com.woodare.template.web.viewdata.chargedetail.ChargeDetailViewData;
import com.woodare.template.web.viewdata.chargedetail.SearchChargeDetailViewData;

/**
 * ClassName: AdminChargeDetailController
 *
 * @description 机构充值管理
 * @author xue_songli
 * @Date 2018/10/01
 */
@Controller
@RequestMapping("/admin/chargeDetail")
public class AdminChargeDetailController extends BaseController {
	private static Logger log = Logger.getLogger(AdminChargeDetailController.class);

	@Autowired
	private IChargeDetailDAO chargeDetailDAO;

	@Autowired
	private IDownMerchantBalanceService downMerchantBalanceService;

	/**
	 * 列表页面初始化&条件查询
	 * 
	 * @param searchData
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping("/index")
	public ModelAndView index(SearchChargeDetailViewData searchData, HttpServletResponse response) throws ControllerException {
		return showIndex(getTemplate("/admin/chargeDetail/index"), searchData);
	}

	/**
	 * 条件导出功能
	 * 
	 * @param searchData
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping("/export")
	public ModelAndView export(SearchChargeDetailViewData searchData, HttpServletResponse response) throws ControllerException {
		List<ChargeDetail> items = chargeDetailDAO.searchItems(searchData);
		String responName = "charge-" + SDFFactory.DATETIME.format(new Date()) + ".csv";
		return this.exportToResponse(response, responName, formatString(items));
	}

	/**
	 * 初始化充值明细页面
	 * 
	 * @param reqData
	 * @return
	 * @throws ControllerException
	 */
	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(ChargeDetailViewData reqData) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/admin/chargeDetail/add"));
		if ("View".equals(reqData.getMode())) {
			mav = new ModelAndView(getTemplate("/admin/chargeDetail/info"));
		}

		ChargeDetailViewData item = null;

		if (StringUtils.isNotEmpty(reqData.getId())) {
			ChargeDetail model = chargeDetailDAO.findOne(reqData.getId());
			item = convertViewData(model);
		}
		else {
			item = new ChargeDetailViewData();
			item.setCreateName(this.getUser().getUsername());
		}

		// IPagedList<DownMerchant> items = downMerchantDAO.searchDownMerchants(searchDataTwo);
		// if (StringUtils.isNotEmpty(reqData.getId())) {
		// ChargeDetail model = chargeDetailDAO.findOne(reqData.getId());
		// item = convertDetails(model);
		// }
		// else {
		// item = new ChargeDetailViewData();
		// }
		// mav.addObject("search", searchDataTwo);
		// mav.addObject("res", convertToListTwo(items));
		mav.addObject("item", item);
		mav.addObject("merchants", DownMerchants.getAll());

		return mav;
	}

	/**
	 * 新增或编辑提交
	 * 
	 * @param reqData
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPost(ChargeDetailViewData reqData) {
		ChargeDetail model = null;

		String error = validData(reqData);
		if (StringUtils.isNotEmpty(error)) {
			ModelAndView mav = alertFailed("/admin/chargeDetail/add", reqData, error);
			mav.addObject("merchants", DownMerchants.getAll());
			return mav;
		}

		if (StringUtils.isNotEmpty(reqData.getId())) {
			model = chargeDetailDAO.findOne(reqData.getId());

			// 充值处理状态， 0-待审核、1-审核通过、2-审核拒绝
			if (0 != model.getStatus() && 2 != model.getStatus()) {
				ModelAndView mav = alertFailed("/admin/chargeDetail/add", reqData, "非待审核状态无法编辑");
				mav.addObject("merchants", DownMerchants.getAll());
				return mav;
			}
		}
		else {
			model = new ChargeDetail();
		}

		SaftyBeanUtils.copyNotEmptyProperties(reqData, model, new String[] { "id" });

		//审核时间
		model.setAuditDate(reqData.getCreateDate().toString());
		// 默认为待审核
		model.setStatus(0);
		//
		model.setCreateName(this.getUser().getUsername());
		model.setRearMoney(reqData.getRearMoneyYuan().multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_EVEN).longValue());

		DownMerchantData merchant = DownMerchants.getByCode(model.getMchNo());
		model.setMchName(merchant.getName());

		if (StringUtils.isNotEmpty(reqData.getId())) {
			this.chargeDetailDAO.update(model);
		}
		else {
			this.chargeDetailDAO.save(model);
		}

		return alertSuccess("/admin/chargeDetail/add", convertViewData(model));
	}

	/**
	 * 审核处理
	 * 
	 * @param itemId
	 * @param reqData
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/audit/{itemId}", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResponseData<Boolean> audit(@PathVariable String itemId, ChargeDetailViewData reqData) {
		AjaxResponseData<Boolean> ret = new AjaxResponseData<Boolean>(false);
		ret.setState(EResponseState.CustomMsg);
		String message = "";
		try {
			if (StringUtils.isNotEmpty(itemId)) {
				ChargeDetail model = chargeDetailDAO.findOne(itemId);
				if (model == null) {
					message = "数据状态已发生变化，请重新刷新画面！";
				}
				else {
					UserDetailData userData = this.getUser();
					// 非超级管理员，且录入人等于当前审核人  问题
					if (!EnumUserRole.SUPERVISOR.equals(userData.getUserRole()) && this.getUser().getUsername().equals(model.getCreateName())) {
						message = "不能审核自己提交的数据";
					}
					else {
						log.info("ChargeMercAccBal[" + model.getMchName() + "], Auditor=" + model.getRearMoney());
						// 机构信息
						DownMerchantData merchant = DownMerchants.getByCode(model.getMchNo());

						// 判定为审核通过时，处理账户余额, 更新
						if (reqData.getStatus() == 1) {
							// 代付资金记账
							DownMerchantBalance txnBal = new DownMerchantBalance();
							// 记账流水号
							txnBal.setBalanceNo(SDFFactory.getBalanceNo());
							// 账单事项
							txnBal.setSourceType(EnumBalSourceType.Charge);
							// 关联交易信息
							txnBal.setReferTransNo(model.getId());
							// 备注信息
							txnBal.setRemark("充值入账");

							// 交易记账明细登记
							downMerchantBalanceService.addMerchantBalance(merchant, txnBal, EnumBalanceAccType.Settle, EnumBalanceType.In, model.getRearMoney(), 0l);
						}

						// 处理审核表状态更新
						model.setStatus(reqData.getStatus());
						chargeDetailDAO.update(model);

						message = "处理成功！";
						ret.setState(EResponseState.Successful);
					}
				}
			}
			else {
				message = "数据状态已发生变化，请重新刷新画面！";
			}
		} catch (Exception e) {
			log.error(e, e);
			message = "处理失败！ " + e.getMessage();
		}
		ret.setMessage(message);
		return ret;
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
				ChargeDetail model = chargeDetailDAO.findOne(itemId);
				if (model == null) {
					message = "数据已被删除，请重新刷新画面！";
				}
				else {
					if (model.getStatus() == 1) {
						message = "数据状态已发生变化，请重新刷新画面！";
					}
					else {
						chargeDetailDAO.delete(model);
						message = "删除成功！";
						ret.setState(EResponseState.Successful);
					}
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

	private ModelAndView showIndex(String jsp, SearchChargeDetailViewData searchData) {
		ModelAndView mav = new ModelAndView(jsp);

		IPagedList<ChargeDetail> items = chargeDetailDAO.searchItems(searchData);

		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		return mav;
	}

	/**
	 * @param items
	 * @return
	 */
	private String formatString(List<ChargeDetail> items) {
		StringBuffer sb = new StringBuffer();
		sb.append("录入人,");
		sb.append("商户号,");
		sb.append("商户名,");
		sb.append("充值处理状态,");
		sb.append("审核时间,");
		sb.append("充值后金额,");
		sb.append("备注,");
		sb.append("\n");
		if (items != null) {
			for (ChargeDetail item : items) {
				sb.append(formatValue(item.getCreateName()));
				sb.append(formatValue(item.getMchNo(), true));
				sb.append(formatValue(item.getMchName()));
				if (item.getStatus() == 0) {
					sb.append(formatValue("待审核"));
				}
				else if (item.getStatus() == 1) {
					sb.append(formatValue("审核通过"));
				}
				else if (item.getStatus() == 2) {
					sb.append(formatValue("审核拒绝"));
				}
				sb.append(formatValue(item.getAuditDate()));
				sb.append(formatValue(item.getRearMoney()));
				sb.append(formatValue(item.getRemark()));

				sb.append("\n");
			}
		}
		return sb.toString();
	}

	private ListResponseData<ChargeDetailViewData> convertToList(IPagedList<ChargeDetail> items) {
		ListResponseData<ChargeDetailViewData> response = new ListResponseData<ChargeDetailViewData>();
		if (items != null) {
			for (ChargeDetail item : items) {
				response.addItem(convertViewData(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	/**
	 * 异常请求参数检查
	 * 
	 * @param item
	 * @return
	 */
	private String validData(ChargeDetailViewData item) {
		String error = "";
		return error;
	}

	private ChargeDetailViewData convertViewData(ChargeDetail model) {
		ChargeDetailViewData item = SaftyBeanUtils.cloneTo(model, ChargeDetailViewData.class, new String[] { "deleteStatus" });

		item.setRearMoneyYuan(new BigDecimal(model.getRearMoney() / 100.0));
		return item;
	}
 
}
