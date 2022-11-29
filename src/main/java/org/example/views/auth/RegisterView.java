package org.example.views.auth;

import java.util.Scanner;

public class RegisterView {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("REGISTRATION");
    }
    public String getUsername() {
        System.out.println("Enter your username: ");
        return scanner.nextLine();
    }

    public String getPassword() {
        System.out.println("Enter your password: ");
        return scanner.nextLine();
    }

    public String getFirstName() {
        System.out.println("Enter your first name: ");
        return scanner.nextLine();
    }

    public String getLastName() {
        System.out.println("Enter your last name: ");
        return scanner.nextLine();
    }
}
