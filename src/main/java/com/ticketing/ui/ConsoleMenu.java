package com.ticketing.ui;

import com.ticketing.model.Role;
import com.ticketing.model.Show;
import com.ticketing.model.TicketPurchase;
import com.ticketing.model.User;
import com.ticketing.service.AuthService;
import com.ticketing.service.ShowService;
import com.ticketing.service.TicketService;

import java.math.BigDecimal;

public class ConsoleMenu {
    private AuthService auth;
    private ShowService showSVC;
    private TicketService ticketSVC;
    private InputReader in;

    public ConsoleMenu(AuthService auth, ShowService showSVC, TicketService ticketSVC, InputReader in) {
        this.auth = auth;
        this.showSVC = showSVC;
        this.ticketSVC = ticketSVC;
        this.in = in;
    }

    public void start() {
        while(true){
            User user = null;
            while(user==null){
                String u = in.readLine("Username (or Q to quit): ");
                if("Q".equalsIgnoreCase(u)) return;
                String p = in.readLine("Password: ");
                user = auth.login(u,p).orElse(null);
                if(user==null) System.out.println("Invalid username or password.");
            }

            if(user.getRole()== Role.OPERATOR){
                operatorMenu(user);
            }else {
                customerMenu(user);
            }

            auth.logout();
        }
    }

    private void operatorMenu(User op){
        while(true){
            try{
                System.out.println("Operator Menu");
                System.out.println("1. Create show");
                System.out.println("2. List all shows");
                System.out.println("3. Logout");
                int choice = in.readInt("Choice: ");
                switch(choice){
                    case 1 ->{
                        String title = in.readLine("Title: ");
                        String priceInput = in.readLine("Price: ");
                        BigDecimal price = priceInput.isEmpty() ?null:new BigDecimal(priceInput);
                        int seats = in.readInt("Total seats: ");
                        Show s = showSVC.createShow(title,seats,price);
                        System.out.println("Show created with title: "+s.getTitle());

                    }
                    case 2 ->{
                        showSVC.showAll().forEach(System.out::println);
                    }
                    case 3 ->{
                        auth.logout();
                        return;
                    }
                    case 4 ->{
                        return;
                    }
                }
            }catch (Exception e){
                System.out.println("An error occurred: "+e.getMessage());
                continue;
            }
        }
    }

    private void customerMenu(User customer){
        while(true){
            try{
                System.out.println("Customer Menu");
                System.out.println("1. Buy ticket");
                System.out.println("2. Logout");
                int choice = in.readInt("Choice: ");
                switch(choice){
                    case 1 ->{
                        String sid = in.readLine("Show ID: ");
                        int qty = in.readInt("Quantity: ");
                        ticketSVC.buyTicket(sid, qty);
                    }
                    case 2 ->{
                        auth.logout();
                        return;
                    }
                    case 3 ->{
                        return;
                    }
                }
            }catch(Exception e){
                System.out.println("An error occurred: "+e.getMessage());
                continue;
            }
        }
    }
}
