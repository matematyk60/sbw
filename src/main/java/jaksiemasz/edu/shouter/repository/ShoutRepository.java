package jaksiemasz.edu.shouter.repository;

import jaksiemasz.edu.shouter.model.Shout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoutRepository extends JpaRepository<Shout, Long> {

    Optional<Shout> findById(long id);

}
