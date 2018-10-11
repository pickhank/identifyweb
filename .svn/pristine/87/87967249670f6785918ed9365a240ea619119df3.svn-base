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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.woodare.framework.utils.SysProperties;
import com.woodare.template.component.notify.NotifyRunnable;
import com.woodare.template.component.notify.NotifyService;
import com.woodare.template.jpa.model.NotifyRecord;
import com.woodare.template.jpa.persistence.persistence.INotifyRecordDAO;

/**
 * ClassName: NotifySchedule
 * 
 * @description
 * @author Luke
 * @Date Feb 26, 2018
 */
@Service
public class NotifySchedule {
	private static Logger log = Logger.getLogger(NotifySchedule.class);

	@Autowired
	private NotifyService notifyService;

	@Autowired
	private INotifyRecordDAO notifyRecordDAO;

	@Scheduled(fixedDelay = 2000, initialDelay = 30000)
	public void notifyRecord() {
		boolean runnning = SysProperties.getInstance().getBooleanProperty("run.timer", false);
		if (runnning) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);

			long start = System.currentTimeMillis();
			List<NotifyRecord> models = notifyRecordDAO.findNeedNotify(c.getTime());

			if (models != null) {
				for (NotifyRecord item : models) {
					if (item.getNotifyLastDate() == null || new Date().getTime() - item.getNotifyLastDate().getTime() >= getDiff(item.getNotifyTimes())) {
						NotifyRunnable runnanle = new NotifyRunnable(item);
						notifyService.exec(runnanle);
						
						if (item.getNotifyTimes() == null) {
							item.setNotifyTimes(1);
						}
						else {
							item.setNotifyTimes(item.getNotifyTimes() + 1);
						}
						item.setNotifyLastDate(new Date());
						notifyRecordDAO.updateForce(item);
					}
				}
			}

			long end = System.currentTimeMillis();
			if (end - start > 500) {
				log.warn("[" + (end - start) + "]" + start);
			}
		}
	}

	private static long getDiff(Integer times) {
		long diff = 0L;
		if (times == null) {
			return 0L;
		}
		switch (times) {
		case 1:
			diff = 4L;
			break;
		case 2:
			diff = 8L;
			break;
		case 3:
			diff = 16L;
			break;
		case 4:
			diff = 32L;
			break;
		case 5:
			diff = 120L;
			break;
		case 6:
			diff = 600L;
			break;
		case 7:
			diff = 1800L;
			break;
		case 8:
			diff = 1800L;
			break;
		default:
			break;
		}
		return diff * 1000;
	}
}
