package com.mvc.sample.javacore.mvcmodel.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AesUtils {
    private AesUtils() {
    }
    private static final String SECRET_KEY = "&E)H@McQfTjWnZq4";
    private static final SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

    private static final String ALGORITHM = "AES/ECB/PKCS5PADDING";

    public static String encrypt(String value) throws NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] byteEncrypted = cipher.doFinal(value.getBytes());
        return Base64.getUrlEncoder().withoutPadding().encodeToString(byteEncrypted);
    }

    public static String decrypt(String value) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] byteDecrypted = cipher.doFinal(Base64.getUrlDecoder().decode(value.getBytes()));
        return new String(byteDecrypted);
    }
}
