package com.woodare.core.web.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.jpa.data.systemmenu.SystemMenuData;
import com.woodare.core.jpa.model.SystemMenu;
import com.woodare.core.jpa.service.ISystemMenuDAO;
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.core.web.system.viewdata.systemmenu.SystemMenuViewData;
import com.woodare.core.web.taglibs.BusiCommonUtils;
import com.woodare.framework.component.SystemCache;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.model.data.EnumSystemMenuType;
import com.woodare.framework.utils.JsonUtils;
import com.woodare.framework.utils.SaftyBeanUtils;

/**
 * 
 * @author lu_feng
 *
 */
@Controller
@RequestMapping("/system/menu")
public class SystemMenuController extends BaseController {
    private static Logger log = Logger.getLogger(SystemMenuController.class);

	@Autowired
	private ISystemMenuDAO systemMenuDAO;
	
	@Autowired
	private MessageSource messageSource;
	
    @Transactional(propagation = Propagation.NEVER)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
    	ModelAndView mav = new ModelAndView("/system/menu/index");
    	List<SystemMenu> modelLst = systemMenuDAO.findAll();
    	List<SystemMenuData> menuLst = new ArrayList<SystemMenuData>();
    	
		if(modelLst != null && modelLst.size() > 0) {
			for(SystemMenu item : modelLst) {
				menuLst.add(SaftyBeanUtils.cloneTo(item, SystemMenuData.class));
			}
		}
		menuLst = BusiCommonUtils.sortForMenuManage(menuLst);
		mav.addObject("menus", menuLst);
		return mav;
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/index", method=RequestMethod.POST)
	public ModelAndView doIndex(SystemMenuViewData viewData) {
    	ModelAndView mav = new ModelAndView("/system/menu/index");
    	List<SystemMenu> modelLst = systemMenuDAO.findAll();
    	List<SystemMenuData> menuLst = new ArrayList<SystemMenuData>();
    	
    	if(viewData.getIds() != null) {
    		int idx = 0;
    		for(String menuId : viewData.getIds()) {
    			SystemMenu menu = systemMenuDAO.findOne(menuId);
    			menu.setSortNum(viewData.getSortNums()[idx]);
    			menu.setName(viewData.getNames()[idx]);
    			idx++;
    			systemMenuDAO.update(menu);
    		}
            SystemCache.fireLoadEvent(SystemMenuData.class);
    	}
    	
		if(modelLst != null && modelLst.size() > 0) {
			for(SystemMenu item : modelLst) {
				menuLst.add(SaftyBeanUtils.cloneTo(item, SystemMenuData.class));
			}
		}
		menuLst = BusiCommonUtils.sortForMenuManage(menuLst);
		mav.addObject("menus", menuLst);
		return mav;
	}
    
