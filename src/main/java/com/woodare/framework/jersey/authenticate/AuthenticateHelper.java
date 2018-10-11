package com.woodare.framework.jersey.authenticate;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 
 * @author lu_feng
 * 
 */
public class AuthenticateHelper {
    private static Logger log = Logger.getLogger(AuthenticateHelper.class);

    public static final String SESSION_TOKEN_KEY = "__SESSION_TOKEN_KEY__";

    /**
     * 
     * @param token
     * @param request
     */
    public static void bindToRequest(String token, HttpServletRequest request) {
        request.setAttribute(SESSION_TOKEN_KEY, getAuthenticateToken(token));
    }

    /**
     * 
     * @param token
     * @param request
     */
    public static AuthorizedData<?> getRequestTokenSession(HttpServletRequest request) {
        return (AuthorizedData<?>) request.getAttribute(SESSION_TOKEN_KEY);
    }

    private static class AuthenticateSession {
        private Long expiredTime;
        private AuthorizedData<?> token;

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

        /**
         * @return the token
         */
        public AuthorizedData<?> getToken() {
            return token;
        }

        /**
         * @param token
         *            the token to set
         */
        public void setToken(AuthorizedData<?> token) {
            this.token = token;
        }
    }

    private static Map<String, AuthenticateSession> tokenCache = new HashMap<String, AuthenticateSession>();

    static {
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        this.wait(5000);
                    } catch (InterruptedException e) {
                    }

                    synchronized (tokenCache) {
                        log.debug("Begin to analysis expired session ");

                        Long nowTime = Calendar.getInstance().getTimeInMillis();
                        for (String token : tokenCache.keySet()) {
                            if (tokenCache.get(token).getExpiredTime() <= nowTime) {
                                tokenCache.remove(token);
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * 
     * @param token
     * @return
     */
    public static AuthorizedData<?> getAuthenticateToken(String token) {
        synchronized (tokenCache) {
            AuthenticateSession session = tokenCache.get(token);
            if (session != null) {
                Calendar calc = Calendar.getInstance();
                calc.add(Calendar.MINUTE, 30);
                session.setExpiredTime(calc.getTimeInMillis());
                return session.getToken();
            }
            return null;
        }
    }

    /**
     * Check whether it is valid token.
     * 
     * @param token
     * @return
     */
    public static boolean hasValidToken(String token) {
        return getAuthenticateToken(token) != null;
    }

    /**
     * Check whether it is valid token.
     * 
     * @param token
     * @return
     */
    public static void invalidToken(String token) {
        synchronized (tokenCache) {
            if(tokenCache.containsKey(token)) {
                tokenCache.remove(token);
            }
        }
    }

    /**
     * 
     * @param token
     * @return
     */
    public static void setAuthenticateToken(String token, AuthorizedData<?> tokenData) {
        synchronized (tokenCache) {
            AuthenticateSession session = tokenCache.get(token);
            if (session == null) {
                session = new AuthenticateSession();
                tokenCache.put(token, session);
            }
            Calendar calc = Calendar.getInstance();
            calc.add(Calendar.MINUTE, 30);
            session.setExpiredTime(calc.getTimeInMillis());
            session.setToken(tokenData);
        }
    }
}
