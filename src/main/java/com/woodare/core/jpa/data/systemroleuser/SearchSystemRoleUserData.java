package com.woodare.core.jpa.data.systemroleuser;

import java.util.List;

import com.woodare.framework.data.impl.AbstractPageData;

/**
 * 
 * ClassName: SystemRoleUserData
 * 
 * @description
 * @author city_maven_auto_generate
 * @Date Jul 16, 2013
 * 
 */
public class SearchSystemRoleUserData extends AbstractPageData {
	private static final long serialVersionUID = 3273785332846163133L;

	/** ID */
	private String id;

	/** IDs */
	private List<String> ids;

	/** 用户ID */
	private String userId;

	/** 用户IDs */
	private List<String> userIds;

	/** 关键字 */
	private String keywords;

	/** 角色ID */
	private String roleId;

	/**
	 * @return the userIds
	 */
	public List<String> getUserIds() {
		return userIds;
	}

	/**
	 * @param userIds
	 *            the userIds to set
	 */
	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the ids
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
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
