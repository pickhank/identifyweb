package com.woodare.core.web.system.viewdata.dicdata;

import com.woodare.core.jpa.data.dicdata.DicDataData;

/**
 * 
 * @author lu_feng
 * 
 */
public class SystemDicDataViewData extends DicDataData {

	private static final long serialVersionUID = -8886741386307281691L;

	/** 父节点Code */
	private String parentName;

	/** 父节点Code */
	private String parentCode1;

	/** 父节点Code */
	private String parentName1;

	/** 父节点Code */
	private String parentCode2;

	/** 父节点Code */
	private String parentName2;

	private String extraUrl;

	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentName
	 *            the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * @return the parentName1
	 */
	public String getParentName1() {
		return parentName1;
	}

	/**
	 * @param parentName1
	 *            the parentName1 to set
	 */
	public void setParentName1(String parentName1) {
		this.parentName1 = parentName1;
	}

	/**
	 * @return the parentName2
	 */
	public String getParentName2() {
		return parentName2;
	}

	/**
	 * @param parentName2
	 *            the parentName2 to set
	 */
	public void setParentName2(String parentName2) {
		this.parentName2 = parentName2;
	}

	/**
	 * @return the parentCode1
	 */
	public String getParentCode1() {
		return parentCode1;
	}

	/**
	 * @param parentCode1
	 *            the parentCode1 to set
	 */
	public void setParentCode1(String parentCode1) {
		this.parentCode1 = parentCode1;
	}

	/**
	 * @return the parentCode2
	 */
	public String getParentCode2() {
		return parentCode2;
	}

	/**
	 * @param parentCode2
	 *            the parentCode2 to set
	 */
	public void setParentCode2(String parentCode2) {
		this.parentCode2 = parentCode2;
	}

	/**
	 * @return the extraUrl
	 */
	public String getExtraUrl() {
		return extraUrl;
	}

	/**
	 * @param extraUrl
	 *            the extraUrl to set
	 */
	public void setExtraUrl(String extraUrl) {
		this.extraUrl = extraUrl;
	}
}
