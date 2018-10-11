package com.woodare.core.web.common.controller;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.woodare.core.base.BaseController;
import com.woodare.core.security.CustomMsgAuthenticationException;

/**
 * 
 * @author lu_feng
 * 
 */
@Controller
public class UserController extends BaseController{

	@RequestMapping(value = "/system/user/login")
	public ModelAndView doLogin(WebRequest webRequest) {
		ModelAndView mav = new ModelAndView(getTemplate("/system/user/login"));
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
}
