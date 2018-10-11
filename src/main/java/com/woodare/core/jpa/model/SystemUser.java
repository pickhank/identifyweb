package com.woodare.core.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.core.jpa.model.data.EnumUserRole;

/**
 * @author lu_feng
 */
@Entity
@NamedQueries({ @NamedQuery(name = "user.findByEmail", query = "select a from SystemUser a where a.email = :email"), @NamedQuery(name = "user.findByUsername", query = "select a from SystemUser a where a.username = :username") })
public class SystemUser extends AbstractBusiModel {
	private static final long serialVersionUID = -8632449877318520090L;

	public SystemUser() {

	}

	public SystemUser(String id) {
		super(id);
	}

	/* 用户名 */
	@Column(length = 32, unique = true)
	private String username;

	/* 姓名 */
	@Column(length = 128)
	private String name;

	/* 邮箱 */
	@Column(length = 128, unique = true)
	private String email;

	/* 密码 */
	@Column(length = 256)
	private String password;

	/* 最后登录 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginDate;

	private Boolean isAdminFlag;

	@Enumerated(EnumType.STRING)
	private EnumUserRole userRole;

	/* 外部关联字段 */
	@Column(length = 128)
	private String referNo;

	/**
	 * @return the referNo
	 */
	public String getReferNo() {
		return referNo;
	}

	/**
	 * @param referNo
	 *            the referNo to set
	 */
	public void setReferNo(String referNo) {
		this.referNo = referNo;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Boolean getIsAdminFlag() {
		return isAdminFlag;
	}

	public void setIsAdminFlag(Boolean isAdminFlag) {
		this.isAdminFlag = isAdminFlag;
	}

	public EnumUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(EnumUserRole userRole) {
		this.userRole = userRole;
	}
}
