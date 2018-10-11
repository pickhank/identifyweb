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

import javax.persistence.Column;
import javax.persistence.Entity;

import com.woodare.core.base.AbstractBusiModel;

/**
 * 附件表
 * 
 * @author lu_feng
 * 
 */
@Entity
public class Attachment extends AbstractBusiModel {
    private static final long serialVersionUID = -5856365919530725519L;

    /* 附件名称 */
    @Column(length = 128)
    private String name;

    /* 附件路径 */
    @Column(length = 512)
    private String path;

    /* 附件组号 */
    @Column(length = 128)
    private String groupId;

    /* 附件序号 */
    private Integer sortNum = 0;

    /* 附件类型 */
    @Column(length = 10)
    private String ext;

    /* 图片宽度 */
    private Long fileSize;

    /* 是否有效 */
    private Boolean validFlag = false;

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
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     *            the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId
     *            the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
     * @return the ext
     */
    public String getExt() {
        return ext;
    }

    /**
     * @param ext
     *            the ext to set
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * @return the fileSize
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize
     *            the fileSize to set
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return the validFlag
     */
    public Boolean getValidFlag() {
        return validFlag;
    }

    /**
     * @param validFlag
     *            the validFlag to set
     */
    public void setValidFlag(Boolean validFlag) {
        this.validFlag = validFlag;
    }

}
