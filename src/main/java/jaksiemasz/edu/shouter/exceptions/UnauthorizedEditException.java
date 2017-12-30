package jaksiemasz.edu.shouter.exceptions;

public class UnauthorizedEditException extends RuntimeException {
    public UnauthorizedEditException() {
        super("Only author can edit his post");
    }
}
