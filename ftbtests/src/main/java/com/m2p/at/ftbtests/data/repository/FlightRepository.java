package com.m2p.at.ftbtests.data.repository;

import com.m2p.at.ftbtests.data.model.Airport;
import com.m2p.at.ftbtests.data.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAllByDepartureAirportEqualsAndDestinationAirportEqualsAndDepartureDateEquals(Airport depAirport, Airport destAirport, LocalDate depDate);
    List<Flight> findAllByDepartureAirportEqualsAndDestinationAirportEquals(Airport depAirport, Airport destAirport);

    List<Flight> findByFlightNumber(String flightNumber);
}
