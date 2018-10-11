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
package com.woodare.framework.data.impl;

import java.util.ArrayList;
import java.util.List;

import com.woodare.framework.data.IPagedList;
import com.woodare.framework.utils.SaftyBeanUtils;

/**
 * 
 * ClassName: PagedList
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public class PagedList<T> extends ArrayList<T> implements IPagedList<T> {
    private static final long serialVersionUID = 5920986017420227704L;

    /**
     * 
     */
    public PagedList() {

    }

    /**
     * 
     * @param items
     */
    public PagedList(List<T> items) {
        super(items);
    }

    /**
     * 
     * @param items
     */
    public <K> PagedList(List<T> items, IPagedList<K> pageInfo) {
        this(items, pageInfo.getTotalSize(), pageInfo.getPageIndex(), pageInfo.getPageSize());
    }

    /**
     * 
     * @param items
     * @param totalSize
     * @param pageSize
     * @param pageIndex
     */
    public PagedList(List<T> items, Long totalSize, Integer pageIndex, Integer pageSize) {
        if (items != null) {
            this.addAll(items);
        }
        this.totalSize = totalSize;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    /**
     * 
     * @param totalSize
     * @param pageSize
     * @param pageIndex
     */
    public PagedList(Long totalSize, Integer pageIndex, Integer pageSize) {
        this.totalSize = totalSize;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    /**
     * 
     * @return
     */
    public Integer getMaxPages() {
        if (totalSize != null && pageSize != null && pageSize != 0) {
            return (totalSize % pageSize > 0 ? 1 : 0) + (int) (totalSize / pageSize);
        }
        return 0;
    }

    /**
     * 
     * @param orgi
     */
    public <S> void copyToBean(List<S> orgi, Class<T> objClass) {
        try {
            for (S s : orgi) {
                T t = objClass.newInstance();
                SaftyBeanUtils.copyProperties(s, t);
                this.add(t);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Help to create paged list
     * 
     * @param orgi
     * @param objClass
     * @return
     */
    public static <S, D extends AbstractData> PagedList<D> toPagedList(IPagedList<S> sources, Class<D> objClass) {
        PagedList<D> target = null;
        if (sources != null) {
            target = new PagedList<D>();

            target.copyToBean(sources, objClass);

            target.setPageIndex(sources.getPageIndex());
            target.setPageSize(sources.getPageSize());
            target.setTotalSize(sources.getTotalSize());
        }
        return target;
    }

    private Long totalSize;

    private Integer pageSize;

    private Integer pageIndex;

    private String extraValue;

    /**
     * @return the extraValue
     */
    public String getExtraValue() {
        return extraValue;
    }

    /**
     * @param extraValue
     *            the extraValue to set
     */
    public void setExtraValue(String extraValue) {
        this.extraValue = extraValue;
    }

    /**
     * @return the totalSize
     */
    public Long getTotalSize() {
        return totalSize;
    }

    /**
     * @param totalSize
     *            the totalSize to set
     */
    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the pageIndex
     */
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * @param pageIndex
     *            the pageIndex to set
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

}
