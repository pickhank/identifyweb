package com.woodare.core.web.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.woodare.core.component.cache.DictionaryListener;
import com.woodare.core.jpa.data.dicdata.DicDataData;
import com.woodare.core.jpa.model.DicData;
import com.woodare.core.jpa.model.SystemConfig;
import com.woodare.core.jpa.service.IDicDataDAO;
import com.woodare.core.jpa.service.ISystemConfigDAO;
import com.woodare.core.web.common.viewdata.AjaxResponseData;
import com.woodare.core.web.system.viewdata.dicdata.SearchSystemDicViewData;
import com.woodare.core.web.system.viewdata.dicdata.SystemDicDataViewData;
import com.woodare.core.web.taglibs.BusiCommonUtils;
import com.woodare.framework.component.SystemCache;
import com.woodare.framework.data.EResponseState;
import com.woodare.framework.model.data.EnumDicExtraType;
import com.woodare.framework.utils.CommonUtils;
import com.woodare.framework.utils.JsonUtils;
import com.woodare.framework.utils.SaftyBeanUtils;

/**
 * 
 * @author lu_feng
 *
 */
@Controller
@RequestMapping("/system/dic")
public class SystemDicController extends BaseController {
    private static Logger log = Logger.getLogger(SystemDicController.class);

	@Autowired
	private IDicDataDAO dicDataDAO;
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ISystemConfigDAO systemConfigDAO;
	
