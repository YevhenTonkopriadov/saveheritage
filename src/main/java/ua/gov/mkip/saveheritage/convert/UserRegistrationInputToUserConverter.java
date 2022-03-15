package ua.gov.mkip.saveheritage.convert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import ua.gov.mkip.saveheritage.input.UserRegistrationInput;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.gov.mkip.saveheritage.models.Role;
import ua.gov.mkip.saveheritage.models.User;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserRegistrationInputToUserConverter implements Converter<UserRegistrationInput, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User convert(UserRegistrationInput input) {
        User user = new User();
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRoles(Set.of(Role.USER));
        user.setOrganizationName(input.getOrganizationName());
        user.setEmail(input.getEmail());
        user.setPhone(input.getPhone());
        return user;
    }
}
