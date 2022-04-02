package ua.gov.mkip.saveheritage.input;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ua.gov.mkip.saveheritage.validator.ImageFile;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ImageDownloadInput {

    @Pattern(regexp = "^[A-Za-zА_Яа-я .,-]+$")
    @Size(min = 4, max = 20)
    private String descriptionImage;

    @ImageFile
    private MultipartFile fileImage;

    private Long recordId;
}
