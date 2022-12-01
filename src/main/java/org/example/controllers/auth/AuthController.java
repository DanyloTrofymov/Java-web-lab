package org.example.controllers.auth;

import org.example.entities.user.User;
import org.example.entities.user.UserRole;
import org.example.exceptions.DatabaseException;
import org.example.services.UserService;
import org.example.services.auth.LoginService;
import org.example.services.auth.RegisterService;
import org.example.views.auth.AuthView;

import java.io.IOException;
import java.util.InputMismatchException;

public class AuthController {

    private final AuthView authView;
    private final LoginService loginService;
    private final RegisterService registerService;
    UserService userService;

    public AuthController(AuthView authView, LoginService loginService, RegisterService registerService, UserService userService) {
        this.authView = authView;
        this.loginService = loginService;
        this.registerService = registerService;
        this.userService = userService;
    }

    public User start() {
        try {
            while (true) {
                AuthAction action = authView.chooseAction();
                User user;
                if (action == AuthAction.LOGIN) {
                    user = login();
                } else {
                    user = register();
                }
                if (user != null) {
                    authView.authorized(user.getFirstname(), user.getLastname(), user.getRole());
                    return user;
                }
            }
        } catch (InputMismatchException e) {
            authView.inputErrorMessage();
            return null;
        }
    }

    protected User login() {
        try {
            User user;
            while (true) {
                authView.startLogin();
                String username = authView.getUsername();
                String password = authView.getPassword();
                user = loginService.login(username, password);
                if (user == null) {
                    authView.wrongPass();
                    if (authView.wantToSmth("try again")) {
                        continue;
                    }
                    return null;
                }
                return user;
            }
        } catch (DatabaseException e) {
            authView.databaseExceptionMessage();
            return null;
        }
    }

    protected User register() {
        try {
            while (true) {
                authView.startRegistation();
                String username = authView.getUsername();
                if (!userService.findByUsername(username).isNull()) {
                    authView.userAlreadyExists();
                    if (authView.wantToSmth("repeat")) {
                        continue;
                    }
                    return null;
                }
                String password = authView.getPassword();
                String firstName = authView.getFirstname();
                String lastName = authView.getLastname();
                UserRole role = authView.getRole();
                return registerService.register(username, password, firstName, lastName, role);
            }
        } catch (DatabaseException e) {
            System.out.println(e);
            authView.databaseExceptionMessage();
            return null;
        }
    }
}
