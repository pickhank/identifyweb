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
package com.woodare.template.web.controller.agent;

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
import com.woodare.template.jpa.model.DownAgent;
import com.woodare.template.jpa.model.DownMerchant;
import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;
import com.woodare.template.jpa.persistence.data.downmerchant.SearchDownMerchantData;
import com.woodare.template.jpa.persistence.persistence.IDownAgentDAO;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantDAO;
import com.woodare.template.web.viewdata.downagent.DownAgentViewData;
import com.woodare.template.web.viewdata.downmerchant.DownMerchantViewData;

/**
 * ClassName: AgentDownMerchantController
 * 
 * @description
 * @author Luke
 * @Date Mar 2,2018
 */
@Controller
@RequestMapping("/agent/downMerchant")
public class AgentDownMerchantController extends BaseController {

	@Autowired
	private IDownAgentDAO downAgentDAO;

	@Autowired
	private IDownMerchantDAO downMerchantDAO;

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index")
	public ModelAndView mch(SearchDownMerchantData searchData) throws ControllerException {
		ModelAndView mav = new ModelAndView(getTemplate("/agent/downMerchant/index"));
		DownAgent agent = downAgentDAO.findByAgentNo(getUsername());
		DownAgentData item = SaftyBeanUtils.cloneTo(agent, DownAgentViewData.class);
		searchData.setAgentId(item.getId());
		IPagedList<DownMerchant> items = downMerchantDAO.searchDownMerchants(searchData);
		mav.addObject("res", convertToListMch(items));
		mav.addObject("search", searchData);
		return mav;
	}

	/**
	 * @param items
	 * @return
	 */
	private ListResponseData<DownMerchantViewData> convertToListMch(IPagedList<DownMerchant> items) {
		ListResponseData<DownMerchantViewData> response = new ListResponseData<DownMerchantViewData>();
		if (items != null) {
			for (DownMerchant item : items) {
				response.addItem(convertMch(item));
			}
		}
		response.setIndex(items.getPageIndex());
		response.setPages(items.getMaxPages());
		response.setSize(items.getPageSize());
		response.setTotal(items.getTotalSize());
		return response;
	}

	/**
	 * @param item
	 * @return
	 */
	private DownMerchantViewData convertMch(DownMerchant item) {
		DownMerchantViewData viewData = SaftyBeanUtils.cloneTo(item, DownMerchantViewData.class);
		return viewData;
	}

}
