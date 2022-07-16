package ua.gov.mkip.craft.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.gov.mkip.craft.models.User;
import ua.gov.mkip.craft.models.enums.Role;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query ("select i from User i where i.id = ?1")
    Optional<User> findById(Long id);

    @Query ("select i from User i where i.roles = ?1")
    Iterable<User> findAllForRoleMkip(Role role);

}
