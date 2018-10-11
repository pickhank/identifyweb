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
package com.woodare.framework.component;

import java.util.List;

import com.woodare.framework.data.impl.AbstractData;

/**
 * @author lu_feng
 *
 */
public interface CacheLoadListener extends ICache {

    /**
     * 
     * @param cacheMaps
     */
    List<? extends AbstractData> execute();
    

    /**
     * 
     * @param pk
     * @return
     */
    AbstractData executeItem(Object pk);
}
