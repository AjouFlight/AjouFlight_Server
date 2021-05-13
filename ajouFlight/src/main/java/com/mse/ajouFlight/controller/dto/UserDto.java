package com.mse.ajouFlight.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDto {

    @NotBlank(message = "ID는 필수값")
    private String userId;

    @NotBlank(message = "pw는 필수값")
    @Min(4)
    private String password;

}
