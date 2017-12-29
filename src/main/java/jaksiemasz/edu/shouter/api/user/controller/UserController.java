package jaksiemasz.edu.shouter.api.user.controller;

import jaksiemasz.edu.shouter.api.user.request.UserRegisterRequest;
import jaksiemasz.edu.shouter.model.User;
import jaksiemasz.edu.shouter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(UserRegisterRequest request) {
        User user = request.extractUser();

        userService.addUser(user);
    }

}
