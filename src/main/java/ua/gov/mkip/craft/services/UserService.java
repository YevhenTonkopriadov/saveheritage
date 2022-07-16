package ua.gov.mkip.craft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.gov.mkip.craft.convert.UserRegistrationInputToUserConverter;
import ua.gov.mkip.craft.input.UserPasswordInput;
import ua.gov.mkip.craft.input.UserRegistrationInput;
import ua.gov.mkip.craft.models.User;
import ua.gov.mkip.craft.models.enums.Role;
import ua.gov.mkip.craft.repositories.UserRepository;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;
    public final UserRegistrationInputToUserConverter userRegistrationInputToUserConverter;
    private final PasswordEncoder passwordEncoder;

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
    public void setUserRole (Long userId, Role userRole) {
        if(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRoles().contains(Role.ADMIN)){
            User user = userRepository.findById(userId).get();
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
        }
    }

    public void delete(Long userId) {
        if(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRoles().contains(Role.ADMIN)){
           if(userRepository.findById(userId).isPresent()){
               userRepository.delete(userRepository.findById(userId).get());
           }
        }
    }

    public void setUserPassword(UserPasswordInput userPasswordInput) {
        if(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRoles().contains(Role.ADMIN)){
            Optional <User> optionalUser = userRepository.findById(userPasswordInput.getUserId());
            if (optionalUser.isPresent()){
                User user = optionalUser.get();
                user.setPassword(passwordEncoder.encode(userPasswordInput.getNewPassword()));
                userRepository.save(user);
            }
        }
        if (((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId().equals(userPasswordInput.getUserId())){
            User user = userRepository.findById(userPasswordInput.getUserId()).get();
            user.setPassword(passwordEncoder.encode(userPasswordInput.getNewPassword()));
            userRepository.save(user);
        }
    }
}