    @Transactional(propagation = Propagation.NEVER)
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView menuDetail(SystemMenuViewData systemMenuData) {
    	ModelAndView mav = new ModelAndView("/system/menu/detail");
    	
    	if(StringUtils.isNotEmpty(systemMenuData.getId())) {
    		SystemMenu model = systemMenuDAO.findOne(systemMenuData.getId());
    		SaftyBeanUtils.copyProperties(model, systemMenuData);
    	}
    	if(StringUtils.isNotEmpty(systemMenuData.getParentMenuId())) {
			systemMenuData.setParentMenuName(systemMenuDAO.findOne(systemMenuData.getParentMenuId()).getName());
			systemMenuData.setParentMenuId(systemMenuData.getParentMenuId());
		}
		mav.addObject("menu", systemMenuData);
		return mav;
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
	public @ResponseBody AjaxResponseData<Boolean> saveMenuDetail(@Valid SystemMenuViewData systemMenuData, BindingResult result) {
    	AjaxResponseData<Boolean> ret = new AjaxResponseData<Boolean>(false);
    	
    	// TODO:
    	if(result.hasErrors()) {
    		Map<String, String> errorMsgs = new HashMap<String, String>();
    		for (Object object : result.getAllErrors()) {
    		    if(object instanceof FieldError) {
    		        FieldError fieldError = (FieldError) object;
    		        String message = messageSource.getMessage(fieldError, null);
    		        if(!errorMsgs.containsKey(fieldError.getField())) {
    		        	errorMsgs.put(fieldError.getField(), message);
    		        }
    		    }
    		    if(object instanceof ObjectError) {
    		        ObjectError objectError = (ObjectError) object;
    		        log.warn(objectError.getCode());
    		    }
    		}
    		ret.setState(EResponseState.FormErrorMsg);
    		ret.setMessage(JsonUtils.toJson(errorMsgs));
    	}
    	
    	try {
    		EnumSystemMenuType menuType = EnumSystemMenuType.NAVIGATION;
        	if(StringUtils.isNotEmpty(systemMenuData.getParentMenuId())) {
        		SystemMenu parentMenu = systemMenuDAO.findOne(systemMenuData.getParentMenuId());
        		if(parentMenu.getMenuType() == EnumSystemMenuType.NAVIGATION) {
        			menuType = EnumSystemMenuType.SUB_NAVIGATION;
        		}
        		else if(parentMenu.getMenuType() == EnumSystemMenuType.SUB_NAVIGATION) {
        			menuType = EnumSystemMenuType.ACTION;
        		}
        	}
        	SystemMenu menuModel = null;
        	if(StringUtils.isNotEmpty(systemMenuData.getId())) {
        		menuModel = systemMenuDAO.findOne(systemMenuData.getId());
        		menuModel.setName(systemMenuData.getName());
        		menuModel.setPageUrl(systemMenuData.getPageUrl());
        		systemMenuDAO.update(menuModel);
        	}
        	else {
        		menuModel = new SystemMenu();
        		menuModel.setMenuType(menuType);
        		menuModel.setParentMenuId(systemMenuData.getParentMenuId());
        		menuModel.setName(systemMenuData.getName());
        		menuModel.setPageUrl(systemMenuData.getPageUrl());
        		menuModel.setSystemMenuFlag(false);
        		systemMenuDAO.save(menuModel);
        	}
            SystemCache.fireLoadEvent(SystemMenuData.class);
        	ret.setPayload(true);
        	ret.setState(EResponseState.Successful);
    	}
    	catch(Exception e) {
    		log.error(e, e);
    		ret.setState(EResponseState.CustomMsg);
    		ret.setMessage("服务器出现异常，请重试！");
    	}
    	
		return ret;
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/delete/{menuId}", method = RequestMethod.POST)
	public AjaxResponseData<Boolean> deleteMenu(@PathVariable String menuId) {
    	AjaxResponseData<Boolean> ret = new AjaxResponseData<Boolean>(false);
    	try {
        	if(StringUtils.isNotEmpty(menuId)) {
        		if(systemMenuDAO.hasChildMenus(menuId)) {
        			ret.setState(EResponseState.CustomMsg);
        			ret.setMessage("请先删除子节点，再删除本菜单！");
        			return ret;
        		}
        		else {
        			SystemMenu menuModel = systemMenuDAO.findOne(menuId);
        			if(menuModel.getSystemMenuFlag() != null && menuModel.getSystemMenuFlag()) {
            			ret.setState(EResponseState.CustomMsg);
            			ret.setMessage("系统保留菜单，禁止操作！");
            			return ret;
        			}
            		systemMenuDAO.delete(menuModel);
        		}
        	}
            SystemCache.fireLoadEvent(SystemMenuData.class);
        	ret.setPayload(true);
        	ret.setState(EResponseState.Successful);
    	}
    	catch(Exception e) {
    		log.error(e, e);
    		ret.setState(EResponseState.CustomMsg);
    		ret.setMessage("服务器出现异常，请重试！");
    	}
		return ret;
	}
}
