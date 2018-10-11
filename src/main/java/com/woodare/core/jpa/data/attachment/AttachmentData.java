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
package com.woodare.core.jpa.data.attachment;

import com.woodare.framework.data.impl.AbstractData;
import com.woodare.framework.model.data.EnumDeleteStatus;


/**
 * 
 * ClassName: AttachmentData
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2014/10/11
 * 
 */
public class AttachmentData extends AbstractData {
	private static final long serialVersionUID = -5856365919530725519L;
	
	/** ID */
	private String id;

	/** name */
    private String name;
    
	/** path */
    private String path;
    
	/** groupId */
    private String groupId;
    
	/** sortNum */
    private Integer sortNum;
    
	/** ext */
    private String ext;
    
	/** fileSize */
    private Long fileSize;
    
	/** validFlag */
    private Boolean validFlag;
    
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
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
    
    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
    
    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
    public Boolean getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(Boolean validFlag) {
        this.validFlag = validFlag;
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

