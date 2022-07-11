package ua.gov.mkip.craft.input;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserMkipRegistrationInput extends UserRegistrationInput {

    @Pattern(regexp = "^[A-Za-zА-Яа-яёЁЇїІіЄєҐґ-]+$")
    @Size(min = 4, max = 100)
    private String position;

}
