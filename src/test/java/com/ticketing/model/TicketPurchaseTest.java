package com.ticketing.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("TicketPurchaseTest- constructor validation")
public class TicketPurchaseTest {

    private static  User buyer;
    private static Show show;

    @BeforeAll
    static void init() {

        buyer = new User(1,"hoco1","secret",Role.CUSTOMER);
        show = new Show(1,"abc", BigDecimal.valueOf(30), LocalDate.now().plusDays(5),100 );
    }

    @Test
    @DisplayName("Constructor Calculator total price")
    void constructorHappyPathTest() {
     int qty = 5;
     LocalDateTime purchaseTime = LocalDateTime.now();

     TicketPurchase tp = new TicketPurchase(25,buyer,show,qty,purchaseTime);

     BigDecimal expectedTotalPrice = show.getPrice().multiply(BigDecimal.valueOf(qty));
     assertEquals(expectedTotalPrice,tp.getTotalPrice(),
             "The Total Price should be calculate correctly.");
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null User is rejected")
    void nullUserTest(User user) {
        assertThrows(IllegalArgumentException.class,
                ()-> new TicketPurchase(25,user,show,5,LocalDateTime.now()));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null Show is rejected")
    void nullShowTest(Show show) {
        assertThrows(IllegalArgumentException.class,
                ()-> new TicketPurchase(25,buyer,show,5,LocalDateTime.now()));
    }

    @Test
    @DisplayName("Negative seats is rejected")
    void negativeSeatsTest() {
        assertThrows(IllegalArgumentException.class,
                ()-> new TicketPurchase(25,buyer,show,-1,LocalDateTime.now()));
    }


    @Test
    @DisplayName("Purchase Time is in the future is rejected")
    void pastPurchaseTimeTest() {
        assertThrows(IllegalArgumentException.class,
                ()-> new TicketPurchase(25,buyer,show,5,LocalDateTime.now().plusDays(1)));
    }

    @Test
    @DisplayName("Purchase Time is in the past is rejected")
    void futurePurchaseTimeTest() {
        assertThrows(IllegalArgumentException.class,
                ()-> new TicketPurchase(25,buyer,show,5,LocalDateTime.now().minusDays(1)));
    }

    @Test
    @DisplayName("Number of tickets cannot exceed available seats")
    void oversellTest() {
        assertThrows(IllegalArgumentException.class,
                ()-> new TicketPurchase(25,buyer,show,1000,LocalDateTime.now()));
    }

    @Test
    @DisplayName("Zero ticket is rejected")
    void zeroTicketTest() {
        assertThrows(IllegalArgumentException.class,
                ()-> new TicketPurchase(25,buyer,show,0,LocalDateTime.now()));
    }
}
