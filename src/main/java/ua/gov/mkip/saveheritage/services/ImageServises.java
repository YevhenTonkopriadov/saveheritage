package ua.gov.mkip.saveheritage.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.gov.mkip.saveheritage.convert.ImageDownloadInputToImage;
import ua.gov.mkip.saveheritage.input.ImageDownloadInput;
import ua.gov.mkip.saveheritage.models.Image;
import ua.gov.mkip.saveheritage.repositories.ImageRepository;
import ua.gov.mkip.saveheritage.repositories.RecordRepository;

@Service
@RequiredArgsConstructor
public class ImageServises {

    private final ImageRepository imageRepository;
    private final RecordRepository recordRepository;
    private final ImageDownloadInputToImage imageDownloadInputToImage;


    public Iterable <Image> findAllImagesOfCurrentRecord(Long recordId){
        return imageRepository.findAllImagesOfCurrentRecord(recordRepository.findById(recordId));
    }

    public boolean saveImage (ImageDownloadInput imageDownloadInput) {
        Image image = imageDownloadInputToImage.convert(imageDownloadInput);
        if (image==null){
            return false;
        }
        else {
            imageRepository.save(image);
            return true;
        }
    }
}
