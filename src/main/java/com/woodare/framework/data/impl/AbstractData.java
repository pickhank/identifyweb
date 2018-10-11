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
package com.woodare.framework.data.impl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.woodare.framework.data.IData;
import com.woodare.framework.utils.JsonUtils;

/**
 * ClassName: AbstractData
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 */
public abstract class AbstractData implements IData, Serializable {
    private static final long serialVersionUID = -5370753736394832569L;

    @JsonIgnore
    private Log log = LogFactory.getLog(AbstractData.class);

    /**
     * 
     */
    public String toString() {
        if (log.isDebugEnabled()) {
            return JsonUtils.toJson(this);
        }
        return super.toString();
    }
}
