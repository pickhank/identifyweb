package com.woodare.template.busi.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.woodare.core.util.SDFFactory;
import com.woodare.template.component.job.ITaskJob;
import com.woodare.template.component.job.SimpleTaskJob;

/**
 * @author Luke
 */
@Service("passwayAssitService")
public class PasswayAssitService implements ITaskJob {
	private Log log = LogFactory.getLog(PasswayAssitService.class);
	public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(50);

	/**
	 * @param obj
	 */
	public static void asyncExecute(Object obj) {
		fixedThreadPool.execute(new SimpleTaskJob("passwayAssitService", obj));
	}

	@Override
	public void executeTask(Object obj) {
		String oriThreadName = Thread.currentThread().getName();
		Thread.currentThread().setName(SDFFactory.getNotifyId());
		try {
			// if (obj instanceof DownNoCardInvoiceExtra) {
			// DownNoCardInvoiceExtra invoiceExtra = (DownNoCardInvoiceExtra) obj;
			// handleDownNoCardInvoiceExtra(invoiceExtra);
			// }
		} catch (Exception e) {
			log.error(e, e);
		} finally {
			Thread.currentThread().setName(oriThreadName);
		}
	}
}
