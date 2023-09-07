package pl.coderslab.magazyn.exception;

public class PrincipalTypeMismatchException extends RuntimeException{
    public PrincipalTypeMismatchException(String message) {
        super(message);
    }

    public PrincipalTypeMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
