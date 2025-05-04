package com.ticketing.repo;

import com.ticketing.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface UserRepo {
    public void save(User user);
    public Optional<User> findByUsername(String username);
    public List<User> findAll();
}
