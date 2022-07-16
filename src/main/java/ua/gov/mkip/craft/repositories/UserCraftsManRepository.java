package ua.gov.mkip.craft.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.gov.mkip.craft.models.UserCraftsMan;

@Repository
public interface UserCraftsManRepository extends CrudRepository<UserCraftsMan, Long> {

    UserCraftsMan findByUsername(String username);
}
