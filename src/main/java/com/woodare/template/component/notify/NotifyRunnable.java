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
package com.woodare.template.component.notify;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.woodare.core.util.SDFFactory;
import com.woodare.framework.spring.ApplicationContextHolder;
import com.woodare.template.jpa.model.NotifyRecord;
import com.woodare.template.jpa.persistence.persistence.INotifyRecordDAO;

/**
 * ClassName: NotifyRunnable
 * 
 * @description
 * @author Luke
 * @Date Feb 28, 2018
 */
public class NotifyRunnable implements Runnable {
	private static Logger log = Logger.getLogger(NotifyService.class);
	// private static WooPoolConfig notifyConfig = new WooPoolConfig(10, 10, 3000, 3000, 5000);
	// private static WooHttpClientConnectionManager conn = WooHttpClientConnectionManager.getInstance("DOWNNOTIFY", notifyConfig);

	private NotifyRecord record;

	public NotifyRunnable(NotifyRecord record) {
		this.record = record;
	}

	public void run() {
		String oriThreadName = Thread.currentThread().getName();
		Thread.currentThread().setName(SDFFactory.getNotifyId());

		String responsString = "";
		try {
			log.info("[NOTIRUN]" + record.getTransNo() + "[->]" + record.getTradeNo() + "[]" + record.getNotifyUrl());

			// 判断为新版推送任务时
			INotifyCommand command = (INotifyCommand) ApplicationContextHolder.getApplicationContext().getBean(record.getNotifyType().serviceName);

			String requestString = command.getDownNotifyData(record);
			log.info("[NOTIRUN_REQE]" + record.getTransNo() + " >>>>>> " + requestString);
			responsString = post(record.getNotifyUrl(), requestString);
			log.info("[NOTIRUN_RESP]" + record.getTransNo() + " <<<<<< " + responsString);
		} 
		catch (Exception e) {
			responsString = e.getMessage();
			log.error("[NOTIRUN_ERR]" + record.getNotifyUrl() + " <<<<<< " + e.getMessage(), e);
		} 
		finally {
			Thread.currentThread().setName(oriThreadName);
		}

		if (responsString != null) {
			responsString = responsString.trim();
		}
		
		if ("success".equalsIgnoreCase(responsString)) {
			INotifyRecordDAO notifyRecordDAO = ApplicationContextHolder.getBean("notifyRecordDAO", INotifyRecordDAO.class);
			record.setNotifySuccess(true);
			notifyRecordDAO.updateForce(record);
		}
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	private static String post(String url, String body) throws Exception {
		CloseableHttpClient httpclient = null;

		if (url.startsWith("https")) {
			httpclient = createSSLInsecureClient();
		}
		else {
			httpclient = HttpClients.custom().build();
		}

		HttpPost post = null;
		String resData = null;
		CloseableHttpResponse result = null;
		try {
			post = new HttpPost(url);
			HttpEntity entity2 = new StringEntity(body, Consts.UTF_8);
			post.setConfig(RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(5000).build());
			post.setHeader("Content-Type", "application/json");
			post.setEntity(entity2);
			result = httpclient.execute(post);
			if (HttpStatus.SC_OK == result.getStatusLine().getStatusCode()) {
				resData = EntityUtils.toString(result.getEntity());
			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (post != null) {
				post.releaseConnection();
			}
			httpclient.close();
		}
		return resData;
	}

	/**
	 * 创建一个SSL信任所有证书的httpClient对象
	 * 
	 * @return
	 */
	public static CloseableHttpClient createSSLInsecureClient() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 默认信任所有证书
				public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					return true;
				}
			}).build();
			// AllowAllHostnameVerifier: 这种方式不对主机名进行验证，验证功能被关闭，是个空操作(域名验证)
			SSLConnectionSocketFactory sslcsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			return HttpClients.custom().setSSLSocketFactory(sslcsf).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}
}