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
 * ClassName: AbstractException
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public class AbstractWooException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -6498674930426414653L;

    /**
     * @param message
     * @param cause
     */
    public AbstractWooException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public AbstractWooException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public AbstractWooException(Throwable cause) {
        super(cause);
    }

    /**
	 * 
	 */
    public AbstractWooException() {
    }

}
