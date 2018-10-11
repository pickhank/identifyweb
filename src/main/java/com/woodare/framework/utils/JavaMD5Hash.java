package com.woodare.framework.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Luke
 *
 */
public class JavaMD5Hash {

    private final static String SALT_PASS = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";

    public static void main(String[] args) {

        String password = "what'sup man what could I help u";

        System.out.println("MD5 in hex: " + md5(password));

        System.out.println("MD5 in hex: " + md5(null));
        // = d41d8cd98f00b204e9800998ecf8427e

        System.out.println("MD5 in hex: " + md5("The quick brown fox jumps over the lazy dog"));
        // = 9e107d9d372bb6826bd81d3542a419d6
    }

    public static String md5(String input) {

        String md5 = null;

        if (null == input)
            return null;

        try {
            input += SALT_PASS;

            // Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            // Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            // Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }
}