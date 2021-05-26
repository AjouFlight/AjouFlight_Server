package com.mse.ajouFlight.service;

import com.mse.ajouFlight.controller.dto.FlightDto;
import com.mse.ajouFlight.controller.dto.UserInfoRequestDto;
import com.mse.ajouFlight.controller.dto.UserInfoResponseDto;
import com.mse.ajouFlight.domain.Flight;
import com.mse.ajouFlight.domain.User;
import com.mse.ajouFlight.domain.utils.JwtUtil;
import com.mse.ajouFlight.exception.AleadyExistedUserIdException;
import com.mse.ajouFlight.exception.InCorrectPasswordException;
import com.mse.ajouFlight.exception.NotExistedUserException;
import com.mse.ajouFlight.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    //회원가입
    public void createUser(String userId, String password){

        Optional<User> existeduser = userRepository.findByUserId(userId);

        if(existeduser.isPresent()){
            throw new AleadyExistedUserIdException();
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .userId(userId)
                .password(encodedPassword)
                .score(0)
                .stage1(false)
                .stage2(false)
                .stage3(false)
                .money(0)
                .build();

        userRepository.save(user);
    }

    //유저정보 갱신용
    public void postUserInfo(UserInfoRequestDto dto, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotExistedUserException());

        user.modifyUser(dto);

        userRepository.save(user);
    }

    //유저정보 조회
    public UserInfoResponseDto getUserInfo(String userId, String password) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new NotExistedUserException());

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw  new InCorrectPasswordException();
        }

        String token = jwtUtil.createToken(user.getId());

        List<Flight> flights = user.getFlights();
        List<FlightDto> flightsN = new ArrayList<>();

        for(Flight flight:flights){
            flightsN.add(FlightDto.builder()
                    .flightId(flight.getFlightId())
                    .build());
        }

        return  UserInfoResponseDto.builder()
                .token(token)
                .stage1(user.isStage1())
                .stage2(user.isStage2())
                .stage3(user.isStage3())
                .score(user.getScore())
                .money(user.getMoney())
                .flights(flightsN)
                .build();
    }
}
