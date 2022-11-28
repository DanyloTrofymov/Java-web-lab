package org.example.views.auth;

import java.util.Scanner;

public class RegisterView {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("REGISTRATION");
    }
    public String getUsername() {
        System.out.println("enter your username and press enter: ");
        return scanner.nextLine();
    }

    public String getPassword() {
        System.out.println("enter your password and press enter: ");
        return scanner.nextLine();
    }

    public String getFirstName() {
        System.out.println("enter your first name and press enter: ");
        return scanner.nextLine();
    }

    public String getLastName() {
        System.out.println("enter your last name and press enter: ");
        return scanner.nextLine();
    }
}
