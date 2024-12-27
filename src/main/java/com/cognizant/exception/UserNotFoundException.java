package com.cognizant.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4934982837958647050L;
    public UserNotFoundException(String message) {
        super(message);
    }
}