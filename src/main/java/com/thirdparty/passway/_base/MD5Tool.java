package com.thirdparty.passway._base;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Luke
 *
 */
public class MD5Tool {
    private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(md5("123456"));
        
        System.out.println(numToLetter("0123456Xx538976"));
        
    }

    public static String md5NoUpper(String val) {
        MessageDigest msgDigest = null;

        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }

        try {
            msgDigest.update(val.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("System doesn't support your  EncodingException.");
        }

        byte[] bytes = msgDigest.digest();
        String md5Str = new String(encodeHex(bytes));

        return md5Str;
    }
    
    public static String md5(String val) {
        MessageDigest msgDigest = null;

        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }

        try {
            msgDigest.update(val.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("System doesn't support your  EncodingException.");
        }

        byte[] bytes = msgDigest.digest();
        String md5Str = new String(encodeHex(bytes));

        return md5Str.toUpperCase();
    }

    public static String md5Gbk(String val) {
        MessageDigest msgDigest = null;

        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }

        try {
            msgDigest.update(val.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("System doesn't support your  EncodingException.");
        }

        byte[] bytes = msgDigest.digest();
        String md5Str = new String(encodeHex(bytes));

        return md5Str.toUpperCase();
    }

    // 将数字转换成字母  
    public static String numToLetter(String input) {
        byte[] sources = input.getBytes();
        byte[] res = new byte[sources.length];
        for (int i = 0; i < sources.length; i++) {
            byte b = sources[i];
            if(b >= '0' && b <= '9') {
                res[i] = (byte) (b + 49);
            }
            else {
                res[i] = b;
            }
        }  
        return new String(res).toUpperCase();
    }  
    
    public static char[] encodeHex(byte[] data) {

        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }

        return out;
    }
}
