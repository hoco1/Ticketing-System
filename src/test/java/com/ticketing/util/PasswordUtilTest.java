package com.ticketing.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordUtilTest {
    @Test
    public void hashTest() {
        String plain = "password";
        String hashed = PasswordUtil.hash(plain);
        assertEquals(plain,hashed);
    }
}
