package org.example.controllers.auth;

import org.example.entities.user.UserRole;
import org.example.services.auth.LoginService;
import org.example.services.auth.RegisterService;
import org.example.views.auth.AuthView;

public class AuthController {

    private final AuthView authView;
    private final LoginService loginService;
    private final RegisterService registerService;

    public AuthController(AuthView authView, LoginService loginService, RegisterService registerService){
        this.authView = authView;
        this.loginService = loginService;
        this.registerService = registerService;
    }

    public boolean start() {
        AuthAction action = authView.chooseAction();
        if (action == AuthAction.LOGIN) {
            return login();
        }
        return register();
    }

    private boolean login() {
        authView.startLogin();
        String username = authView.getUsername();
        String password = authView.getPassword();
        return loginService.login(username, password);
    }

    private boolean register() {
        authView.startRegistation();
        String username = authView.getUsername();
        String password = authView.getPassword();
        String firstName = authView.getFirstname();
        String lastName = authView.getLastname();
        UserRole role = authView.getRole();
        return registerService.register(username, password, firstName, lastName, role);
    }
}
