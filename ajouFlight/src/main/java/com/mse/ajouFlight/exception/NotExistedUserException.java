package com.mse.ajouFlight.exception;

public class NotExistedUserException extends RuntimeException {

    private static final String  message = "This account does not exist";
    public NotExistedUserException(){
        super(message);
    }
}
