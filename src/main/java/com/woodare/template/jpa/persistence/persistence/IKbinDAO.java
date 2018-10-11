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
package com.woodare.template.jpa.persistence.persistence;

import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.ISimpleDAO;
import com.woodare.template.jpa.model.Kbin;
import com.woodare.template.jpa.persistence.data.kbin.SearchKbinData;

/**
 * ClassName: IKbinDAO
 *
 * @description
 * @author to_be_replaced_by_owner
 * @Date 2017/04/25
 */
public interface IKbinDAO extends ISimpleDAO<Kbin> {

	/**
	 * @param searchData
	 * @return
	 */
	IPagedList<Kbin> searchKbins(SearchKbinData searchData);

    String getBankCode(String cardNo);
    
    Kbin getByCardNo(String cardNo);
}
