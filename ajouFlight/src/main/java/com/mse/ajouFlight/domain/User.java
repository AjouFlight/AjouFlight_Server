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

    @ColumnDefault("0")
    private boolean stage1;

    @ColumnDefault("0")
    private boolean stage2;

    @ColumnDefault("0")
    private boolean stage3;

    @ColumnDefault("0")
    private Integer score;

    @ColumnDefault("0")
    private Integer money;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private List<Flight> flights;



    public void modifyUser(UserInfoRequestDto dto){
        if(dto.isStage1()){
            this.stage1= dto.isStage1();
        }
        if(dto.isStage2()){
            this.stage2= dto.isStage2();
        }
        if(dto.isStage3()){
            this.stage3= dto.isStage3();
        }
        if(dto.getScore()!=null){
            this.score+=dto.getScore();
        }
        if(dto.getMoney()!=null){
            this.money+=dto.getMoney();
        }

    }
}
