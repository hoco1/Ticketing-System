package com.ticketing.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

public class Show {

    private final long showId;
    private String title;
    private LocalDate date;
    private BigDecimal price;
    private final int seats;
    private boolean openForSale = true;
    private int availableSeats;

    public Show(long showId, String title, BigDecimal price, LocalDate date, int seats) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be before today");
        }
        if (seats < 0) {
            throw new IllegalArgumentException("Seats cannot be negative");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative or null");
        }
        this.showId = showId;
        this.title = title;
        this.date = date;
        this.price = price;
        this.seats = seats;
        this.availableSeats = seats;
    }

    public long getId() {
        return showId;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getSeats() {
        return seats;
    }

    public boolean isOpenForSale() {
        return openForSale;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setClosed() {
        this.openForSale = false;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Date: %s, Price: %s, Seats: %d",
                title, date, price, seats);
    }
}


