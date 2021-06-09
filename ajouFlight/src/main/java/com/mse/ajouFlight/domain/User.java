package com.mse.ajouFlight.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mse.ajouFlight.controller.dto.UserInfoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Data // lombok
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;


    private String password;

    @ColumnDefault("1")
    private Integer stage;

    @ColumnDefault("0")
    private Integer score;

    @ColumnDefault("0")
    private Integer money;

    @ColumnDefault("0")
    private Integer skin;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private List<Flight> flights;

    public void modifyUser(UserInfoRequestDto dto){
        if(dto.getStage()!=null) {
            this.stage = dto.getStage();
        }
        if(dto.getScore()!=null){
            this.score+=dto.getScore();
        }
        if(dto.getMoney()!=null){
            this.money+=dto.getMoney();
        }
        if(dto.getSkin()!=null){
            this.skin = dto.getSkin();
        }
    }
}
