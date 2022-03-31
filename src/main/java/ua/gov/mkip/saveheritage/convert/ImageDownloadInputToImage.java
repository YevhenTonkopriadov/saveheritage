package ua.gov.mkip.saveheritage.convert;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.gov.mkip.saveheritage.input.ImageDownloadInput;
import ua.gov.mkip.saveheritage.models.Image;
import ua.gov.mkip.saveheritage.models.Record;
import ua.gov.mkip.saveheritage.services.RecordService;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ImageDownloadInputToImage  implements Converter<ImageDownloadInput, Image>{

    @Value("${fileresources.storange}")
    String imageStorangeType;

    @Value("${fileresources.path}")
    String imagePath;

    private final RecordService recordService;

    @Override
    public Image convert(ImageDownloadInput imageDownloadInput) {
        Image image = new Image();
        String imageName = UUID.randomUUID().toString()+"."+imageDownloadInput.getFileImage().getOriginalFilename();
        image.setImageName(imageName);
        if (imageStorangeType.equals("fileSystem")) {
            try {
                File imageStorange = new File(imagePath);
                if (!imageStorange.exists()) {
                    imageStorange.mkdir();
                }
                imageDownloadInput.getFileImage().transferTo(new File(imageStorange.getAbsolutePath() + "/" +imageName));
            } catch (IOException e) {
                System.out.println("File can't transfer to storange");
                return null;
            }

        } else {
            try {
                image.setImage(imageDownloadInput.getFileImage().getBytes());
            } catch (IOException e) {
                System.out.println("File can't transfer to byte array");
                return null;
            }
        }
        image.setDescriptionImage(imageDownloadInput.getDescriptionImage());
        image.setOriginalImageName(imageDownloadInput.getFileImage().getOriginalFilename());
        Record record = new Record();
        record.setRecordId(imageDownloadInput.getRecordId());
        image.setRecord(record);
        return image;
    }
}
