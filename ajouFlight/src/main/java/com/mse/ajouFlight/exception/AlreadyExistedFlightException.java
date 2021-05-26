package com.mse.ajouFlight.exception;

public class AlreadyExistedFlightException extends RuntimeException {
    private static final String  message = "이미 가진 비행기 입니다";
    public AlreadyExistedFlightException(){
        super(message);
    }
}
