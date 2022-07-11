package ua.gov.mkip.craft.input;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ua.gov.mkip.craft.models.enums.Region;
import ua.gov.mkip.craft.validator.ImageFile;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserCraftsManRegistrationInput extends UserRegistrationInput {

    private Region region;


    @Pattern(regexp = "^[A-Za-zА-Яа-яёЁЇїІіЄєҐґ, -]+$")
    @Size(min = 4, max = 20)
    private String historicalEthnographicDistrict;

    @Pattern(regexp = "^[A-Za-zА-Яа-яёЁЇїІіЄєҐґ, -]+$")
    @Size(min = 4, max = 20)
    private String tradition;

    @Pattern(regexp = "^[A-Za-zА-Яа-яёЁЇїІіЄєҐґ, -]+$")
    @Size(min = 4, max = 20)
    private String type;

    @Pattern(regexp = "^[0-9A-Za-zА-Яа-яёЁЇїІіЄєҐґ, -]+$")
    @Size(min = 4, max = 100)
    private String address;

    @Pattern(regexp = "^[A-Za-zА-Яа-яёЁЇїІіЄєҐґ, -]+$")
    @Size(min = 4, max = 20)
    private String organization;

    @Pattern(regexp = "^[0-9A-Za-zА-Яа-яёЁЇїІіЄєҐґ#№, -]+$")
    @Size(min = 4, max = 20)
    private String document;

    @ImageFile
    private MultipartFile craftsManImage;
}
