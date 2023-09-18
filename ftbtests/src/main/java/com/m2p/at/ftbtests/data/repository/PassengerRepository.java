package com.m2p.at.ftbtests.data.repository;

import com.m2p.at.ftbtests.data.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    List<Passenger> findAllByPassportNumber(String number);
}
