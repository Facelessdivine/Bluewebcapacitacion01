/*
 * @(#)HexDijest.java        1.0 2010/01/12
 * Copyright (c) 2010-2012 Blueweb Software Solutions SC
 *
 */

package utils;


import java.security.MessageDigest;
/**
 *
 *
 * Hash para password
 *
 * @version 1.00 01 Enero 2012
 * @author Arnulfo Gomez
 */
public class HexDigest {
            public static String hexDigest(String message) {
        MessageDigest md;

        byte[] buffer, digest;
        String hash = "";

        try {
            buffer = message.getBytes("UTF-8");
            md = MessageDigest.getInstance("SHA1");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        md.update(buffer);
        digest = md.digest();

        for (byte aux : digest) {
            int b = aux & 0xff;

            String s = Integer.toHexString(b);

            if (s.length() == 1) {
                hash += "0";
            }
            hash += s;
        }
        return hash;
    }
}
