package com.mse.ajouFlight.exception;

public class InCorrectUserIdException extends RuntimeException {

    private static final String  message = "존재하지 않는 계정입니다";
    public InCorrectUserIdException(){
        super(message);
    }
}
