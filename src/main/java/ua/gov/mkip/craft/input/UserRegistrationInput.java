package ua.gov.mkip.craft.input;

import lombok.Data;
import ua.gov.mkip.craft.models.enums.Role;
import ua.gov.mkip.craft.models.enums.Sex;
import ua.gov.mkip.craft.validator.PasswordRequirements;
import ua.gov.mkip.craft.validator.UniqueLogin;
import javax.validation.constraints.*;
import java.util.Set;

@Data
public class UserRegistrationInput {

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9_.-]{6,12}")
    @UniqueLogin
    private String username;

    @NotNull
    @PasswordRequirements
    @Pattern(regexp = "^[A-Za-z0-9!@#$%^&*()]{3,20}")
    private String password;

    private String confirmPassword;

    @Pattern(regexp = "^[A-Za-zА-Яа-яёЁЇїІіЄєҐґ-]+$")
    @Size(min = 4, max = 20)
    private String firstName;

    @Pattern(regexp = "^[A-Za-zА-Яа-яёЁЇїІіЄєҐґ-]+$")
    @Size(min = 4, max = 20)
    private String lastName;

    @Pattern(regexp = "^[A-Za-zА-Яа-яёЁЇїІіЄєҐґ-]+$")
    @Size(min = 4, max = 20)
    private String middleName;

    @NotNull
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String dateOfBirth;

    private Set<Role> roles;

    private Sex sex;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "^\\+380\\([0-9]{2}\\)[0-9]{7}$")
    private String phone;

    @AssertTrue
    public boolean isPasswordsMatches() {
        return password.equals(confirmPassword);
    }

}
