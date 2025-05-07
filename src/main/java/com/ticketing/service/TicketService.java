package com.ticketing.service;

import com.ticketing.model.Show;
import com.ticketing.model.TicketPurchase;
import com.ticketing.model.User;
import com.ticketing.repo.PurchaseRepo;
import com.ticketing.repo.ShowRepo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TicketService {
    private final ShowRepo showRepo;
    private final PurchaseRepo purchaseRepo;
    private final AuthService auth;


    public TicketService(ShowRepo showRepo, PurchaseRepo purchaseRepo, AuthService auth) {
        this.showRepo=showRepo;
        this.purchaseRepo=purchaseRepo;
        this.auth=auth;
    }

    public void buyTicket(String title,int qty){

        User buyer = auth.getCurrentUser();
        Show show = showRepo.findById(title).orElse(null);

        if (show == null) {
            throw new RuntimeException("Show not found");
        }

        if (qty > show.getAvailableSeats()) {
            throw new IllegalArgumentException(
                    "Cannot purchase " + qty +
                            " tickets; only " + show.getAvailableSeats() + " seats remaining");
        }

        BigDecimal totalPrice = BigDecimal.valueOf(qty).multiply(show.getPrice());

        long random = System.currentTimeMillis();
        TicketPurchase purchase = new TicketPurchase(
                random,
                buyer,
                show,
                qty,
                LocalDateTime.now(),
                totalPrice
        );

        show.setAvailableSeats(show.getAvailableSeats()-qty);
        showRepo.save(show);
        purchaseRepo.save(purchase);

    }

}
