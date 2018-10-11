package com.woodare.template.web.controller._base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.woodare.framework.utils.SysProperties;
import com.woodare.template.constant.SystemPropertiesConstant;

/**
 * @author Luke
 */
public class BasePayController {

	/**
	 * @param request
	 * @param getDataMap
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	protected String parseRequestParameters(HttpServletRequest request, JSONObject getDataMap, String encoding) throws IOException {
		InputStream is = request.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, encoding));
		String buffer = null;
		StringBuffer sb = new StringBuffer();
		while ((buffer = br.readLine()) != null) {
			sb.append(buffer + "\n");
		}

		String postText = sb.toString().trim();

		String name = "", values = "";
		for (Enumeration<String> names = request.getParameterNames(); names.hasMoreElements(); getDataMap.put(name, values)) {
			name = (String) names.nextElement();
			values = request.getParameter(name);
		}
		return postText;
	}

	/**
	 * @param transNo
	 * @return
	 */
	protected String getPayResult(String transNo) {
		String path = SysProperties.getInstance().getProperty(SystemPropertiesConstant.CODE_BASE_URL_ALIAS);
		return path + "/creditpay/result/" + transNo;
	}
}
