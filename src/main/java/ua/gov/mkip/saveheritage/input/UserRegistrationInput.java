package ua.gov.mkip.saveheritage.input;

import lombok.Data;
import ua.gov.mkip.saveheritage.models.Role;
import ua.gov.mkip.saveheritage.validator.PasswordRequirements;
import ua.gov.mkip.saveheritage.validator.UniqueLogin;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    private Set<Role> roles;

    @NotNull
    @Pattern(regexp = "^[А_Яа-яA-Za-z0-9-]{3,12}")
    private String organizationName;

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
