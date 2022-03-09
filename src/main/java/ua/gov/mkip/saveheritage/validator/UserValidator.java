package ua.gov.mkip.saveheritage.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.gov.mkip.saveheritage.models.User;
import ua.gov.mkip.saveheritage.services.UserService;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.registration.username");
        }
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.registration.password");
        }
        if (user.getPassword().length()<3) {
            errors.rejectValue("password", "Size.user.password");
        }
        if (user.getUsername().length()<3) {
            errors.rejectValue("username", "Size.user.username");
        }
        if (user.getPassword().codePoints().filter(Character::isDigit).count()<2) {
            System.out.println(user.getPassword().codePoints().filter(Character::isDigit).count());
            errors.rejectValue("password", "Pattern.user.password.notTwoDigit");
        }
        if (user.getPassword().codePoints().filter(Character::isUpperCase).count()<1) {
            System.out.println(user.getPassword().codePoints().filter(Character::isUpperCase).count());
            errors.rejectValue("password", "Pattern.user.password.notUpperCase");
        }
    }
}
