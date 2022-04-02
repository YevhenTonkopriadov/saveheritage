package ua.gov.mkip.saveheritage.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordRequirementsValidator.class)
public @interface PasswordRequirements {
    String message() default "{message.passwordRequirementsNotMet}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
