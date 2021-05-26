package com.mse.ajouFlight.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RankingResponseDto {

    private String userId;

    private Integer score;
}
