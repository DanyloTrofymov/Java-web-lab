package org.example.controllers.auth;

import org.example.entities.user.Role;
import org.example.views.auth.RegisterView;
/*
import org.example.services.auth.RegisterService;
public class RegisterController {
    private final RegisterView registerView;
    private final RegisterService registerService;

    public boolean login() {
        try {
            registerView.start();
            String username = registerView.getUsername();
            String password = registerView.getPassword();
            String firstName = registerView.getFirstName();
            String lastName = registerView.getLastName();
            return registerService.register(username, password, firstName, lastName);
        } catch (DatabaseException e) {
            System.out.println("Database error");
            return false;
        }
    }

    public RegisterController(RegisterView registerView, RegisterService registerService) {
        this.registerView = registerView;
        this.registerService = registerService;
    }
}
*/