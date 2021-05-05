package com.mse.ajouFlight.repository;

import com.mse.ajouFlight.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userID); //유저 아이디 있는지 확인하는 쿼리문

}
