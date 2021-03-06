package com.mse.ajouFlight.controller.dto;


import com.mse.ajouFlight.domain.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoRequestDto {

    private Integer score;

    private Integer money;

    private Integer stage;

    private Integer skin;

}
