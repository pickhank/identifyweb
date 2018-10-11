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

import com.woodare.core.jpa.data.systemmenu.SearchSystemMenuData;
import com.woodare.core.jpa.model.SystemMenu;
import com.woodare.core.jpa.service.ISystemMenuDAO;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;

/**
 * 
 * ClassName: SystemMenuDAO
 * 
 * @description
 * @author city_maven_auto_generate
 * @Date Jul 16, 2013
 * 
 */
@Service
public class SystemMenuDAO extends AbstractPagedDAO<SystemMenu> implements ISystemMenuDAO {

    @Override
    protected Class<SystemMenu> getDomainClass() {
        return SystemMenu.class;
    }
    
	@Override
    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<SystemMenu> searchSystemMenus(SearchSystemMenuData searchData) {
		
		StringBuffer sql = new StringBuffer("from SystemMenu a");
		
		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if(StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if(searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}
		// TODO: add more conditions
		
		// Append conditions
		if(conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}
		
		// Create query statements
		TypedQuery<SystemMenu> query = this.getEntityManager().createQuery(sql.toString() + " order by a.", SystemMenu.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);
		
		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);
		
		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	public boolean hasChildMenus(String parentMenuId) {
		boolean ret = false;
		if(StringUtils.isNotEmpty(parentMenuId)) {
			String sql = "select count(a.id) from SystemMenu a where a.parentMenuId = :parentMenuId";
			TypedQuery<Long> totalQuery = this.getEntityManager().createQuery(sql, Long.class);
			totalQuery.setParameter("parentMenuId", parentMenuId);
			ret = totalQuery.getSingleResult() > 0;
		}
		return ret;
	}
}


