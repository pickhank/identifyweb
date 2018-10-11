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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.exception.ControllerException;
import com.woodare.core.web.common.viewdata.ListResponseData;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.jpa.model.DownTradeLog;
import com.woodare.template.jpa.persistence.persistence.IDownTradeLogDAO;
import com.woodare.template.web.viewdata.downtradelog.DownTradeLogViewData;
import com.woodare.template.web.viewdata.downtradelog.SearchDownTradeLogViewData;

/**
 * ClassName: AdminDownTradeLogController
 * 
 * @description 交易日志管理
 * @author Luke
 * @Date Feb 28, 2018
 */
@Controller
@RequestMapping("/admin/downTradeLog")
public class AdminDownTradeLogController extends BaseController {

	@Autowired
	private IDownTradeLogDAO downTradeLogDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index")
	public ModelAndView index(SearchDownTradeLogViewData searchData) throws ControllerException {
		return showIndex(getTemplate("/admin/downTradeLog/index"), searchData);
	}

	private ModelAndView showIndex(String jsp, SearchDownTradeLogViewData searchData) {
		ModelAndView mav = new ModelAndView(jsp);
		IPagedList<DownTradeLog> items = downTradeLogDAO.searchItems(searchData);
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		return mav;
	}

	private ListResponseData<DownTradeLogViewData> convertToList(IPagedList<DownTradeLog> items) {
		ListResponseData<DownTradeLogViewData> response = new ListResponseData<DownTradeLogViewData>();
		if (items != null) {
			for (DownTradeLog item : items) {
				response.addItem(convert(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private DownTradeLogViewData convert(DownTradeLog item) {
		DownTradeLogViewData viewData = SaftyBeanUtils.cloneTo(item, DownTradeLogViewData.class);
		return viewData;
	}
}
