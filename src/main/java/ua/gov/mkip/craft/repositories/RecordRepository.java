package ua.gov.mkip.craft.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ua.gov.mkip.craft.models.Record;
import ua.gov.mkip.craft.models.User;


@Repository
public interface RecordRepository extends PagingAndSortingRepository<Record, Long> {

    @Query("select i from Record i where i.userCraftsMan = ?1")
    Iterable<Record> findByUser(User user);

}
