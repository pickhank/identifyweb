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

import com.thirdparty.passway._base.MD5Tool;
import com.woodare.core.base.BaseController;
import com.woodare.core.exception.ControllerException;
import com.woodare.core.util.AesUtils;
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.core.web.common.viewdata.ListResponseData;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.busi.service.CacheDataChangeService;
import com.woodare.template.helper.cache.PasswayDspMerchants;
import com.woodare.template.jpa.model.PasswayDspMerchant;
import com.woodare.template.jpa.persistence.data.passwaydspmerchant.PasswayDspMerchantData;
import com.woodare.template.jpa.persistence.persistence.IPasswayDspMerchantDAO;
import com.woodare.template.web.viewdata.passwaydspmerchant.PasswayDspMerchantViewData;
import com.woodare.template.web.viewdata.passwaydspmerchant.SearchPasswayDspMerchantViewData;

/**
 * ClassName: PasswayDspMerchantController
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2015/11/15
 */
@Controller
@RequestMapping("/admin/passwayDspMerchant")
public class AdminPasswayDspMerchantController extends BaseController {
	private static Logger log = Logger.getLogger(AdminPasswayDspMerchantController.class);

	@Autowired
	private CacheDataChangeService cacheDataChangeService;

	@Autowired
	private IPasswayDspMerchantDAO passwayDspMerchantDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index")
	public ModelAndView index(SearchPasswayDspMerchantViewData searchData) throws ControllerException {
		return showIndex("/admin/passwayDspMerchant/index", searchData);
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(PasswayDspMerchantViewData searchData) throws ControllerException {
		ModelAndView mav = new ModelAndView("/admin/passwayDspMerchant/add");
		PasswayDspMerchantViewData item = null;
		if (StringUtils.isNotEmpty(searchData.getId())) {
			PasswayDspMerchant model = passwayDspMerchantDAO.findOne(searchData.getId());
			item = convertDetails(model);
		}
		else {
			item = new PasswayDspMerchantViewData();
		}
		mav.addObject("item", item);
		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPost(PasswayDspMerchantViewData item) {
		PasswayDspMerchant model = null;

		String error = validData(item);
		if (StringUtils.isNotEmpty(error)) {
			return alertFailed("/admin/passwayDspMerchant/add", item, error);
		}

		PasswayDspMerchantData existed = PasswayDspMerchants.get(item.getChannel(), item.getChannelAccNo());
		if(existed != null) {
			if(!existed.getId().equals(item.getId())) {
				return alertFailed(getTemplate("/admin/passwayDspMerchant/add"), item, "渠道[" + item.getChannel() + "]已存在相同账户数据[" + item.getChannelAccNo() + "]");
			}
		}
		
		try {
			// 渠道不为空，且加密密钥不为空
			if(item.getChannel() != null && StringUtils.isNotEmpty(item.getChannel().getAesKey())) {
				if(StringUtils.isNotEmpty(item.getSignKeyPlain())) {
					item.setSignKey(AesUtils.encrypt(item.getSignKeyPlain(), item.getChannel().getAesKey()));
				}
				if(StringUtils.isNotEmpty(item.getEncKeyPlain())) {
					item.setEncKey(AesUtils.encrypt(item.getEncKeyPlain(), item.getChannel().getAesKey()));
				}
			}	
		}
		catch(Exception e) {
			return alertFailed(getTemplate("/admin/passwayDspMerchant/add"), item, "数据加密处理失败");
		}
		
		if (StringUtils.isNotEmpty(item.getId())) {
			model = passwayDspMerchantDAO.findOne(item.getId());
		}
		else {
			model = new PasswayDspMerchant();
		}
		SaftyBeanUtils.copyNotEmptyProperties(item, model, new String[] { "id", "deleteStatus", "createDate", "updateDate" });
		
		if (StringUtils.isNotEmpty(item.getId())) {
			this.passwayDspMerchantDAO.update(model);
		}
		else {
			this.passwayDspMerchantDAO.save(model);
		}

		cacheDataChangeService.notifyOthers(PasswayDspMerchantData.class);

		return alertSuccess("/admin/passwayDspMerchant/add", convertDetails(model));
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
				PasswayDspMerchant model = passwayDspMerchantDAO.findOne(itemId);
				/* 如果没有返回试图，提示用户 */
				if (model == null) {
					message = "数据已被删除，请重新刷新画面！";
				}
				else {
					passwayDspMerchantDAO.delete(model);
					message = "删除成功！";
					ret.setState(EResponseState.Successful);

					cacheDataChangeService.notifyOthers(PasswayDspMerchantData.class);
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

	private ModelAndView showIndex(String jsp, SearchPasswayDspMerchantViewData searchData) {
		ModelAndView mav = new ModelAndView(jsp);
		IPagedList<PasswayDspMerchant> items = passwayDspMerchantDAO.searchItems(searchData);
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		return mav;
	}

	private ListResponseData<PasswayDspMerchantViewData> convertToList(IPagedList<PasswayDspMerchant> items) {
		ListResponseData<PasswayDspMerchantViewData> response = new ListResponseData<PasswayDspMerchantViewData>();
		if (items != null) {
			for (PasswayDspMerchant item : items) {
				response.addItem(convert(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private String validData(PasswayDspMerchantViewData item) {
		String error = "";
		return error;
	}

	private PasswayDspMerchantViewData convert(PasswayDspMerchant model) {
		PasswayDspMerchantViewData viewData = SaftyBeanUtils.cloneTo(model, PasswayDspMerchantViewData.class, new String[] { "deleteStatus" });
		
		try {
			// 渠道不为空，且加密密钥不为空
			if(viewData.getChannel() != null && StringUtils.isNotEmpty(viewData.getChannel().getAesKey())) {
				if(StringUtils.isNotEmpty(viewData.getSignKey())) {
					viewData.setSignKeyMd5(MD5Tool.md5(AesUtils.decrypt(viewData.getSignKey(), viewData.getChannel().getAesKey())).substring(0, 8));
				}
				if(StringUtils.isNotEmpty(viewData.getEncKey())) {
					viewData.setEncKeyMd5(MD5Tool.md5(AesUtils.decrypt(viewData.getEncKey(), viewData.getChannel().getAesKey())).substring(0, 8));
				}
			}
		}
		catch(Exception e) {
			log.warn("生成效验值处理失败");
		}
		
		return viewData;
	}

	private PasswayDspMerchantViewData convertDetails(PasswayDspMerchant model) {
		PasswayDspMerchantViewData item = convert(model);
		return item;
	}
}
