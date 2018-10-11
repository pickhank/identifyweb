package com.woodare.core.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.woodare.core.base.AbstractBusiModel;

/**
 * 系统角色权限表
 * 
 * @author lu_feng
 * 
 */
@Entity
public class SystemRoleRight extends AbstractBusiModel implements Serializable {
	private static final long serialVersionUID = 6005121702085120630L;

	/* 角色表ID */
	@Column(length = 64, nullable = false)
	private String roleId;

	/* 菜单ID */
	@Column(length = 64, nullable = false)
	private String menuId;

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
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId
	 *            the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
