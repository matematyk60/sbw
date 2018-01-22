package jaksiemasz.edu.shouter.api.user.controller;

import jaksiemasz.edu.shouter.api.user.request.UserRegisterRequest;
import jaksiemasz.edu.shouter.model.User;
import jaksiemasz.edu.shouter.repository.UserRepository;
import jaksiemasz.edu.shouter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {


    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody UserRegisterRequest request) {
        User user = request.extractUser();

        userService.addUser(user);
    }

    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public void delete(Authentication authentication) {
        User user = (User)authentication.getCredentials();

        userRepository.delete(user);
    }

}
