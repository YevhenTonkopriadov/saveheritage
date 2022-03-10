package ua.gov.mkip.saveheritage.input;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserRegistrationInput {

    @NotNull
    @Pattern(regexp = "^[a-z0-9-]{3,20}")
    private String username;
    private String password;
    private String confirmPassword;
    private String roles;
    private String organizationName;
    @Email
    private String email;
    private String phone;

    @AssertTrue
    public boolean isPasswordsMatches() {
        return password.equals(confirmPassword);
    }

}
