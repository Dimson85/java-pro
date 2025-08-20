package org.example.Exceptions;

public class TestAssertionError extends RuntimeException {
    public TestAssertionError(String message) {
        super(message);
    }
}
