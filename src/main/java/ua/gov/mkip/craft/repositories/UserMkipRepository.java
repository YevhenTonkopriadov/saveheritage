package ua.gov.mkip.craft.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.gov.mkip.craft.models.UserMkip;

@Repository
public interface UserMkipRepository extends CrudRepository<UserMkip, Long> {
    UserMkip findByUsername(String username);
}
