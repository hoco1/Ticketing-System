package com.ticketing.ui;

import com.ticketing.model.Role;
import com.ticketing.model.User;
import com.ticketing.repo.InMemoryPurchaseRepo;
import com.ticketing.repo.InMemoryShowRepo;
import com.ticketing.repo.InMemoryUserRepo;
import com.ticketing.service.AuthService;
import com.ticketing.service.ShowService;
import com.ticketing.service.TicketService;
import com.ticketing.util.PasswordUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        var userRepo = new InMemoryUserRepo();
        var showRepo = new InMemoryShowRepo();
        var purchaseRepo = new InMemoryPurchaseRepo();

        userRepo.save(new User(12,"operator1", PasswordUtil.hash("123"), Role.OPERATOR));
        userRepo.save(new User(13,"operator2", PasswordUtil.hash("123"), Role.CUSTOMER));

        var authSVC = new AuthService(userRepo);
        var showSVC = new ShowService(showRepo,authSVC);
        var ticketSVC = new TicketService(showRepo,purchaseRepo,authSVC);

        var scanner = new Scanner(System.in);
        var reader = new InputReader(scanner);

        new ConsoleMenu(authSVC,showSVC,ticketSVC,reader).start();

    }
}
