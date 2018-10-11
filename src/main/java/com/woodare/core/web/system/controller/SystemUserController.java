package com.woodare.core.web.system.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.jpa.model.SystemUser;
import com.woodare.core.jpa.service.ISystemUserDAO;
import com.woodare.core.security.CustomMsgAuthenticationException;
import com.woodare.core.util.ValidatorUtils;
import com.woodare.core.web.system.viewdata.systemuser.SystemUserViewData;
import com.woodare.framework.utils.JavaMD5Hash;
import com.woodare.framework.utils.SaftyBeanUtils;
import com.woodare.framework.utils.StringUtils;

/**
 * 
 * @author lu_feng
 * 
 */
@Controller
@RequestMapping("/user")
public class SystemUserController extends BaseController {
	private static Logger log = Logger.getLogger(SystemUserController.class);

	@Autowired
	private ISystemUserDAO systemUserDAO;

	// @Autowired
	// private MessageSource messageSource;
	//
	// @Autowired
	// private ISystemRoleUserDAO systemRoleUserDAO;
	//
	// @Autowired
	// private ISystemRoleDAO systemRoleDAO;

	/**
	 * 
	 * @param webRequest
	 * @return
	 */
	@RequestMapping(value = "/system/user/login")
	public ModelAndView doLogin(WebRequest webRequest) {
		ModelAndView mav = new ModelAndView("/system/user/login");
		Object lastException = webRequest.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, WebRequest.SCOPE_SESSION);
		if (lastException != null) {
			if (lastException instanceof CustomMsgAuthenticationException) {
				mav.addObject("errorMsg", ((CustomMsgAuthenticationException) lastException).getMessage());
			} else {
				mav.addObject("errorMsg", "用户名或密码错误");
			}
		}
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public ModelAndView resetPassword(SystemUserViewData resetData) {
		log.debug("Reset password: " + resetData);
		ModelAndView mav = new ModelAndView("/user/resetPassword");

		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView saveResetPassword(SystemUserViewData resetData) {
		log.debug("save reset password: " + resetData);

		ModelAndView mav = new ModelAndView("/user/resetPassword");
		SystemUser user = systemUserDAO.findOne(this.getUserId());
		if (!user.getPassword().equals(JavaMD5Hash.md5(resetData.getPassword()))) {
			mav.addObject("error", "密码错误！");
		} else if (!resetData.getNewPassword().equals(resetData.getNewPasswordConfirm())) {
			mav.addObject("error", "两次密码不一致！");
		} else {
			user.setPassword(JavaMD5Hash.md5(resetData.getNewPassword()));
			this.systemUserDAO.save(user);
			mav.addObject("success", "密码修改成功！");
		}
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView detail(SystemUserViewData userData) {
		log.debug("Load user detail with: " + userData);

		ModelAndView mav = new ModelAndView("/user/index");

		SystemUser user = null;
		if (StringUtils.isNotEmpty(getUserId())) {
			user = systemUserDAO.findOne(getUserId());
			userData = convertToViewData(user);
		}
		mav.addObject("item", userData);

		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ModelAndView saveDetail(@Valid SystemUserViewData userData, BindingResult result) {
		log.debug("Save user with: " + userData);
		ModelAndView mav = new ModelAndView("/user/index");
		String error = valid(userData, false);
		if (StringUtils.isNotEmpty(error)) {
			mav.addObject("item", userData);
			mav.addObject("error", error);
			return mav;
		}
		SystemUser user = null;
		if (StringUtils.isNotEmpty(getUserId())) {
			user = this.systemUserDAO.findOne(getUserId());
		} else {
			user = new SystemUser();
			user.setUsername(userData.getUsername());
			user.setIsAdminFlag(false);
		}
		user.setEmail(userData.getEmail());

		if (StringUtils.isNotEmpty(userData.getPassword())) {
			user.setPassword(JavaMD5Hash.md5(userData.getPassword()));
		}

		if (StringUtils.isNotEmpty(userData.getId())) {
			this.systemUserDAO.update(user);
		} else {
			this.systemUserDAO.save(user);
		}
		userData.setId(getUserId());
		mav.addObject("success", "账号修改成功！");
		mav.addObject("item", userData);
		return mav;
	}

	@Transactional(propagation = Propagation.NEVER)
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		log.debug("user register");
		ModelAndView mav = new ModelAndView("/system/user/register");
		return mav;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerPost(SystemUserViewData userData) {
		log.debug("Save user with: " + userData);
		String error = valid(userData, true);
		if (StringUtils.isNotEmpty(error)) {
			ModelAndView mav = new ModelAndView("/system/user/register");
			mav.addObject("item", userData);
			mav.addObject("error", error);
			return mav;
		}
		SystemUser user = new SystemUser();
		user.setIsAdminFlag(false);
		user.setUsername(userData.getUsername());
		user.setEmail(userData.getEmail());
		user.setPassword(JavaMD5Hash.md5(userData.getNewPassword()));

		if (StringUtils.isNotEmpty(userData.getId())) {
			this.systemUserDAO.update(user);
		} else {
			this.systemUserDAO.save(user);
		}

		ModelAndView mav = new ModelAndView("/system/user/register");
		mav.addObject("item", userData);
		mav.addObject("success", "success");
		return mav;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	private SystemUserViewData convertToViewData(SystemUser user) {
		SystemUserViewData viewData = SaftyBeanUtils.cloneTo(user, SystemUserViewData.class, new String[] { "password" });
		return viewData;
	}

	private String valid(SystemUserViewData userData, boolean checkPassword) {
		String error = "";
		if (StringUtils.isEmpty(userData.getUsername())) {
			error += "用户名不能为空<br/>";
		} else if (StringUtils.isEmpty(userData.getId())) {
			SystemUser user = systemUserDAO.findByUsername(userData.getUsername());
			if (user != null) {
				error += "用户名已被使用<br/>";
			}
		}
		if (StringUtils.isEmpty(userData.getEmail())) {
			error += "邮箱不能为空<br/>";
		} else if (!ValidatorUtils.isEmail(userData.getEmail())) {
			error += "请输入正确的邮箱格式，如：xxx@163.com<br/>";
		}

		if (checkPassword) {
			if (StringUtils.isEmpty(userData.getNewPassword()) || StringUtils.isEmpty(userData.getNewPasswordConfirm())) {
				error += "密码和重复密码不能为空<br/>";
			} else if (!userData.getNewPassword().equals(userData.getNewPasswordConfirm())) {
				error += "密码和重复密码必须一致<br/>";
			}
		}

		return error;
	}
}
