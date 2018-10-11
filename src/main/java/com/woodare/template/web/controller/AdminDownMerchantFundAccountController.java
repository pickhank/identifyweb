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

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.exception.ControllerException;
import com.woodare.core.util.SDFFactory;
import com.woodare.core.web.common.viewdata.ListResponseData;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.utils.ExcelUtils;
import com.woodare.framework.utils.ExcelUtils.CellFormatter;
import com.woodare.framework.utils.ExcelUtils.GenericCellFunction;
import com.woodare.framework.utils.ExcelUtils.GenericExportSetInfo;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.helper.cache.DownMerchants;
import com.woodare.template.jpa.model.DownMerchantBalance;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.model.data.EnumBalSourceType;
import com.woodare.template.jpa.model.data.EnumBalanceAccType;
import com.woodare.template.jpa.model.data.EnumFundAccountType;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantBalanceDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantFundAccountDAO;
import com.woodare.template.web.controller.helper.CellFormatterFactory;
import com.woodare.template.web.viewdata.downmerchantfundaccount.DownMerchantFundAccountViewData;
import com.woodare.template.web.viewdata.downmerchantfundaccount.SearchDownMerchantFundAccountViewData;

/**
 * ClassName: AdminDownMerchantFundAccountController
 * 
 * @description
 * @author Luke
 * @Date Mar 5, 2018
 */
@Controller
@RequestMapping("/admin/downMerchantFundAccount")
public class AdminDownMerchantFundAccountController extends BaseController {

	@Autowired
	private IDownMerchantFundAccountDAO downMerchantFundAccountDAO;

