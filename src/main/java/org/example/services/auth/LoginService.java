package org.example.services.auth;

import org.example.entities.user.User;
import org.example.repositories.dao.AbstractDaoUserService;

public class LoginService {
    private final AbstractDaoUserService userService;
    public LoginService (AbstractDaoUserService userService){ this.userService = userService; }
    public User login(String username, String password) {
        User user = userService.findByUsername(username);
        if(user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}


