package com.ticketing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketPurchase {
    private long ticketId;
    private User buyer;
    private Show show;
    private int numberOfTickets;
    private BigDecimal totalPrice;
    private LocalDateTime purchaseDate;


    public TicketPurchase(long ticketId, User buyer, Show show, int numberOfTickets, LocalDateTime purchaseDate) {
        // validation
        if(buyer == null || show == null) throw new IllegalArgumentException("User and Show cannot be null");
        if(numberOfTickets<1) throw new IllegalArgumentException("Number of tickets cannot be less than 1");
        if(purchaseDate.isBefore(LocalDateTime.now().minusHours(12))) throw new IllegalArgumentException("Purchase date cannot be before today");
        if(purchaseDate.isAfter(LocalDateTime.now())) throw new IllegalArgumentException("Purchase date cannot be future");
        if(numberOfTickets>show.getSeats()) throw new IllegalArgumentException("Number of tickets cannot be greater than the number of seats");
        if(numberOfTickets>show.getAvailableSeats()) throw new IllegalArgumentException("Number of tickets cannot be greater than the number of available seats");
        if (purchaseDate.isAfter(show.getDate().atStartOfDay().minusHours(12)))  throw new IllegalArgumentException("Purchase date cannot be after the show date");

        this.ticketId = ticketId;
        this.buyer = buyer;
        this.show = show;
        this.numberOfTickets = numberOfTickets;
        this.totalPrice = BigDecimal.valueOf(numberOfTickets).multiply(show.getPrice());
        this.purchaseDate = purchaseDate;
    }

    public long getTicketId() {
        return ticketId;
    }

    public User getBuyer() {
        return buyer;
    }

    public Show getShow() {
        return show;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
}
