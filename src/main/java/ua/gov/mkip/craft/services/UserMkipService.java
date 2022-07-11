package ua.gov.mkip.craft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.gov.mkip.craft.convert.UserMkipRegistrationInputToUserMkipConverter;
import ua.gov.mkip.craft.input.UserMkipRegistrationInput;
import ua.gov.mkip.craft.models.User;
import ua.gov.mkip.craft.models.UserMkip;
import ua.gov.mkip.craft.repositories.UserMkipRepository;

@Service
@RequiredArgsConstructor
public class UserMkipService {

    public final UserMkipRepository userMkipRepository;
    public final UserMkipRegistrationInputToUserMkipConverter userMkipRegistrationInputToUserMkipConverter;

    public void save(UserMkipRegistrationInput userMkipRegistrationInput) {
        UserMkip userMkip = userMkipRegistrationInputToUserMkipConverter.convert(userMkipRegistrationInput);
        if (userMkip == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        userMkipRepository.save(userMkip);
    }

    public UserMkip findByUsername(String userMkipName) {
        return userMkipRepository.findByUsername(userMkipName);
    }
}
