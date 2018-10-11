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

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.woodare.framework.data.EResponseState;
import com.woodare.framework.data.impl.WebResponseData;
import com.woodare.framework.utils.JsonUtils;

/**
 * 
 * @author lu_feng
 *
 */
public class AjaxAwareAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	public AjaxAwareAuthenticationEntryPoint(String loginUrl) {
		super(loginUrl);
	}


	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {

		String requestType = request.getHeader("requestType");
		boolean isAjax = StringUtils.isNotEmpty(requestType) && requestType.equals("ajax");
		if (isAjax) {
			response.setHeader("accessDenied", "true");
			String contentType = "application/json";
			response.setContentType(contentType);
			
			WebResponseData respData = new WebResponseData();
			respData.setState(EResponseState.Unauthority);
            
			respData.setPayload(this.getLoginFormUrl());
			PrintWriter out = response.getWriter();
			out.print(JsonUtils.toJson(respData));
			out.flush();
			out.close();
		} else {
			super.commence(request, response, authException);
		}
	}
}