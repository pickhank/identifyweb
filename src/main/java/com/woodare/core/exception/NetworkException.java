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
package com.woodare.core.exception;

/**
 * 
 * ClassName: NetworkException
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public class NetworkException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = -5021046560869905491L;

    public NetworkException() {
        super();
    }

    public NetworkException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NetworkException(String detailMessage) {
        super(detailMessage);
    }

    public NetworkException(Throwable throwable) {
        super(throwable);
    }

}
