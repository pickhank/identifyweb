package com.woodare.core.jpa.data.systemrole;

import java.util.List;

import com.woodare.framework.data.impl.AbstractPageData;

/**
 * 
 * ClassName: SystemRoleData
 * 
 * @description
 * @author city_maven_auto_generate
 * @Date Jul 16, 2013
 * 
 */
public class SearchSystemRoleData extends AbstractPageData {
	private static final long serialVersionUID = 7804323381648124677L;

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

