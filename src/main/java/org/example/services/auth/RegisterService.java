package org.example.services.auth;

import org.example.entities.user.User;
import org.example.entities.user.UserRole;
import org.example.exceptions.DatabaseException;
import org.example.repositories.dao.AbstractUserService;

public class RegisterService {
    private final AbstractUserService userService;
    public RegisterService (AbstractUserService userService){ this.userService = userService; }
    public boolean register(String username, String password, String firstName, String lastName, UserRole role) {
        try {
            User user = new User(username, password, firstName, lastName, role);
            userService.create(user);
        } catch (DatabaseException exception) {
            return false;
        }
        return true;
    }
}
