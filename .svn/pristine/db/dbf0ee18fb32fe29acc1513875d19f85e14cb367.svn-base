package com.woodare.core.web.system.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.constant.CommonConstant;
import com.woodare.core.jpa.data.systemmenu.SystemMenuData;
import com.woodare.core.jpa.model.SystemMenu;
import com.woodare.core.jpa.model.SystemRole;
import com.woodare.core.jpa.model.SystemRoleRight;
import com.woodare.core.jpa.service.ISystemMenuDAO;
import com.woodare.core.jpa.service.ISystemRoleDAO;
import com.woodare.core.jpa.service.ISystemRoleRightDAO;
import com.woodare.core.web.common.viewdata.CodeAndName;
import com.woodare.core.web.system.viewdata.systemmenu.SystemMenuViewData;
import com.woodare.core.web.system.viewdata.systemroleright.SearchSystemRoleRightViewData;
import com.woodare.core.web.system.viewdata.systemroleright.SystemRoleRightViewData;
import com.woodare.core.web.taglibs.BusiCommonUtils;
import com.woodare.framework.component.SystemCache;
import com.woodare.framework.utils.SaftyBeanUtils;

/**
 * @author lu_feng
 */
@Controller
@RequestMapping("/system/roleRight")
public class SystemRoleRightController extends BaseController {
	// private static Logger log = Logger.getLogger(SystemRoleRightController.class);

	@Autowired
	private ISystemRoleRightDAO systemRoleRightDAO;

	@Autowired
	private ISystemMenuDAO systemMenuDAO;

	@Autowired
	private ISystemRoleDAO systemRoleDAO;

	// @Autowired
	// private MessageSource messageSource;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(SearchSystemRoleRightViewData searchData) {
		// log.debug("List role right with:" + searchData);

		ModelAndView mav = new ModelAndView("/system/roleRight/index");

		searchData.setPageIndex(0);
		searchData.setAutoRedirect(false);
		searchData.setPageSize(Integer.MAX_VALUE);

		if (StringUtils.isEmpty(searchData.getRoleId())) {
			searchData.setRoleId(CommonConstant.SYSTEM_ROLE.SUPER_USER);
		}
		List<SystemMenu> modelLst = systemMenuDAO.findAll();
		List<SystemMenuViewData> menuLst = SaftyBeanUtils.cloneToList(modelLst, SystemMenuViewData.class);
		menuLst = BusiCommonUtils.sortForMenuManage(menuLst);
		List<SystemRoleRight> rightLst = systemRoleRightDAO.searchSystemRoleRights(searchData);

		if (rightLst != null && rightLst.size() > 0) {
			for (SystemRoleRight right : rightLst) {
				String menuId = right.getMenuId();
				for (SystemMenuViewData menu : menuLst) {
					if (menu.getId().equals(menuId)) {
						menu.setCheckedFlag(true);
						break;
					}
				}
			}
		}
		SystemRoleRightViewData viewData = new SystemRoleRightViewData();
		viewData.setMenus(menuLst);
		viewData.setRoleId(searchData.getRoleId());
		java.util.List<CodeAndName> roles = new ArrayList<CodeAndName>();
		List<SystemRole> roleLst = systemRoleDAO.findAll();
		if (roleLst != null && roleLst.size() > 0) {
			for (SystemRole item : roleLst) {
				CodeAndName can = new CodeAndName(item.getRoleName(), item.getId());
				roles.add(can);
			}
		}
		mav.addObject("rightData", viewData);
		mav.addObject("roles", roles);
		mav.addObject("searchData", searchData);
		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String save(SystemRoleRightViewData data) {
		String roleId = data.getRoleId();
		List<String> menuIds = data.getMenuIds();

		systemRoleRightDAO.deleteAllByRoleId(roleId);

		if (menuIds != null) {
			for (String menuId : menuIds) {
				SystemRoleRight right = new SystemRoleRight();
				right.setMenuId(menuId);
				right.setRoleId(roleId);
				systemRoleRightDAO.save(right);
			}
		}

		SystemCache.fireLoadEvent(SystemMenuData.class);

		return "redirect:/system/roleRight/index?noiframe=true&roleId=" + roleId;
	}
}
