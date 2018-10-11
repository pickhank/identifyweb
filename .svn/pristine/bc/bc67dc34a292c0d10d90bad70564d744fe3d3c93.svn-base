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
package com.woodare.core.jpa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.core.jpa.data.systemproperties.SearchSystemPropertiesData;
import com.woodare.core.jpa.model.SystemProperties;
import com.woodare.core.jpa.service.ISystemPropertiesDAO;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;

/**
 * 
 * ClassName: SystemPropertiesDAO
 * 
 * @description
 * @author city_maven_auto_generate
 * @Date Sep 28, 2013
 * 
 */
@Service
public class SystemPropertiesDAO extends AbstractPagedDAO<SystemProperties> implements ISystemPropertiesDAO {

	@Override
	protected Class<SystemProperties> getDomainClass() {
		return SystemProperties.class;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<SystemProperties> searchSystemPropertiess(SearchSystemPropertiesData searchData) {

		StringBuffer sql = new StringBuffer("from SystemProperties a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if (StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if (searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.ids in (:ids)", searchData.getIds()));
		}
		// TODO: add more conditions

		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}

		// Create query statements
		TypedQuery<SystemProperties> query = this.getEntityManager().createQuery(sql.toString() + " order by a.sortNum", SystemProperties.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.woodare.core.jpa.service.ISystemPropertiesDAO#searchByPcode(java.
	 * lang.String)
	 */
	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public SystemProperties searchByPcode(String pcode) {

		StringBuffer sql = new StringBuffer("from SystemProperties a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if (StringUtils.isNotEmpty(pcode)) {
			conditions.add(new TypeCondition("pcode", "a.pcode = :pcode", pcode));
		}

		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}

		// Create query statements
		TypedQuery<SystemProperties> query = this.getEntityManager().createQuery(sql.toString() + " order by a.sortNum", SystemProperties.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		IPagedList<SystemProperties> items = this.getPagedList(totalQuery, query, new SearchSystemPropertiesData());
		return (items != null && items.size() > 0) ? items.get(0) : null;
	}
}
