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

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.impl.AbstractPagedDAO;
import com.woodare.template.jpa.model.DownMerchantProduct;
import com.woodare.template.jpa.persistence.data.downmerchantproduct.SearchDownMerchantProductData;
import com.woodare.template.jpa.persistence.persistence.IDownMerchantProductDAO;

/**
 * ClassName: DownMerchantProductDAO
 * 
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/03/07
 */
@Service
public class DownMerchantProductDAO extends AbstractPagedDAO<DownMerchantProduct> implements IDownMerchantProductDAO {

	@Override
	protected Class<DownMerchantProduct> getDomainClass() {
		return DownMerchantProduct.class;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<DownMerchantProduct> searchItems(SearchDownMerchantProductData searchData) {

		StringBuffer sql = new StringBuffer("from DownMerchantProduct a");

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
		if (StringUtils.isNotEmpty(searchData.getMchNo())) {
			conditions.add(new TypeCondition("mchNo", "a.mchNo = :mchNo", searchData.getMchNo()));
		}

		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}

		if (StringUtils.isEmpty(searchData.getOrderString())) {
			searchData.setOrderString("createDate desc");
		}

		// Create query statements
		TypedQuery<DownMerchantProduct> query = this.getEntityManager().createQuery(sql.toString() + " order by a." + searchData.getOrderString(), DownMerchantProduct.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	public int updateFee(DownMerchantProduct product) {
		StringBuffer sql = new StringBuffer("update DownMerchantProduct a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();
		// /** 姓名 */
		conditions.add(new TypeCondition("mchName", "a.mchName = :mchName", product.getMchName()));
		// 交易费率，单位：千分之
		if (product.getFeeRatio() != null)
			conditions.add(new TypeCondition("feeRatio", "a.feeRatio = :feeRatio", product.getFeeRatio()));
		// 单笔交易加收费，单位：元
		if (product.getAddFeeAmt() != null)
			conditions.add(new TypeCondition("addFeeAmt", "a.addFeeAmt = :addFeeAmt", product.getAddFeeAmt()));
		// 代付费率，单位：千分之
		if (product.getDrawFeeRatio() != null)
			conditions.add(new TypeCondition("drawFeeRatio", "a.drawFeeRatio = :drawFeeRatio", product.getDrawFeeRatio()));
		// 单笔代付加收费，单位：元
		if (product.getAddDrawFeeAmt() != null)
			conditions.add(new TypeCondition("addDrawFeeAmt", "a.addDrawFeeAmt = :addDrawFeeAmt", product.getAddDrawFeeAmt()));
		// 单笔代付加收费，单位：元
		if (product.getMinPerAmt() != null)
			conditions.add(new TypeCondition("minPerAmt", "a.minPerAmt = :minPerAmt", product.getMinPerAmt()));
		// 单笔代付加收费，单位：元
		if (product.getMaxPerAmt() != null)
			conditions.add(new TypeCondition("maxPerAmt", "a.maxPerAmt = :maxPerAmt", product.getMaxPerAmt()));
		// 单笔代付加收费，单位：元
		if (product.getMaxTotalAmt() != null)
			conditions.add(new TypeCondition("maxTotalAmt", "a.maxTotalAmt = :maxTotalAmt", product.getMaxTotalAmt()));
		// 状态
		conditions.add(new TypeCondition("status", "a.status = :status", product.getStatus()));
		// 修改时间
		conditions.add(new TypeCondition("updateDate", "a.updateDate = :updateDate", new Date()));

		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" set ").append(this.joinConditions(conditions, ", "));
		}
		sql.append(" where a.mchNo = :mchNo and a.dspMode = :dspMode");

		// Create query statements
		Query query = this.getEntityManager().createQuery(sql.toString());

		// Append conditions' variables
		this.addParameters(query, conditions);

		query.setParameter("mchNo", product.getMchNo());
		query.setParameter("dspMode", product.getDspMode());

		return query.executeUpdate();
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public DownMerchantProduct findById(String id) {
		DownMerchantProduct item = this.findOne(id);
		if (item == null) {
			item = null;
		}
		return item;
	}
}
