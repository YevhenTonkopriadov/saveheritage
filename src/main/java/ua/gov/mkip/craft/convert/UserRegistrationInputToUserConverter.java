package ua.gov.mkip.craft.convert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import ua.gov.mkip.craft.input.UserRegistrationInput;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.gov.mkip.craft.models.enums.Role;
import ua.gov.mkip.craft.models.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setMiddleName(input.getMiddleName());
        user.setDateOfBirth(LocalDate.parse(input.getDateOfBirth(), DateTimeFormatter.ofPattern("uuuu-MM-dd")));
        user.setSex(input.getSex());
        user.setRoles(Set.of(Role.ADMIN));
        user.setEmail(input.getEmail());
        user.setPhone(input.getPhone());
        return user;
    }
}
