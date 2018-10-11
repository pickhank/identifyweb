package com.woodare.framework.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 扩展HttpServletRequest的功能，所有请求参数获取都通过该类方法来获取。
 */
public class RequestUtil {

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * contentType常见文件类型java设置
	 * 
	 * @param postfix
	 * @return
	 */
	public static String getContentType(String postfix) {
		String contentType = "x-msdownload";
		postfix = postfix.toUpperCase();
		if (postfix.endsWith("XLS") || postfix.endsWith("XLT") || postfix.endsWith(".XLW") || postfix.endsWith("CSV")) {
			contentType = "application/vnd.ms-excel";
		}
		else if (postfix.endsWith("DOC")) {
			contentType = "application/msword";
		}
		else if (postfix.endsWith("RTF")) {
			contentType = "application/rtf";
		}
		else if (postfix.endsWith("TEXT") || postfix.endsWith("TXT")) {
			contentType = "text/plain";
		}
		else if (postfix.endsWith("XML")) {
			contentType = "text/xml";
		}
		else if (postfix.endsWith("BMP")) {
			contentType = "image/bmp";
		}
		else if (postfix.endsWith("JPG") || postfix.endsWith("JPEG")) {
			contentType = "image/jpeg";
		}
		else if (postfix.endsWith("GIF")) {
			contentType = "image/gif";
		}
		else if (postfix.endsWith("AVI")) {
			contentType = "video/x-msvideo";
		}
		else if (postfix.endsWith("MP3")) {
			contentType = "audio/mpeg";
		}
		else if (postfix.endsWith("MPA") || postfix.endsWith("MPE") || postfix.endsWith("MPEG") || postfix.endsWith("MPG")) {
			contentType = "video/mpeg";
		}
		else if (postfix.endsWith("PPT") || postfix.endsWith("PPS")) {
			contentType = "application/vnd.ms-powerpoint";
		}
		else if (postfix.endsWith("PDF")) {
			contentType = "application/pdf";
		}
		else if (postfix.endsWith("ZIP") || postfix.endsWith("RAR")) {
			contentType = "application/zip";
		}
		return contentType;
	}

}
