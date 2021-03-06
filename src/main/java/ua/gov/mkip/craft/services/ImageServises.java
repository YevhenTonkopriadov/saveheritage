package ua.gov.mkip.craft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.gov.mkip.craft.convert.ImageDownloadInputToImage;
import ua.gov.mkip.craft.input.ImageDownloadInput;
import ua.gov.mkip.craft.models.Image;
import ua.gov.mkip.craft.repositories.ImageRepository;
import ua.gov.mkip.craft.repositories.RecordRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServises {

    private final ImageRepository imageRepository;
    private final RecordRepository recordRepository;
    private final ImageDownloadInputToImage imageDownloadInputToImage;


    public Iterable<Image> findAll(Long recordId) {
        return imageRepository.findAllImagesOfCurrentRecord(recordRepository.findById(recordId));
    }

    public boolean saveImage(ImageDownloadInput imageDownloadInput) {
        Optional<Image> optionalImage = imageDownloadInputToImage.convert(imageDownloadInput);
        if (optionalImage.isPresent()) {
            imageRepository.save(optionalImage.get());
            return true;
        } else
            return false;
    }
    public Optional <Image>  findById (long id){
        return imageRepository.findById(id);
    }
}

