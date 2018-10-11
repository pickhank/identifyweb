package com.woodare.core.component.captcha;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.woodare.core.util.RandomUtils;

/**
 * 
 * @author lu_feng
 *
 */
public class VerificationService {

    private static Logger log = Logger.getLogger(VerificationService.class);

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
                        this.wait(5000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("run");
                    synchronized (codeCache) {
                        log.debug("Begin to analysis expired session ");

                        Long nowTime = Calendar.getInstance().getTimeInMillis();
                        for (String phonenum : codeCache.keySet()) {
                            if (codeCache.get(phonenum).getExpiredTime() <= nowTime) {
                                codeCache.remove(phonenum);
                            }
                        }
                    }
                }
            }
        });
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
                if (session.verificationCode.equalsIgnoreCase(code)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 
     * @param token
     * @return
     */
    public static void saveVerificationCode(String phonenum, String code) {
        synchronized (codeCache) {
            VerificationSession session = codeCache.get(phonenum);
            if (session == null) {
                session = new VerificationSession();
                Calendar calc = Calendar.getInstance();
                calc.add(Calendar.SECOND, 1200);
                session.setExpiredTime(calc.getTimeInMillis());
                session.setVerificationCode(code);
                codeCache.put(phonenum, session);
            } else {
                Calendar calc = Calendar.getInstance();
                calc.add(Calendar.SECOND, 1200);
                session.setExpiredTime(calc.getTimeInMillis());
                session.setVerificationCode(code);
            }
        }
    }

    public static String generateVerificationCode() {
        long timestamp = new Date().getTime();
        String code = Integer.toHexString((int) timestamp);
        return code;
    }

    public static void removeVerificationCodeByPhoneNum(String phoneNum) {
        if (codeCache.get(phoneNum) != null) {
            codeCache.remove(phoneNum);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String code = "" + RandomUtils.randomChoose(99999, 10000);
        VerificationService.saveVerificationCode("18951819389", code);

        boolean rs = VerificationService.verifyCode("18951819389", code);
        if (rs) {
            System.out.println("successful");
        } else {
            System.out.println("failed");
        }

        boolean rs1 = VerificationService.verifyCode("18951819389", "1234");
        if (rs1) {
            System.out.println("successful");
        } else {
            System.out.println("failed");
        }

    }

}
