package com.woodare.core.jpa.data.systemmenu;

import java.util.List;

import com.woodare.framework.data.impl.AbstractPageData;

/**
 * 
 * ClassName: SystemMenuData
 * 
 * @description
 * @author city_maven_auto_generate
 * @Date 2013/12/31
 * 
 */
public class SearchSystemMenuData extends AbstractPageData {
	private static final long serialVersionUID = 5629072783586899407L;

	/** ID */
	private String id;
	
	/** IDs */
	private List<String> ids;
	
	/**
	 * @return the ids
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}

