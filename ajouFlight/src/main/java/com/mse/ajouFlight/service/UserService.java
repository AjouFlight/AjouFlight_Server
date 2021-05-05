package com.mse.ajouFlight.service;

import com.mse.ajouFlight.domain.User;
import com.mse.ajouFlight.exception.AleadyExistedUserIdException;
import com.mse.ajouFlight.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(String userId, String password){

        Optional<User> existeduser = userRepository.findByUserId(userId);

        if(existeduser.isPresent()){
            throw new AleadyExistedUserIdException();
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .userId(userId)
                .password(encodedPassword)
                .build();

        userRepository.save(user);
    }
}
