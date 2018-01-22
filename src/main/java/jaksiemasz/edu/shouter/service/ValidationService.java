package jaksiemasz.edu.shouter.service;

import jaksiemasz.edu.shouter.exceptions.UnauthorizedEditException;
import jaksiemasz.edu.shouter.model.Shout;
import jaksiemasz.edu.shouter.model.User;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    public void validateShoutEdit(Shout shout, User user) {
        if(!shout.getAuthorEmail().equals(user.getEmail())) {
            throw new UnauthorizedEditException();
        }
    }
}
