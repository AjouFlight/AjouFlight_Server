package com.mse.ajouFlight.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage {
    private int code;
    private String message;
    private Object data;


    public ResponseMessage(HttpStatus httpStatus, String message){
        this.code = httpStatus.value();
        this.message = message;
    }

    public ResponseMessage(HttpStatus httpStatus,Object dto){
        this.code = httpStatus.value();
        this.data = dto;
    }

    public ResponseMessage(HttpStatus httpStatus,String message,Object dto){
        this.code = httpStatus.value();
        this.message = message;
        this.data = dto;
    }


}
