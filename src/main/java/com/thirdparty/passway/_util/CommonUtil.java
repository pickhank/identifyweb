package com.thirdparty.passway._util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author linda1@cmbc.com.cn<br>
 */
public final class CommonUtil {
	/**
	 * 
	 * @param length
	 * @return
	 */
	public static String generateLenString(int length) {
		char[] cResult = new char[length];
		int[] flag = { 0, 0, 0 }; // A-Z, a-z, 0-9
		int i = 0;
		while (flag[0] == 0 || flag[1] == 0 || flag[2] == 0 || i < length) {
			i = i % length;
			int f = (int) (Math.random() * 3 % 3);
			if (f == 0)
				cResult[i] = (char) ('A' + Math.random() * 26);
			else if (f == 1)
				cResult[i] = (char) ('a' + Math.random() * 26);
			else
				cResult[i] = (char) ('0' + Math.random() * 10);
			flag[f] = 1;
			i++;
		}
		return new String(cResult);
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @author guoyx
	 */
	public static boolean isnull(String str) {
		if (null == str || str.equalsIgnoreCase("null") || str.trim().equals("")) {
			return true;
		} else
			return false;
	}

	public static void main(String[] args) {
		String a = "null";
		System.out.println(isnull(a));
	}

	/**
	 * 
	 * @param paramMap
	 * @return
	 * @author guoyx
	 */
	public static String getPlainText(Map<String, Object> params) {
		StringBuffer content = new StringBuffer();

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			if ("sign".equals(key)) {
				continue;
			}
			String value = (String) params.get(key);
			if (isnull(value)) {
				continue;
			}
			content.append((i == 0 ? "" : "&") + key + "=" + value);

		}
		String signSrc = content.toString();
		if (signSrc.startsWith("&")) {
			signSrc = signSrc.replaceFirst("&", "");
		}
		return signSrc;
	}


	/**
	 * 
	 * @param paramMap
	 * @return
	 * @author guoyx
	 * @throws UnsupportedEncodingException
	 */
	public static String genSignData(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			if ("sign".equals(key)) {
				continue;
			}
			String value = params.get(key);
			if (CommonUtil.isnull(value)) {
				continue;
			}
			content.append((i == 0 ? "" : "&") + key + "=" + value);

		}
		String signSrc = content.toString();
		if (signSrc.startsWith("&")) {
			signSrc = signSrc.replaceFirst("&", "");
		}
		return signSrc;
	}

	public static JSONObject formStr2Json(String content) {
		JSONObject reqObj = new JSONObject();
		String splitStr = "&";
		String data[] = content.split(splitStr);
		for (int i = 0; i < data.length; i++) {
			String value = data[i];
			String[] temp = value.split("=");
			try {
				if (temp.length >= 2) {
					reqObj.put(temp[0], value.substring(value.indexOf("=") + 1, value.length()));
				} else if (temp.length == 1) {
					reqObj.put(temp[0], "");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return reqObj;
	}
}