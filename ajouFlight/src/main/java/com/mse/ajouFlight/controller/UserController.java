package com.mse.ajouFlight.controller;

import com.mse.ajouFlight.controller.dto.Token;
import com.mse.ajouFlight.controller.dto.UserDto;
import com.mse.ajouFlight.controller.dto.UserInfoRequestDto;
import com.mse.ajouFlight.controller.dto.UserInfoResponseDto;
import com.mse.ajouFlight.message.ResponseMessage;
import com.mse.ajouFlight.repository.UserRepository;
import com.mse.ajouFlight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping(value = "/user")
@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    //회원가입
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> createUser(@RequestBody @Valid UserDto dto) throws URISyntaxException{
        String userId = dto.getUserId();
        String password = dto.getPassword();

        userService.createUser(userId, password);

        return ResponseEntity.created(new URI("/register")).body(new ResponseMessage(HttpStatus.CREATED, "회원가입완료"));
    }

    //사용자정보갱신
    @PutMapping("/{token}")
    public ResponseEntity<ResponseMessage> postUserInfo(@RequestBody UserInfoRequestDto dto,@PathVariable Long token){

        userService.postUserInfo(dto,token);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK, "유저 정보가 갱신되었습니다."));
    }


}
