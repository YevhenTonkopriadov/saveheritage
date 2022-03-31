package ua.gov.mkip.saveheritage.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordRequirementsValidator implements ConstraintValidator<PasswordRequirements, String> {

    public boolean isValid(String password, ConstraintValidatorContext context) {
        return (password.codePoints().filter(Character::isDigit).count()>=2)
                &&(password.codePoints().filter(Character::isUpperCase).count()>=2)
                &&(password.codePoints().filter(ch -> ch == '!'||ch == '@'||ch == '#'||ch == '$'||ch == '%'||ch == '^'||ch == '&'||ch == '*'||ch == '('||ch == ')').count()>=1);
    }
}
