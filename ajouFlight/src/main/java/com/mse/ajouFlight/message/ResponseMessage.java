package com.mse.ajouFlight.message;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data

public class ResponseMessage {
    private int code;
    private String message;

    public ResponseMessage(HttpStatus httpStatus, String message){
        this.code = httpStatus.value();
        this.message = message;
    }
}
