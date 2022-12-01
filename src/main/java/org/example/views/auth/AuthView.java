package org.example.views.auth;

import org.example.controllers.auth.AuthAction;
import org.example.entities.user.UserRole;
import org.example.views.MainView;

import java.util.Objects;
import java.util.Scanner;

public class AuthView extends MainView {
    private final Scanner scanner = new Scanner(System.in);

    public AuthAction chooseAction() {
        AuthAction action = null;
        System.out.println("Choose action:");
        do{
            System.out.println("Enter number\n1: Login\n2: Register\n");
            int line = scanner.nextInt();
            if (line == 1) {
                action = AuthAction.LOGIN;
            }
            if (line == 2) {
                action = AuthAction.REGISTER;
            }
        } while (action == null);
        return action;
    }

    public void startLogin() {
        System.out.println("LOG IN");
    }

    public void startRegistation() {
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

    public String getFirstname() {
        System.out.println("Enter your first name: ");
        return scanner.nextLine();
    }

    public String getLastname() {
        System.out.println("Enter your last name: ");
        return scanner.nextLine();
    }

    public UserRole getRole() {
        UserRole role = null;
        System.out.println("Enter your role: ");
        do{
            System.out.println("Enter number \n1: Casher\n 2: Senior casher \n 3: Expert");
            int line = scanner.nextInt();
            switch (line) {
                case 1 -> role = UserRole.CASHER;
                case 2 -> role = UserRole.SENOR_CASHER;
                case 3 -> role = UserRole.EXPERT;
                default -> role = null;
            }
        } while (role == null);
        return role;
    }
}
