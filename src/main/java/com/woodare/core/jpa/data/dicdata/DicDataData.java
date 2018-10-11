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
package com.woodare.core.jpa.data.dicdata;

import com.woodare.framework.data.impl.AbstractData;
import com.woodare.framework.model.data.EnumDeleteStatus;
import com.woodare.framework.model.data.EnumDicExtraType;

/**
 * 
 * ClassName: DicDataData
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2014/10/11
 * 
 */
public class DicDataData extends AbstractData {
	private static final long serialVersionUID = 6353917269898934954L;
	
	/** ID */
	private String id;

	/** code */
    private String code;
    
	/** name */
    private String name;
    
	/** descriptions */
    private String descriptions;
    
	/** parentCode */
    private String parentCode;
    
	/** extra */
    private String extra;
    
	/** extraType */
    private EnumDicExtraType extraType;
    
	/** extraDescription */
    private String extraDescription;
    
	/** level */
    private Integer level;
    
	/** sortNum */
    private Integer sortNum;
    
	/** systemMenuFlag */
    private Boolean systemMenuFlag;
    
	/** isValidFlag */
    private Boolean isValidFlag;
    
	private EnumDeleteStatus deleteStatus = EnumDeleteStatus.VALID; 
	
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
	
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
    
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    
    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
    
    public EnumDicExtraType getExtraType() {
        return extraType;
    }

    public void setExtraType(EnumDicExtraType extraType) {
        this.extraType = extraType;
    }
    
    public String getExtraDescription() {
        return extraDescription;
    }

    public void setExtraDescription(String extraDescription) {
        this.extraDescription = extraDescription;
    }
    
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    
    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
    
    public Boolean getSystemMenuFlag() {
        return systemMenuFlag;
    }

    public void setSystemMenuFlag(Boolean systemMenuFlag) {
        this.systemMenuFlag = systemMenuFlag;
    }
    
    public Boolean getIsValidFlag() {
        return isValidFlag;
    }

    public void setIsValidFlag(Boolean isValidFlag) {
        this.isValidFlag = isValidFlag;
    }
    
	/**
	 * @return the deleteStatus
	 */
	public EnumDeleteStatus getDeleteStatus() {
		return deleteStatus;
	}

	/**
	 * @param deleteStatus
	 *            the deleteStatus to set
	 */
	public void setDeleteStatus(EnumDeleteStatus deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
}

