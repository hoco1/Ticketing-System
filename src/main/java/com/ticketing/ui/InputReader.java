package com.ticketing.ui;

import java.util.Scanner;

public class InputReader {
    private Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readLine(String prompt) {
    System.out.print(prompt);

    return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        while(true){
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try{
                return Integer.parseInt(line);
            }catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
