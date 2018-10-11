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
package com.woodare.core.web.common.viewdata;

import com.woodare.framework.data.EResponseState;
import com.woodare.framework.data.impl.AbstractData;

/**
 * 
 * @author lu_feng
 * 
 */
public class AjaxResponseData<T> extends AbstractData {
    private static final long serialVersionUID = -4999558766335188250L;

    public AjaxResponseData(T payload) {
        this.payload = payload;
    }

    public AjaxResponseData() {
    }

    private T payload;

    private EResponseState state = EResponseState.Failed;

    private String message;

    /**
     * @return the payload
     */
    public T getPayload() {
        return payload;
    }

    /**
     * @param payload
     *            the payload to set
     */
    public void setPayload(T payload) {
        this.payload = payload;
    }

    /**
     * @return the state
     */
    public EResponseState getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(EResponseState state) {
        this.state = state;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}