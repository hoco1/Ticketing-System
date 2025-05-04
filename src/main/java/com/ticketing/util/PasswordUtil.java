package com.ticketing.util;

public class PasswordUtil {

    public PasswordUtil() {}

    public static String hash(String plain){
        return plain;
    }

    public static boolean verify(String plain, String hashed){
        return plain.equals(hashed);
    }
}
