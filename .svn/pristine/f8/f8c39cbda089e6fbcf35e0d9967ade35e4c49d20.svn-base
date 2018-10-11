package com.woodare.core.web.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.exception.ControllerException;
import com.woodare.core.jpa.data.systemproperties.SearchSystemPropertiesData;
import com.woodare.core.jpa.data.systemproperties.SystemPropertiesData;
import com.woodare.core.jpa.model.SystemProperties;
import com.woodare.core.jpa.service.ISystemPropertiesDAO;
import com.woodare.core.web.system.viewdata.systemproperties.SystemPropertiesViewData;
import com.woodare.framework.utils.BrowserTypeUtils;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.template.busi.service.CacheDataChangeService;

/**
 * @author lu_feng
 */
@Controller
@RequestMapping("/admin/system")
public class SystemPropertiesController {
	// private static Logger log = Logger.getLogger(SystemPropertiesController.class);

	@Autowired
	private CacheDataChangeService cacheDataChangeService;

	@Autowired
	private ISystemPropertiesDAO systemPropertiesDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate(request));
		SearchSystemPropertiesData searchPropData = new SearchSystemPropertiesData();
		searchPropData.setPageSize(Integer.MAX_VALUE);
		List<SystemProperties> models = systemPropertiesDAO.searchSystemPropertiess(searchPropData);
		mav.addObject("items", SaftyBeanUtils.cloneToList(models, SystemPropertiesViewData.class));

		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ModelAndView saveIndex(SystemPropertiesViewData reqData, HttpServletRequest request) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate(request));

		SearchSystemPropertiesData searchPropData = new SearchSystemPropertiesData();
		searchPropData.setPageSize(Integer.MAX_VALUE);
		List<SystemProperties> models = systemPropertiesDAO.searchSystemPropertiess(searchPropData);

		List<String> pcodes = reqData.getPcodes();
		List<String> pvalues = reqData.getPvalues();

		if (pcodes != null && models != null) {
			for (SystemProperties model : models) {
				int index = pcodes.indexOf(model.getPcode());
				if (index >= 0 && StringUtils.isNotEmpty(pvalues.get(index))) {
					if (model != null && (model.getIsEditFlag() != null && model.getIsEditFlag()) && !pvalues.get(index).equals(model.getPvalue())) {
						model.setPvalue(pvalues.get(index));
						systemPropertiesDAO.update(model);
						// break;
					}
				}
			}

			cacheDataChangeService.notifyOthers(SystemPropertiesData.class);
		}

		mav.addObject("items", SaftyBeanUtils.cloneToList(models, SystemPropertiesViewData.class));
		return mav;
	}

	private String getTemplate(HttpServletRequest request) {
		if (BrowserTypeUtils.checkAgentIsMobile(request.getHeader("user-agent"))) {
			return "/mobile/admin/system/index";
		}
		else {
			return "/admin/system/index";
		}
	}
}