    @Transactional(propagation = Propagation.NEVER)
    @RequestMapping(value = "/index")
	public ModelAndView index(SearchSystemDicViewData searchData) {
    	ModelAndView mav = new ModelAndView("/system/dic/index");
    	
    	searchData.setAutoRedirect(false);
    	searchData.setPageSize(Integer.MAX_VALUE);
    	String parentCode = "";
    	DicData topDicData = null;
    	if(StringUtils.isNotEmpty(searchData.getParentCode1())) {
    		parentCode = searchData.getParentCode1();
    		topDicData = dicDataDAO.findByCode(parentCode);
    	}
    	if(StringUtils.isNotEmpty(searchData.getParentCode2())) {
    		parentCode = searchData.getParentCode2();
    	}
    	searchData.setParentCode(parentCode);
    	List<DicData> modelLst = dicDataDAO.searchDicDatas(searchData);
    	
    	List<SystemDicDataViewData> items = new ArrayList<SystemDicDataViewData>();
    	
		if(modelLst != null && modelLst.size() > 0) {
			for(DicData item : modelLst) {
				SystemDicDataViewData viewData = SaftyBeanUtils.cloneTo(item, SystemDicDataViewData.class);
				if(topDicData != null && topDicData.getExtraType() == EnumDicExtraType.IMAGE) {
					// viewData.setExtraUrl(this.parseImageUrl(viewData.getExtra()));
				}
				viewData.setParentName(DictionaryListener.getNameByCode(viewData.getParentCode()));
				items.add(viewData);
			}
		}
		items = BusiCommonUtils.sortForDicDataData(items);
		mav.addObject("items", items);
		mav.addObject("parentCode", parentCode);
		mav.addObject("searchData", searchData);
		mav.addObject("topDicData", SaftyBeanUtils.cloneTo(topDicData, SystemDicDataViewData.class));
		return mav;
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/batchSave", method=RequestMethod.POST)
	public ModelAndView doIndex(SearchSystemDicViewData searchData, HttpServletRequest request) {
    	String[] itemIds = request.getParameterValues("itemIds");
    	
    	
    	if(itemIds != null && itemIds.length > 0) {
    		// Code
        	String[] codes = request.getParameterValues("codes");
        	// 名称
        	String[] names = request.getParameterValues("names");
        	// 描述信息
        	String[] descriptionss = request.getParameterValues("descriptionss");
        	// 父节点Code
        	// String[] parentCodes = request.getParameterValues("parentCodes");
        	// 备用字段
        	String[] extras = request.getParameterValues("extras");
        	// 排序
        	String[] sortNums = request.getParameterValues("sortNums");
        	
        	DicData topDicData = null;
        	String parentCode = null;
        	Integer level = 0;
        	if(StringUtils.isNotEmpty(searchData.getParentCode1())) {
        		parentCode = searchData.getParentCode1();
        		topDicData = dicDataDAO.findByCode(parentCode);
        		level = 1;
        	}
        	if(StringUtils.isNotEmpty(searchData.getParentCode2())) {
        		parentCode = searchData.getParentCode2();
        		level = 2;
        	}
        	
        	List<String> ids = new ArrayList<String>();
        	for(String id : itemIds) {
        		if(StringUtils.isNotEmpty(id)) {
        			ids.add(id);
        		}
        	}
        	
        	List<DicData> models = null;
        	
        	if(ids.size() > 0) {
        		SearchSystemDicViewData searchItemData = new SearchSystemDicViewData();
        		searchItemData.setIds(ids);
        		searchItemData.setAutoRedirect(false);
        		searchItemData.setPageSize(Integer.MAX_VALUE);
        		models = dicDataDAO.searchDicDatas(searchItemData);
        	}
        	
        	for (int i = 0; i < itemIds.length; i++) {
				String id = itemIds[i];
				if(StringUtils.isNotEmpty(id) && models != null) {
					for(DicData model : models) {
        				if(model.getId().equals(id)) {
        					model.setCode(codes[i]);
        					model.setName(names[i]);
        					model.setDescriptions(descriptionss[i]);
        					model.setParentCode(parentCode);
        					try{
        						model.setSortNum(Integer.parseInt(sortNums[i]));
        					}
        					catch(Exception e) {
        						model.setSortNum(0);
        					}
        					
        					if(topDicData != null && topDicData.getExtraType() == EnumDicExtraType.IMAGE) {
        						// model.setExtra(this.getAndEnsureAttachemt(extras[i]));
        					}
        					else if(topDicData != null && topDicData.getExtraType() != EnumDicExtraType.NONE) {
        						model.setExtra(extras[i]);
        					}
        					dicDataDAO.update(model);
        					break;
        				}
        			}
				}
				else {
					DicData model = new DicData();
					model.setCode(CommonUtils.uuid());
					model.setName(names[i]);
					model.setDescriptions(descriptionss[i]);
					model.setParentCode(parentCode);
					try{
						model.setSortNum(Integer.parseInt(sortNums[i]));
					}
					catch(Exception e) {
						model.setSortNum(0);
					}
					model.setLevel(level);
					model.setIsValidFlag(true);
					model.setSystemMenuFlag(false);
					
					if(topDicData != null && topDicData.getExtraType() == EnumDicExtraType.IMAGE) {
						// model.setExtra(this.getAndEnsureAttachemt(extras[i]));
					}
					else if(topDicData != null && topDicData.getExtraType() != EnumDicExtraType.NONE) {
						model.setExtra(extras[i]);
					}
					dicDataDAO.save(model);
				}
    		}
			// 更新系统配置文件
			SystemConfig systemConfig = systemConfigDAO.findAll().get(0);
			systemConfig.setVersion(CommonUtils.uuid());
			systemConfigDAO.update(systemConfig);
			
        	SystemCache.fireLoadEvent(DicDataData.class);
    	}
    	String suffix = "";
    	if(StringUtils.isNotEmpty(searchData.getParentCode1())) {
    		suffix += "&parentCode1=" + searchData.getParentCode1();
    	}
    	if(StringUtils.isNotEmpty(searchData.getParentCode2())) {
    		suffix += "&parentCode2=" + searchData.getParentCode2();
    	}
		return new ModelAndView("redirect:/system/dic/index?noiframe=true" + suffix);
	}
    
    @Transactional(propagation = Propagation.NEVER)
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView menuDetail(SystemDicDataViewData dicData) {
    	ModelAndView mav = new ModelAndView("/system/dic/detail");
    	
    	DicData topDicData = null;
    	if(StringUtils.isNotEmpty(dicData.getId())) {
    		DicData model = dicDataDAO.findOne(dicData.getId());
    		SaftyBeanUtils.copyProperties(model, dicData);

    		if(model.getLevel() == 1) {
    			topDicData = dicDataDAO.findByCode(dicData.getParentCode());
    			dicData.setParentCode1(topDicData.getCode());
    			dicData.setParentName1(topDicData.getName());
    		}
    		else if(model.getLevel() == 2) {
    			topDicData = dicDataDAO.findByCode(dicData.getParentCode());
    			dicData.setParentCode2(topDicData.getCode());
    			dicData.setParentName2(topDicData.getName());
    			
    			topDicData = dicDataDAO.findByCode(topDicData.getParentCode());
    			dicData.setParentCode1(topDicData.getCode());
    			dicData.setParentName1(topDicData.getName());
    		}
    		if(topDicData != null && topDicData.getExtraType() == EnumDicExtraType.IMAGE) {
				// model.setExtra(this.getAndEnsureAttachemt(dicData.getExtra()));
			}
    	}
		mav.addObject("topDicData", topDicData);
		mav.addObject("item", dicData);
		return mav;
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
	public @ResponseBody AjaxResponseData<Boolean> saveMenuDetail(@Valid SystemDicDataViewData dicData, BindingResult result) {
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
    		DicData topDicData = null;
        	String parentCode = null;
        	
        	Integer level = 0;
        	if(StringUtils.isNotEmpty(dicData.getParentCode1())) {
        		level = 1;
        		parentCode = dicData.getParentCode1();
        		topDicData = dicDataDAO.findByCode(parentCode);
        	}
        	if(StringUtils.isNotEmpty(dicData.getParentCode2())) {
        		level = 2;
        		parentCode = dicData.getParentCode2();
        	}
        	DicData model = null;
        	if(StringUtils.isNotEmpty(dicData.getId())) {
        		model = dicDataDAO.findOne(dicData.getId());
        	}
        	else {
        		model = new DicData();
        		model.setLevel(level);
        		model.setParentCode(parentCode);
        		model.setSystemMenuFlag(false);
        		model.setIsValidFlag(true);
        	}
        	model.setCode(dicData.getCode());
        	model.setName(dicData.getName());
        	model.setDescriptions(dicData.getDescriptions());
        	if(topDicData != null) {
        		
        	}
        	if(topDicData != null) {
        		if(topDicData.getExtraType() == EnumDicExtraType.IMAGE) {
        			// model.setExtra(this.getAndEnsureAttachemt(dicData.getExtra()));
        		}
        		else if(dicData.getExtraType() != EnumDicExtraType.NONE) {
        			model.setExtra(dicData.getExtra());	
        		}
			}
        	else if(dicData.getExtraType() != null) {
        		model.setExtraType(dicData.getExtraType());
        		model.setExtraDescription(dicData.getDescriptions());
        		if(dicData.getExtraType() == EnumDicExtraType.IMAGE) {
        			// model.setExtra(this.getAndEnsureAttachemt(dicData.getExtra()));
        		}
        		else if(dicData.getExtraType() != EnumDicExtraType.NONE) {
        			model.setExtra(dicData.getExtra());	
        		}
        	}
        	if(StringUtils.isNotEmpty(dicData.getId())) {
        		dicDataDAO.update(model);
        	}
        	else {
        		dicDataDAO.save(model);
        	}
        	
			// 更新系统配置文件
			SystemConfig systemConfig = systemConfigDAO.findAll().get(0);
			systemConfig.setVersion(CommonUtils.uuid());
			systemConfigDAO.update(systemConfig);
			
			// 重新加载缓存
			SystemCache.fireLoadEvent(DicDataData.class);
			
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
    @RequestMapping(value = "/delete/{code}", method = RequestMethod.POST)
	public @ResponseBody AjaxResponseData<Boolean> deleteMenu(@PathVariable String code) {
    	AjaxResponseData<Boolean> ret = new AjaxResponseData<Boolean>(false);
    	try {
        	if(StringUtils.isNotEmpty(code)) {
        		if(dicDataDAO.hasChildren(code)) {
        			ret.setState(EResponseState.CustomMsg);
        			ret.setMessage("请先删除节点下内容，再删除本节点！");
        			return ret;
        		}
        		else {
        			DicData model = dicDataDAO.findByCode(code);
        			if(model.getSystemMenuFlag() != null && model.getSystemMenuFlag()) {
            			ret.setState(EResponseState.CustomMsg);
            			ret.setMessage("系统保留节点，禁止操作！");
            			return ret;
        			}
        			
        			dicDataDAO.delete(model);
        		}
        	}

			// 更新系统配置文件
			SystemConfig systemConfig = systemConfigDAO.findAll().get(0);
			systemConfig.setVersion(CommonUtils.uuid());
			systemConfigDAO.update(systemConfig);
			
			SystemCache.fireLoadEvent(DicDataData.class);

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
