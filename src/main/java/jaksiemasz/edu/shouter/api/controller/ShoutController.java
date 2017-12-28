package jaksiemasz.edu.shouter.api.controller;

import jaksiemasz.edu.shouter.api.request.AddShoutRequest;
import jaksiemasz.edu.shouter.model.Shout;
import jaksiemasz.edu.shouter.repository.ShoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ShoutController {

    private ShoutRepository shoutRepository;

    @Autowired
    public ShoutController(ShoutRepository shoutRepository) {
        this.shoutRepository = shoutRepository;
    }

    @GetMapping("/shouts")
    public List<Shout> getAllShouts() {
        return shoutRepository.findAll();
    }

    @PostMapping("/shouts")
    @ResponseStatus(HttpStatus.CREATED)
    public void addShout(@Valid @RequestBody AddShoutRequest request) {
        shoutRepository.save(new Shout(request.getContent()));
    }

}
