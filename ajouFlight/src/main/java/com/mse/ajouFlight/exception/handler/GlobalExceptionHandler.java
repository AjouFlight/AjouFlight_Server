package com.mse.ajouFlight.exception.handler;

import com.mse.ajouFlight.exception.AleadyExistedUserIdException;
import com.mse.ajouFlight.exception.InCorrectPasswordException;
import com.mse.ajouFlight.exception.NotExistedUserException;
import com.mse.ajouFlight.exception.message.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AleadyExistedUserIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleAlreadyExistedUserIdException(AleadyExistedUserIdException ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(NotExistedUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotExistedUserExceptionException(NotExistedUserException ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(InCorrectPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInCorrectPasswordException(InCorrectPasswordException ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST,ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleRuntimeException(RuntimeException ex){
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,"error가 발생하였습니다");
    }

}
