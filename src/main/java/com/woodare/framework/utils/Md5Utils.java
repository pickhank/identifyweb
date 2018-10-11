package com.woodare.framework.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author Luke
 * 
 */
public class Md5Utils {
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}
		return out;
	}

	/**
	 * @param val
	 * @param
	 * @return
	 */
	public static String md5(String val, String charsetName) {
		return md5(val, Charset.forName(charsetName));
	}

	/**
	 * @param
	 * @param val
	 * @return
	 */
	public static byte[] md5Base64(String val, String charsetName) {
		return md5Base64(val, Charset.forName(charsetName));
	}

	/**
	 * @param val
	 * @param charset
	 * @return
	 */
	public static String md5(String val, Charset charset) {
		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("System doesn't support MD5 algorithm.");
		}
		msgDigest.update(val.getBytes(charset));
		byte[] bytes = msgDigest.digest();
		return new String(encodeHex(bytes));
	}

	/**
	 * @param val
	 * @param charset
	 * @return
	 */
	public static byte[] md5Base64(String val, Charset charset) {
		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("System doesn't support MD5 algorithm.");
		}
		msgDigest.update(val.getBytes(charset));
		byte[] bytes = msgDigest.digest();
		return Base64.encodeBase64(bytes);
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		Date startTime = new Date();
		for (int i = 0; i < 10000; i++) {
			md5("1" + i, "UTF8");
		}
		System.out.println(new Date().getTime() - startTime.getTime());
	}
}
