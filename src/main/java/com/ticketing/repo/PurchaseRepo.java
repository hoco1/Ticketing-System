package com.ticketing.repo;

import com.ticketing.model.TicketPurchase;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepo {
    public void save(TicketPurchase ticket);
    public Optional<TicketPurchase> findById(long id);
    public List<TicketPurchase> findByBuyer(String username);
    public List<TicketPurchase> findByShowTitle(String title);
}
