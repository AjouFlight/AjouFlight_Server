package com.mse.ajouFlight.controller.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder

public class Token {

    private Long token;
    private String message;
}
