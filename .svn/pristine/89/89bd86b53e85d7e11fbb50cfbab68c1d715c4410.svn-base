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
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.exception.ControllerException;
import com.woodare.core.jpa.model.SystemUser;
import com.woodare.core.jpa.model.data.EnumUserRole;
import com.woodare.core.jpa.service.ISystemUserDAO;
import com.woodare.core.web.common.viewdata.ListResponseData;
import com.woodare.core.web.system.viewdata.systemuser.SearchSystemUserViewData;
import com.woodare.core.web.system.viewdata.systemuser.SystemUserViewData;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.utils.JavaMD5Hash;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.template.jpa.model.DownAgent;
import com.woodare.template.jpa.persistence.persistence.IDownAgentDAO;
import com.woodare.template.web.viewdata.downagent.SearchDownAgentViewData;

/**
 * ClassName: AdminUserController
 * 
 * @description
 * @author Xinxing Jiang
 * @Date May 12, 2015
 */
@Controller
@RequestMapping("/admin/agent")
public class AdminAgentController extends BaseController {

	private static Logger log = Logger.getLogger(AdminAgentController.class);

	@Autowired
	private ISystemUserDAO systemUserDAO;

	@Autowired
	private IDownAgentDAO downAgentDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index")
	public ModelAndView index(SearchSystemUserViewData searchData) throws ControllerException {
		searchData.setUserRole(EnumUserRole.AGENT);
		searchData.setOrderString("orderNo asc");
		return showIndex(getTemplate("/admin/agent/index"), searchData);
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(SystemUserViewData data) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/admin/agent/add"));
		SystemUser item = null;
		if (StringUtils.isNotEmpty(data.getId())) {
			item = systemUserDAO.findOne(data.getId());
		}
		mav.addObject("item", convertDetails(item));
		SearchDownAgentViewData searchData = new SearchDownAgentViewData();
		searchData.setPageIndex(0);
		searchData.setPageSize(Integer.MAX_VALUE);
		searchData.setOrderString("orderNo asc");
		IPagedList<DownAgent> items = downAgentDAO.searchDownAgents(searchData);
		mav.addObject("downagents", items);
		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addP(SystemUserViewData data) throws ControllerException {
		String error = validError(data);
		if (StringUtils.isEmpty(error)) {
			SystemUser item = null;
			if (StringUtils.isNotEmpty(data.getId())) {
				item = systemUserDAO.findOne(data.getId());
			}
			boolean update = true;
			if (item == null) {
				item = new SystemUser();
				item.setUserRole(EnumUserRole.AGENT);
				if (StringUtils.isEmpty(data.getPassword())) {
					item.setPassword(JavaMD5Hash.md5("P" + data.getUsername()));
				}
				else {
					item.setPassword(JavaMD5Hash.md5(data.getPassword()));
				}
				update = false;
			}
			else {
				if (StringUtils.isEmpty(data.getPassword())) {
					item.setPassword(item.getPassword());
				}
				else {
					item.setPassword(JavaMD5Hash.md5(data.getPassword()));
				}
			}
			if (StringUtils.isNotEmpty(data.getUsername())) {
				if (data.getUsername().indexOf("P") != -1) {
					item.setUsername(data.getUsername());
				}
				else {
					item.setUsername("P" + data.getUsername());
				}
			}
			if (StringUtils.isNotEmpty(data.getEmail())) {
				item.setEmail(data.getEmail());
			}
			// String psd = data.getPassword();
			// if (StringUtils.isEmpty(psd)) {
			// psd = data.getUsername();
			// }
			// item.setPassword(JavaMD5Hash.md5(data.getPassword()));
			item.setIsAdminFlag(false);
			if (update) {
				systemUserDAO.update(item);
			}
			else {
				systemUserDAO.save(item);
			}
			return alertSuccess(getTemplate("/admin/agent/add"), convertDetails(item));
		}
		else {
			ModelAndView mav = new ModelAndView(getTemplate("/admin/agent/add"));
			mav.addObject("error", error);
			mav.addObject("item", data);
			SearchDownAgentViewData searchData = new SearchDownAgentViewData();
			searchData.setPageIndex(0);
			searchData.setPageSize(Integer.MAX_VALUE);
			searchData.setOrderString("orderNo asc");
			IPagedList<DownAgent> items = downAgentDAO.searchDownAgents(searchData);
			mav.addObject("downagents", items);
			return mav;
		}
	}

	private ModelAndView showIndex(String jsp, SearchSystemUserViewData searchData) {
		ModelAndView mav = new ModelAndView(jsp);
		IPagedList<SystemUser> items = systemUserDAO.searchUsers(searchData);
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		return mav;
	}

	private ListResponseData<SystemUserViewData> convertToList(IPagedList<SystemUser> items) {
		ListResponseData<SystemUserViewData> response = new ListResponseData<SystemUserViewData>();
		if (items != null) {
			for (SystemUser item : items) {
				response.addItem(convert(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	private SystemUserViewData convert(SystemUser item) {
		SystemUserViewData viewData = SaftyBeanUtils.cloneTo(item, SystemUserViewData.class);
		return viewData;
	}

	private SystemUserViewData convertDetails(SystemUser item) {
		SystemUserViewData viewData = convert(item);
		return viewData;
	}

	private String validError(SystemUserViewData data) {
		String error = "";
		if (StringUtils.isEmpty(data.getUsername())) {
			error += "用户名不能为空<br/>";
		}
		if (StringUtils.isNotEmpty(data.getUsername()) && StringUtils.isEmpty(data.getId())) {
			SystemUser systemUser = systemUserDAO.findByUsername("P" + data.getUsername());
			if (systemUser != null) {
				error += "该用户名已经被创建过了<br/>";
			}
		}
		return error;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/delete/{itemId}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String itemId) {
		String message = "";
		try {
			if (StringUtils.isNotEmpty(itemId)) {
				SystemUser model = systemUserDAO.findOne(itemId);
				if (model == null) {
					message = "数据已被删除，请重新刷新画面！";
				}
				else {
					systemUserDAO.delete(model);
				}
				message = "删除成功！";
			}
			else {
				message = "数据已被删除，请重新刷新画面！";
			}
		} catch (Exception e) {
			log.error(e, e);
			message = "删除失败！ " + e.getMessage();
		}
		return alertMessage(message);
	}
}
