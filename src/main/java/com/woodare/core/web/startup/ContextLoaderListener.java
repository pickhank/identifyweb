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
package com.woodare.core.web.startup;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.woodare.core.component.cache.DictionaryListener;
import com.woodare.core.component.cache.SysPropertiesListener;
import com.woodare.core.component.cache.SystemMenuListener;
import com.woodare.core.component.cache.SystemRoleRightListener;
import com.woodare.core.jpa.data.dicdata.DicDataData;
import com.woodare.core.jpa.data.systemmenu.SystemMenuData;
import com.woodare.core.jpa.data.systemproperties.SystemPropertiesData;
import com.woodare.core.jpa.data.systemroleright.SystemRoleRightData;
import com.woodare.framework.component.SystemCache;
import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.template.component.cache.DownAgentListener;
import com.woodare.template.component.cache.DownDspInvoiceRouteListener;
import com.woodare.template.component.cache.DownMerchantListener;
import com.woodare.template.component.cache.DownMerchantProductListener;
import com.woodare.template.component.cache.KbinListener;
import com.woodare.template.component.cache.PasswayDspMerchantListener;
import com.woodare.template.jpa.persistence.data.downagent.DownAgentData;
import com.woodare.template.jpa.persistence.data.downdspinvoiceroute.DownDspInvoiceRouteData;
import com.woodare.template.jpa.persistence.data.downmerchant.DownMerchantData;
import com.woodare.template.jpa.persistence.data.downmerchantproduct.DownMerchantProductData;
import com.woodare.template.jpa.persistence.data.kbin.KbinData;
import com.woodare.template.jpa.persistence.data.passwaydspmerchant.PasswayDspMerchantData;

/**
 * Please describe
 *
 * @version 1.0
 * @createDate 2013-6-11 下午5:41:48
 * @author lu_feng
 */
public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {
	private final Logger log = Logger.getLogger(ContextLoaderListener.class);

	/**
	 * (non-Javadoc) Description:
	 *
	 * @param
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		log.debug("Destroy the inistialized Data");

		ApplicationContext application = ApplicationContextHolder.getApplicationContext();

		if (application != null) {
			SystemCache.clean();
		}

	}

	/**
	 * (non-Javadoc) Description:
	 *
	 * @param
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		log.info("Begin to load the data from static database");

		InitializeDatabase.execute();

		try {
			// Bind event firstly
			SystemCache.registListener(DicDataData.class, new DictionaryListener());
			SystemCache.registListener(SystemPropertiesData.class, new SysPropertiesListener());
			SystemCache.registListener(SystemMenuData.class, new SystemMenuListener());
			SystemCache.registListener(SystemRoleRightData.class, new SystemRoleRightListener());

			SystemCache.registListener(DownAgentData.class, new DownAgentListener());
			SystemCache.registListener(DownMerchantData.class, new DownMerchantListener());
			SystemCache.registListener(KbinData.class, new KbinListener());

			SystemCache.registListener(DownMerchantProductData.class, new DownMerchantProductListener());

			SystemCache.registListener(PasswayDspMerchantData.class, new PasswayDspMerchantListener());
			SystemCache.registListener(DownDspInvoiceRouteData.class, new DownDspInvoiceRouteListener());

			SystemCache.fireLoadEvent();
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}

	}
}
