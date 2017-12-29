package jaksiemasz.edu.shouter.api.shout.controller;

import jaksiemasz.edu.shouter.api.shout.request.AddShoutRequest;
import jaksiemasz.edu.shouter.api.shout.response.ShoutAddedResponse;
import jaksiemasz.edu.shouter.exceptions.NoSuchShoutException;
import jaksiemasz.edu.shouter.model.Shout;
import jaksiemasz.edu.shouter.repository.ShoutRepository;
import jaksiemasz.edu.shouter.service.ShoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ShoutController {

    private ShoutRepository shoutRepository;
    private ShoutService shoutService;


    @Autowired
    public ShoutController(ShoutRepository shoutRepository, ShoutService shoutService) {
        this.shoutRepository = shoutRepository;
        this.shoutService = shoutService;
    }

    @GetMapping("/shouts")
    public List<Shout> getAllShouts() {
        return shoutRepository.findAll();
    }

    @GetMapping("/shouts/{id}")
    public Shout getShoutById(@PathVariable long id) {
        return shoutRepository.findById(id)
                .orElseThrow(NoSuchShoutException::new);
    }

    @PostMapping("/shouts")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoutAddedResponse addShout(@Valid @RequestBody AddShoutRequest request) {
        Shout shout = request.extractShout();
        long createdId = shoutService.addShout(shout);

        return new ShoutAddedResponse(createdId);
    }

    @DeleteMapping("/shouts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShout(@PathVariable long id) {
        Shout shoutToDelete = shoutRepository.findById(id)
                .orElseThrow(NoSuchShoutException::new);

        shoutRepository.delete(shoutToDelete);
    }

}
