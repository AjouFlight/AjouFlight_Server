package com.mse.ajouFlight.exception.message;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    int code;
    String message;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.code =  httpStatus.value();
        this.message = message;
    }
}
