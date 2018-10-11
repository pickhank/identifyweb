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
package com.woodare.framework.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * ClassName: AbstractModel
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 */
public abstract class AbstractModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AbstractModel() {
		this.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	}

	public AbstractModel(String uuid) {
		this.setId(uuid);
	}

	/**
	 * @return the createDate
	 */
	public abstract Date getCreateDate();

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public abstract void setCreateDate(Date createDate);

	/**
	 * @return the updateDate
	 */
	public abstract Date getUpdateDate();

	/**
	 * @param updateDate
	 *            the updateDate to set
	 */
	public abstract void setUpdateDate(Date updateDate);

	/**
	 * @return the id
	 */
	public abstract String getId();

	/**
	 * @param id
	 *            the id to set
	 */
	public abstract void setId(String id);

	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractModel)) {
			return false;
		}
		AbstractModel other = (AbstractModel) obj;
		return getId().equals(other.getId());
	}
}
