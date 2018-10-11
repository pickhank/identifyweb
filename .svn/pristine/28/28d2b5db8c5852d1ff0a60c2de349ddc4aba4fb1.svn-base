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
package com.woodare.core.web.common.viewdata;

import java.util.ArrayList;
import java.util.List;

import com.woodare.framework.data.impl.AbstractData;

/**
 * ClassName: ListResponseData
 * 
 * @description
 * @author Xinxing Jiang
 * @Date May 11, 2015
 * 
 */
public class ListResponseData<T> extends AbstractData {
	private static final long serialVersionUID = -7980513815018801743L;

	private long total;

	private int size;

	private int index;

	private int pages;

	private List<T> items;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public void addItem(T item) {
		if (this.items == null) {
			this.items = new ArrayList<T>();
		}
		this.items.add(item);
	}

}
