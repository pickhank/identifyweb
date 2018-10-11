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

import com.woodare.framework.data.IPaged;

/**
 * @author lu_feng
 */
public abstract class AbstractPageData extends AbstractData implements IPaged {
	private static final long serialVersionUID = -1891054955103536038L;

	private Integer pageSize;

	private Integer pageIndex;

	private String orderString;

	private boolean autoRedirect = false;
	private Boolean searchFlag;

	private String columnFields;
	private Boolean doExportFlag = false;

	/**
	 * @return the doExportFlag
	 */
	public Boolean getDoExportFlag() {
		return doExportFlag;
	}

	/**
	 * @param doExportFlag
	 *            the doExportFlag to set
	 */
	public void setDoExportFlag(Boolean doExportFlag) {
		this.doExportFlag = doExportFlag;
	}

	/**
	 * @return the columnFields
	 */
	public String getColumnFields() {
		return columnFields;
	}

	/**
	 * @param columnFields
	 *            the columnFields to set
	 */
	public void setColumnFields(String columnFields) {
		this.columnFields = columnFields;
	}

	/**
	 * @return the searchFlag
	 */
	public Boolean getSearchFlag() {
		return searchFlag;
	}

	/**
	 * @param searchFlag
	 *            the searchFlag to set
	 */
	public void setSearchFlag(Boolean searchFlag) {
		this.searchFlag = searchFlag;
	}

	/**
	 * @return the autoRedirect
	 */
	@Override
	public boolean isAutoRedirect() {
		return autoRedirect;
	}

	/**
	 * @param autoRedirect
	 *            the autoRedirect to set
	 */
	public void setAutoRedirect(boolean autoRedirect) {
		this.autoRedirect = autoRedirect;
	}

	/**
	 * @return the pageSize
	 */
	@Override
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
	@Override
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

	public String getOrderString() {
		return orderString;
	}

	public void setOrderString(String orderString) {
		this.orderString = orderString;
	}

}
