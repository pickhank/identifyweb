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
import java.text.ParseException;
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
import com.woodare.framework.utils.ExcelUtils.ExportSetInfo;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.jpa.model.DownMerchantBalance;
import com.woodare.template.jpa.model.data.EnumBalanceAccType;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantBalanceDAO;
import com.woodare.template.web.controller.helper.CellFormatterFactory;
import com.woodare.template.web.viewdata.downmerchantbalance.DownMerchantBalanceViewData;
import com.woodare.template.web.viewdata.downmerchantbalance.SearchDownMerchantBalanceViewData;

/**
 * ClassName: AdminDownMerchantBalanceController
 * 
 * @description
 * @author Luke
 * @Date Mar 5,2018
 */
@Controller
@RequestMapping("/admin/downMerchantBalance")
public class AdminDownMerchantBalanceController extends BaseController {

	@Autowired
	private IDownMerchantBalanceDAO downMerchantBalanceDAO;

	/**
	 * @param searchData
	 */
	private void formatSearchData(SearchDownMerchantBalanceViewData searchData) {
		if(searchData.getStartDate() == null) {
			searchData.setStartDate(new Date());
		}
		if(searchData.getEndDate() == null) {
			searchData.setEndDate(new Date());
		}
		Calendar c = Calendar.getInstance();
		c.setTime(searchData.getStartDate());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		searchData.setStartDate(c.getTime());
		
		c.setTime(searchData.getEndDate());
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		searchData.setEndDate(c.getTime());
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index")
	public ModelAndView index(SearchDownMerchantBalanceViewData searchData) throws ControllerException {
		return showIndex(getTemplate("/admin/downMerchantBalance/index"), searchData, null);
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(SearchDownMerchantBalanceViewData searchData, HttpServletResponse response) throws ControllerException {
		formatSearchData(searchData);
		searchData.setPageSize(Integer.MAX_VALUE);
		searchData.setPageIndex(0);
		IPagedList<DownMerchantBalance> items = downMerchantBalanceDAO.searchItems(searchData);

		String fileName = "balance-detail-" + SDFFactory.DATETIME_DASH.format(new Date()) + ".xls";
		try {
			byte[] content = formatString(items);
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.addHeader("Content-Length", "" + content.length);
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("GBK");
			response.getOutputStream().write(content);
			response.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private byte[] formatString(List<DownMerchantBalance> items) throws ParseException {
		List<DownMerchantBalanceViewData> viewItems = new ArrayList<DownMerchantBalanceViewData>();
		if (items != null) {
			for (DownMerchantBalance item : items) {
				DownMerchantBalanceViewData viewItem = SaftyBeanUtils.cloneTo(item, DownMerchantBalanceViewData.class);
				
				if (EnumBalanceAccType.Settle.equals(viewItem.getAccType())) {
					viewItem.setAccTypeFormat("结算账户");
				}
				else if (EnumBalanceAccType.Credit.equals(viewItem.getAccType())) {
					viewItem.setAccTypeFormat("实时账户");
				}

				viewItem.setLastBalAmt(viewItem.getBalAmt().subtract(viewItem.getDiffAmt()));
			}
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String[]> headNames = new ArrayList<String[]>();

		headNames.add(new String[] { "账单编号", "账户", "业务类别", "业务流水号", "渠道", "渠道商户", "原账户金额", "变动金额", "账户金额", "登记时间", "原因" });
		List<String[]> fieldNames = new ArrayList<String[]>();
		fieldNames.add(new String[] { "balanceNo", "accTypeFormat", "sourceType", "referTransNo", "referChannel", "referChannelAccNo", "lastBalAmt", "diffAmt", "balAmt", "createDate", "remark" });

		ExportSetInfo setInfo = new ExportSetInfo();
		setInfo.addFomatter("status", CellFormatterFactory.TRANSFER_STATUS);
		setInfo.addFomatter("mode", CellFormatterFactory.BAL_SOURCE_TYPE);
		setInfo.addFomatter("sourceType", CellFormatterFactory.TRANSFER_MODE);
		setInfo.addFomatter("referChannel", CellFormatterFactory.NO_CARD_CHANNEL);

		LinkedHashMap<String, List<?>> objsMap = new LinkedHashMap<String, List<?>>();
		objsMap.put("数据", viewItems);
		setInfo.setObjsMap(objsMap);
		setInfo.setFieldNames(fieldNames);
		setInfo.setTitles(new String[] { "收支明细记录" });
		setInfo.setHeadNames(headNames);
		setInfo.setOut(baos);
		try {
			ExcelUtils.export2Excel(setInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView add(DownMerchantBalanceViewData data) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/admin/downMerchantBalance/details"));
		DownMerchantBalance item = null;
		if (StringUtils.isNotEmpty(data.getId())) {
			item = downMerchantBalanceDAO.findOne(data.getId());
		}
		mav.addObject("item", convertDetails(item));
		return mav;
	}

	private ModelAndView showIndex(String jsp, SearchDownMerchantBalanceViewData searchData, String error) {
		ModelAndView mav = new ModelAndView(jsp);
		formatSearchData(searchData);

		IPagedList<DownMerchantBalance> items = downMerchantBalanceDAO.searchItems(searchData);
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		mav.addObject("error", error);
		return mav;
	}

	private ListResponseData<DownMerchantBalanceViewData> convertToList(IPagedList<DownMerchantBalance> items) {
		ListResponseData<DownMerchantBalanceViewData> response = new ListResponseData<DownMerchantBalanceViewData>();
		if (items != null) {
			for (DownMerchantBalance item : items) {
				response.addItem(convert(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private DownMerchantBalanceViewData convert(DownMerchantBalance item) {
		DownMerchantBalanceViewData viewData = SaftyBeanUtils.cloneTo(item, DownMerchantBalanceViewData.class);
		return viewData;
	}

	private DownMerchantBalanceViewData convertDetails(DownMerchantBalance item) {
		DownMerchantBalanceViewData viewData = convert(item);
		return viewData;
	}
}
