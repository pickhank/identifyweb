package com.woodare.core.util;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Luke
 */
public class UrlBase64Coder {
	public final static String ENCODING = "UTF-8";

	// 加密
	public static String encoded(byte[] bytes) {
		return new String(Base64.encodeBase64(bytes), Charset.forName(ENCODING));
	}

	// 解密
	public static byte[] decode(String data) {
		return Base64.decodeBase64(data.getBytes(Charset.forName(ENCODING)));
	}
}
