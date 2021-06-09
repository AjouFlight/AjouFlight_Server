package com.mse.ajouFlight.service;

import com.mse.ajouFlight.controller.dto.FlightDto;
import com.mse.ajouFlight.controller.dto.RankingResponseDto;
import com.mse.ajouFlight.controller.dto.UserInfoRequestDto;
import com.mse.ajouFlight.controller.dto.UserInfoResponseDto;
import com.mse.ajouFlight.domain.Flight;
import com.mse.ajouFlight.domain.User;
import com.mse.ajouFlight.domain.utils.JwtUtil;
import com.mse.ajouFlight.exception.AlreadyExistedFlightException;
import com.mse.ajouFlight.exception.AlreadyExistedUserIdException;
import com.mse.ajouFlight.exception.InCorrectPasswordException;
import com.mse.ajouFlight.exception.NotExistedUserException;
import com.mse.ajouFlight.repository.FlightRepository;
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
    private FlightRepository flightRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


    //회원가입
    public void createUser(String userId, String password){

        Optional<User> existeduser = userRepository.findByUserId(userId);

        if(existeduser.isPresent()){
            throw new AlreadyExistedUserIdException();
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .userId(userId)
                .password(encodedPassword)
                .score(0)
                .stage(1)
                .money(0)
                .skin(-1)
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
                .stage(user.getStage())
                .score(user.getScore())
                .money(user.getMoney())
                .skin(user.getSkin())
                .flights(flightsN)
                .build();
    }

    //랭킹 조회
    public RankingResponseDto getRanking(Long id){
        List<User> users =  userRepository.findByRanking();
        List<User> totalUsers = userRepository.findAll();

        List<UserInfoResponseDto> topTenUsers = new ArrayList<>();
        int myLanking=0;
        for(int i=0; i<users.size(); i++){
            if(i<10){
                topTenUsers.add(UserInfoResponseDto.builder()
                        .userId(users.get(i).getUserId())
                        .score(users.get(i).getScore())
                        .build());
            }
            if(users.get(i).getId().equals(id)){
                myLanking = i+1;
                if(i>=10){
                    break;
                }
            }

        }

        return RankingResponseDto.builder()
                .totalNum(totalUsers.size())
                .top10(topTenUsers)
                .myLanking(myLanking)
                .build();
    }

    //비행기 사기
    public void postFlights(Long id, Long flightId, Integer money) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotExistedUserException());

        if(user.getMoney()-money<0){
            throw new RuntimeException();
        }
        user.setMoney(user.getMoney()-money);

        for(Flight flight: user.getFlights()){
            if(flight.getFlightId() == flightId){
                throw new AlreadyExistedFlightException();
            }
        }

        Flight flight = Flight.builder()
                .user(user)
                .flightId(flightId)
                .build();

        flightRepository.save(flight);
    }
}
