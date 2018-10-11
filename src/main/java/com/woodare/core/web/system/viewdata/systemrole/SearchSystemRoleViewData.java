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
package com.woodare.core.web.system.viewdata.systemrole;

import java.util.List;

import com.woodare.core.jpa.data.systemrole.SearchSystemRoleData;
import com.woodare.framework.data.ISearchData;

/**
 * 
 * ClassName: SearchSystemRoleViewData
 * 
 * @description
 * @author 
 * @Date 2014/10/08
 * 
 */
public class SearchSystemRoleViewData extends SearchSystemRoleData implements ISearchData {
	private static final long serialVersionUID = 7804323381648124677L;

    // ID
    private List<String> itemIds;
    // 名称
    private List<String> roleNames;

    @Override
    public String getSearchFields() {
        return "";
    }

    /**
     * @return the itemIds
     */
    public List<String> getItemIds() {
        return itemIds;
    }

    /**
     * @param itemIds
     *            the itemIds to set
     */
    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    /**
     * @return the roleNames
     */
    public List<String> getRoleNames() {
        return roleNames;
    }

    /**
     * @param roleNames
     *            the roleNames to set
     */
    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}

