/******************************************************************************
 *                                                                             
 *                      Woodare PROPRIETARY INFORMATION                        
 *                                                                             
 *          The information contained herein is proprietary to Woodare         
 *           and shall not be reproduced or disclosed in whole or in part      
 *                    or used for any design or manufacture                    
 *              without direct written authorization from FengDa.              
 *                                                                             
 *            Copyright (c) 2013 by Woodare.  All rights reserved.             
 *                                                                             
 *****************************************************************************/
package com.woodare.core.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.woodare.core.base.AbstractBusiModel;
import com.woodare.framework.model.data.EnumDicExtraType;

/**
 * ClassName: DicData.java
 * 
 * @description 字典定义表
 * @author jiang_xx
 * @Date 2013-7-15
 * 
 */
@Entity
public class DicData extends AbstractBusiModel implements Serializable {
	private static final long serialVersionUID = 6353917269898934954L;

	/* Code */
	@Column(length = 50)
	private String code;

	/* 名称 */
	@Column(length = 128)
	private String name;

	/* 描述信息 */
	@Column(length = 256)
	private String descriptions;

	/* 父节点Code */
	@Column(length = 50)
	private String parentCode;

	/* 备用字段 */
	@Column(length = 128)
	private String extra;

	/* 备用字段 */
	@Enumerated(EnumType.STRING)
	private EnumDicExtraType extraType = EnumDicExtraType.NONE;

	/* 文字表述 */
	@Column(length = 128)
	private String extraDescription;

	/* 节点层级 */
	private Integer level = 0;

	/* 排序 */
	private Integer sortNum = 0;

	/* 系统保留菜单 */
	private Boolean systemMenuFlag = false;

	/* 是否使用 */
	private Boolean isValidFlag = true;

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the extraType
	 */
	public EnumDicExtraType getExtraType() {
		return extraType;
	}

	/**
	 * @param extraType
	 *            the extraType to set
	 */
	public void setExtraType(EnumDicExtraType extraType) {
		this.extraType = extraType;
	}

	/**
	 * @return the extraDescription
	 */
	public String getExtraDescription() {
		return extraDescription;
	}

	/**
	 * @param extraDescription
	 *            the extraDescription to set
	 */
	public void setExtraDescription(String extraDescription) {
		this.extraDescription = extraDescription;
	}

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
	 * @return the extra
	 */
	public String getExtra() {
		return extra;
	}

	/**
	 * @param extra
	 *            the extra to set
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}

	/**
	 * @return the isValidFlag
	 */
	public Boolean getIsValidFlag() {
		return isValidFlag;
	}

	/**
	 * @param isValidFlag
	 *            the isValidFlag to set
	 */
	public void setIsValidFlag(Boolean isValidFlag) {
		this.isValidFlag = isValidFlag;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the descriptions
	 */
	public String getDescriptions() {
		return descriptions;
	}

	/**
	 * @param descriptions
	 *            the descriptions to set
	 */
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	/**
	 * @return the parentCode
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * @param parentCode
	 *            the parentCode to set
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
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

}
