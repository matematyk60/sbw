package jaksiemasz.edu.shouter;

import jaksiemasz.edu.shouter.exceptions.NoSuchShoutException;
import jaksiemasz.edu.shouter.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error processRequestValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        String message = result.getFieldError()
                .getDefaultMessage();

        return new Error(message);
    }

    @ExceptionHandler(NoSuchShoutException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error processMissingShoutError(NoSuchShoutException ex) {

        return new Error(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Error processCreatingDuplicateUsers(UserAlreadyExistsException ex) {

        return new Error(ex.getMessage());
    }
}
