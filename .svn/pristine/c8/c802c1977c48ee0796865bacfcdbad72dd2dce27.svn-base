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
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.core.web.common.viewdata.ListResponseData;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.busi.service.CacheDataChangeService;
import com.woodare.template.helper.cache.Kbins;
import com.woodare.template.jpa.model.Kbin;
import com.woodare.template.jpa.persistence.data.kbin.KbinData;
import com.woodare.template.jpa.persistence.persistence.IKbinDAO;
import com.woodare.template.web.viewdata.kbin.KbinViewData;
import com.woodare.template.web.viewdata.kbin.SearchKbinViewData;

/**
 * ClassName: AdminKbinController
 * 
 * @description 卡bin管理
 * @author Luke
 * @Date Feb 28, 2018
 */
@Controller
@RequestMapping("/admin/kbin")
public class AdminKbinController extends BaseController {

	private static Logger log = Logger.getLogger(AdminKbinController.class);

	@Autowired
	private IKbinDAO kbinDAO;

	@Autowired
	private CacheDataChangeService cacheDataChangeService;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index")
	public ModelAndView index(SearchKbinViewData searchData) throws ControllerException {
		return showIndex("/admin/kbin/index", searchData);
	}

	private ModelAndView showIndex(String jsp, SearchKbinViewData searchData) {
		ModelAndView mav = new ModelAndView(jsp);
		
		SearchKbinViewData dupliSearchData = SaftyBeanUtils.cloneTo(searchData, SearchKbinViewData.class);
		if(StringUtils.isNotEmpty(dupliSearchData.getKeywords())) {
			KbinData existedKbin = Kbins.getByCardNo(dupliSearchData.getKeywords());
			if(existedKbin != null) {
				dupliSearchData.setKeywords(null);
				dupliSearchData.setCardLength(existedKbin.getCardLength());
				dupliSearchData.setStartNum(existedKbin.getStartNum());
			}
		}
		IPagedList<Kbin> items = kbinDAO.searchKbins(dupliSearchData);
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(KbinViewData data) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/admin/kbin/add"));
		Kbin item = null;
		if (StringUtils.isNotEmpty(data.getId())) {
			item = kbinDAO.findOne(data.getId());
		}
		SearchKbinViewData search = new SearchKbinViewData();
		search.setPageIndex(0);
		search.setPageSize(Integer.MAX_VALUE);
		mav.addObject("item", convertDetails(item));
		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addP(KbinViewData data) throws ControllerException {
		String error = validError(data);
		if (StringUtils.isEmpty(error)) {
			Kbin item = null;
			if (StringUtils.isNotEmpty(data.getId())) {
				item = kbinDAO.findOne(data.getId());
			}
			boolean update = true;
			if (item == null) {
				item = new Kbin();
				update = false;
			}
			SaftyBeanUtils.copyProperties(data, item, new String[] { "id", "createDate", "updateDate", "deleteStatus" });
			if (update) {
				kbinDAO.update(item);
			}
			else {
				kbinDAO.save(item);
			}
			cacheDataChangeService.notifyOthers(KbinData.class);
			return alertSuccess(getTemplate("/admin/kbin/add"), convertDetails(item));
		}
		else {
			return alertFailed(getTemplate("/admin/kbin/add"), data, error);
		}
	}

	private ListResponseData<KbinViewData> convertToList(IPagedList<Kbin> items) {
		ListResponseData<KbinViewData> response = new ListResponseData<KbinViewData>();
		if (items != null) {
			for (Kbin item : items) {
				response.addItem(convert(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private KbinViewData convert(Kbin item) {
		KbinViewData viewData = SaftyBeanUtils.cloneTo(item, KbinViewData.class);
		return viewData;
	}

	private KbinViewData convertDetails(Kbin item) {
		KbinViewData viewData = convert(item);
		return viewData;
	}

	private String validError(KbinViewData data) {
		String error = "";
		return error;
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
				Kbin model = kbinDAO.findOne(itemId);
				if (model == null) {
					message = "数据已被删除，请重新刷新画面！";
				}
				else {
					kbinDAO.delete(model);

					cacheDataChangeService.notifyOthers(KbinData.class);

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

}
