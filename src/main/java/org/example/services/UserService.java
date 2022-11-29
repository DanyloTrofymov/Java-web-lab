package org.example.services;

import org.example.entities.user.User;
import org.example.entities.user.UserRole;
import org.example.repositories.dao.AbstractUserService;

import java.util.List;

public class UserService {
    private final AbstractUserService userService;

    public UserService(AbstractUserService userService){ this.userService = userService; }

    public void create(User user) { userService.create(user); }

    public List<User> findAll() { return userService.findAll(); }

    public User findById(int id) { return userService.findById(id); }

    public void update(int id, User user) { userService.update(id, user); }

    public void delete(int id) { userService.delete(id); }

    public List<User> findByRole (UserRole role) { return userService.findByRole(role); }

    public User findByUsername (String username)  { return userService.findByUsername(username); }
}
