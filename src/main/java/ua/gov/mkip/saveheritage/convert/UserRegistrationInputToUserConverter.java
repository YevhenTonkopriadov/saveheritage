package ua.gov.mkip.saveheritage.convert;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.gov.mkip.saveheritage.input.UserRegistrationInput;
import ua.gov.mkip.saveheritage.models.User;

@Component
@RequiredArgsConstructor
public class UserRegistrationInputToUserConverter implements Converter<UserRegistrationInput, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User convert(UserRegistrationInput input) {
        User user = new User();
        user.setUsername(input.getUsername());
        user.setPhone(input.getPhone());
        // set other fields
        return user;
    }
}
