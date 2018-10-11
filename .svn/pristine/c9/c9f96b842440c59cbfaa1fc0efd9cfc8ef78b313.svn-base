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
package com.woodare.framework.persistence.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.woodare.framework.persistence.service.ISequenceDAO;
import com.woodare.framework.utils.CommonUtils;

/**
 * ClassName: SequenceDAO
 * 
 * @description
 * @author Xinxing Jiang
 * @Date 2017年10月20日
 */
@Service
public class SequenceDAO implements ISequenceDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Long getSeq(String name) {
		Query query = this.entityManager.createNativeQuery("select nextval(:name);");
		query.setParameter("name", name);
		Object item = query.getSingleResult();
		return CommonUtils.toLong(item);
	}

}
