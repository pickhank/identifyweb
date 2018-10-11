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
package com.woodare.core.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.woodare.framework.model.AbstractModel;

/**
 * 
 * ClassName: AbstractModel
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
@MappedSuperclass
public class AbstractBusiModel extends AbstractModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public AbstractBusiModel() {

	}

	public AbstractBusiModel(String uuid) {
		super(uuid);
	}

	@Id
	@Column(length = 64)
	private String id;

	/** 创建时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	/** 修改时间 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
