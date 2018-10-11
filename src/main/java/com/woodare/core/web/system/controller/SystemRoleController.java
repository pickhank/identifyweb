package com.woodare.core.web.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.woodare.core.jpa.data.systemrole.SearchSystemRoleData;
import com.woodare.core.jpa.model.SystemRole;
import com.woodare.core.jpa.service.ISystemRoleDAO;
import com.woodare.core.jpa.service.ISystemRoleRightDAO;
import com.woodare.core.jpa.service.ISystemRoleUserDAO;
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.core.web.system.viewdata.systemrole.SearchSystemRoleViewData;
import com.woodare.core.web.system.viewdata.systemrole.SystemRoleViewData;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.utils.SaftyBeanUtils;

/**
 * 
 * @author lu_feng
 *
 */
@Controller
@RequestMapping("/system/role")
public class SystemRoleController extends BaseController {
    private static Logger log = Logger.getLogger(SystemRoleController.class);

	@Autowired
	private ISystemRoleDAO systemRoleDAO;
	
	@Autowired
	private ISystemRoleRightDAO systemRoleRightDAO;
	
	@Autowired
	private ISystemRoleUserDAO systemRoleUserDAO;
	
	/**
	 * 
	 * @param searchData
	 * @return
	 */
    @Transactional(propagation = Propagation.NEVER)
    @RequestMapping(value = "/index")
	public ModelAndView index(SearchSystemRoleViewData searchData) {
    	ModelAndView mav = new ModelAndView("/system/role/index");
    	
    	searchData.setAutoRedirect(false);
    	searchData.setPageSize(Integer.MAX_VALUE);
    	 
    	List<SystemRole> modelLst = systemRoleDAO.searchSystemRoles(searchData);
    	
    	List<SystemRoleViewData> items = new ArrayList<SystemRoleViewData>();
    	
		if(modelLst != null && modelLst.size() > 0) {
			for(SystemRole item : modelLst) {
				SystemRoleViewData viewData = SaftyBeanUtils.cloneTo(item, SystemRoleViewData.class);
				items.add(viewData);
			}
		}
		mav.addObject("items", items);
		return mav;
	}
    
    /**
     * 
     * @param searchData
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/batchSave", method=RequestMethod.POST)
	public ModelAndView doIndex(SearchSystemRoleViewData searchData) {
    	if(searchData.getItemIds() != null && searchData.getItemIds().size() > 0) {
        	List<String> ids = new ArrayList<String>();
        	for(String id : searchData.getItemIds()) {
        		ids.add(id);
        	}
        	if(ids.size() > 0) {
        		SearchSystemRoleData searchItemData = new SearchSystemRoleData();
        		searchItemData.setIds(searchData.getItemIds());
        		searchItemData.setAutoRedirect(false);
        		searchItemData.setPageSize(Integer.MAX_VALUE);
        		List<SystemRole> models = systemRoleDAO.searchSystemRoles(searchItemData);
        		
    			for (int i = 0; i < searchData.getItemIds().size(); i++) {
    				String id = searchData.getItemIds().get(i);
    				if(StringUtils.isNotEmpty(id)) {
    					for(SystemRole model : models) {
            				if(model.getId().equals(id)) {
            					model.setRoleName(searchData.getRoleNames().get(i));
            					systemRoleDAO.update(model);
            					break;
            				}
            			}
    				}
    				else {
    					SystemRole model = new SystemRole();
    					model.setRoleName(searchData.getRoleNames().get(i));
    					model.setSystemMenuFlag(false);
    					systemRoleDAO.save(model);
    				}
        		}
        	}
    	}
		return new ModelAndView("redirect:/system/role/index?noiframe=true");
	}

    /**
     * 
     * @param roleId
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/delete/{roleId}", method = RequestMethod.POST)
	public @ResponseBody AjaxResponseData<Boolean> deleteMenu(@PathVariable String roleId) {
    	AjaxResponseData<Boolean> ret = new AjaxResponseData<Boolean>(false);
    	try {
    		SystemRole role = systemRoleDAO.findOne(roleId);
    		if(role.getSystemMenuFlag()) {
    			ret.setMessage("系统保留角色禁止操作!");
    		}
    		else if(systemRoleUserDAO.hasUserByRoleId(roleId)) {
    			ret.setMessage("角色正在使用中!");
    		}
    		else {
    			systemRoleRightDAO.deleteAllByRoleId(roleId);
    			systemRoleDAO.delete(role);
    		}
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
