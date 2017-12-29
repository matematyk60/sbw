package jaksiemasz.edu.shouter.service;

import jaksiemasz.edu.shouter.model.Shout;
import jaksiemasz.edu.shouter.repository.ShoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShoutService {

    @Autowired
    private ShoutRepository repository;

    public long addShout(Shout shout) {
        shout.setDate(LocalDate.now());

        Shout saved = repository.save(shout);

        return saved.getId();
    }
}
