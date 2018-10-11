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
import com.woodare.template.jpa.model.DownMerchantBalance;
import com.woodare.template.jpa.persistence.data.downmerchantbalance.SearchDownMerchantBalanceData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantBalanceDAO;

/**
 * 
 * ClassName: DownMerchantBalanceDAO
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/03/03
 * 
 */
@Service
public class DownMerchantBalanceDAO extends AbstractPagedDAO<DownMerchantBalance> implements IDownMerchantBalanceDAO {

	@Override
	protected Class<DownMerchantBalance> getDomainClass() {
		return DownMerchantBalance.class;
	}
	
	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<DownMerchantBalance> searchItems(SearchDownMerchantBalanceData searchData) {
		
		StringBuffer sql = new StringBuffer("from DownMerchantBalance a");
		
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
		if (StringUtils.isNotEmpty(searchData.getReferChannel())) {
			conditions.add(new TypeCondition("referChannel", "a.referChannel = :referChannel", searchData.getReferChannel()));
		}
		if (StringUtils.isNotEmpty(searchData.getReferChannelAccNo())) {
			conditions.add(new TypeCondition("referChannelAccNo", "a.referChannelAccNo = :referChannelAccNo", searchData.getReferChannelAccNo()));
		}
		if (StringUtils.isNotEmpty(searchData.getReferTransNo())) {
			conditions.add(new TypeCondition("referTransNo", "a.referTransNo = :referTransNo", searchData.getReferTransNo()));
		}
		// 收支流水号
		if (StringUtils.isNotEmpty(searchData.getBalanceNo())) {
			conditions.add(new TypeCondition("balanceNo", "a.balanceNo = :balanceNo", searchData.getBalanceNo()));
		}
		else {
			if (searchData.getStartDate() != null) {
				conditions.add(new TypeCondition("startDate", "a.createDate >= :startDate", searchData.getStartDate()));
			}
			if (searchData.getEndDate() != null) {
				conditions.add(new TypeCondition("endDate", "a.createDate < :endDate", searchData.getEndDate()));
			}
		}
		// 收支日期YYYYMMDD
		if (StringUtils.isNotEmpty(searchData.getBalanceDate())) {
			conditions.add(new TypeCondition("balanceDate", "a.balanceDate = :balanceDate", searchData.getBalanceDate().replaceAll("/", "")));
		}
		// 虚拟账户
		if(searchData.getAccType() != null) {
			conditions.add(new TypeCondition("accType", "a.accType = :accType", searchData.getAccType()));
		}
		// 收支来源
		if (searchData.getSourceType() != null) {
			conditions.add(new TypeCondition("sourceType", "a.sourceType = :sourceType", searchData.getSourceType()));
		}
		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}

		if (StringUtils.isEmpty(searchData.getOrderString())) {
			searchData.setOrderString("balanceNo desc, sortNum desc");
		}

		// Create query statements
		TypedQuery<DownMerchantBalance> query = this.getEntityManager().createQuery(sql.toString() 
				+ " order by a." + searchData.getOrderString(), DownMerchantBalance.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}
	
	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public DownMerchantBalance findById(String id) {
		DownMerchantBalance item = this.findOne(id);
		if (item == null) {
			item = null;
		}
		return item;
	}
}


