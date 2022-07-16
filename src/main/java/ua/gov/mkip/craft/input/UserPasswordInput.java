package ua.gov.mkip.craft.input;

import lombok.Data;
import ua.gov.mkip.craft.validator.PasswordRequirements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserPasswordInput {

    long userId;

    private String oldPassword;

    @NotNull
    @PasswordRequirements
    @Pattern(regexp = "^[A-Za-z0-9!@#$%^&*()]{3,20}")
    private String newPassword;

    private String confirmPassword;

    public UserPasswordInput(long userId) {
        this.userId = userId;
    }

    public UserPasswordInput(){};
}
