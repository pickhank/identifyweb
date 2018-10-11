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
package com.woodare.template.web.controller.content;

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
import com.woodare.template.jpa.model.DownDspInvoice;
import com.woodare.template.jpa.model.data.EnumDateCate;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceSumData;
import com.woodare.template.jpa.persistence.persistence.IDownDspInvoiceDAO;
import com.woodare.template.web.controller.helper.CellFormatterFactory;
import com.woodare.template.web.viewdata.downdspinvoice.DownDspInvoiceViewData;
import com.woodare.template.web.viewdata.downdspinvoice.SearchDownDspInvoiceViewData;

/**
 * ClassName: ContentDownDspInvoiceController
 * 
 * @description
 * @author Luke
 * @Date Mar 2,2018
 */
@Controller
@RequestMapping("/content/DownDspInvoice")
public class ContentDownDspInvoiceController extends BaseController {

	@Autowired
	private IDownDspInvoiceDAO downDspInvoiceDAO;

	private void formatSearchData(SearchDownDspInvoiceViewData searchData) {
		searchData.setMchNo(getUsername());

		if (searchData.getDateCate() == null) {
			searchData.setDateCate(EnumDateCate.CUSTOM);
		}
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.MILLISECOND, -1);
		switch (searchData.getDateCate()) {
		case TODAY:
			searchData.setEndDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, -1);
			c.add(Calendar.MILLISECOND, 1);
			searchData.setStartDate(c.getTime());
			break;
		case YESTERDAY:
			c.add(Calendar.DAY_OF_MONTH, -1);
			searchData.setEndDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, -1);
			c.add(Calendar.MILLISECOND, 1);
			searchData.setStartDate(c.getTime());
			break;
		case LAST_7_DAYS:
			searchData.setEndDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, -7);
			c.add(Calendar.MILLISECOND, 1);
			searchData.setStartDate(c.getTime());
			break;
		case LAST_30_DAYS:
			searchData.setEndDate(c.getTime());
			c.add(Calendar.DAY_OF_MONTH, -30);
			c.add(Calendar.MILLISECOND, 1);
			searchData.setStartDate(c.getTime());
			break;
		default:
			if (searchData.getEndDate() == null) {
				searchData.setEndDate(c.getTime());
			}
			else {
				Calendar c1 = Calendar.getInstance();
				c1.setTime(searchData.getEndDate());
				c1.set(Calendar.HOUR_OF_DAY, 0);
				c1.set(Calendar.MINUTE, 0);
				c1.set(Calendar.SECOND, 0);
				c1.set(Calendar.MILLISECOND, 0);
				c1.add(Calendar.DAY_OF_MONTH, 1);
				c1.add(Calendar.MILLISECOND, -1);
				searchData.setEndDate(c1.getTime());
			}
			if (searchData.getStartDate() == null) {
				c.add(Calendar.DAY_OF_MONTH, -1);
				c.add(Calendar.MILLISECOND, 1);
				searchData.setStartDate(c.getTime());
			}
			else {
				Calendar c1 = Calendar.getInstance();
				c1.setTime(searchData.getStartDate());
				c1.set(Calendar.HOUR_OF_DAY, 0);
				c1.set(Calendar.MINUTE, 0);
				c1.set(Calendar.SECOND, 0);
				c1.set(Calendar.MILLISECOND, 0);
				searchData.setStartDate(c1.getTime());
			}
			break;
		}
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/sum")
	public ModelAndView sum(SearchDownDspInvoiceViewData searchData) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/content/DownDspInvoice/sum"));

		formatSearchData(searchData);

		List<DownDspInvoiceSumData> items = downDspInvoiceDAO.sumInvoice(searchData);
		mav.addObject("items", items);
		mav.addObject("search", searchData);
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index")
	public ModelAndView index(SearchDownDspInvoiceViewData searchData) throws ControllerException {
		return showIndex(getTemplate("/content/DownDspInvoice/index"), searchData, null);
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public void export(SearchDownDspInvoiceViewData searchData, HttpServletResponse response) throws ControllerException {
		formatSearchData(searchData);
		searchData.setPageSize(Integer.MAX_VALUE);
		searchData.setPageIndex(0);

		IPagedList<DownDspInvoice> items = downDspInvoiceDAO.searchItems(searchData);

		String fileName = "invoice-" + SDFFactory.DATETIME_DASH.format(new Date()) + ".xls";
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

	private byte[] formatString(List<DownDspInvoice> items) throws ParseException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String[]> headNames = new ArrayList<String[]>();
		headNames.add(new String[] { "机构号", "机构名称", "机构流水号", "平台流水号", "支付方式", "交易金额", "结算额", "手续费", "订单时间", "交易状态", "交易状态描述" });
		List<String[]> fieldNames = new ArrayList<String[]>();
		fieldNames.add(new String[] { "mchNo", "mchName", "tradeNo", "transNo", "payType", "price", "downRealT1Price", "downPayFee", "orderDate", "status", "statusDesc" });

		ExportSetInfo setInfo = new ExportSetInfo();
		setInfo.addFomatter("channel", CellFormatterFactory.NO_CARD_CHANNEL);
		setInfo.addFomatter("status", CellFormatterFactory.NO_CARD_STATUS);

		LinkedHashMap<String, List<?>> objsMap = new LinkedHashMap<String, List<?>>();
		objsMap.put("数据", items);
		setInfo.setObjsMap(objsMap);
		setInfo.setFieldNames(fieldNames);
		setInfo.setTitles(new String[] { "交易记录" });
		setInfo.setHeadNames(headNames);
		setInfo.setOut(baos);
		try {
			ExcelUtils.export2Excel(setInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ModelAndView add(DownDspInvoiceViewData data) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/content/DownDspInvoice/details"));
		DownDspInvoice item = null;
		if (StringUtils.isNotEmpty(data.getId())) {
			item = downDspInvoiceDAO.findOne(data.getId());
		}
		mav.addObject("item", convertDetails(item));
		return mav;
	}

	private ModelAndView showIndex(String jsp, SearchDownDspInvoiceViewData searchData, String error) {
		ModelAndView mav = new ModelAndView(jsp);
		formatSearchData(searchData);

		IPagedList<DownDspInvoice> items = downDspInvoiceDAO.searchItems(searchData);
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		mav.addObject("error", error);
		return mav;
	}

	private ListResponseData<DownDspInvoiceViewData> convertToList(IPagedList<DownDspInvoice> items) {
		ListResponseData<DownDspInvoiceViewData> response = new ListResponseData<DownDspInvoiceViewData>();
		if (items != null) {
			for (DownDspInvoice item : items) {
				response.addItem(convert(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private DownDspInvoiceViewData convert(DownDspInvoice item) {
		DownDspInvoiceViewData viewData = SaftyBeanUtils.cloneTo(item, DownDspInvoiceViewData.class);
		return viewData;
	}

	private DownDspInvoiceViewData convertDetails(DownDspInvoice item) {
		DownDspInvoiceViewData viewData = convert(item);
		return viewData;
	}
}
