package jaksiemasz.edu.shouter.api.shout.controller;

import jaksiemasz.edu.shouter.api.shout.request.AddShoutRequest;
import jaksiemasz.edu.shouter.api.shout.response.ShoutAddedResponse;
import jaksiemasz.edu.shouter.exceptions.NoSuchShoutException;
import jaksiemasz.edu.shouter.model.Shout;
import jaksiemasz.edu.shouter.model.User;
import jaksiemasz.edu.shouter.repository.ShoutRepository;
import jaksiemasz.edu.shouter.service.ShoutService;
import jaksiemasz.edu.shouter.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ShoutController {

    private ShoutRepository shoutRepository;
    private ShoutService shoutService;
    private ValidationService validationService;


    @Autowired
    public ShoutController(ShoutRepository shoutRepository, ShoutService shoutService, ValidationService validationService) {
        this.shoutRepository = shoutRepository;
        this.shoutService = shoutService;
        this.validationService = validationService;
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

    @GetMapping("/shouts/byUser")
    public List<Shout> getAllUsersShouts(@RequestParam String email) {
        return shoutRepository.findAllByAuthorEmail(email);
    }

    @PostMapping("/shouts")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoutAddedResponse addShout(@Valid @RequestBody AddShoutRequest request, Authentication auth) {
        User user = (User)auth.getPrincipal();
        Shout shout = request.extractShout();
        shout.setAuthorEmail(user.getEmail());
        long createdId = shoutService.addShout(shout);

        return new ShoutAddedResponse(createdId);
    }

    @DeleteMapping("/shouts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShout(@PathVariable long id, Authentication auth) {
        User user = (User) auth.getPrincipal();
        Shout shoutToDelete = shoutRepository.findById(id)
                .orElseThrow(NoSuchShoutException::new);

        validationService.validateShoutEdit(shoutToDelete, user);

        shoutRepository.delete(shoutToDelete);
    }

}
