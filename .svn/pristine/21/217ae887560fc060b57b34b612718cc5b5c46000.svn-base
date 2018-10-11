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
import com.woodare.template.jersery.servicedata.downmerchant.DownMerchantServiceData;
import com.woodare.template.jpa.model.DownMerchantFundAccount;
import com.woodare.template.jpa.persistence.data.downmerchantfundaccount.SearchDownMerchantFundAccountData;

/**
 * 
 * ClassName: IDownMerchantFundAccountDAO
 *
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/03/05
 *
 */
public interface IDownMerchantFundAccountDAO extends ISimpleDAO<DownMerchantFundAccount> {

	IPagedList<DownMerchantFundAccount> searchItems(SearchDownMerchantFundAccountData searchData);

	DownMerchantFundAccount findByMchNo(String mchNo);
	
	/**
	 * 
	 * @param diff
	 * @return
	 */
	int changeBalance(DownMerchantServiceData diff);

	/**
	 * 遍历存在资金变动的数据，备份至历史数据表内
	 * 
	 * @param settleDate
	 * @return
	 */
	int addToHistory(String settleDate);
	
	/**
	 * 隔日结算统计
	 * 
	 * @return
	 */
	int makeSettle(String settleDate, String settleBalanceNo);
	
	/**
	 * 
	 * @param frozenAmt
	 * @param mchNo
	 * @return
	 */
	int frozenBalance(Long frozenAmt, String mchNo);
}

