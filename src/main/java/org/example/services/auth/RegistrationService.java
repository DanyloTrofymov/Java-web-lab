package org.example.services.auth;

import org.example.entities.user.Role;
import org.example.entities.user.User;

public class RegistrationService {
    private final UserRepository userRepository;

    public boolean registerClient(String username, String password, String firstName, String lastName) {
        try {
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .firstName(firstName)
                    .lastName(lastName)
                    .role(Role.CLIENT)
                    .build();
            userRepository.insert(user);
            SecurityContext.getContext().setCurrentUser(user);
        } catch (DatabaseException exception) {
            return false;
        }
        return true;
    }
}
