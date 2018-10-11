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

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.core.jpa.data.systemroleright.SearchSystemRoleRightData;
import com.woodare.core.jpa.model.SystemRoleRight;
import com.woodare.core.jpa.service.ISystemRoleRightDAO;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;

/**
 * 
 * ClassName: SystemRoleRightDAO
 * 
 * @description
 * @author city_maven_auto_generate
 * @Date Jul 16, 2013
 * 
 */
@Service
public class SystemRoleRightDAO extends AbstractPagedDAO<SystemRoleRight> implements ISystemRoleRightDAO {

    @Override
    protected Class<SystemRoleRight> getDomainClass() {
        return SystemRoleRight.class;
    }
    
	@Override
    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<SystemRoleRight> searchSystemRoleRights(SearchSystemRoleRightData searchData) {
		
		StringBuffer sql = new StringBuffer("from SystemRoleRight a");
		
		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if(StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if(searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}
		if(StringUtils.isNotEmpty(searchData.getRoleId())) {
			conditions.add(new TypeCondition("roleId", "a.roleId = :roleId", searchData.getRoleId()));
		}
		// Append conditions
		if(conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}
		
		// Create query statements
		TypedQuery<SystemRoleRight> query = this.getEntityManager().createQuery(sql.toString(), SystemRoleRight.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);
		
		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);
		
		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	public void deleteAllByRoleId(String roleId) {
		StringBuffer sql = new StringBuffer("delete from system_role_right where role_id = :roleId ");
		Query query = this.getEntityManager().createNativeQuery(sql.toString());
		query.setParameter("roleId", roleId);
		query.executeUpdate();
	}

	@Override
	public boolean hasRightByRoleId(String roleId) {

		StringBuffer sql = new StringBuffer("from SystemRoleRight a");		
		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		conditions.add(new TypeCondition("roleId", "a.roleId = :roleId", roleId));
		
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);
		
		// Append conditions' variables
		this.addParameters(totalQuery, conditions);
		
		return totalQuery.getSingleResult() > 0;
	}
}


