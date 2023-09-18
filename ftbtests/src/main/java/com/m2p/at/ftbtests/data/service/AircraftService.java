package com.m2p.at.ftbtests.data.service;

import com.m2p.at.ftbtests.data.model.Aircraft;
import com.m2p.at.ftbtests.data.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AircraftService extends AbstractEntityService<Aircraft> {
    private final AircraftRepository aircraftRepository;
    private final String[] sortBy = new String[]{"model"};

    @Autowired
    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    @Override
    protected JpaRepository<Aircraft, Long> getRepository() {
        return aircraftRepository;
    }

    @Override
    protected String[] getSortByProperties() {
        return sortBy;
    }

    @Override
    protected NoSuchElementException buildEntityNotFoundException(long id) {
        return buildEntityNotFoundException("Aircraft", id);
    }

    @Transactional
    public List<Aircraft> getByModel(String modelName) {
        return aircraftRepository.findByModel(modelName);
    }

    @Transactional
    public List<Aircraft> getByManufacturer(String manufacturerName) {
        return aircraftRepository.findByManufacturer(manufacturerName);
    }
}
