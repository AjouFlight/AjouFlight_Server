package com.mse.ajouFlight.exception;

public class AlreadyExistedFlightException extends RuntimeException {
    private static final String  message = "It's a flight you already have";
    public AlreadyExistedFlightException(){
        super(message);
    }
}
