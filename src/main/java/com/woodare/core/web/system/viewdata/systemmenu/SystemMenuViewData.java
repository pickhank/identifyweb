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
package com.woodare.core.web.system.viewdata.systemmenu;


import com.woodare.core.jpa.data.systemmenu.SystemMenuData;

/**
 * 
 * ClassName: SystemMenuViewData
 * 
 * @description
 * @author 
 * @Date 2014/10/08
 * 
 */
public class SystemMenuViewData extends SystemMenuData {
	private static final long serialVersionUID = 5629072783586899407L;

    private String parentMenuName;

    private String[] names;

    private String[] ids;

    private Integer[] sortNums;

    private Boolean checkedFlag;

    /**
     * @return the checkedFlag
     */
    public Boolean getCheckedFlag() {
        return checkedFlag;
    }

    /**
     * @param checkedFlag
     *            the checkedFlag to set
     */
    public void setCheckedFlag(Boolean checkedFlag) {
        this.checkedFlag = checkedFlag;
    }

    /**
     * @return the names
     */
    public String[] getNames() {
        return names;
    }

    /**
     * @param names
     *            the names to set
     */
    public void setNames(String[] names) {
        this.names = names;
    }

    /**
     * @return the ids
     */
    public String[] getIds() {
        return ids;
    }

    /**
     * @param ids
     *            the ids to set
     */
    public void setIds(String[] ids) {
        this.ids = ids;
    }

    /**
     * @return the sortNums
     */
    public Integer[] getSortNums() {
        return sortNums;
    }

    /**
     * @param sortNums
     *            the sortNums to set
     */
    public void setSortNums(Integer[] sortNums) {
        this.sortNums = sortNums;
    }

    /**
     * @return the parentMenuName
     */
    public String getParentMenuName() {
        return parentMenuName;
    }

    /**
     * @param parentMenuName
     *            the parentMenuName to set
     */
    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }
}

