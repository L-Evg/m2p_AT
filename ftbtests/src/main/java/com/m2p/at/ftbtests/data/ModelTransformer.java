package com.m2p.at.ftbtests.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2p.at.ftbtests.api.rest.v0.model.AircraftDto;
import com.m2p.at.ftbtests.config.ModelMappingConfig;
import com.m2p.at.ftbtests.data.model.Aircraft;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A utility to transform entities to DTOs etc.
 * See also {@link ModelMappingConfig}
 */
@Slf4j
@Service
public class ModelTransformer {

    private final ModelMapper modelMapper;
    private final ObjectMapper mapper;

    @Autowired
    public ModelTransformer(ModelMapper modelMapper, ObjectMapper mapper) {
        this.modelMapper = modelMapper;
        this.mapper = mapper;
    }

    public AircraftDto aircraftToDto(Aircraft input) {
        return modelMapper.map(input, AircraftDto.class);
    }

    public String toJson(Object obj) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }
}
