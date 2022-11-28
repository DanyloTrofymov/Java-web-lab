package org.example.controllers.auth;

import org.example.views.auth.LoginView;
/*
import org.example.services.auth.LoginService;
public class LoginController {
    private final LoginView loginView;
    private final LoginService loginService;

    public boolean login() {
        try {
            loginView.start();
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            return loginService.login(username, password);
        }
        catch (DatabaseException e){
            System.out.println("Database error");
            return false;
        }
    }

    public LoginController(LoginView loginView, LoginService loginService) {
        this.loginView = loginView;
        this.loginService = loginService;
    }
}
*/