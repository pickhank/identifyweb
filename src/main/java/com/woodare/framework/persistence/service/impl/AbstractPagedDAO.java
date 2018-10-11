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
package com.woodare.framework.persistence.service.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.woodare.framework.data.IPaged;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.data.impl.PagedList;
import com.woodare.framework.model.AbstractModel;
import com.woodare.framework.utils.CommonUtils;
import com.woodare.framework.utils.StringUtils;
import com.woodare.framework.utils.SysProperties;

/**
 * 
 * ClassName: AbstractPagedDAO
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
public abstract class AbstractPagedDAO<T extends AbstractModel> extends AbstractSimpleDAO<T> {

    /**
     * 
     * @param query
     * @param searchData
     */
    protected <K> void addPagination(TypedQuery<K> query, IPaged searchData) {
        int index = searchData.getPageIndex() == null ? 0 : searchData.getPageIndex();
        int size = searchData.getPageSize() == null || searchData.getPageSize() == 0 ? SysProperties.getInstance().getIntProperty("default.data.paged.size", 5) : searchData
                .getPageSize();

        int firstResult = index * size;

        query.setFirstResult(firstResult);
        query.setMaxResults(size);
    }

    /**
     * Get the result list.
     * 
     * @param totalQuery
     *            the query of search total size
     * @param query
     *            the query of search list data
     * @param searchData
     *            the pagination condition
     * @return
     */
    protected <K> IPagedList<K> getPagedList(TypedQuery<Long> totalQuery, TypedQuery<K> query, IPaged searchData) {
        IPagedList<K> rel = null;
        
        int index = searchData.getPageIndex() == null ? 0 : searchData.getPageIndex();
        int size = searchData.getPageSize() == null || searchData.getPageSize() == 0 ? SysProperties.getInstance().getIntProperty("default.data.paged.size", 5) : searchData
                .getPageSize();

        long total = 0l;
        // 不限制分页时，不做汇总查询
        if(size != Integer.MAX_VALUE) {
        	total = CommonUtils.toLong(totalQuery.getSingleResult());
        }
        int firstResult = index * size;

        if (total == 0 && size != Integer.MAX_VALUE) {
            rel = new PagedList<K>(null, 0L, 0, size);
        } else {
            // When first result is more than current total of records, reset to
            // show first page result
            if (searchData.isAutoRedirect()) {
                if (firstResult >= total && size != Integer.MAX_VALUE) {
                    index = 0;
                    firstResult = index * size;
                }
                query.setFirstResult(firstResult);
                query.setMaxResults(size);

                List<K> items = query.getResultList();
                if(size == Integer.MAX_VALUE && items != null) {
                	total = items.size();
                }
                rel = new PagedList<K>(items, total, index, size);
            } else {
                if (firstResult >= total && size != Integer.MAX_VALUE) {
                    rel = new PagedList<K>(null, total, index, size);
                } else {
                    query.setFirstResult(firstResult);
                    query.setMaxResults(size);

                    List<K> items = query.getResultList();
                    if(size == Integer.MAX_VALUE && items != null) {
                    	total = items.size();
                    }
                    rel = new PagedList<K>(items, total, index, size);
                }
            }
        }
        return rel;
    }
    

    /**
     * Get the result list.
     * 
     * @param totalQuery
     *            the query of search total size
     * @param query
     *            the query of search list data
     * @param searchData
     *            the pagination condition
     * @return
     */
    @SuppressWarnings("unchecked")
    protected IPagedList<Object> getNativePagedList(Query totalQuery, Query query, IPaged searchData) {
        IPagedList<Object> rel = null;

        Long total = CommonUtils.toLong(totalQuery.getSingleResult());
        int index = searchData.getPageIndex() == null ? 0 : searchData.getPageIndex();
        int size = searchData.getPageSize() == null || searchData.getPageSize() == 0 ? SysProperties.getInstance().getIntProperty("default.data.paged.size", 5) : searchData
                .getPageSize();

        int firstResult = index * size;

        if (total == 0) {
            rel = new PagedList<Object>(null, 0L, 0, size);
        } else {
            // When first result is more than current total of records, reset to
            // show first page result
            if (searchData.isAutoRedirect()) {
                if (firstResult >= total) {
                    index = 0;
                    firstResult = index * size;
                }
                query.setFirstResult(firstResult);
                query.setMaxResults(size);

                rel = new PagedList<Object>(query.getResultList(), total, index, size);
            } else {
                if (firstResult >= total) {
                    rel = new PagedList<Object>(null, total, index, size);
                } else {
                    query.setFirstResult(firstResult);
                    query.setMaxResults(size);

                    rel = new PagedList<Object>(query.getResultList(), total, index, size);
                }
            }
        }
        return rel;
    }

    /**
     * 
     * @return
     */
    protected String getSortString(IPaged searchData) {
        return this.getSortString(searchData, "a");
    }

    /**
     * TODO: Take consider it to move sort mode into Model define, to avoid dead
     * codes in here.
     * 
     * @return
     */
    protected String getSortString(IPaged searchData, String prefix) {
        String sql = "";
//        if (searchData.getSortMode() == EnumSortItem.DEFAULT) {
//        } else if (searchData.getSortMode() == EnumSortItem.UPDATE_DATE) {
//            sql += prefix + ".updateDate desc";
//        } else if (searchData.getSortMode() == EnumSortItem.SORT_NUM) {
//            sql += prefix + ".sortNum asc, " + prefix + ".updateDate desc";
//        }

        return StringUtils.isNotEmpty(sql) ? " order by " + sql : sql;
    }
}
