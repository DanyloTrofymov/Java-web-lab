package org.example.services.auth;

import org.example.entities.user.User;

import java.util.Objects;

/*public class LoginService {
    private final UserRepository userRepository;
    public boolean login(String username, String password) {
        // User user = userRepository.findByUsername(username);
        boolean loginResult = !(user == null) && Objects.equals(user.getPassword(), password);
        if (loginResult) {
            SecurityContext.getContext().setCurrentUser(user);
        }
        return loginResult;
    }
}
*/

