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
package com.woodare.core.web.system.viewdata.systemroleright;


import java.util.List;

import com.woodare.core.jpa.data.systemroleright.SystemRoleRightData;
import com.woodare.core.web.system.viewdata.systemmenu.SystemMenuViewData;

/**
 * 
 * ClassName: SystemRoleRightViewData
 * 
 * @description
 * @author 
 * @Date 2014/10/08
 * 
 */
public class SystemRoleRightViewData extends SystemRoleRightData {
	private static final long serialVersionUID = 6005121702085120630L;
	/** 菜单ID */
    private List<String> menuIds;

    /** All 菜单 */
    private List<SystemMenuViewData> menus;

    /**
     * @return the menuIds
     */
    public List<String> getMenuIds() {
        return menuIds;
    }

    /**
     * @param menuIds
     *            the menuIds to set
     */
    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }

    /**
     * @return the menus
     */
    public List<SystemMenuViewData> getMenus() {
        return menus;
    }

    /**
     * @param menus
     *            the menus to set
     */
    public void setMenus(List<SystemMenuViewData> menus) {
        this.menus = menus;
    }
}

