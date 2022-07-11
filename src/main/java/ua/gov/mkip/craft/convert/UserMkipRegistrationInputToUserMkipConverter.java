package ua.gov.mkip.craft.convert;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.gov.mkip.craft.input.UserMkipRegistrationInput;
import ua.gov.mkip.craft.models.UserMkip;
import ua.gov.mkip.craft.models.enums.Role;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMkipRegistrationInputToUserMkipConverter implements Converter<UserMkipRegistrationInput, UserMkip> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserMkip convert(UserMkipRegistrationInput input) {
        UserMkip userMkip = new UserMkip();
        userMkip.setUsername(input.getUsername());
        userMkip.setPassword(passwordEncoder.encode(input.getPassword()));
        userMkip.setFirstName(input.getFirstName());
        userMkip.setLastName(input.getLastName());
        userMkip.setMiddleName(input.getMiddleName());
        System.out.println(input.getDateOfBirth());
        userMkip.setDateOfBirth(LocalDate.parse(input.getDateOfBirth(), DateTimeFormatter.ofPattern("uuuu-MM-dd")));
        System.out.println(input.getDateOfBirth());
        System.out.println(userMkip.getDateOfBirth());
        userMkip.setSex(input.getSex());
        userMkip.setRoles(Set.of(Role.MKIP));
        userMkip.setEmail(input.getEmail());
        userMkip.setPhone(input.getPhone());
        userMkip.setPosition(input.getPosition());
        return userMkip;
    }
}
