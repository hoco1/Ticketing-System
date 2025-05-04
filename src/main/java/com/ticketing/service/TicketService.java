package com.ticketing.service;

import com.ticketing.model.Show;
import com.ticketing.model.TicketPurchase;
import com.ticketing.model.User;
import com.ticketing.repo.PurchaseRepo;
import com.ticketing.repo.ShowRepo;

import java.time.LocalDateTime;

public class TicketService {
    private ShowRepo showRepo;
    private PurchaseRepo purchaseRepo;
    private AuthService auth;

    public TicketService(ShowRepo showRepo, PurchaseRepo purchaseRepo, AuthService auth) {
        this.showRepo=showRepo;
        this.purchaseRepo=purchaseRepo;
        this.auth=auth;
    }

    public TicketPurchase buyTicket(String title,int qty){
        User buyer = auth.getCurrentUser();
        if(buyer == null){
            throw new RuntimeException("You must be logged in to purchase tickets");
        }

        Show show = showRepo.findById(title).orElse(null);
        if(show == null){
            throw new RuntimeException("Show not found");
        }

        long random = System.currentTimeMillis();
        TicketPurchase purchase = new TicketPurchase(
                random,
                buyer,
                show,
                qty,
                LocalDateTime.now()
        );

        return purchase;

    }

}
