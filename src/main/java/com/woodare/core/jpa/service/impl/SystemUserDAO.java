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

import com.woodare.core.jpa.data.systemuser.SearchSystemUserData;
import com.woodare.core.jpa.model.SystemUser;
import com.woodare.core.jpa.model.data.EnumUserRole;
import com.woodare.core.jpa.service.ISystemUserDAO;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;

/**
 * 
 * ClassName: SystemUserDAO
 * 
 * @description
 * @author city_maven_auto_generate
 * @Date Jul 12, 2013
 * 
 */
@Service
public class SystemUserDAO extends AbstractPagedDAO<SystemUser> implements ISystemUserDAO {

	@Override
	protected Class<SystemUser> getDomainClass() {
		return SystemUser.class;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public Long countUsers(SearchSystemUserData searchData) {
		StringBuffer sql = new StringBuffer("from SystemUser a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if (StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if (searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}
		String keywords = StringUtils.trim(searchData.getKeywords());
		if (StringUtils.isNotEmpty(keywords)) {
			String keywordFilter = "(a.username like :keywords or a.email like :keywords)";
			conditions.add(new TypeCondition("keywords", keywordFilter, "%" + keywords + "%"));
		}
		if (StringUtils.isNotEmpty(searchData.getUsername())) {
			conditions.add(new TypeCondition("username", "a.username = :username", searchData.getUsername()));
		}
		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}

		// Create query statements
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return totalQuery.getSingleResult();
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<SystemUser> searchUsers(SearchSystemUserData searchData) {

		StringBuffer sql = new StringBuffer("from SystemUser a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if (StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if (searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}

		String keywords = StringUtils.trim(searchData.getKeywords());
		if (StringUtils.isNotEmpty(keywords)) {
			String keywordFilter = "(a.username like :keywords or a.email like :keywords or a.name like :keywords)";
			conditions.add(new TypeCondition("keywords", keywordFilter, "%" + keywords + "%"));
		}
		if (StringUtils.isNotEmpty(searchData.getUsername())) {
			conditions.add(new TypeCondition("username", "a.username = :username", searchData.getUsername()));
		}
		if (searchData.getFilterIsAdminFlag() != null) {
			conditions.add(new TypeCondition("filterIsAdminFlag", "a.isAdminFlag = :filterIsAdminFlag", searchData.getFilterIsAdminFlag()));
		}
		
		// 	SUPERVISOR,//超级管理员
//		ADMIN, //运维
//		LIQUIDATING,//清算员
//		OPERATION,//管理员
		if (searchData.getUserRole() != null) {
			if (searchData.getUserRole() == EnumUserRole.SUPERVISOR) {
				conditions.add(new TypeCondition("userRole", "(a.userRole = :userRole or a.userRole is null)", searchData.getUserRole()));
			} else {
				conditions.add(new TypeCondition("userRole", "a.userRole = :userRole", searchData.getUserRole()));
			}
		}
		if(searchData.getUserRoles() != null && searchData.getUserRoles().size() > 0) {
//			List<String> userRoles = new ArrayList<String>();
//			for(EnumUserRole r : searchData.getUserRoles()) {
//				userRoles.add(r.toString());
//			}
			conditions.add(new TypeCondition("userRoles", "a.userRole in (:userRoles)", searchData.getUserRoles()));
		}
			
		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}

		// Create query statements
		TypedQuery<SystemUser> query = this.getEntityManager().createQuery(sql.toString() + " order by a.lastLoginDate desc, a.updateDate desc",
				SystemUser.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public SystemUser findByEmail(String email) {
		TypedQuery<SystemUser> query = this.getEntityManager().createNamedQuery("user.findByEmail", SystemUser.class);
		query.setMaxResults(1);
		query.setParameter("email", email);

		List<SystemUser> users = query.getResultList();
		return users != null && users.size() > 0 ? users.get(0) : null;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public SystemUser findByUsername(String username) {
		TypedQuery<SystemUser> query = this.getEntityManager().createNamedQuery("user.findByUsername", SystemUser.class);
		query.setMaxResults(1);

		query.setParameter("username", username);

		List<SystemUser> users = query.getResultList();
		return users != null && users.size() > 0 ? users.get(0) : null;
	}
}
