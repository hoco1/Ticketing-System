package com.ticketing.repo;

import com.ticketing.model.Show;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryShowRepo implements ShowRepo {
    HashMap<String, Show> shows = new HashMap<>();

    public void save(Show show) {
        shows.put(show.getTitle(),show);
    }

    public Optional<Show> findById(String title) {
        return Optional.ofNullable(shows.get(title));
    }


    public List<Show> findAll() {
        return List.copyOf(shows.values());
    }

    public void delete(String title){
        shows.remove(title);
    }

}
