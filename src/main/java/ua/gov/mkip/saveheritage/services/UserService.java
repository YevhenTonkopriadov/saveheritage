package ua.gov.mkip.saveheritage.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.gov.mkip.saveheritage.convert.UserRegistrationInputToUserConverter;
import ua.gov.mkip.saveheritage.input.UserRegistrationInput;
import ua.gov.mkip.saveheritage.models.User;
import ua.gov.mkip.saveheritage.repositories.UserRepository;


@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;
    public final UserRegistrationInputToUserConverter userRegistrationInputToUserConverter;

    public void save(UserRegistrationInput userRegistrationInput) {
        User user = userRegistrationInputToUserConverter.convert(userRegistrationInput);
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
