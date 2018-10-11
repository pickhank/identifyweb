package com.woodare.framework.jersey;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.woodare.framework.data.impl.WebRequestData;
import com.woodare.framework.exception.AbstractWooException;

/**
 * 
 * @author lu_feng
 * 
 */
public interface IWebService {

    /**
     * 
     * @param action
     * @return
     */
    Method getActionMethod(String action) throws AbstractWooException;

    /**
     * Check whether the action requires authority checking.
     * 
     * @return
     */
    boolean checkAuthority(String action, WebRequestData requestData);

    /**
     * 
     * @param actionMethod
     * @param request
     * @return
     */
    Object execute(HttpServletRequest httpRequest, Method actionMethod, Object request) throws AbstractWooException;

    /**
     * 
     * @param actionMethod
     * @param request
     * @return
     * @throws AbstractWooException
     */
    Object executeWithNoTransaction(HttpServletRequest httpRequest, Method actionMethod, Object request) throws AbstractWooException;
    
    /**
     * 
     * @param actionMethod
     * @param request
     * @return
     */
    Object executeWithReflection(HttpServletRequest httpRequest, Method actionMethod, Object request) throws AbstractWooException;
}
