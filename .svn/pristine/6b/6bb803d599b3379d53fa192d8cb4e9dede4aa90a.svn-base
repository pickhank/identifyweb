package com.woodare.template.component.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import com.woodare.core.util.SDFFactory;
import com.woodare.framework.spring.ApplicationContextHolder;

/**
 * 信用卡查询接口
 * 
 * @author Luke
 *
 */
public class SimpleTaskJob implements Runnable {
	private Log log = LogFactory.getLog(SimpleTaskJob.class);
	
	private Object obj;
	
	private String serviceName;

	public SimpleTaskJob(Object obj) {
		this.obj = obj;
	}
	
	public SimpleTaskJob(String serviceName, Object obj) {
		this.serviceName = serviceName;
		this.obj = obj;
	}
	
	@Override
	public void run() {
		String oriThreadName = Thread.currentThread().getName();
		String newThreadName = SDFFactory.getRestLogId();
		Thread.currentThread().setName(newThreadName);
		log.debug(String.format("currentThread.setName[%s]->[%s]", oriThreadName, newThreadName));
		
		try {
			ApplicationContext context = ApplicationContextHolder.getApplicationContext();
			((ITaskJob) context.getBean(serviceName)).executeTask(obj);
		}
		catch(Exception e) {
			log.error("", e);
		}
		finally {
			Thread.currentThread().setName(oriThreadName);
		}
	}

}
