package org.example.Exceptions;

public class BadTestClassError extends RuntimeException {
    public BadTestClassError(String message) {
        super(message);
    }
}
