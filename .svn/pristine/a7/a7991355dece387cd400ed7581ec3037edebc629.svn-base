package com.woodare.core.util;

import java.util.Random;

/**
 * 
 * @author lu_feng
 * 
 */
public class RandomUtils {

	/**
	 * 
	 * @param ratio
	 * @return
	 */
	public static boolean randomTrue(Integer ratio) {
		Integer rand = new Random().nextInt(100);
		System.out.println(rand);
		return rand < ratio;
	}

	/**
	 * 
	 * @param ratio
	 * @return
	 */
	public static Integer randomChoose(Integer max) {
		return randomChoose(max, 0);
	}

	/**
	 * 
	 * @param ratio
	 * @return
	 */
	public static String random6() {
		return randomChoose(10) + "" + randomChoose(10) + "" + randomChoose(10) + "" + randomChoose(10) + "" + randomChoose(10) + "" + randomChoose(10);
	}

	/**
	 * 
	 * @param max
	 * @param from
	 * @return
	 */
	public static Integer randomChoose(Integer max, Integer from) {
		return new Random().nextInt(max - from) + from;
	}

	public static String randomString() {
		String s = ConvertTo36(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		sb.append(s.substring(6, 7));
		sb.append(s.substring(0, 1));
		sb.append(s.substring(5, 6));
		sb.append("-");
		sb.append(s.substring(7));
		sb.append(s.substring(2, 4));
		sb.append("-");
		sb.append(s.substring(4, 5));
		sb.append(s.substring(1, 2));
		return sb.toString().toUpperCase();// PIS-VRI-JB
	}

	private static String X36 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	// 10进制转换成36进制
	public static String ConvertTo36(Long val) {
		String result = "";
		while (val >= 36) {
			result = X36.charAt((int) (val % 36)) + result;
			val /= 36;
		}
		if (val >= 0)
			result = X36.charAt((int) (val % 36)) + result;
		return result;
	}

	public static void main(String[] args) {
		System.out.println(randomChoose(10) + "" + randomChoose(10) + "" + randomChoose(10) + "" + randomChoose(10) + "" + randomChoose(10) + "" + randomChoose(10) );
	}
}
