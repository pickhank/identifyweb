package com.woodare.core.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.woodare.core.base.AbstractBusiModel;

/**
 * 系统配置表
 * 
 * @author lu_feng
 * 
 */
@Entity
public class SystemConfig extends AbstractBusiModel implements Serializable {
	private static final long serialVersionUID = 6577287406178696277L;

	/* 版本号 */
	@Column(length = 128)
	private String version;

	/* 地图 */
	@Column(length = 128)
	private String mapVersion;

	/* 皮肤 */
	@Column(length = 128)
	private String skinVersion;

	/**
	 * @return the skinVersion
	 */
	public String getSkinVersion() {
		return skinVersion;
	}

	/**
	 * @param skinVersion
	 *            the skinVersion to set
	 */
	public void setSkinVersion(String skinVersion) {
		this.skinVersion = skinVersion;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the mapVersion
	 */
	public String getMapVersion() {
		return mapVersion;
	}

	/**
	 * @param mapVersion
	 *            the mapVersion to set
	 */
	public void setMapVersion(String mapVersion) {
		this.mapVersion = mapVersion;
	}

}
