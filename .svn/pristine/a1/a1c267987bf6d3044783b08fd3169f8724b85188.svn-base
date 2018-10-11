package com.woodare.template.busi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.woodare.framework.component.SystemCache;

@Service
public class CacheDataChangeService {

	@Autowired
	@Qualifier("jmsCacheUpdateTemplate")
	private JmsTemplate jmsCacheUpdateTemplate;

	/**
	 * @param objClass
	 */
	public void notifyOthers(final Class<?> objClass) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5 * 1000);

					SystemCache.fireLoadEvent(objClass);

					Thread.sleep(1 * 1000);

					jmsCacheUpdateTemplate.convertAndSend(objClass.getName());
				} catch (Exception e) {
				}
			}

		}).start();
	}

	/**
	 * @param objClass
	 */
	public void notifyOthers(final Class<?> objClass, final String pk) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5 * 1000);
					SystemCache.fireLoadEventByPk(objClass, pk);

					Thread.sleep(1 * 1000);
					jmsCacheUpdateTemplate.convertAndSend(objClass.getName() + "," + pk);
				} catch (Exception e) {
				}
			}
		}).start();
	}
}
