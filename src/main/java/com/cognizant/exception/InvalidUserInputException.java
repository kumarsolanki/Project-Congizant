package com.cognizant.exception;


public class InvalidUserInputException extends RuntimeException {

    private static final long serialVersionUID = 5707152456996652100L;

    public InvalidUserInputException(String message) {
        super(message);
    }

}



