package com.ticketing.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Show - constructor validation")
public class ShowTest {

    @ParameterizedTest
    @NullSource
    @DisplayName("Null name is rejected")
    void nullTitleTest(String title) {
        assertThrows(IllegalArgumentException.class,
                        () -> new Show(1, title, BigDecimal.valueOf(500), LocalDate.now(), 500));
    }
    @Test
    @DisplayName("Empty name is rejected")
    void emptyTitleTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Show(1,"",BigDecimal.valueOf(500), LocalDate.now(), 500));
    }

    @Test
    @DisplayName("Set time to Yesterday")
    void lastTimeTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Show(1,"TEST",BigDecimal.valueOf(500), LocalDate.now().minusDays(1), 500));
    }

    @Test
    @DisplayName("Negative seats is rejected")
    void negativeSeatsTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Show(1,"TEST",BigDecimal.valueOf(500), LocalDate.now(), -1));
    }

    @Test
    @DisplayName("Negative Price is rejected")
    void negativePriceTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Show(1,"TEST",BigDecimal.valueOf(-500), LocalDate.now(), 500));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Null Price is rejected")
    void nullPriceTest(BigDecimal price) {
        assertThrows(IllegalArgumentException.class,
                () -> new Show(1,"TEST",price, LocalDate.now(), 500));
    }


}
