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

import com.woodare.framework.persistence.service.ISimpleDAO;
import com.woodare.framework.data.IPagedList;
import com.woodare.template.jpa.model.DownDspProduct;
import com.woodare.template.jpa.persistence.data.downdspproduct.DownDspProductData;
import com.woodare.template.jpa.persistence.data.downdspproduct.SearchDownDspProductData;

/**
 * ClassName: IDownDspProductDAO
 *
 * @description
 * @author woo_maven_auto_generate
 * @Date 2018/09/15
 */
public interface IDownDspProductDAO extends ISimpleDAO<DownDspProduct> {

	IPagedList<DownDspProduct> searchItems(SearchDownDspProductData searchData);

	DownDspProduct findById(String id);

	int updateSelectiveById(DownDspProductData product);
}
