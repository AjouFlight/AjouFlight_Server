package com.mse.ajouFlight.controller;

import com.mse.ajouFlight.controller.dto.Token;
import com.mse.ajouFlight.controller.dto.UserDto;
import com.mse.ajouFlight.controller.dto.UserInfoResponseDto;
import com.mse.ajouFlight.domain.User;
import com.mse.ajouFlight.exception.InCorrectPasswordException;
import com.mse.ajouFlight.exception.NotExistedUserException;
import com.mse.ajouFlight.message.ResponseMessage;
import com.mse.ajouFlight.repository.UserRepository;
import com.mse.ajouFlight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")

public class LoginController {

    @Autowired
    private UserService userService;

    //로그인
    @PostMapping
    public ResponseEntity<ResponseMessage> login(@RequestBody UserDto dto){

        UserInfoResponseDto responseDto = userService.getUserInfo(dto.getUserId(),dto.getPassword());

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"success", responseDto));
    }

}
