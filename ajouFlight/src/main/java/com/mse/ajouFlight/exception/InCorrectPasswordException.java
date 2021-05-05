package com.mse.ajouFlight.exception;

public class InCorrectPasswordException extends RuntimeException{
    private static final String  message = "비밀번호가 틀렸습니다";
    public InCorrectPasswordException(){
        super(message);
    }
}
