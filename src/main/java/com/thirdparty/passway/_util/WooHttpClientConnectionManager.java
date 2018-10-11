package com.thirdparty.passway._util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.woodare.framework.utils.CommonUtils;

/**
 * @author Luke
 */
public class WooHttpClientConnectionManager {
	private final static Logger logger = LoggerFactory.getLogger(WooHttpClientConnectionManager.class);

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

	private static Map<String, WooHttpClientConnectionManager> connMap = new HashMap<String, WooHttpClientConnectionManager>();

	private volatile boolean shutdown;
	private WooPoolConfig config = null;

	private static Object obj = new Object();

	public static class WooPoolConfig {
		private String uuid = CommonUtils.uuid();
		private int maxTotal;
		private int maxRoute;
		private int connectTimeout;
		private int readTimeout;
		private int requestTimeout;

		public WooPoolConfig() {
			this(MAX_CONN, MAX_ROUTE);
		}

		public WooPoolConfig(int maxTotal, int maxRoute) {
			this.maxTotal = maxTotal;
			this.maxRoute = maxRoute;

			connectTimeout = CONN_TIMEOUT;
			readTimeout = SO_READ_TIMEOUT;
			requestTimeout = REQUEST_CONN_TIMEOUT;
		}

		/**
		 * @param maxTotal
		 * @param maxRoute
		 * @param connectTimeout
		 *            the seconds
		 * @param readTimeout
		 *            the seconds
		 * @param requestTimeout
		 *            the seconds
		 */
		public WooPoolConfig(int maxTotal, int maxRoute, int connectTimeout, int readTimeout, int requestTimeout) {
			this.maxTotal = maxTotal;
			this.maxRoute = maxRoute;
			this.connectTimeout = connectTimeout;
			this.readTimeout = readTimeout;
			this.requestTimeout = requestTimeout;
		}

		/**
		 * @return the uuid
		 */
		public String getUuid() {
			return uuid;
		}

		/**
		 * @param uuid
		 *            the uuid to set
		 */
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		/**
		 * @return the maxTotal
		 */
		public int getMaxTotal() {
			return maxTotal;
		}

		/**
		 * @param maxTotal
		 *            the maxTotal to set
		 */
		public void setMaxTotal(int maxTotal) {
			this.maxTotal = maxTotal;
		}

		/**
		 * @return the maxRoute
		 */
		public int getMaxRoute() {
			return maxRoute;
		}

		/**
		 * @param maxRoute
		 *            the maxRoute to set
		 */
		public void setMaxRoute(int maxRoute) {
			this.maxRoute = maxRoute;
		}

		/**
		 * @return the connectTimeout
		 */
		public int getConnectTimeout() {
			return connectTimeout;
		}

		/**
		 * @param connectTimeout
		 *            the connectTimeout to set
		 */
		public void setConnectTimeout(int connectTimeout) {
			this.connectTimeout = connectTimeout;
		}

		/**
		 * @return the readTimeout
		 */
		public int getReadTimeout() {
			return readTimeout;
		}

		/**
		 * @param readTimeout
		 *            the readTimeout to set
		 */
		public void setReadTimeout(int readTimeout) {
			this.readTimeout = readTimeout;
		}

		/**
		 * @return the requestTimeout
		 */
		public int getRequestTimeout() {
			return requestTimeout;
		}

		/**
		 * @param requestTimeout
		 *            the requestTimeout to set
		 */
		public void setRequestTimeout(int requestTimeout) {
			this.requestTimeout = requestTimeout;
		}
	}

	public static WooHttpClientConnectionManager getInstance() {
		return getInstance("_DEFAULT_");
	}

	public static WooHttpClientConnectionManager getInstance(String key) {
		return getInstance(key, new WooPoolConfig());
	}

	public static WooHttpClientConnectionManager getInstance(WooPoolConfig config) {
		return getInstance(config.getUuid(), config);
	}

