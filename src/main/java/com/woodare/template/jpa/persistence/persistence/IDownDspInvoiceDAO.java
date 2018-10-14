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

import java.util.Date;
import java.util.List;

import com.woodare.framework.data.IPagedList;
import com.woodare.framework.persistence.service.ISimpleDAO;
import com.woodare.template.jpa.model.DownDspInvoice;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceData;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceSumData;
import com.woodare.template.jpa.persistence.data.downdspinvoice.DownDspInvoiceUpSumData;
import com.woodare.template.jpa.persistence.data.downdspinvoice.SearchDownDspInvoiceData;

/**
 * ClassName: IDownDspInvoiceDAO
 *
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/09/13
 */
public interface IDownDspInvoiceDAO extends ISimpleDAO<DownDspInvoice> {

	IPagedList<DownDspInvoice> searchItems(SearchDownDspInvoiceData searchData);

	DownDspInvoice findById(String id);

	DownDspInvoice findByTradeNoAndMchNo(String tradeNo, String mchNo);

	DownDspInvoice findByTransNo(String transNo);

	List<DownDspInvoice> findNeedCheckByStatusChgDate(Date start, Date end);

	int updateSelectiveByCons(DownDspInvoiceData values, DownDspInvoiceData cons);

	List<DownDspInvoiceSumData> sumInvoice(SearchDownDspInvoiceData searchData);

	List<DownDspInvoiceUpSumData> upSumInvoice(SearchDownDspInvoiceData searchData);

//	int sumSummary(DownDspInvoiceSumData downDspInvoiceSumData);
//
//	List<DownDspInvoiceSumData> listInovice();
}
