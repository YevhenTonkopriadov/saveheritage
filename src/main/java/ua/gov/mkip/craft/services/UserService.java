package ua.gov.mkip.craft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.gov.mkip.craft.convert.UserRegistrationInputToUserConverter;
import ua.gov.mkip.craft.input.UserRegistrationInput;
import ua.gov.mkip.craft.models.User;
import ua.gov.mkip.craft.models.enums.Role;
import ua.gov.mkip.craft.repositories.UserRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


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

    public Iterable<User> findAll() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRoles().contains(Role.ADMIN)) {
            return userRepository.findAll();
        }
        if (user.getRoles().contains(Role.MKIP)) {
            return StreamSupport.stream(userRepository.findAll().spliterator(), false).
                    filter(user1 -> user1.getRoles().contains(Role.USER) || user1.getRoles().contains(Role.USERADOPED) ||
                            user1.getRoles().contains(Role.USERREGISTERS)).collect(Collectors.toList());
        } else return null;
    }
}
