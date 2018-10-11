package com.thirdparty.passway._base;

import java.io.File;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;
import com.thirdparty.passway._data.AbstractRequestData;
import com.thirdparty.passway._util.WooHttpClientConnectionManager;
import com.thirdparty.passway._util.WooHttpClientConnectionManager.WooPoolConfig;

/**
 * 通道处理基类
 * 
 * @author Luke
 */
public abstract class AbstractPasswayApi {

	/**
	 * 预处理请求参数
	 * 
	 * @param reqData
	 */
	protected void preHandler(AbstractRequestData reqData) {
	}

	/**
	 * @return
	 */
	protected abstract String getPasswayName();

	/**
	 * @return
	 */
	protected WooHttpClientConnectionManager getConnection() {
		return WooHttpClientConnectionManager.getInstance(getPasswayName());
	}

	/**
	 * @return
	 */
	protected WooHttpClientConnectionManager getConnection(WooPoolConfig config) {
		return WooHttpClientConnectionManager.getInstance(config);
	}

	/**
	 * @return
	 */
	protected String fetchNonceStr() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String post(String url, String body) throws Exception {
		return post(url, body, "application/json");
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String put(String url, String body) throws Exception {
		return put(url, body, "application/json");
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String get(String url, Map<String, String> mapData) throws Exception {
		List<NameValuePair> queryParams = null;
		if (mapData != null) {
			queryParams = new ArrayList<NameValuePair>();
			for (String k : mapData.keySet()) {
				queryParams.add(new BasicNameValuePair(k, mapData.get(k)));
			}
		}
		return get(url, queryParams, Consts.UTF_8);
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String put(String url, String body, String contentType) throws Exception {
		return getConnection().doPutString(url, body, contentType, Consts.UTF_8);
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String get(String url, List<NameValuePair> queryParams, Charset charset) throws Exception {
		return getConnection().doGetString(url, queryParams, charset);
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String post(String url, String body, String contentType) throws Exception {
		return getConnection().doPostString(url, body, contentType, Consts.UTF_8);
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String postForm(String url, Map<String, ?> formData) throws Exception {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (formData != null) {
			for (String k : formData.keySet()) {
				params.add(new BasicNameValuePair(k, (String) formData.get(k)));
			}
		}
		return getConnection().doPostForm(url, null, params, Consts.UTF_8);
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String postForm(String url, Map<String, ?> formData, String contentType) throws Exception {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (formData != null) {
			for (String k : formData.keySet()) {
				params.add(new BasicNameValuePair(k, (String) formData.get(k)));
			}
		}
		return getConnection().doPostForm(url, null, params, Consts.UTF_8);
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String postForm(String url, Map<String, ?> formData, Charset charset) throws Exception {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (formData != null) {
			for (String k : formData.keySet()) {
				params.add(new BasicNameValuePair(k, (String) formData.get(k)));
			}
		}
		return getConnection().doPostForm(url, null, params, charset);
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String postFormWithConfig(WooPoolConfig config, String url, Map<String, ?> formData) throws Exception {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (formData != null) {
			for (String k : formData.keySet()) {
				params.add(new BasicNameValuePair(k, (String) formData.get(k)));
			}
		}
		return getConnection(config).doPostForm(url, null, params, Consts.UTF_8);
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String postFormWithConfig(WooPoolConfig config, String url, Map<String, ?> formData, Charset charset) throws Exception {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (formData != null) {
			for (String k : formData.keySet()) {
				params.add(new BasicNameValuePair(k, (String) formData.get(k)));
			}
		}
		return getConnection(config).doPostForm(url, null, params, charset);
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String postBinaryForm(String url, Map<String, ?> formData, Charset charset) throws Exception {
		MultipartEntityBuilder bodyBuilder = MultipartEntityBuilder.create();
		bodyBuilder.setCharset(charset);

		ContentType textType = ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), charset);

		if (formData != null) {
			for (String k : formData.keySet()) {
				Object obj = formData.get(k);
				if (obj instanceof File) {
					File file = (File) obj;
					if (file.getName().endsWith("jpg")) {
						bodyBuilder.addBinaryBody(k, (File) obj, ContentType.create("image/jpeg", charset), file.getName());
					}
					else if (file.getName().endsWith("png")) {
						bodyBuilder.addBinaryBody(k, (File) obj, ContentType.create("image/png", charset), file.getName());
					}
					else {
						bodyBuilder.addBinaryBody(k, (File) obj);
					}
				}
				else if (obj instanceof InputStream) {
					bodyBuilder.addBinaryBody(k, (InputStream) obj);
				}
				else if (obj instanceof byte[]) {
					bodyBuilder.addBinaryBody(k, (byte[]) obj);
				}
				else if (obj instanceof String) {
					bodyBuilder.addTextBody(k, (String) obj, textType);
				}
				else if (obj != null) {
					bodyBuilder.addTextBody(k, (String) obj.toString(), textType);
				}
			}
		}

		byte[] respBytes = getConnection().doPostByEntity(new URIBuilder(url).build(), bodyBuilder.build());
		if (respBytes != null) {
			return new String(respBytes, charset);
		}
		return null;
	}

	/**
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	protected String generateHtml(String url, Map<String, ?> formData) throws Exception {
		String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">";
		html += "<html>";
		html += "<head>";
		html += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">";
		html += "<title></title>";
		html += "</head>";
		html += "<body>";
		html += "	<form action=\"%s\" method=\"post\" id=\"AutoForm\" name=\"AutoForm\">%s</form>";
		html += "</body>";
		html += "<script type=\"text/javascript\">";
		html += "	window.onload = function(){";
		html += "		document.AutoForm.submit();";
		html += "	}";
		html += "</script>";
		html += "</html>";
		String formFields = "";
		if (formData != null) {
			for (String k : formData.keySet()) {
				formFields += String.format("<input type=\"hidden\" name=\"%s\"  value='%s'/>", k, formData.get(k));
			}
		}
		return String.format(html, url, formFields);
	}

	public <T> Map<String, T> ignoreEmpty(Map<String, T> sArray) {
		Map<String, T> result = new HashMap<String, T>();
		if (sArray == null || sArray.size() <= 0) {
			return result;
		}

		for (String key : sArray.keySet()) {
			T value = sArray.get(key);
			if (value == null || value.equals("")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}

	public static String createLinkString(Map<String, ?> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Object value = params.get(key);
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			}
			else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}

	public static String createTrimLinkString(Map<String, ?> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Object value = params.get(key);
			if (value == null || "".equals(value)) {

			}
			else {
				prestr += "&" + key + "=" + value;
			}
		}
		return prestr.substring(1);
	}

	public static String createValueString(Map<String, ?> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Object value = params.get(key);
			if (value != null) {
				prestr = prestr + value;
			}
		}
		return prestr;
	}

	/**
	 * @param params
	 * @param encoding
	 * @return
	 */
	public static String createEncodedLinkString(Map<String, String> params, String encoding) {
		try {
			String prestr = "";
			for (String key : params.keySet()) {
				String value = params.get(key);
				prestr += "&" + key + "=" + URLEncoder.encode(value, encoding);
			}
			return prestr.substring(1);
		} catch (Exception e) {
			return null;
		}
	}

	public static String createSortedValueString(Map<String, String> params) {
		String prestr = "";
		for (String key : params.keySet()) {
			String value = params.get(key);
			if (value != null) {
				prestr = prestr + value;
			}
		}
		return prestr;
	}

	public Map<String, String> recoverEncodedLinkString(String respMsg, String encoding) {
		String[] resArr = StringUtils.split(respMsg, "&");
		Map<String, String> resMap = new LinkedHashMap<String, String>();
		for (int i = 0; i < resArr.length; ++i) {
			String data = resArr[i];
			int index = StringUtils.indexOf(data, '=');
			String nm = StringUtils.substring(data, 0, index);
			String val = StringUtils.substring(data, index + 1);
			try {
				resMap.put(nm, URLDecoder.decode(val, encoding));
			} catch (Exception e) {
				resMap.put(nm, val);
			}
		}
		return resMap;
	}

	public JSONObject recoverLinkString2Object(String respMsg) {
		String[] resArr = StringUtils.split(respMsg, "&");
		JSONObject resMap = new JSONObject();
		for (int i = 0; i < resArr.length; ++i) {
			String data = resArr[i];
			int index = StringUtils.indexOf(data, '=');
			String nm = StringUtils.substring(data, 0, index);
			String val = StringUtils.substring(data, index + 1);
			resMap.put(nm, val);
		}
		return resMap;
	}

	public static Map<String, String> recoverLinkString2Map(String respMsg) {
		String[] resArr = StringUtils.split(respMsg, "&");
		Map<String, String> resMap = new HashMap<String, String>();
		for (int i = 0; i < resArr.length; ++i) {
			String data = resArr[i];
			int index = StringUtils.indexOf(data, '=');
			String nm = StringUtils.substring(data, 0, index);
			String val = StringUtils.substring(data, index + 1);
			resMap.put(nm, val);
		}
		return resMap;
	}

	/**
	 * @param url
	 * @param method
	 * @param charset
	 * @param reqData
	 * @return
	 */
	protected String buildAutoForm(String url, String method, String charset, Map<String, String> reqData) {
		// 提取网银提交的form表单数据
		String h = "";
		h += "<html>";
		h += "<head><meta http-equiv='Content-Type' content='text/html; charset=" + charset + "'/><title> </title></head>";
		h += "<body>";
		h += "<form name='AutoForm' target='_self' action='" + url + "' method='" + method + "'>";
		for (String k : reqData.keySet()) {
			h += "<input type='hidden' name='" + k + "' value=\"" + reqData.get(k) + "\" />";
		}
		h += "</form>";
		h += "<script type='text/javascript'>document.AutoForm.submit();</script></body></html>";

		return h;
	}

	/**
	 */
	protected String MD5(String sourceStr) {
		String result = "";

		try {
			MessageDigest e = MessageDigest.getInstance("MD5");
			e.update(sourceStr.getBytes());
			byte[] b = e.digest();
			StringBuffer buf = new StringBuffer("");

			for (int offset = 0; offset < b.length; ++offset) {
				int i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}

				buf.append(Integer.toHexString(i));
			}

			result = buf.toString();
		} catch (NoSuchAlgorithmException var7) {
			System.out.println(var7);
		}

		return result;
	}

	public void shutdown() throws Exception {
		getConnection().shutdown();
	}
}
