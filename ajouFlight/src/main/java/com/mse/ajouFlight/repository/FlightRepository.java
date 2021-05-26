package com.mse.ajouFlight.repository;

import com.mse.ajouFlight.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {

}
