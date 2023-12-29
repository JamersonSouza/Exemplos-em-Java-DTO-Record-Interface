package tech.jamersondev.records.exceptions;

public class NotFoundPersonException extends RuntimeException{

    public NotFoundPersonException(String message) {
        super(message);
    }

    public NotFoundPersonException(String message, Throwable cause) {
        super(message, cause);
    }
}
