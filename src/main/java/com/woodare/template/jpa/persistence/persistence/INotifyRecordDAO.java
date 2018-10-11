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
import com.woodare.template.jpa.model.NotifyRecord;
import com.woodare.template.jpa.persistence.data.notifyrecord.SearchNotifyRecordData;

/**
 * ClassName: INotifyRecordDAO
 *
 * @description
 * @author woo_maven_auto_generate
 * @Date 2017/09/11
 */
public interface INotifyRecordDAO extends ISimpleDAO<NotifyRecord> {

	IPagedList<NotifyRecord> searchItems(SearchNotifyRecordData searchData);

	NotifyRecord findById(String id);

	List<NotifyRecord> findNeedNotify(Date today);
}
