package org.example.task1.Exceptions;

public class TestAssertionError extends RuntimeException {
    public TestAssertionError(String message) {
        super(message);
    }
}
