package com.mse.ajouFlight.exception;

public class AlreadyExistedUserIdException extends RuntimeException {
    private static final String  message = "This is an account that already exists.";
    public AlreadyExistedUserIdException(){
        super(message);
    }
}
