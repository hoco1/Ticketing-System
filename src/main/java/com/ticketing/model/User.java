package com.ticketing.model;

import com.ticketing.util.PasswordUtil;

public class User {
    private final long userId;
    private final String username;
    private final String password;
    private final Role role;

    public User(long id,String username, String password, Role role) {

        if (password == null) {
            throw new IllegalArgumentException("password cannot be null");
        }
        if (username == null) {
            throw new IllegalArgumentException("username cannot be null");
        }

        this.username = username;


        try {
            // Hash Password
            this.password = PasswordUtil.hash(password);
        } catch (Exception e) {
            throw new RuntimeException("password cannot be hashed");
        }

        this.role = role;
        this.userId=id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public Role getRole() {
        return role;
    }

}
