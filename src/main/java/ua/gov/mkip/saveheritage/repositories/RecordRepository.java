package ua.gov.mkip.saveheritage.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.gov.mkip.saveheritage.models.Record;
import ua.gov.mkip.saveheritage.models.User;

@Repository
public interface RecordRepository extends PagingAndSortingRepository<Record, Long> {
    Iterable <Record> findByUser(User user);

}
