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
import java.util.ArrayList;
import java.util.List;

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
import com.woodare.core.jpa.model.SystemRole;
import com.woodare.core.jpa.model.SystemUser;
import com.woodare.core.jpa.model.data.EnumUserRole;
import com.woodare.core.jpa.service.ISystemRoleDAO;
import com.woodare.core.jpa.service.ISystemUserDAO;
import com.woodare.core.util.JavaMD5Hash;
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.core.web.common.viewdata.ListResponseData;
import com.woodare.core.web.system.viewdata.systemrole.SearchSystemRoleViewData;
import com.woodare.core.web.system.viewdata.systemrole.SystemRoleViewData;
import com.woodare.core.web.system.viewdata.systemuser.SearchSystemUserViewData;
import com.woodare.core.web.system.viewdata.systemuser.SystemUserViewData;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;


/**
 * ClassName: TransfController
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2015/11/15
 */
@Controller
@RequestMapping("/admin/user")
public class SysUserController extends BaseController {
	private static Logger log = Logger.getLogger(SysUserController.class);

	@Autowired
	private ISystemUserDAO systemUserDAO;
	
	@Autowired
	private ISystemRoleDAO systemRoleDAO;
	
	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index")
	public ModelAndView index(SearchSystemUserViewData searchData) throws ControllerException {
		if (searchData.getUserRole() == null) {
			List<EnumUserRole> roles = new ArrayList<EnumUserRole>();
			roles.add(EnumUserRole.SUPERVISOR);
			roles.add(EnumUserRole.ADMIN);
			roles.add(EnumUserRole.LIQUIDATING);
			roles.add(EnumUserRole.OPERATION);
			searchData.setUserRoles(roles);
		}
		return showIndex("/admin/user/index", searchData);
	}

	private ModelAndView showIndex(String jsp, SearchSystemUserViewData searchData) {
		ModelAndView mav = new ModelAndView(jsp);
		IPagedList<SystemUser> items = systemUserDAO.searchUsers(searchData);
		mav.addObject("search", searchData);
		mav.addObject("res", convertToList(items));
		mav.addObject("accRole", getUser().getUserRole());
		return mav;
	}
	
	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(SystemUserViewData searchData) throws ControllerException {
		ModelAndView mav = new ModelAndView("/admin/user/add");
		SystemUserViewData item = null;
		if (StringUtils.isNotEmpty(searchData.getId())) {
			SystemUser model = systemUserDAO.findOne(searchData.getId());
			item = convertDetails(model);
		} else {
			item = new SystemUserViewData();
		}
		mav.addObject("item", item);
		return mav;
	}
	
	@Transactional(propagation = Propagation.NEVER)
    @RequestMapping(value = "/indexRole")
	public ModelAndView index(SearchSystemRoleViewData searchData) {
    	ModelAndView mav = new ModelAndView("/admin/user/indexRole");
    	
    	searchData.setAutoRedirect(false);
    	searchData.setPageSize(Integer.MAX_VALUE);
    	 
    	List<SystemRole> modelLst = systemRoleDAO.searchSystemRoles(searchData);
    	
    	List<SystemRoleViewData> items = new ArrayList<SystemRoleViewData>();
    	
		if(modelLst != null && modelLst.size() > 0) {
			for(SystemRole item : modelLst) {
				SystemRoleViewData viewData = SaftyBeanUtils.cloneTo(item, SystemRoleViewData.class);
				items.add(viewData);
			}
			System.out.println(items);
			mav.addObject("items", items);
		}
		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addPost(SystemUserViewData item) {
		SystemUser model = null;

		String error = validData(item);
		if (StringUtils.isNotEmpty(error)) {
			return alertFailed("/admin/user/add", item, error);
		}

		if (StringUtils.isNotEmpty(item.getId())) {
			model = systemUserDAO.findOne(item.getId());
		} else {
			model = new SystemUser();
			model.setPassword(JavaMD5Hash.md5(item.getUsername()));
		}

		if (StringUtils.isNotEmpty(item.getUsername())) {
			model.setUsername(item.getUsername());
		}
		if (StringUtils.isNotEmpty(item.getPassword())) {
			model.setPassword(JavaMD5Hash.md5(item.getPassword()));
		}

		if (StringUtils.isNotEmpty(item.getEmail())) {
			model.setEmail(item.getEmail());
		}
		if (StringUtils.isNotEmpty(item.getUserRole())) {
			model.setUserRole(item.getUserRole());
		}
		// 设置成超级管理
		model.setIsAdminFlag(true);
		
		if (StringUtils.isNotEmpty(item.getId())) {
			this.systemUserDAO.update(model);
		} else {
			this.systemUserDAO.save(model);
		}
		return alertSuccess("/admin/user/add", convertDetails(model));
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
	
	private String validData(SystemUserViewData item) {
		String error = "";
		if (StringUtils.isEmpty(item.getUsername())) {
			error += "用户名不能为空<br/>";
		}
		return error;
	}


	private SystemUserViewData convert(SystemUser model) {
		SystemUserViewData item = SaftyBeanUtils.cloneTo(model, SystemUserViewData.class, new String[] { "deleteStatus" });
		return item;
	}

	private SystemUserViewData convertDetails(SystemUser model) {
		SystemUserViewData item = convert(model);
		return item;
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
				SystemUser model = systemUserDAO.findOne(itemId);
				if (model == null) {
					message = "数据已被删除，请重新刷新画面！";
				} else {
					systemUserDAO.delete(model);
					message = "删除成功！";
					ret.setState(EResponseState.Successful);
				}
			} else {
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
