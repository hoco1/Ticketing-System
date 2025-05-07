package com.ticketing.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Show {

    private final long showId;
    private final String title;
    private final LocalDate date;
    private final BigDecimal price;
    private final int seats;
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



    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }



    @Override
    public String toString() {
        return String.format("Title: %s, Date: %s, Price: %s, Seats: %d",
                title, date, price, seats);
    }
}


