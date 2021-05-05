package com.mse.ajouFlight.controller;

import com.mse.ajouFlight.controller.dto.Token;
import com.mse.ajouFlight.controller.dto.UserDto;
import com.mse.ajouFlight.domain.User;
import com.mse.ajouFlight.exception.InCorrectPasswordException;
import com.mse.ajouFlight.exception.InCorrectUserIdException;
import com.mse.ajouFlight.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")

public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //로그인
    @PostMapping
    public ResponseEntity<Token> login(@RequestBody UserDto dto){
        User user = userRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new InCorrectUserIdException());

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw  new InCorrectPasswordException();
        }

        //일치하면 user 고유 id 내려준다.(token 역할)
        Token token = Token.builder()
                .token(user.getId())
                .message("토큰이 발급되었습니다")
                .build();

        return ResponseEntity.ok(token);
    }
}
