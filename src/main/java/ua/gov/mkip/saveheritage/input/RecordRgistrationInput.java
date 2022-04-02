package ua.gov.mkip.saveheritage.input;

import lombok.Data;
import ua.gov.mkip.saveheritage.models.User;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RecordRgistrationInput {

    private Long recordId;
    private User user;

    @Pattern(regexp = "^[A-Za-zА_Яа-я]+$")
    @Size(min = 4, max = 20)
    private String recordName;

    @Pattern(regexp = "^[A-Za-zА_Яа-я]+$")
    @Size(max = 200)
    private String recordDescription;
}
