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
package com.woodare.template.component.schedule;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.woodare.framework.utils.SysProperties;

/**
 * ClassName: MqHeartbeatSchedule
 * 
 * @description
 * @author Xinxing Jiang
 * @Date Aug 12, 2015
 */
@Service
public class MqHeartbeatSchedule {
	private static Logger log = Logger.getLogger(MqHeartbeatSchedule.class);

	@Autowired
	@Qualifier("jmsCacheUpdateTemplate")
	private JmsTemplate jmsCacheUpdateTemplate;

	@Scheduled(cron = "0 0/10 * * * ?") // 凌晨三点
	public void notifyRecord() {
		boolean runnning = SysProperties.getInstance().getBooleanProperty("run.timer", false);
		if (runnning) {
			boolean need = SysProperties.getInstance().getBooleanProperty("jms.open", false);
			if (need) {
				log.info("Send HeartBeat");
				jmsCacheUpdateTemplate.convertAndSend("heartbeat");
			}
		}
	}

}
