package ua.gov.mkip.craft.convert;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.gov.mkip.craft.input.ImageDownloadInput;
import ua.gov.mkip.craft.models.Image;
import ua.gov.mkip.craft.models.Record;
import ua.gov.mkip.craft.services.RecordService;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ImageDownloadInputToImage  implements Converter<ImageDownloadInput, Optional <Image>> {

    private final RecordService recordService;

    @Override
    public Optional <Image> convert(ImageDownloadInput imageDownloadInput) {
        if (imageDownloadInput.getFileImage()!=null) {
            Image image = new Image();
            try {
                image.setImage(imageDownloadInput.getFileImage().getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return null;
            }
            image.setDescriptionImage(imageDownloadInput.getDescriptionImage());
            image.setImageName(imageDownloadInput.getFileImage().getOriginalFilename());
            image.setRecord(new Record(imageDownloadInput.getRecordId()));
            return Optional.of(image);
        }else return null;
    }
}
