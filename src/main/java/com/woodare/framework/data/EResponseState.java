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
package com.woodare.framework.data;

/**
 * ClassName: EResponseState
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public enum EResponseState {
    Successful,

    Unauthority,

    CustomMsg,

    CustomMsgWithExtra,

    FormErrorMsg,

    Failed
}
