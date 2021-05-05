package com.mse.ajouFlight.controller;

import com.mse.ajouFlight.controller.dto.UserDto;
import com.mse.ajouFlight.message.ResponseMessage;
import com.mse.ajouFlight.repository.UserRepository;
import com.mse.ajouFlight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping(value = "/register")
@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> createUser(@RequestBody UserDto dto) throws URISyntaxException{
        String userId = dto.getUserId();
        String password = dto.getPassword();

        userService.createUser(userId, password);

        return ResponseEntity.created(new URI("/register")).body(new ResponseMessage(HttpStatus.CREATED, "회원가입완료"));
    }
}
