package com.mse.ajouFlight.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RankingResponseDto {

    private List<UserInfoResponseDto> top10;
    private Integer totalNum;
    private Integer myRanking;
}
