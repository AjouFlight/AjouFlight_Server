package com.mse.ajouFlight.controller;

import com.mse.ajouFlight.controller.dto.*;
import com.mse.ajouFlight.message.ResponseMessage;
import com.mse.ajouFlight.repository.UserRepository;
import com.mse.ajouFlight.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping(value = "/user")
@RestController

public class UserController {

    @Autowired
    private UserService userService;

    //회원가입
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> createUser(@RequestBody @Valid UserDto dto) throws URISyntaxException{
        String userId = dto.getUserId();
        String password = dto.getPassword();

        userService.createUser(userId, password);

        return ResponseEntity.created(new URI("/register")).body(new ResponseMessage(HttpStatus.CREATED, "success"));
    }

    //사용자정보갱신
    @PutMapping
    public ResponseEntity<ResponseMessage> postUserInfo(Authentication authentication, @RequestBody UserInfoRequestDto dto){
        Claims claims = (Claims) authentication.getPrincipal();
        Long id = claims.get("id",Long.class);

        userService.postUserInfo(dto,id);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK, "success"));
    }

    //랭킹조회
    @GetMapping("/ranking")
    public ResponseEntity<ResponseMessage> getRanking(Authentication authentication){
        Claims claims = (Claims) authentication.getPrincipal();
        Long id = claims.get("id",Long.class);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"success",userService.getRanking(id)));
    }

    //비행기 추가
    @PostMapping("/flight")
    public ResponseEntity<ResponseMessage> postFlights(Authentication authentication, @RequestBody FlightDto dto){
        Claims claims = (Claims) authentication.getPrincipal();
        Long id = claims.get("id",Long.class);

        userService.postFlights(id,dto.getFlightId(),dto.getMoney());

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.CREATED,"success"));
    }
}
