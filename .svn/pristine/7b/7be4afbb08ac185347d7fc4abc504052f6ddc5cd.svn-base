package com.woodare.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author lu_feng
 * 
 */
public final class ValidatorUtils {

	public static boolean isURL(String url) {
		return matchingText("(http)s?\\:\\/{2}[0-9a-zA-Z]+\\..+", url);
	}

	public static boolean isEmail(String email) {
		return matchingText("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+", email);
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		return matchingText("[0-9\\(\\)\\.\\-]{7,15}", phoneNumber);
	}

	public static boolean isMoblie(String phone) {
		return matchingText("^\\d{11}$", phone);
	}

	public static boolean isMoblie2(String phone) {
		return matchingText("^\\d{11}$", phone);
	}

	/**
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean validateMoblie(String phone) {
		int l = phone.length();
		boolean rs = false;
		switch (l) {
		case 7:
			if (matchingText("^(13[0-9]|15[0-9]|18[7|8|9|6|5])\\d{4}$", phone)) {
				rs = true;
			}
			break;
		case 11:
			if (matchingText("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$", phone)) {
				rs = true;
			}
			break;
		default:
			rs = false;
			break;
		}
		return rs;
	}

	public static boolean validateIdCard(String idcard) {
		return new VerifyIdCard().verify(idcard);
	}

	/**
	 * 
	 * @param expression
	 * @param text
	 * @return
	 */
	private static boolean matchingText(String expression, String text) {
		Pattern p = Pattern.compile(expression); // 正则表达式
		Matcher m = p.matcher(text); // 操作的字符串
		boolean b = m.matches();
		return b;
	}

	public static void main(String[] arg) {
		System.out.println(validateIdCard("321323198606045338"));
		System.out.println(validateIdCard("320982198410074518"));

		System.out.println(validateMoblie("18260198979"));

		//
	}

	static class VerifyIdCard {
		// wi =2(n-1)(mod 11);加权因子
		final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
		// 校验码
		final int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
		private int[] ai = new int[18];

		public VerifyIdCard() {
		}

		// 校验身份证的校验码
		public boolean verify(String idcard) {
			if (idcard.length() == 15) {
				idcard = uptoeighteen(idcard);
			}
			if (idcard.length() != 18) {
				return false;
			}
			String verify = idcard.substring(17, 18);
			if (verify.equals(getVerify(idcard))) {
				return true;
			}
			return false;
		}

		// 15位转18位
		public String uptoeighteen(String fifteen) {
			StringBuffer eighteen = new StringBuffer(fifteen);
			eighteen = eighteen.insert(6, "19");
			return eighteen.toString();
		}

		// 计算最后一位校验值
		public String getVerify(String eighteen) {
			int remain = 0;
			if (eighteen.length() == 18) {
				eighteen = eighteen.substring(0, 17);
			}
			if (eighteen.length() == 17) {
				int sum = 0;
				for (int i = 0; i < 17; i++) {
					String k = eighteen.substring(i, i + 1);
					ai[i] = Integer.valueOf(k);
				}
				for (int i = 0; i < 17; i++) {
					sum += wi[i] * ai[i];
				}
				remain = sum % 11;
			}
			return remain == 2 ? "X" : String.valueOf(vi[remain]);

		}
	}
}
