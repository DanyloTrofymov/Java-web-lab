package org.example.views.auth;

import java.util.Scanner;

public class LoginView {
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("LOG IN");
    }

    public String getUsername() {
        System.out.println("Username: ");
        return scanner.nextLine();
    }

    public String getPassword() {
        System.out.println("Password: ");
        return scanner.nextLine();
    }
}