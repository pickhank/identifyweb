package com.thirdparty.passway._util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
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

public class HttpClient4Util {
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

	private final static Logger logger = LoggerFactory.getLogger(HttpClient4Util.class);
	private CloseableHttpClient httpclient;
	private RequestConfig requestConfig;
	private PoolingHttpClientConnectionManager connManager;
	private static HttpClient4Util httpClient4UtilNew;
	private volatile boolean shutdown;

	public synchronized static HttpClient4Util getInstance() throws Exception {
		if (httpClient4UtilNew == null) {
			httpClient4UtilNew = new HttpClient4Util();
			httpClient4UtilNew.init();
		}
		return httpClient4UtilNew;
	}

	private HttpClient4Util() {

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
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @return
	 */
	public byte[] doPost(String url, List<NameValuePair> queryParams, List<NameValuePair> formParams) throws Exception {
		if (CommonUtil.isnull(url)) {
			return null;
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
				httppost.setEntity(new UrlEncodedFormEntity(formParams, "utf-8"));
			}
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			int stateCode = response.getStatusLine().getStatusCode();
			if (200 != stateCode) {
				return null;
			}
			if (entity != null) {
				return EntityUtils.toByteArray(entity);
			}
		} catch (Exception e) {
			logger.error("", e);
			throw e;
		} finally {
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

	public String doPostJson(String url, String body) throws Exception {
		if (CommonUtil.isnull(url)) {
			return null;
		}
		CloseableHttpResponse response = null;
		HttpPost httppost = new HttpPost();
		try {
			URIBuilder builder = new URIBuilder(url);
			httppost.setURI(builder.build());
			httppost.setConfig(requestConfig);
			httppost.setHeader("Content-Type", "application/json");

			if (!CommonUtil.isnull(body)) {
				HttpEntity entity2 = new StringEntity(body, Consts.UTF_8);
				httppost.setEntity(entity2);
			}
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			int stateCode = response.getStatusLine().getStatusCode();
			if (200 != stateCode) {
				return null;
			}
			if (entity != null) {
				return EntityUtils.toString(entity, Consts.UTF_8);
			}
		} catch (Exception e) {
			logger.error("", e);
			throw e;
		} finally {
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

	public void shutdown() throws IOException {
		shutdown = true;
		connManager.shutdown();
		httpclient.close();
		synchronized (this) {
			notifyAll();
		}
	}
}