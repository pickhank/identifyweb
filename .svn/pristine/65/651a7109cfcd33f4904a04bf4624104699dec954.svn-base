package com.woodare.core.jpa.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.woodare.core.base.AbstractBusiModel;

/**
 * 系统角色用户表
 * 
 * @author lu_feng
 * 
 */
@Entity
public class SystemRoleUser extends AbstractBusiModel implements Serializable {
	private static final long serialVersionUID = 3273785332846163133L;

	/* 角色表ID */
	@Column(name = "role_id", length = 64, nullable = false)
	private String roleId;
	
	/* 角色 */
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private SystemRole role;

	/* 用户表ID */
	@Column(name = "user_id", length = 64, nullable = false)
	private String userId;

	/* 用户 */
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private SystemUser user;

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the role
	 */
	public SystemRole getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(SystemRole role) {
		this.role = role;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the user
	 */
	public SystemUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(SystemUser user) {
		this.user = user;
	}

}
