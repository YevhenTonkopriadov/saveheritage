package ua.gov.mkip.craft.convert;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.gov.mkip.craft.input.UserCraftsManRegistrationInput;
import ua.gov.mkip.craft.models.UserCraftsMan;
import ua.gov.mkip.craft.models.enums.Role;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserCraftsManRegistrationInputToUserCraftsManConverter implements Converter<UserCraftsManRegistrationInput, UserCraftsMan> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserCraftsMan convert(UserCraftsManRegistrationInput input) {
        System.out.println(input.getRoles());
        UserCraftsMan userCraftsMan = new UserCraftsMan();
        userCraftsMan.setUsername(input.getUsername());
        userCraftsMan.setPassword(passwordEncoder.encode(input.getPassword()));
        userCraftsMan.setFirstName(input.getFirstName());
        userCraftsMan.setLastName(input.getLastName());
        userCraftsMan.setMiddleName(input.getMiddleName());
        userCraftsMan.setDateOfBirth(LocalDate.parse(input.getDateOfBirth(), DateTimeFormatter.ofPattern("uuuu-MM-dd")));
        System.out.println(input.getDateOfBirth());
        System.out.println(userCraftsMan.getDateOfBirth());
        userCraftsMan.setSex(input.getSex());
        if (input.getRoles().isEmpty()){
            userCraftsMan.setRoles(Set.of(Role.USERREGISTERS));
        } else
         {
             userCraftsMan.setRoles(input.getRoles());
         }
        userCraftsMan.setEmail(input.getEmail());
        userCraftsMan.setPhone(input.getPhone());
        userCraftsMan.setRegion(input.getRegion());
        userCraftsMan.setHistoricalEthnographicDistrict(input.getHistoricalEthnographicDistrict());
        userCraftsMan.setTradition (input.getTradition());
        userCraftsMan.setType(input.getType());
        userCraftsMan.setAddress(input.getAddress());
        userCraftsMan.setOrganization(input.getOrganization());
        userCraftsMan.setDocument(input.getDocument());
        userCraftsMan.setFileNameCraftsManImage(input.getFirstName());
        try {
            userCraftsMan.setCraftsManImage(input.getCraftsManImage().getBytes());
            userCraftsMan.setFileNameCraftsManImage(UUID.randomUUID().toString()+input.getCraftsManImage().getOriginalFilename());
            System.out.println(userCraftsMan.getFileNameCraftsManImage());
        } catch (IOException e) {
            System.err.println("фото майстра не збережено");;
        }
        return userCraftsMan;
    }
}
