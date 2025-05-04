package com.ticketing.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName( "User - constructor validation")
public class UserTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("Null password is regjected")
    void nullPasswordTest(String pwd) {
        assertThrows(IllegalArgumentException.class,
                ()-> new User(1,"hoco1",pwd,Role.OPERATOR));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null username is regjected")
    void nullUsernameTest(String user) {
        assertThrows(IllegalArgumentException.class,
                ()-> new User(1,user,"secret",Role.OPERATOR));
    }

    @Test
    @DisplayName("hasRole() returns true only for the the matching role")
    void hasRoleTest() {
        User user = new User(1,"hoco1","secret",Role.CUSTOMER);
        assertTrue(user.hasRole(Role.CUSTOMER));
        assertFalse(user.hasRole(Role.OPERATOR));
    }
}
