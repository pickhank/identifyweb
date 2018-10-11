package com.woodare.core.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.woodare.framework.utils.Base64Utils;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 * 
 * @author Sheldon
 *
 */
public class AesUtils {

	/*
	 * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	 */
	public AesUtils() {
	}

	public static void main(String[] args) throws Exception {
		String enc = "SI+rUQmlYiTidnDPCsl69VOQSwQiRT0f9ZKdP6lpnwpAXqJaI3ow05kYVelm7vZjXLIrg5o9kyQ+vceUngsCe2zOiq6B/VoIBf9B+Fbmxb36IjX/PlygtOzF4DPJCqGWZAxl+w8mNA2M79aRaVlmP16UufQv1KeVw/bAlJ7pcRARB0Mhb2szOJY1khl+Jmyxbb4xb+pyDu4RLksLZwGIBrWrnhgmrVCuVvS6+nG5mMynleE3E9nV8t1SIWA09IXNY3bbj0pTnsw/Lil7NV/Bvo51naLyZgKvQPEV9dQgi2aR1r+mbRfo0n1QHwM5IYJ/Ngj37eQU11jvrX3efJjQSEmqcyzbLctU1uVC7gcDzc5oGG1lq44wG9yQUmoS2JflnrsM5jBORt/JORhvwYQLcRxEJ6aE31JwciiO5EX7IioK9/m4x9eQF0fmUVUrK0v9jTVfXwwkJXVRPOn42FJ/Yk2ZqroZjtwOl2ShbQ1CQWVjLbrtpDzRrH+sqNBRUkfYmTwTKN9ohLaERWEY4YQb0qmHZ4aYXAbdejrEC92UfJHix7ZLz4knXrCO1RqyUBJ3";
		System.out.println(decrypt(enc, "1234567812345678"));

		String p = "{\"versionNo\":\"1\",\"mchNo\":\"100275\",\"price\":\"500.00\",\"description\":\"收款\",\"orderDate\":\"20180106143728\",\"tradeNo\":\"PH180106724153600040\",\"notifyUrl\":\"http://mdy.wxgolf.cn/PayAsync/mbnotcard.html\",\"callbackUrl\":\"http://mdy.wxgolf.cn/mobile/index/success/500.00.html\",\"payCardNo\":\"4581240712189662\",\"accName\":\"陈轶文\",\"accIdCard\":\"360731199209156096\",\"bankName\":\"平安银行\",\"cardNo\":\"6230580000111440512\",\"downPayFee\":4,\"downDrawFee\":\"1\"}";
		System.out.println(encrypt(p, "BG8IG7BVIH9GF7F"));
	}
	
	// 加密
	public static String sencrypt(String sSrc, String sKey) {
		try {
			return encrypt(sSrc, sKey);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 加密
	public static String encrypt(String sSrc, String sKey) throws Exception {
		if (null != sSrc && !"".equals(sSrc)) {
			if (null == sKey || "".equals(sKey.trim())) {
				sKey = "0102030405060708";
			}
			String ivParameter = "0102030405060708";
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
			return Base64Utils.encode(encrypted);// 此处使用BASE64做转码。
		}
		else {
			return "";
		}
	}
	// 解密
	public static String sdecrypt(String sSrc, String sKey) {
		try {
			return decrypt(sSrc, sKey);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 解密
	public static String decrypt(String sSrc, String sKey) throws Exception {
		try {
			if (null != sSrc && !"".equals(sSrc)) {
				if (null == sKey || "".equals(sKey.trim())) {
					sKey = "0102030405060708";
				}
				String ivParameter = "0102030405060708";
				byte[] raw = sKey.getBytes("ASCII");
				SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
				cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
				byte[] encrypted1 = Base64Utils.decode(sSrc);// 先用base64解密

				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original, "utf-8");
				return originalString;
			}
			else {
				return "";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