	public static WooHttpClientConnectionManager getInstance(String key, WooPoolConfig configure) {
		if (!connMap.containsKey(key)) {
			synchronized (connMap) {
				if (!connMap.containsKey(key)) {
					synchronized (obj) {
						try {
							WooHttpClientConnectionManager conn = new WooHttpClientConnectionManager(configure);
							conn.init();
							connMap.put(key, conn);
						} catch (Exception e) {
							throw new RuntimeException(e);
						}
					}
				}
			}
		}
		return connMap.get(key);
	}

	private WooHttpClientConnectionManager(WooPoolConfig configure) {
		this.config = configure;
		requestConfig = RequestConfig.custom().setConnectTimeout(configure.connectTimeout).setSocketTimeout(configure.readTimeout).setConnectionRequestTimeout(configure.requestTimeout).build();
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
		connManager.setMaxTotal(config.maxTotal);
		connManager.setDefaultMaxPerRoute(config.maxRoute);

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

	public String doGetString(String url) throws IOException {
		return doGetString(url, null, Consts.UTF_8);
	}

	public String doGetString(String url, List<NameValuePair> queryParams, Charset charset) throws IOException {
		return new String(this.doGet(url, queryParams), charset);
	}

	/**
	 * @param url
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String doPostJson(String url, Object obj) throws Exception {
		if (obj == null) {
			return this.doPostString(url, null, ContentType.APPLICATION_JSON);
		}
		if (obj instanceof String) {
			return this.doPostString(url, (String) obj, ContentType.APPLICATION_JSON);
		}
		else {
			return this.doPostString(url, JSONObject.toJSONString(obj), ContentType.APPLICATION_JSON);
		}
	}

	/**
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public String doPostForm(String url, List<NameValuePair> formParams) throws Exception {
		return doPostForm(url, null, formParams, Consts.UTF_8);
	}

	/**
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public String doPostForm(String url, List<NameValuePair> queryParams, List<NameValuePair> formParams, Charset charset) throws Exception {
		Asserts.check(url != null && !"".equals(url), "url required");
		Asserts.check(charset != null, "charset required");

		try {
			URIBuilder builder = new URIBuilder(url);
			if (queryParams != null && !queryParams.isEmpty()) {
				builder.setParameters(queryParams);
			}
			URI uri = builder.build();

			HttpEntity entity = null;
			if (formParams != null && !formParams.isEmpty()) {
				entity = new UrlEncodedFormEntity(formParams, charset);
			}

			byte[] respBytes = doPostByEntity(uri, entity);

			return new String(respBytes, charset);
		} catch (URISyntaxException e) {
			Asserts.check(true, "URL is invalid");
		}
		throw new IOException("It's impossible to reach");
	}

	/**
	 * @param url
	 * @param body
	 * @param contentType
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public String doPostString(String url, String bodyData, ContentType contentType) throws IOException {
		Asserts.check(url != null && !"".equals(url), "url required");
		Asserts.check(contentType != null, "contentType required");
		try {
			HttpEntity entity = null;
			if (bodyData != null && !"".equals(bodyData)) {
				entity = new StringEntity(bodyData, contentType);
			}

			URI uri = new URIBuilder(url).build();
			byte[] respBytes = doPostByEntity(uri, entity);

			return new String(respBytes, contentType.getCharset());
		} catch (URISyntaxException e) {
			Asserts.check(true, "URL is invalid");
		}
		throw new IOException("It's impossible to reach");
	}

	/**
	 * @param url
	 * @param bodyData
	 * @param mimeType
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public String doPostString(String url, String bodyData, String mimeType, Charset charset) throws IOException {
		Asserts.check(url != null && !"".equals(url), "url required");
		Asserts.check(mimeType != null && !"".equals(mimeType), "mimeType required");
		Asserts.check(charset != null, "charset required");

		try {
			HttpEntity entity = null;
			if (bodyData != null && !"".equals(bodyData)) {
				ContentType type = ContentType.create(mimeType, charset);
				entity = new StringEntity(bodyData, type);
			}

			URI uri = new URIBuilder(url).build();
			byte[] respBytes = doPostByEntity(uri, entity);

			return new String(respBytes, charset);
		} catch (URISyntaxException e) {
			Asserts.check(true, "URL is invalid");
		}
		throw new IOException("It's impossible to reach");
	}

	/**
	 * @param url
	 * @param bodyData
	 * @param mimeType
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public String doPutString(String url, String bodyData, String mimeType, Charset charset) throws IOException {
		Asserts.check(url != null && !"".equals(url), "url required");
		Asserts.check(mimeType != null && !"".equals(mimeType), "mimeType required");
		Asserts.check(charset != null, "charset required");

		try {
			HttpEntity entity = null;
			if (bodyData != null && !"".equals(bodyData)) {
				ContentType type = ContentType.create(mimeType, charset);
				entity = new StringEntity(bodyData, type);
			}

			URI uri = new URIBuilder(url).build();
			byte[] respBytes = doPutByEntity(uri, entity);

			return new String(respBytes, charset);
		} catch (URISyntaxException e) {
			Asserts.check(true, "URL is invalid");
		}
		throw new IOException("It's impossible to reach");
	}

	public byte[] doGet(String url, List<NameValuePair> queryParams) throws IOException {
		Asserts.notEmpty(url, "URL");

		CloseableHttpResponse response = null;
		HttpGet method = null;
		try {
			method = new HttpGet();

			URIBuilder builder = new URIBuilder(url);
			if (queryParams != null && !queryParams.isEmpty()) {
				builder.setParameters(queryParams);
			}
			method.setURI(builder.build());
			method.setConfig(requestConfig);
			response = httpclient.execute(method);
			HttpEntity entity = response.getEntity();

			int stateCode = response.getStatusLine().getStatusCode();
			if (200 != stateCode) {
				throw new IOException("StatusCode " + stateCode);
			}
			if (entity != null) {
				return EntityUtils.toByteArray(entity);
			}
			throw new IOException("Result is null");
		} catch (URISyntaxException e) {
			Asserts.check(true, "URL is invalid");
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		throw new IOException("It's impossible to reach");
	}

	/**
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public byte[] doPutByEntity(URI uri, HttpEntity reqEntity) throws IOException {
		Asserts.check(uri != null, "uri required");
		Asserts.check(reqEntity != null, "reqEntity required");

		HttpPut method = null;
		CloseableHttpResponse response = null;
		try {
			method = new HttpPut();
			method.setURI(uri);
			method.setConfig(requestConfig);

			if (reqEntity != null) {
				method.setEntity(reqEntity);
			}

			response = httpclient.execute(method);
			HttpEntity entity = response.getEntity();
			int stateCode = response.getStatusLine().getStatusCode();
			if (200 != stateCode) {
				throw new IOException("StatusCode " + stateCode);
			}
			if (entity != null) {
				return EntityUtils.toByteArray(entity);
			}
			throw new IOException("Result is null");
		} finally {
			method.releaseConnection();
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public CloseableHttpClient getHttpClient() {
		return httpclient;
	}
	
	/**
	 * 
	 * @return
	 */
	public HttpPost getHttpPost() {
		HttpPost method = new HttpPost();
		method.setConfig(requestConfig);
		return method;
	}

	/**
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public byte[] doPostByEntity(URI uri, HttpEntity reqEntity) throws IOException {
		Asserts.check(uri != null, "uri required");
		Asserts.check(reqEntity != null, "reqEntity required");

		HttpPost method = null;
		CloseableHttpResponse response = null;
		try {
			method = new HttpPost();
			method.setURI(uri);
			method.setConfig(requestConfig);

			if (reqEntity != null) {
				method.setEntity(reqEntity);
			}

			response = httpclient.execute(method);
			HttpEntity entity = response.getEntity();
			int stateCode = response.getStatusLine().getStatusCode();
			if (200 != stateCode) {
				throw new IOException("StatusCode " + stateCode);
			}
			if (entity != null) {
				return EntityUtils.toByteArray(entity);
			}
			throw new IOException("Result is null");
		} finally {
			method.releaseConnection();
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
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