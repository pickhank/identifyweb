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

import com.woodare.core.jpa.data.systemroleuser.SearchSystemRoleUserData;
import com.woodare.core.jpa.model.SystemRoleUser;
import com.woodare.core.jpa.model.SystemUser;
import com.woodare.core.jpa.service.ISystemRoleUserDAO;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;

/**
 * 
 * ClassName: SystemRoleUserDAO
 * 
 * @description
 * @author city_maven_auto_generate
 * @Date Jul 16, 2013
 * 
 */
@Service
public class SystemRoleUserDAO extends AbstractPagedDAO<SystemRoleUser> implements ISystemRoleUserDAO {

    @Override
    protected Class<SystemRoleUser> getDomainClass() {
        return SystemRoleUser.class;
    }
    
	@Override
    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<SystemUser> searchUsers(SearchSystemRoleUserData searchData) {
		StringBuffer sql = new StringBuffer("from SystemRoleUser a");
		
		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if(StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if(searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}
		if(StringUtils.isNotEmpty(searchData.getUserId())) {
			conditions.add(new TypeCondition("userId", "a.userId = :userId", searchData.getUserId()));
		}
		if(StringUtils.isNotEmpty(searchData.getKeywords())) {
			String keywordsFilter = "(a.role.roleName like :roleName or a.user.id like :keywords or a.user.username like :roleName or a.user.nickName like :roleName)";
			conditions.add(new TypeCondition("keywords", keywordsFilter, "%" + searchData.getKeywords() + "%"));
		}
		if(StringUtils.isNotEmpty(searchData.getRoleId())) {
			conditions.add(new TypeCondition("roleId", "a.roleId = :roleId", searchData.getRoleId()));
		}
		// Append conditions
		if(conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}
		
		// Create query statements
		TypedQuery<SystemUser> query = this.getEntityManager().createQuery("select distinct a.user " + sql.toString(), SystemUser.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(distinct a.userId) " + sql.toString(), Long.class);
		
		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);
		
		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}
	
	@Override
    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<SystemRoleUser> searchSystemRoleUsers(SearchSystemRoleUserData searchData) {
		
		StringBuffer sql = new StringBuffer("from SystemRoleUser a");
		
		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if(StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if(searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}
		if(StringUtils.isNotEmpty(searchData.getUserId())) {
			conditions.add(new TypeCondition("userId", "a.userId = :userId", searchData.getUserId()));
		}
		if(searchData.getUserIds() != null && searchData.getUserIds().size() > 0) {
			conditions.add(new TypeCondition("userIds", "a.userId in (:userIds) ", searchData.getUserIds()));
		}
		if(StringUtils.isNotEmpty(searchData.getKeywords())) {
			String keywordsFilter = "(a.role.roleName like :roleName or a.user.id like :keywords or a.user.username like :roleName or a.user.nickName like :roleName)";
			conditions.add(new TypeCondition("keywords", keywordsFilter, "%" + searchData.getKeywords() + "%"));
		}
		if(StringUtils.isNotEmpty(searchData.getRoleId())) {
			conditions.add(new TypeCondition("roleId", "a.roleId = :roleId", searchData.getRoleId()));
		}
		// Append conditions
		if(conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}
		
		// Create query statements
		TypedQuery<SystemRoleUser> query = this.getEntityManager().createQuery(sql.toString() + " order by a.userId", SystemRoleUser.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);
		
		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);
		
		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	public boolean hasRole(String userId, String roleId) {
		StringBuffer sql = new StringBuffer("select count(a.id) from SystemRoleUser a ");
		
		List<TypeCondition> conditions = new ArrayList<TypeCondition>();
		conditions.add(new TypeCondition("userId", "a.userId = :userId", userId));
		conditions.add(new TypeCondition("roleId", "a.roleId = :roleId", roleId));
		sql.append(" where ").append(this.joinConditions(conditions, " and "));
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery(sql.toString(), Long.class);
		this.addParameters(totalQuery, conditions);
		
		return totalQuery.getSingleResult() > 0;
	}

	@Override
	public void addRole(String userId, String roleId) {
		SystemRoleUser roleUser = new SystemRoleUser();
		roleUser.setRoleId(roleId);
		roleUser.setUserId(userId);
		
		this.save(roleUser);
	}

	@Override
	public void removeRole(String userId, String roleId) {
		StringBuffer sql = new StringBuffer("from SystemRoleUser a ");
		
		List<TypeCondition> conditions = new ArrayList<TypeCondition>();
		conditions.add(new TypeCondition("userId", "a.userId = :userId", userId));
		conditions.add(new TypeCondition("roleId", "a.roleId = :roleId", roleId));
		sql.append(" where ").append(this.joinConditions(conditions, " and "));
		TypedQuery<SystemRoleUser> query = this.getEntityManager().createQuery(sql.toString(), SystemRoleUser.class);
		this.addParameters(query, conditions);
		List<SystemRoleUser> items = query.getResultList();
		
		if(items != null) {
			for(SystemRoleUser item : items) {
				this.delete(item);
			}
		}
		
	}

	@Override
	public boolean hasUserByRoleId(String roleId) {
		StringBuffer sql = new StringBuffer("from SystemRoleUser a ");
		
		List<TypeCondition> conditions = new ArrayList<TypeCondition>();
		conditions.add(new TypeCondition("roleId", "a.roleId = :roleId", roleId));
		sql.append(" where ").append(this.joinConditions(conditions, " and "));
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);
		this.addParameters(totalQuery, conditions);
		
		return totalQuery.getSingleResult() > 0;
	}
}


