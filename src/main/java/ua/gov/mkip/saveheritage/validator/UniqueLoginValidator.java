package ua.gov.mkip.saveheritage.validator;

import lombok.RequiredArgsConstructor;
import ua.gov.mkip.saveheritage.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

    private final UserRepository userRepository;

    public void initialize(UniqueLogin constraint) {
    }

    public boolean isValid(String username, ConstraintValidatorContext context) {
        return (username != null) && (userRepository.findByUsername(username) == null);
    }

}
