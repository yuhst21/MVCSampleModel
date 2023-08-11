package com.mvc.sample.javacore.mvcmodel.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

;

public class SecretUtils {
    public SecretUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String Sha256(String message) {
        String digest;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(message.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }

            digest = sb.toString();

        } catch (NoSuchAlgorithmException ex) {
            digest = "";
        }
        return digest;
    }
}