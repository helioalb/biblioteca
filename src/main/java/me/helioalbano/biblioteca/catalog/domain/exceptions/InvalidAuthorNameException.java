package me.helioalbano.biblioteca.catalog.domain.exceptions;

public class InvalidAuthorNameException extends RuntimeException {
    public InvalidAuthorNameException(String message) {
        super(message);
    }
}