	@Autowired
	private IDownMerchantBalanceDAO downMerchantBalanceDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index")
	public ModelAndView index(SearchDownMerchantFundAccountViewData searchData, HttpServletResponse response) throws ControllerException {
		if (searchData.getSearchFlag() == null) {
			if (searchData.getChangedFlag() == null) {
				searchData.setChangedFlag(true);
			}
		}

		IPagedList<DownMerchantFundAccount> items = downMerchantFundAccountDAO.searchItems(searchData);

//		导出xls
		if (searchData.getDoExportFlag() != null && searchData.getDoExportFlag()) {
			String responName = "downMerchantFundAccount-" + SDFFactory.DATETIME.format(new Date()) + ".xls";
			return this.exportToResponse(response, responName, formatString(convertToList(items).getItems()));
		}

		ModelAndView mav = new ModelAndView(getTemplate("/admin/downMerchantFundAccount/index"));
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/frozen", method = RequestMethod.GET)
	public ModelAndView frozen(DownMerchantFundAccountViewData data) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/admin/downMerchantFundAccount/frozen"));
		DownMerchantFundAccount item = null;
		if (StringUtils.isNotEmpty(data.getId())) {
			item = downMerchantFundAccountDAO.findOne(data.getId());
		}
		mav.addObject("item", convert(item));

		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/frozen", method = RequestMethod.POST)
	public ModelAndView doFrozen(DownMerchantFundAccountViewData data) throws ControllerException {
		DownMerchantFundAccount model = downMerchantFundAccountDAO.findOne(data.getId());

		long diff = data.getCurFrozenAmt().multiply(new BigDecimal("100")).longValue();
		int num = downMerchantFundAccountDAO.frozenBalance(diff, model.getMchNo());
		if (num > 0) {
			// 代付资金记账
			DownMerchantBalance txnBal = new DownMerchantBalance();
			// 关联机构信息
			txnBal.setMchNo(model.getMchNo());
			// 关联机构名称
			txnBal.setMchName(model.getMchName());
			// 记账流水号
			txnBal.setBalanceNo(SDFFactory.getBalanceNo());
			// 账单事由
			txnBal.setSourceType(EnumBalSourceType.Other);
			// 关联交易信息
			txnBal.setReferTransNo("0");
			txnBal.setReferChannel("0");
			txnBal.setReferChannelAccNo("0");
			txnBal.setRemark(data.getRemark());
			// 账户类型
			txnBal.setAccType(EnumBalanceAccType.Settle);
			// 记账日期
			txnBal.setBalanceDate(SDFFactory.DATE.format(new Date()));
			// 本次交易额
			txnBal.setDiffAmt(new BigDecimal(diff).divide(new BigDecimal("100")));
			// 交易入账后余额
			txnBal.setBalAmt(new BigDecimal(model.getFrozenAmt() + diff).divide(new BigDecimal("100")));

			txnBal.setSortNum((short) 0);

			Calendar nowTime = Calendar.getInstance();
			txnBal.setCreateDate(nowTime.getTime());

			downMerchantBalanceDAO.save(txnBal);

			return new ModelAndView("redirect:/admin/downMerchantFundAccount/frozen?id=" + model.getId());
		}

		return alertFailed(getTemplate("/admin/downMerchantFundAccount/frozen"), data, "累计冻结余额不能为负数");

	}

	private byte[] formatString(List<DownMerchantFundAccountViewData> items) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String[]> headNames = new ArrayList<String[]>();

		headNames.add(new String[] { "账户类型", "机构号", "机构名称", "授权比率", "结算日期", "T1结算额（元）", "T1已代付（元）", "T1剩余额度（元）", "当日入账（元）", "T0授信额", "T0已代付（元）", "冻结金额（元）" });
		List<String[]> fieldNames = new ArrayList<String[]>();
		fieldNames.add(new String[] { "accountType", "mchName", "mchNo", "creditRatio", "settleDate", "settleInAmt", "settleOutAmt", "settleLeftT1Amt", "curInAmt", "creditLine", "curOutAmt", "frozenAmt" });

		GenericExportSetInfo<DownMerchantFundAccountViewData> setInfo = new GenericExportSetInfo<DownMerchantFundAccountViewData>();

		// settleInAmt - settleOutAmt
		setInfo.addFunction("settleLeftT1Amt", new GenericCellFunction<DownMerchantFundAccountViewData>() {
			@Override
			public String value(DownMerchantFundAccountViewData o) {
				try {
					return new BigDecimal((o.getSettleInAmt() - o.getSettleOutAmt()) / 100.0).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
				} catch (Exception e) {
					return "";
				}
			}
		});

		setInfo.addFomatter("accountType", new CellFormatter() {
			@Override
			public String format(Object o) {
				if (EnumFundAccountType.Merchant.equals(o)) {
					return "机构";
				}
				else if (EnumFundAccountType.Agent.equals(o)) {
					return "代理商";
				}
				return o.toString();
			}
		});
		setInfo.addFomatter("settleInAmt", CellFormatterFactory.FEN_2_YUAN);
		setInfo.addFomatter("curInAmt", CellFormatterFactory.FEN_2_YUAN);
		setInfo.addFomatter("curOutAmt", CellFormatterFactory.FEN_2_YUAN);
		setInfo.addFomatter("fronzenAmt", CellFormatterFactory.FEN_2_YUAN);

		LinkedHashMap<String, List<?>> objsMap = new LinkedHashMap<String, List<?>>();
		objsMap.put("数据", items);
		setInfo.setObjsMap(objsMap);
		setInfo.setFieldNames(fieldNames);
		setInfo.setTitles(new String[] { "交易记录" });
		setInfo.setHeadNames(headNames);
		setInfo.setOut(baos);
		try {
			ExcelUtils.export2ExcelGeneric(setInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	private ListResponseData<DownMerchantFundAccountViewData> convertToList(IPagedList<DownMerchantFundAccount> items) {
		ListResponseData<DownMerchantFundAccountViewData> response = new ListResponseData<DownMerchantFundAccountViewData>();
		if (items != null) {
			for (DownMerchantFundAccount item : items) {
				response.addItem(convert(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private DownMerchantFundAccountViewData convert(DownMerchantFundAccount item) {
		DownMerchantFundAccountViewData viewData = SaftyBeanUtils.cloneTo(item, DownMerchantFundAccountViewData.class);

		DownMerchantData mch = DownMerchants.getByCode(item.getMchNo());
		if (mch != null) {
			viewData.setCreditRatio(mch.getCreditRatio() == null ? 0l : mch.getCreditRatio());
			viewData.setCreditLine(viewData.getCreditRatio() * viewData.getCurInAmt() / 100);
		}

		return viewData;
	}
}
