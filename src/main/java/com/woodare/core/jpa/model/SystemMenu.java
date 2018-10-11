package com.woodare.core.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.model.data.EnumSystemMenuType;

/**
 * 系统菜单表
 * 
 * @author lu_feng
 * 
 */
@Entity
public class SystemMenu extends AbstractBusiModel implements Serializable {
	private static final long serialVersionUID = 5629072783586899407L;

	/**
	 * 
	 */
	public SystemMenu() {
	}

	/**
	 * 
	 * @param customerId
	 */
	public SystemMenu(String customerId) {
		super(customerId);
	}
	
	/* 菜单类型 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumSystemMenuType menuType;

	/* 显示名称 */
	@Column(length = 20, nullable = false)
	private String name;

	/* 页面入口地址 */
	@Column(length = 256)
	private String pageUrl;

	/* 排序 */
	private Integer sortNum = 0;

	/* 父菜单ID */
	@Column(length = 64)
	private String parentMenuId;
	
	/* 系统保留菜单 */
	private Boolean systemMenuFlag = false;

	/**
	 * @return the systemMenuFlag
	 */
	public Boolean getSystemMenuFlag() {
		return systemMenuFlag;
	}

	/**
	 * @param systemMenuFlag the systemMenuFlag to set
	 */
	public void setSystemMenuFlag(Boolean systemMenuFlag) {
		this.systemMenuFlag = systemMenuFlag;
	}

	/**
	 * @return the sortNum
	 */
	public Integer getSortNum() {
		return sortNum;
	}

	/**
	 * @param sortNum
	 *            the sortNum to set
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * @return the menuType
	 */
	public EnumSystemMenuType getMenuType() {
		return menuType;
	}

	/**
	 * @param menuType
	 *            the menuType to set
	 */
	public void setMenuType(EnumSystemMenuType menuType) {
		this.menuType = menuType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the pageUrl
	 */
	public String getPageUrl() {
		return pageUrl;
	}

	/**
	 * @param pageUrl
	 *            the pageUrl to set
	 */
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	/**
	 * @return the parentMenuId
	 */
	public String getParentMenuId() {
		return parentMenuId;
	}

	/**
	 * @param parentMenuId
	 *            the parentMenuId to set
	 */
	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
}
