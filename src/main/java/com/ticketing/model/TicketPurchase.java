package com.ticketing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketPurchase(long ticketId, User buyer, Show show, int quantity, LocalDateTime purchaseDate,
                             BigDecimal price) {
    public TicketPurchase {

        if (buyer == null) {
            throw new RuntimeException("You must be logged in to purchase tickets");
        }

        if (show == null) {
            throw new RuntimeException("Show not found");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        if (price == null || price.signum() < 0) {
            throw new IllegalArgumentException("Price must be non-negative");
        }

        if(purchaseDate==null || purchaseDate.isBefore(LocalDateTime.now().minusDays(30))){
            throw new IllegalArgumentException("Purchase date must be in the future");
        }

    }
}
