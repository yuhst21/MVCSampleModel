package com.mvc.sample.javacore.mvcmodel.util;

import java.security.SecureRandom;
import java.util.Random;

public class CaptchaUtils {
    static char[] SYMBOLS = "?=.*".toCharArray();
    static char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    static char[] NUMBERS = "0123456789".toCharArray();
    static char[] ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789?=.*".toCharArray();
    static Random rand = new SecureRandom();

    public static String getCaptcha(int length) {

        assert length >= 4;
        char[] captcha = new char[length];

        //gen ra một chuỗi mã captcha có độ dài length, bắt đầu bằng một ký tự in hoa, tiếp theo là một ký tự thường,
        // tiếp theo là một số, và các ký tự còn lại là các ký tự trong tập ALL_CHARS
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                captcha[i] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
            } else if (i == 1) {
                captcha[i] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
            } else if (i == 2) {
                captcha[i] = NUMBERS[rand.nextInt(NUMBERS.length)];
            } else {
                captcha[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
            }
        }
        for( int i =0 ; i < captcha.length; i++){
            int index = rand.nextInt(captcha.length);
            char temp = captcha[i];
            captcha[i] = captcha[index];
            captcha[index] = temp;
        }
        return new String(captcha);
    }

}

