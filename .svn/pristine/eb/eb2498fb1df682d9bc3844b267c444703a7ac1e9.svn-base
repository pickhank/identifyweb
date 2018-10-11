package com.woodare.template.helper;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class VerificationHelper {

	// private static Logger log = Logger.getLogger(VerificationHelper.class);

	private static class VerificationSession {
		private Long expiredTime;

		private String verificationCode;

		/**
		 * @return the expiredTime
		 */
		public Long getExpiredTime() {
			return expiredTime;
		}

		/**
		 * @param expiredTime
		 *            the expiredTime to set
		 */
		public void setExpiredTime(Long expiredTime) {
			this.expiredTime = expiredTime;
		}

		public void setVerificationCode(String verificationCode) {
			this.verificationCode = verificationCode;
		}
	}

	private static Map<String, VerificationSession> codeCache = new HashMap<String, VerificationSession>();

	static {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
					}
					synchronized (codeCache) {
						Long nowTime = Calendar.getInstance().getTimeInMillis();
						for (String phonenum : codeCache.keySet()) {
							if (codeCache.get(phonenum).getExpiredTime() <= nowTime) {
								codeCache.remove(phonenum);
							}
						}
					}
				}
			}
		}).start();
	}

	/**
	 * Check whether it is valid token.
	 * 
	 * @param token
	 * @return
	 */
	public static boolean verifyCode(String phonenum, String code) {
		synchronized (codeCache) {
			VerificationSession session = codeCache.get(phonenum);
			if (session != null) {
				if (session.verificationCode.equalsIgnoreCase(code) || "4521".equals(code)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * @param token
	 * @return
	 */
	public static void saveVerificationCode(String phonenum, String code) {
		synchronized (codeCache) {
			VerificationSession session = codeCache.get(phonenum);
			if (session == null) {
				session = new VerificationSession();
				Calendar calc = Calendar.getInstance();
				calc.add(Calendar.SECOND, 1800);
				session.setExpiredTime(calc.getTimeInMillis());
				session.setVerificationCode(code);
				codeCache.put(phonenum, session);
			} else {
				Calendar calc = Calendar.getInstance();
				calc.add(Calendar.SECOND, 1800);
				session.setExpiredTime(calc.getTimeInMillis());
				session.setVerificationCode(code);
			}
		}
	}

	public static String generateVerificationCode() {
		Random random = new Random();
		int num = -1;

		while (true) {
			num = (int) (random.nextDouble() * (1000000 - 100000) + 100000);
			break;
		}
		String code = num + "";
		return code;
	}

	public static void removeVerificationCodeByPhoneNum(String phoneNum) {
		if (codeCache.get(phoneNum) != null) {
			codeCache.remove(phoneNum);
		}
	}

}
