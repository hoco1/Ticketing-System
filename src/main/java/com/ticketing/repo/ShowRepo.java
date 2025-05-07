package com.ticketing.repo;

import com.ticketing.model.Show;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ShowRepo {
    public void save(Show show);
    public Optional<Show> findById(String title);
    public List<Show> findAll();
    public void delete(String title);
}
