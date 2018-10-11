package com.woodare.core.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.model.data.EnumPropertyType;

/**
 * 
 * @author lu_feng
 * 
 */
@Entity
public class SystemProperties extends AbstractBusiModel implements Serializable {
	private static final long serialVersionUID = -7467799779478963793L;

	/* 属性Code */
	@Column(length = 256)
	private String pcode;

	/* 属性值 */
	@Column(length = 256)
	private String pvalue;

	/* 属性名称 */
	@Column(length = 40)
	private String pname;

	/* 属性描述 */
	@Column(length = 256)
	private String pdesc;

	/** Sort number */
	private Integer sortNum;

	private Boolean isEditFlag;

	@Column(length = 64)
	private String creatorId;

	@Enumerated(EnumType.STRING)
	private EnumPropertyType propertyType;

	public EnumPropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(EnumPropertyType propertyType) {
		this.propertyType = propertyType;
	}

	/**
	 * @return the isEditFlag
	 */
	public Boolean getIsEditFlag() {
		return isEditFlag;
	}

	/**
	 * @param isEditFlag
	 *            the isEditFlag to set
	 */
	public void setIsEditFlag(Boolean isEditFlag) {
		this.isEditFlag = isEditFlag;
	}

	/**
	 * @return the creatorId
	 */
	public String getCreatorId() {
		return creatorId;
	}

	/**
	 * @param creatorId
	 *            the creatorId to set
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
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
	 * @return the pdesc
	 */
	public String getPdesc() {
		return pdesc;
	}

	/**
	 * @param pdesc
	 *            the pdesc to set
	 */
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	/**
	 * @return the pcode
	 */
	public String getPcode() {
		return pcode;
	}

	/**
	 * @param pcode
	 *            the pcode to set
	 */
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	/**
	 * @return the pvalue
	 */
	public String getPvalue() {
		return pvalue;
	}

	/**
	 * @param pvalue
	 *            the pvalue to set
	 */
	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}

	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}

	/**
	 * @param pname
	 *            the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
}
