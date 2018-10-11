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
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;
import com.woodare.template.jpa.model.NotifyRecord;
import com.woodare.template.jpa.persistence.data.notifyrecord.SearchNotifyRecordData;
import com.woodare.template.jpa.persistence.persistence.INotifyRecordDAO;

/**
 * ClassName: NotifyRecordDAO
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2017/09/11
 */
@Service
public class NotifyRecordDAO extends AbstractPagedDAO<NotifyRecord> implements INotifyRecordDAO {

	@Override
	protected Class<NotifyRecord> getDomainClass() {
		return NotifyRecord.class;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<NotifyRecord> searchItems(SearchNotifyRecordData searchData) {

		StringBuffer sql = new StringBuffer("from NotifyRecord a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if (StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if (searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}
		// change the key
		if (StringUtils.isNotEmpty(searchData.getKeywords())) {
			conditions.add(new TypeCondition("keywords", "(a.id like :keywords)", "%" + searchData.getKeywords() + "%"));
		}

		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}

		if (StringUtils.isEmpty(searchData.getOrderString())) {
			searchData.setOrderString("createDate desc");
		}

		// Create query statements
		TypedQuery<NotifyRecord> query = this.getEntityManager().createQuery(sql.toString() + " order by a." + searchData.getOrderString(), NotifyRecord.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public NotifyRecord findById(String id) {
		NotifyRecord item = this.findOne(id);
		if (item == null) {
			item = null;
		}
		return item;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public List<NotifyRecord> findNeedNotify(Date today) {
		TypedQuery<NotifyRecord> query = this.getEntityManager().createQuery(
				"from NotifyRecord a "
				+ "where a.createDate > :today "
				+ "and a.notifySuccess = false "
				+ "and a.notifyTimes < :notifyTimes "
				+ "order by a.notifyTimes asc",
				NotifyRecord.class);
		
		query.setParameter("notifyTimes", 5);
		query.setParameter("today", today);
		query.setMaxResults(300);
		
		return query.getResultList();
	}
}
