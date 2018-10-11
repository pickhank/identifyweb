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
package com.woodare.framework.model.data;

/**
 * ClassName: EnumAuditType.java
 * 
 * @description 审核状态
 * @author jiang_xx
 * @Date 2013-7-15
 * 
 */
public enum EnumAuditType {
    /** 新纪录 */
    NEW,

    /** 待审核 */
    PENDING,

    /** 已审核 */
    PASSED,

    /** 已过期 */
    EXPIRED,

    /** 已拒绝 */
    REJECTED;
}
