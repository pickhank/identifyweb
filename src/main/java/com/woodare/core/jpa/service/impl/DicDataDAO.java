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

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.core.jpa.data.dicdata.SearchDicDataData;
import com.woodare.core.jpa.model.DicData;
import com.woodare.core.jpa.service.IDicDataDAO;
import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;

/**
 * 
 * ClassName: DicDataDAO
 * 
 * @description
 * @author city_maven_auto_generate
 * @Date Jul 16, 2013
 * 
 */
@Service
public class DicDataDAO extends AbstractPagedDAO<DicData> implements IDicDataDAO {

    @Override
    protected Class<DicData> getDomainClass() {
        return DicData.class;
    }
    
	@Override
    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<DicData> searchDicDatas(SearchDicDataData searchData) {
		
		StringBuffer sql = new StringBuffer("from DicData a");
		
		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if(StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if(searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}
		if(StringUtils.isNotEmpty(searchData.getParentCode())) {
			conditions.add(new TypeCondition("parentCode", "a.parentCode = :parentCode", searchData.getParentCode()));
		}
		else if(searchData.getParentCode() != null && searchData.getParentCode().equals("")) {
			conditions.add(new TypeCondition(null, "a.parentCode is null", null));
		}
		
		// Append conditions
		if(conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}
		
		// Create query statements
		TypedQuery<DicData> query = this.getEntityManager().createQuery(sql.toString(), DicData.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);
		
		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);
		
		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public DicData findByCode(String code) {
		StringBuffer sql = new StringBuffer("from DicData a where a.code = :code");
		TypedQuery<DicData> query = this.getEntityManager().createQuery(sql.toString(), DicData.class);
		query.setParameter("code", code);
		
		try {
			return query.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}

	@Override
    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public boolean hasChildren(String code) {
		StringBuffer sql = new StringBuffer("select count(a.id) from DicData a where a.parentCode = :parentCode");
		TypedQuery<Long> query = this.getEntityManager().createQuery(sql.toString(), Long.class);
		query.setParameter("parentCode", code);
		return query.getSingleResult() > 0;
	}
}


