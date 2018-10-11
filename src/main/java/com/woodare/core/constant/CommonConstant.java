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
package com.woodare.core.constant;

/**
 * @author lu_feng
 */
public final class CommonConstant {
    
    String CODE_SYSTEM_VERSION = "system.version";

    /** 系统角色ID */
    public interface SYSTEM_ROLE {

        /** 超级用户 */
        String SUPER_USER = "super_system_role";

        /** 管理员 */
        String MANGER_USER = "manager_system_role";

        /** 普通 */
        String NORMAL_USER = "normal_system_role";
        
    }

    /** 字典表 */
    public interface DICTIONRY {

        /** 类别CODE */
        String CODE_ROOT = "TOP_ROOT";
        
    }
}
