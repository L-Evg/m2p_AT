package com.m2p.at.ftbtests.data.repository;

import com.m2p.at.ftbtests.data.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByAirportCode(String airportCode);
}
