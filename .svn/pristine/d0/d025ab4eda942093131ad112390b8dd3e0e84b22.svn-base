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

import java.util.List;

/**
 * ClassName: IPagedList
 * 
 * @param <T>
 *            the type of list result
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public interface IPagedList<T> extends List<T> {

    /**
     * Total page size
     * 
     * @return the totalSize
     */
    Long getTotalSize();

    /**
     * The size of each page
     * 
     * @return the pageSize
     */
    Integer getPageSize();

    /**
     * The page index
     * 
     * @return the page index
     */
    Integer getPageIndex();

    /**
     * 
     * @return
     */
    Integer getMaxPages();
    
    /**
     * @return the extraValue
     */
    String getExtraValue();

    /**
     * @param extraValue
     *            the extraValue to set
     */
    void setExtraValue(String extraValue);
}
