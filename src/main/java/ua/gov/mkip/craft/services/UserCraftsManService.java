package ua.gov.mkip.craft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.gov.mkip.craft.convert.UserCraftsManRegistrationInputToUserCraftsManConverter;
import ua.gov.mkip.craft.input.UserCraftsManRegistrationInput;
import ua.gov.mkip.craft.models.UserCraftsMan;
import ua.gov.mkip.craft.repositories.UserCraftsManRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCraftsManService {

    public final UserCraftsManRepository userCraftsManRepository;
    public final UserCraftsManRegistrationInputToUserCraftsManConverter userCraftsManRegistrationInputToUserCraftsManConverter;

    public void save(UserCraftsManRegistrationInput userCraftsManRegistrationInput) {
        UserCraftsMan userCraftsMan = userCraftsManRegistrationInputToUserCraftsManConverter.convert(userCraftsManRegistrationInput);
        if (userCraftsMan == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        if (userCraftsManRepository.findByUsername(userCraftsMan.getUsername())==null){
        userCraftsManRepository.save(userCraftsMan);}
    }

    public UserCraftsMan findByUsername(String userCraftsManName) {
        return userCraftsManRepository.findByUsername(userCraftsManName);
    }
    public Iterable<UserCraftsMan> findAll(){
        return userCraftsManRepository.findAll();
    }

    public Optional<UserCraftsMan> findById(Long id) {
        return userCraftsManRepository.findById(id);
    }
}
