package org.example.services.auth;

import org.example.entities.user.User;
import org.example.repositories.dao.AbstractUserService;
import org.example.security.SecurityContext;

import java.util.Objects;

public class LoginService {
    private final AbstractUserService userService;
    public LoginService (AbstractUserService userService){ this.userService = userService; }
    public boolean login(String username, String password) {
        User user = userService.findByUsername(username);
        boolean loginResult = !(user == null) && Objects.equals(user.getPassword(), password);
        if (loginResult) {
            SecurityContext.getContext().setCurrentUser(user);
        }
        return loginResult;
    }
}


