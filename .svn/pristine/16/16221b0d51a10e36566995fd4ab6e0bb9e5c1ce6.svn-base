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
package com.woodare.framework.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 
 * ClassName: ApplicationContextHolder
 * 
 * @description
 * @author framework
 * @Date 2013-4-1
 * 
 */
@Service("applicationContextProvider")
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext appContext;

    /**
     * 
     */
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        System.out.println("*** ApplicationContextHolder::setApplicationContext()...");
        ApplicationContextHolder.appContext = ctx;
    }

    /**
     * 
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return appContext;
    }
    

    /**
     * 获取对应网关处理类
     * 
     * @param channel
     * @param objClass
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName, Class<T> objClass) {
        T t = (T)getApplicationContext().getBean(beanName);
        return t;
    }

}
