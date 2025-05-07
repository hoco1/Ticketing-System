package com.ticketing.service;

import com.ticketing.model.Role;
import com.ticketing.model.Show;
import com.ticketing.repo.ShowRepo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ShowService {
    private ShowRepo showRepo;
    private AuthService auth;

    public ShowService(ShowRepo showRepo, AuthService auth) {
        this.showRepo = showRepo;
        this.auth=auth;
    }

    private void requireLogin(){
        if(!auth.isLoggedIn()){
            throw new RuntimeException("You must be logged in to perform this action");
        }
    }

    private void requireOperator(){
        requireLogin();
        if(auth.getCurrentUser().getRole() != Role.OPERATOR){
            throw new RuntimeException("You must be an operator to perform this action");
        }
    }

    public Show createShow(String title, int seats, BigDecimal price){
        requireOperator();

        if(showRepo.findById(title).isPresent()){
            throw new RuntimeException("Show already exists");
        }

        long random = System.currentTimeMillis();
        Show show = new Show(
                random,
                title,
                price,
                LocalDate.now(),
                seats

        );
        showRepo.save(show);
        return show;
    }

    public List<Show> showAll(){
        requireOperator();
        return showRepo.findAll();
    }
}
