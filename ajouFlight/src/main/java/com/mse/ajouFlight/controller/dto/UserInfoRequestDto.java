package com.mse.ajouFlight.controller.dto;


import com.mse.ajouFlight.domain.Flight;
import lombok.Data;

import java.util.List;

@Data
public class UserInfoDto {

    private Integer score;

    private Double money;

    private boolean stage1;

    private boolean stage2;

    private boolean stage3;

    private List<Flight>flights;

}
