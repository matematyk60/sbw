package jaksiemasz.edu.shouter.exceptions;

import java.util.NoSuchElementException;

public class NoSuchShoutException extends NoSuchElementException {
    public NoSuchShoutException() {
        super("There is no shout with given ID");
    }
}
