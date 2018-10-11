package com.woodare.core.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.woodare.core.jpa.model.SystemUser;
import com.woodare.core.jpa.service.ISystemUserDAO;
import com.woodare.core.security.UserDetailsImpl;

/**
 * 
 * @author lu_feng
 *
 */
public final class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
    @Autowired
    private ISystemUserDAO userDAO;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if(principal instanceof UserDetailsImpl) {
    		UserDetailsImpl userDetails = (UserDetailsImpl)principal;
    		SystemUser user = userDAO.findOne(userDetails.getUserId());
    		user.setLastLoginDate(new Date());
    		userDAO.update(user);
    	}
    }
}