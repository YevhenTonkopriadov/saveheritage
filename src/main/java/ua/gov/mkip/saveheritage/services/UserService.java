package ua.gov.mkip.saveheritage.services;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ua.gov.mkip.saveheritage.input.UserRegistrationInput;
import ua.gov.mkip.saveheritage.models.User;
import ua.gov.mkip.saveheritage.repositories.UserRepository;

@Service
public record UserService(UserRepository userRepository,
                          ConversionService mvcConversionService) {

    public User save(UserRegistrationInput input) {
        User user = mvcConversionService.convert(input, User.class);
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
