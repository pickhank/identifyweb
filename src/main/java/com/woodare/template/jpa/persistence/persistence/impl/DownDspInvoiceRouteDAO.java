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
import com.woodare.template.jpa.model.DownDspInvoiceRoute;
import com.woodare.template.jpa.persistence.data.downdspinvoiceroute.SearchDownDspInvoiceRouteData;
import com.woodare.template.jpa.persistence.persistence.IDownDspInvoiceRouteDAO;

/**
 * ClassName: DownDspInvoiceRouteDAO
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/09/15
 */
@Service
public class DownDspInvoiceRouteDAO extends AbstractPagedDAO<DownDspInvoiceRoute> implements IDownDspInvoiceRouteDAO {

	@Override
	protected Class<DownDspInvoiceRoute> getDomainClass() {
		return DownDspInvoiceRoute.class;
	}

	@Override
	@Transactional(readOnly = true)
	public IPagedList<DownDspInvoiceRoute> searchItems(SearchDownDspInvoiceRouteData searchData) {

		StringBuffer sql = new StringBuffer("from DownDspInvoiceRoute a");

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
		if (StringUtils.isNotEmpty(searchData.getKeywords())) {
			conditions.add(new TypeCondition("keywords", "(a.downAgentNo like :keywords or a.downMchNo like :keywords)", "%" + searchData.getKeywords() + "%"));
		}
		if (searchData.getMercCategory() != null) {
			conditions.add(new TypeCondition("mercCategory", "a.mercCategory = :mercCategory", searchData.getMercCategory()));
		}
		if (searchData.getChannel() != null) {
			conditions.add(new TypeCondition("channel", "a.channel = :channel", searchData.getChannel()));
		}
		if (StringUtils.isNotEmpty(searchData.getStartDate())) {
			conditions.add(new TypeCondition("startDate", "a.startDate <= :startDate", searchData.getStartDate().replaceAll("-", "").replaceAll("/", "")));
		}
		if (StringUtils.isNotEmpty(searchData.getEndDate())) {
			conditions.add(new TypeCondition("endDate", "a.endDate >= :endDate", searchData.getEndDate().replaceAll("-", "").replaceAll("/", "")));
		}
		if (StringUtils.isNotEmpty(searchData.getStartTime())) {
			conditions.add(new TypeCondition("startTime", "a.startTime <= :startTime", searchData.getStartTime().replaceAll(":", "")));
		}
		if (StringUtils.isNotEmpty(searchData.getEndTime())) {
			conditions.add(new TypeCondition("endTime", "a.endTime >= :endTime", searchData.getEndTime().replaceAll(":", "")));
		}

		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}

		if (StringUtils.isEmpty(searchData.getOrderString())) {
			searchData.setOrderString("createDate desc");
		}

		// Create query statements
		TypedQuery<DownDspInvoiceRoute> query = this.getEntityManager().createQuery(sql.toString() + " order by a." + searchData.getOrderString(), DownDspInvoiceRoute.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	@Transactional(readOnly = true)
	public DownDspInvoiceRoute findById(String id) {
		DownDspInvoiceRoute item = this.findOne(id);
		if (item == null) {
			item = null;
		}
		return item;
	}
}
