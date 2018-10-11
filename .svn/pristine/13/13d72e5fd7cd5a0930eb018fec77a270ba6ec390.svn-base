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
import com.woodare.template.jpa.model.Kbin;
import com.woodare.template.jpa.persistence.data.kbin.SearchKbinData;
import com.woodare.template.jpa.persistence.persistence.IKbinDAO;

/**
 * ClassName: KbinDAO
 * 
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2017/04/25
 */
@Service
public class KbinDAO extends AbstractPagedDAO<Kbin> implements IKbinDAO {

	@Override
	protected Class<Kbin> getDomainClass() {
		return Kbin.class;
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public IPagedList<Kbin> searchKbins(SearchKbinData searchData) {

		StringBuffer sql = new StringBuffer("from Kbin a");

		List<TypeCondition> conditions = new ArrayList<TypeCondition>();

		// Add filter conditions
		if (StringUtils.isNotEmpty(searchData.getId())) {
			conditions.add(new TypeCondition("id", "a.id = :id", searchData.getId()));
		}
		if (searchData.getIds() != null && searchData.getIds().size() > 0) {
			conditions.add(new TypeCondition("ids", "a.id in (:ids)", searchData.getIds()));
		}
		// TODO: add more conditions
		if (StringUtils.isNotEmpty(searchData.getStartNum())) {
			conditions.add(new TypeCondition("startNum", "a.startNum = :startNum", searchData.getStartNum()));
		}
		if (StringUtils.isNotEmpty(searchData.getBankName())) {
			conditions.add(new TypeCondition("bankName", "a.bankName = :bankName", searchData.getBankName()));
		}
		if (StringUtils.isNotEmpty(searchData.getBankCode())) {
			conditions.add(new TypeCondition("bankCode", "a.bankCode = :bankCode", searchData.getBankCode()));
		}
		if (StringUtils.isNotEmpty(searchData.getBankNo())) {
			conditions.add(new TypeCondition("bankNo", "a.bankNo = :bankNo", searchData.getBankNo()));
		}
		if (StringUtils.isNotEmpty(searchData.getBankNameAbbr())) {
			conditions.add(new TypeCondition("bankNameAbbr", "a.bankNameAbbr = :bankNameAbbr", searchData.getBankNameAbbr()));
		}
		if (searchData.getCardLength()!=null) {
			conditions.add(new TypeCondition("cardLength", "a.cardLength = :cardLength", searchData.getCardLength()));
		}
		if (StringUtils.isNotEmpty(searchData.getCardAttr())) {
			conditions.add(new TypeCondition("cardAttr", "a.cardAttr = :cardAttr", searchData.getCardAttr()));
		}
		if (StringUtils.isNotEmpty(searchData.getKeywords())) {
			conditions.add(new TypeCondition("keywords",
					"(a.startNum like :keywords or a.bankName like :keywords or a.bankCode like :keywords or a.bankNo like :keywords or a.bankNameAbbr like :keywords or a.cardLength like :keywords)",
					"%" + searchData.getKeywords() + "%"));
		}
		// Append conditions
		if (conditions != null && conditions.size() > 0) {
			sql.append(" where ").append(this.joinConditions(conditions, " and "));
		}

		// Create query statements
		TypedQuery<Kbin> query = this.getEntityManager().createQuery(sql.toString(), Kbin.class);
		TypedQuery<Long> totalQuery = this.getEntityManager().createQuery("select count(a.id) " + sql.toString(), Long.class);

		// Append conditions' variables
		this.addParameters(query, conditions);
		this.addParameters(totalQuery, conditions);

		// Send back returns
		return this.getPagedList(totalQuery, query, searchData);
	}

	@Override
	@Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
	public String getBankCode(String cardNo) {
		StringBuffer sql = new StringBuffer("from Kbin a where a.cardLength = :len and instr(:cardNo, a.startNum) = 1");

		TypedQuery<Kbin> query = this.getEntityManager().createQuery(sql.toString(), Kbin.class);
		query.setMaxResults(1);
		query.setParameter("len", cardNo.length());
		query.setParameter("cardNo", cardNo);
		List<Kbin> models = query.getResultList();
		String bankNo = null;
		if (models != null && models.size() > 0) {
			Kbin model = models.get(0);
			if (model != null) {
				bankNo = model.getBankCode();
			}
		}
		return bankNo;
	}
	


    @Override
    @Transactional(propagation = org.springframework.transaction.annotation.Propagation.SUPPORTS, readOnly = true)
    public Kbin getByCardNo(String cardNo) {
        StringBuffer sql = new StringBuffer("from Kbin a where a.cardLength = :len and instr(:cardNo, a.startNum) = 1");

        TypedQuery<Kbin> query = this.getEntityManager().createQuery(sql.toString(), Kbin.class);
        query.setMaxResults(1);
        query.setParameter("len", cardNo.length());
        query.setParameter("cardNo", cardNo);
        List<Kbin> models = query.getResultList();

        if (models != null && models.size() > 0) {
            Kbin model = models.get(0);
            return model;
        }
        return null;
    }
}
