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
package com.woodare.core.jpa.data.systemproperties;

import com.woodare.framework.data.impl.AbstractData;
import com.woodare.framework.model.data.EnumDeleteStatus;


/**
 * 
 * ClassName: SystemPropertiesData
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2014/10/11
 * 
 */
public class SystemPropertiesData extends AbstractData {
	private static final long serialVersionUID = -7467799779478963793L;
	
	/** ID */
	private String id;

	/** pcode */
    private String pcode;
    
	/** pvalue */
    private String pvalue;
    
	/** pname */
    private String pname;
    
	/** pdesc */
    private String pdesc;
    
	/** Sort number */
    private Integer sortNum;
    
	/** isEditFlag */
    private Boolean isEditFlag;
    
	/** creatorId */
    private String creatorId;
    
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
	
    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }
    
    public String getPvalue() {
        return pvalue;
    }

    public void setPvalue(String pvalue) {
        this.pvalue = pvalue;
    }
    
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
    
    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }
    
    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
    
    public Boolean getIsEditFlag() {
        return isEditFlag;
    }

    public void setIsEditFlag(Boolean isEditFlag) {
        this.isEditFlag = isEditFlag;
    }
    
    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
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

