package ua.gov.mkip.saveheritage.validator;

import net.sf.jmimemagic.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;


public class ImageFileValidator implements ConstraintValidator<ImageFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile imageDownloadInput, ConstraintValidatorContext constraintValidatorContext) {

        if ((imageDownloadInput.isEmpty())
                &&(imageDownloadInput.getSize()>5_000_000)) return false;

        try {
            byte[] data = imageDownloadInput.getBytes();
            MagicMatch match=Magic.getMagicMatch(data);
            if(match.getMimeType().startsWith("image")){
                return true;
            }
            }
        catch (IOException | MagicException | MagicMatchNotFoundException | MagicParseException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
