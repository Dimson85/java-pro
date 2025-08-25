package org.example.task1.Exceptions;

public class BadTestClassError extends RuntimeException {
    public BadTestClassError(String message) {
        super(message);
    }
}
