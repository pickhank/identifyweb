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
package com.woodare.core.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.woodare.framework.jersey.authenticate.AuthenticateHelper;

/**
 * ClassName: BaseService
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public abstract class BaseService {

    @Autowired
    private HttpServletRequest request;

    /**
     * 
     * @return
     */
    protected String getUserId() {
        return AuthenticateHelper.getRequestTokenSession(request).getUserId();
    }
}
