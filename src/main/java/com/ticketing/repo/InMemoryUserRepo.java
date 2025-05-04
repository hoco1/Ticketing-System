package com.ticketing.repo;

import com.ticketing.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepo implements UserRepo {
    private HashMap<String, User> users = new HashMap<>();
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }
    public List<User> findAll() {
        return List.copyOf(users.values());
    }
}
