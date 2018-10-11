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
package com.woodare.framework.jersey.authenticate;

import com.woodare.framework.data.impl.AbstractData;

/**
 * ClassName: AuthorizeData
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public class AuthorizedData<T> extends AbstractData {
    private static final long serialVersionUID = 4828049697210691761L;

    /** 用户主键ID */
    private String userId;

    /** 授权KEY */
    private String authToken;

    /** User information */
    private T user;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * @param authToken
     *            the authToken to set
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * @return the user
     */
    public T getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(T user) {
        this.user = user;
    }

}
