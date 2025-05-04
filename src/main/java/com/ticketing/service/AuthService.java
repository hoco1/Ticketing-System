package com.ticketing.service;

import com.ticketing.model.User;
import com.ticketing.repo.UserRepo;
import com.ticketing.util.PasswordUtil;

import java.util.Optional;

public class AuthService {
    private UserRepo userRepo;
    private User currentUser;

    public AuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> login(String username, String password) {
        Optional<User> maybe = userRepo.findByUsername(username);
        if (maybe.isPresent()) {
            User u = maybe.get();
            if (PasswordUtil.verify(password, u.getPassword())) {
                this.currentUser = u;
                return Optional.of(u);
            }
        }

        return Optional.empty();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
