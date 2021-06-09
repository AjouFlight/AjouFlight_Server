package com.mse.ajouFlight.controller.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mse.ajouFlight.domain.Flight;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
public class UserInfoResponseDto {
    private String token;

    private Integer stage;

    private Integer score;

    private Integer money;

    private Integer skin;

    private List<FlightDto> flights;

}
