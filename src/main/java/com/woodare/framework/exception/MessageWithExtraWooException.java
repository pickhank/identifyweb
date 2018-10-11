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
package com.woodare.framework.exception;

/**
 * ClassName: MessageWooException
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public class MessageWithExtraWooException extends AbstractWooException {
    private static final long serialVersionUID = 2516233569852806074L;

    private Object extraObj;

    /**
     * @param cause
     */
    public MessageWithExtraWooException(String message, Object extraObj) {
        super(message);
    }

    /**
     * @return the extraObj
     */
    public Object getExtraObj() {
        return extraObj;
    }

    /**
     * @param extraObj
     *            the extraObj to set
     */
    public void setExtraObj(Object extraObj) {
        this.extraObj = extraObj;
    }

}
