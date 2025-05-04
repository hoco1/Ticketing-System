package com.ticketing.repo;

import com.ticketing.model.TicketPurchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.List;
public class InMemoryPurchaseRepo implements PurchaseRepo {
    HashMap<Long, TicketPurchase> purchases = new HashMap<>();

    public void save(TicketPurchase ticket) {
        purchases.put(ticket.getTicketId(),ticket);
    }

    public Optional<TicketPurchase> findById(long id){
        return Optional.ofNullable(purchases.get(id));
    }

    public List<TicketPurchase> findByBuyer(String username){
        List<TicketPurchase> result = new ArrayList<>();
        for(TicketPurchase purchase : purchases.values()){
            if(purchase.getBuyer().getUsername().equals(username)){
                result.add(purchase);
            }
        }
        return result;
    }
    public List<TicketPurchase> findByShowTitle(String title){
        List<TicketPurchase> result = new ArrayList<>();
        for(TicketPurchase purchase : purchases.values()){
            if(purchase.getShow().getTitle().equals(title)){
                result.add(purchase);
            }
        }
        return result;
    }
}
