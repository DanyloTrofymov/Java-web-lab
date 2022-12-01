package org.example.services.auth;

import org.example.entities.user.User;
import org.example.entities.user.UserRole;
import org.example.exceptions.DatabaseException;
import org.example.repositories.dao.AbstractDaoUserService;

public class RegisterService {
    private final AbstractDaoUserService userService;
    public RegisterService (AbstractDaoUserService userService){ this.userService = userService; }
    public User register(String username, String password, String firstName, String lastName, UserRole role) {
        User user = new User(username, password, firstName, lastName, role);
        try {
            userService.create(user);
        } catch (DatabaseException exception) {
            return null;
        }
        return user;
    }
}
