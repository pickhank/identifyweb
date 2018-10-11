package com.thirdparty.passway._util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Luke
 */
public class WooHttpClientPool {
	private final static Logger logger = LoggerFactory.getLogger(WooHttpClientPool.class);
	
	// 系统最大并发连接数
	private static int MAX_CONN = 200;
	// 单个域名最大并发连接数
	private static int MAX_ROUTE = 100;
	// 连接超时等待时间
	private static int CONN_TIMEOUT = 8000;
	// 读取数据返回等待时间
	private static int SO_READ_TIMEOUT = 20000;
	// 获取连接池等待时间
	private static int REQUEST_CONN_TIMEOUT = 4000;

	private CloseableHttpClient httpclient;
	private RequestConfig requestConfig;
	private PoolingHttpClientConnectionManager connManager;

	private static Map<String, WooHttpClientPool> connMap = new HashMap<String, WooHttpClientPool>();
	private volatile boolean shutdown;

	private static Object obj = new Object();

	public static WooHttpClientPool getInstance(String key) throws Exception {
		if (!connMap.containsKey(key)) {
			synchronized (connMap) {
				if (!connMap.containsKey(key)) {
					synchronized (obj) {
						WooHttpClientPool conn = new WooHttpClientPool();
						conn.init();
						connMap.put(key, conn);
					}
				}
			}
		}
		return connMap.get(key);
	}

	private WooHttpClientPool() {
	}

	/**
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public void init() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sslCtx = SSLContext.getInstance("TLS");
		X509TrustManager trustManager = new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			}
		};
		sslCtx.init(null, new TrustManager[] { trustManager }, null);
		LayeredConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslCtx, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory> create();
		ConnectionSocketFactory plainSocketFactory = new PlainConnectionSocketFactory();
		registryBuilder.register("http", plainSocketFactory);
		registryBuilder.register("https", sslSocketFactory);

		Registry<ConnectionSocketFactory> registry = registryBuilder.build();
		connManager = new PoolingHttpClientConnectionManager(registry);
		connManager.setMaxTotal(MAX_CONN);
		connManager.setDefaultMaxPerRoute(MAX_ROUTE);

		ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
			@Override
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase("timeout")) {
						try {
							return Long.parseLong(value) * 1000;
						} catch (NumberFormatException ignore) {
						}
					}
				}
				return 20 * 1000;
			}
		};

		httpclient = HttpClientBuilder.create().setConnectionManager(connManager).setKeepAliveStrategy(myStrategy).build();

		requestConfig = RequestConfig.custom().setConnectTimeout(CONN_TIMEOUT).setSocketTimeout(SO_READ_TIMEOUT).setConnectionRequestTimeout(REQUEST_CONN_TIMEOUT).build();
		shutdown = false;

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (!shutdown) {
					try {
						synchronized (this) {
							wait(500);
							connManager.closeExpiredConnections();
							connManager.closeIdleConnections(40, TimeUnit.SECONDS);
						}
					} catch (Exception e) {
						logger.error(e.getLocalizedMessage(), e);
					}
				}
			}
		}).start();
	}

	public byte[] doGet(String url, List<NameValuePair> nameValuePairs) throws Exception {
		CloseableHttpResponse response = null;
		HttpGet httpget = new HttpGet();
		try {
			URIBuilder builder = new URIBuilder(url);
			if (nameValuePairs != null && !nameValuePairs.isEmpty()) {
				builder.setParameters(nameValuePairs);
			}
			httpget.setURI(builder.build());
			httpget.setConfig(requestConfig);
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toByteArray(entity);
			}
		} catch (Exception e) {
			logger.error("", e);
			throw e;
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("", e);
					throw e;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String doPostJSONObject(String url, JSONObject obj) throws Exception {
		return this.doPost(url, obj.toJSONString(), "application/json", Consts.UTF_8);
	}
	
	/**
	 * 
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public String doPostJsonData(String url, String body) throws Exception {
		return this.doPost(url, body, "application/json", Consts.UTF_8);
	}

	/**
	 * 
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public byte[] doPostForm(String url, List<NameValuePair> queryParams, List<NameValuePair> formParams, Charset charset) throws Exception {
		if (CommonUtil.isnull(url)) {
			throw new Exception("未设置请求地址");
		}
		CloseableHttpResponse response = null;
		HttpPost httppost = new HttpPost();
		try {
			URIBuilder builder = new URIBuilder(url);
			if (queryParams != null && !queryParams.isEmpty()) {
				builder.setParameters(queryParams);
			}
			httppost.setURI(builder.build());
			httppost.setConfig(requestConfig);

			if (formParams != null && !formParams.isEmpty()) {
				httppost.setEntity(new UrlEncodedFormEntity(formParams, charset));
			}
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			int stateCode = response.getStatusLine().getStatusCode();
			if (200 != stateCode) {
				throw new Exception("StatusCode " + stateCode);
			}
			if (entity != null) {
				return EntityUtils.toByteArray(entity);
			}
		}
		finally {
			httppost.releaseConnection();
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("", e);
					throw e;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public byte[] doPostByEntity(String url, HttpEntity reqEntity) throws Exception {
		if (CommonUtil.isnull(url)) {
			throw new Exception("未设置请求地址");
		}
		CloseableHttpResponse response = null;
		HttpPost httppost = new HttpPost();
		try {
			URIBuilder builder = new URIBuilder(url);

			httppost.setURI(builder.build());
			httppost.setConfig(requestConfig);
			if (reqEntity != null) {
				httppost.setEntity(reqEntity);
			}
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			int stateCode = response.getStatusLine().getStatusCode();
			if (200 != stateCode) {
				throw new Exception("StatusCode " + stateCode);
			}
			if (entity != null) {
				return EntityUtils.toByteArray(entity);
			}
		}
		finally {
			httppost.releaseConnection();
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("", e);
					throw e;
				}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param url
	 * @param body
	 * @param contentType
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public String doPost(String url, String body, String contentType, Charset charset) throws Exception {
		if (CommonUtil.isnull(url)) {
			throw new Exception("未设置请求地址");
		}
		if (CommonUtil.isnull(contentType)) {
			throw new Exception("未设置Content-Type");
		}
		CloseableHttpResponse response = null;
		HttpPost httppost = new HttpPost();
		try {
			URIBuilder builder = new URIBuilder(url);
			httppost.setURI(builder.build());
			httppost.setConfig(requestConfig);
			httppost.setHeader("Content-Type", contentType);

			if (!CommonUtil.isnull(body)) {
				HttpEntity entity2 = new StringEntity(body, charset);
				httppost.setEntity(entity2);
			}
			
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			int stateCode = response.getStatusLine().getStatusCode();
			if (200 != stateCode) {
				throw new Exception("StatusCode " + stateCode);
			}
			if (entity != null) {
				return EntityUtils.toString(entity, charset);
			}
		} 
		finally {
			httppost.releaseConnection();
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					logger.error("", e);
					throw e;
				}
			}
		}
		return null;
	
	}

	/**
	 * @throws IOException
	 */
	public void shutdown() throws IOException {
		shutdown = true;
		connManager.shutdown();
		httpclient.close();
		synchronized (this) {
			notifyAll();
		}
	}
}