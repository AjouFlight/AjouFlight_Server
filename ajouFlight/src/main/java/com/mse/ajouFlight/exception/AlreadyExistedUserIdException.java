package com.mse.ajouFlight.exception;

public class AlreadyExistedUserIdException extends RuntimeException {
    private static final String  message = "이미 존재하는 계정입니다";
    public AlreadyExistedUserIdException(){
        super(message);
    }
}
