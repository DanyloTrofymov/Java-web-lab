package org.example.services.auth;

import org.example.entities.user.Role;
import org.example.entities.user.User;

/*public class RegisterService {
    private final UserRepository userRepository;

    public boolean register(String username, String password, String firstName, String lastName, Role role) {
        try {
            User user = new User(username, password, firstName, lastName, Role.CASHER);
            userRepository.insert(user);
        } catch (DatabaseException exception) {
            return false;
        }
        return true;
    }
}
*/