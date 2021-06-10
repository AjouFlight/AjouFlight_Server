package com.mse.ajouFlight.exception;

public class InCorrectPasswordException extends RuntimeException{
    private static final String  message = "The password is incorrect";
    public InCorrectPasswordException(){
        super(message);
    }
}
