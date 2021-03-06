package ua.gov.mkip.craft.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.gov.mkip.craft.models.Image;
import ua.gov.mkip.craft.models.Record;

import java.util.Optional;

@Repository
public interface ImageRepository extends PagingAndSortingRepository<Image, Long> {

    @Query("select i from Image i where i.record = ?1")
    Iterable<Image> findAllImagesOfCurrentRecord(Optional<Record> record);
}
