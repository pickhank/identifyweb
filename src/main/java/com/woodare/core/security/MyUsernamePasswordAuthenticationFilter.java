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
package com.woodare.core.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.octo.captcha.service.CaptchaServiceException;
import com.woodare.core.component.captcha.VerificationService;
import com.woodare.framework.utils.StringUtils;

/**
 * 
 * @author lu_feng
 * 
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@SuppressWarnings("unused")
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (false && request.getMethod().equals("POST")) {
			String captchaId = request.getSession(true).getId();
			String regcode = request.getParameter("regcode");
			try {
				if (StringUtils.isEmpty(regcode)) {
					throw new CustomMsgAuthenticationException("验证码错误!");
				} else {
					if (VerificationService.verifyCode(captchaId, regcode)) {
						VerificationService.removeVerificationCodeByPhoneNum(captchaId);
					} else {
						throw new CustomMsgAuthenticationException("验证码错误!");
					}
				}
			} catch (CaptchaServiceException e) {
				if (VerificationService.verifyCode(captchaId, regcode)) {
					VerificationService.removeVerificationCodeByPhoneNum(captchaId);
				} else {
					throw new CustomMsgAuthenticationException("验证码错误!");
				}
			}
		}

		return super.attemptAuthentication(request, response);
	}

}
