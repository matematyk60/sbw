package jaksiemasz.edu.shouter.repository;

import jaksiemasz.edu.shouter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String email);

    User findByUsername(String username);

}
