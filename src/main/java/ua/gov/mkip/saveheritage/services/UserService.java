package ua.gov.mkip.saveheritage.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.gov.mkip.saveheritage.models.User;
import ua.gov.mkip.saveheritage.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
