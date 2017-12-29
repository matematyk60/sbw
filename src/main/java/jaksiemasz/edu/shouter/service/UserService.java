package jaksiemasz.edu.shouter.service;

import jaksiemasz.edu.shouter.exceptions.UserAlreadyExistsException;
import jaksiemasz.edu.shouter.model.User;
import jaksiemasz.edu.shouter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        checkForDuplicates(user);

        userRepository.save(user);
    }


    private void checkForDuplicates(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with given email already exists");
        } else if (userRepository.existsByNickname(user.getNickname())) {
            throw new UserAlreadyExistsException("User with given nickname already exists");
        }
    }
}
