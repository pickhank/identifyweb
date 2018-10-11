package com.woodare.core.web.system.viewdata.dicdata;

import com.woodare.core.jpa.data.dicdata.SearchDicDataData;
import com.woodare.framework.data.ISearchData;

/**
 * 
 * @author lu_feng
 * 
 */
public class SearchSystemDicViewData extends SearchDicDataData implements ISearchData {
    private static final long serialVersionUID = -3220353493817278104L;

    // 暂时只支持二级节点
    /** 父节点Code */
    private String parentCode1;

    /** 父节点Code */
    private String parentCode2;

    @Override
    public String getSearchFields() {
        return "parentCode1,parentCode2";
    }

    /**
     * @return the parentCode1
     */
    public String getParentCode1() {
        return parentCode1;
    }

    /**
     * @param parentCode1
     *            the parentCode1 to set
     */
    public void setParentCode1(String parentCode1) {
        this.parentCode1 = parentCode1;
    }

    /**
     * @return the parentCode2
     */
    public String getParentCode2() {
        return parentCode2;
    }

    /**
     * @param parentCode2
     *            the parentCode2 to set
     */
    public void setParentCode2(String parentCode2) {
        this.parentCode2 = parentCode2;
    }

}
