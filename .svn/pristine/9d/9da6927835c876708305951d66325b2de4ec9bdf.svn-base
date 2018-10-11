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
package com.woodare.template.jpa.persistence.persistence.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;
import com.woodare.template.jpa.model.Recon;
import com.woodare.template.jpa.persistence.data.recon.SearchReconData;
import com.woodare.template.jpa.persistence.persistence.IReconDAO;

/**
 * 
 * ClassName: ReconDAO
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2016/11/30
 * 
 */
@Service
public class ReconDAO extends AbstractPagedDAO<Recon> implements IReconDAO {

	@Override
	protected Class<Recon> getDomainClass() {
		return Recon.class;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<Recon> searchRecons(SearchReconData searchData) {

		StringBuffer sql = new StringBuffer("from Recon a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if (StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if (searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}
		if (StringUtils.isNotEmpty(searchData.getMchNo())) {
			conditions.add(new TypeCondition("mchNo", "a.mchNo = :mchNo", searchData.getMchNo()));
		}
		
		if (StringUtils.isNotEmpty(searchData.getAgentNo())) {
			conditions.add(new TypeCondition("agentNo", "a.agentNo = :agentNo", searchData.getAgentNo()));
		}
		
		if (StringUtils.isNotEmpty(searchData.getOrderDate())) {
			conditions.add(new TypeCondition("orderDate", "a.orderDate = :orderDate", searchData.getOrderDate()));
		}

		if (StringUtils.isNotEmpty(searchData.getKeywords())) {
			conditions.add(new TypeCondition("keywords", "(a.mchNo like :keywords or a.orderDate like :keywords)", "%" + searchData.getKeywords() + "%"));
		}

		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}

		// Create query statements
		TypedQuery<Recon> query = this.getEntityManager().createQuery(sql.toString() + " order by a.orderDate desc", Recon.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public Recon findByMchNoAndDate(String mchNo, String dateStr) {
		TypedQuery<Recon> query = this.getEntityManager()
				.createQuery("from Recon a where a.mchNo = :mchNo and a.orderDate=:orderDate ", Recon.class);
		query.setMaxResults(1);
		query.setParameter("mchNo", mchNo);
		query.setParameter("orderDate", dateStr);
		List<Recon> users = query.getResultList();
		return users != null && users.size() > 0 ? users.get(0) : null;
	}
}
