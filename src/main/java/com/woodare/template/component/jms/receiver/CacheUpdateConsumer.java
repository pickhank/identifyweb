package com.woodare.template.component.jms.receiver;

import java.util.Timer;
import java.util.TimerTask;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.woodare.framework.component.SystemCache;

/**
 * 更新缓存
 * 
 * @author Luke
 */
@Component("cacheUpdateConsumer")
public class CacheUpdateConsumer implements MessageListener {
	private final Logger log = Logger.getLogger(CacheUpdateConsumer.class);

	@Override
	public void onMessage(Message message) {
		try {
			final String text = ((TextMessage) message).getText();
			log.info("接收到消息:" + text);
			if (!"heartbeat".equals(text)) {
				Timer t = new Timer();
				t.schedule(new TimerTask() {
					@Override
					public void run() {
						try {
							if(text.indexOf(",") != -1) {
								String className = text.split(",")[0];
								String pk = text.split(",")[1];
								SystemCache.fireLoadEventByPk(Class.forName(className), pk);
							}
							else {
								SystemCache.fireLoadEvent(Class.forName(text));	
							}
							
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}, 5000);
			}
		} catch (JMSException e) {
			log.error(e, e);
		}
	}
}
