package com.m2p.at.ftbtests.data.repository;

import com.m2p.at.ftbtests.data.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    List<Aircraft> findByModel(String model);

    List<Aircraft> findByManufacturer(String manufacturer);
}
