package com.ticketing.service;

import com.ticketing.model.Show;
import com.ticketing.model.TicketPurchase;
import com.ticketing.model.User;
import com.ticketing.repo.PurchaseRepo;
import com.ticketing.repo.ShowRepo;

public class TicketService {
    private ShowRepo showRepo;
    private PurchaseRepo purchaseRepo;
    private AuthService auth;

    public TicketService(ShowRepo showRepo, PurchaseRepo purchaseRepo, AuthService auth) {
        this.showRepo=showRepo;
        this.purchaseRepo=purchaseRepo;
        this.auth=auth;
    }

}
