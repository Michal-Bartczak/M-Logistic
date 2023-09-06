package pl.coderslab.magazyn.exception;

public class UnknownUserTypeException extends RuntimeException{
    public UnknownUserTypeException(String message) {
        super(message);
    }
}
