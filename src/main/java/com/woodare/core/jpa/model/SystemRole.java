package com.woodare.core.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.woodare.core.base.AbstractBusiModel;

/**
 * 系统角色表
 * 
 * @author lu_feng
 * 
 */
@Entity
public class SystemRole extends AbstractBusiModel implements Serializable {

	private static final long serialVersionUID = 7804323381648124677L;

	/**
	 * 
	 */
	public SystemRole() {
	}

	/**
	 * 
	 * @param customerId
	 */
	public SystemRole(String customerId) {
		super(customerId);
	}

	/* 角色名称 */
	@Column(length = 20)
	private String roleName;

	/* 系统保留 */
	private Boolean systemMenuFlag = false;

	/**
	 * @return the systemMenuFlag
	 */
	public Boolean getSystemMenuFlag() {
		return systemMenuFlag;
	}

	/**
	 * @param systemMenuFlag
	 *            the systemMenuFlag to set
	 */
	public void setSystemMenuFlag(Boolean systemMenuFlag) {
		this.systemMenuFlag = systemMenuFlag;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
