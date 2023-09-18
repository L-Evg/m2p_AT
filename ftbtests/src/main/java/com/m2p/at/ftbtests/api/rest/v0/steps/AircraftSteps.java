package com.m2p.at.ftbtests.api.rest.v0.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.m2p.at.ftbtests.api.rest.RestApiCallSteps;
import com.m2p.at.ftbtests.api.rest.v0.model.AircraftDto;
import com.m2p.at.ftbtests.api.rest.v0.model.ApiResponse;
import com.m2p.at.ftbtests.api.rest.v0.model.CreateAircraftDto;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

/**
 * Contains REST API Aircrafts' related methods.
 */
//TODO(One of the best students): Look around all the steps classes you created so far.
// Is there anything which could be extracted and re-used? E.g. READ/DELETE etc calls look pretty similar. What about making them generic?
@Slf4j
@Service
public class AircraftSteps {
    private final static String PATH = "aircrafts";
    private final static String PATH_DETAILS = PATH + "/{id}";
    private final static String PATH_MODELNAME = PATH + "/model/{modelName}";

    private final RestApiCallSteps apiCalls;

    /**
     * A re-usable request specification.
     * To be set by tests, see base test class(es).
     */
    @Setter
    @Getter
    private RequestSpecification requestSpec;

    public AircraftSteps(RestApiCallSteps apiCalls) {
        this.apiCalls = apiCalls;
    }

    @Step("Get Aircraft by {path} + {pathParam}")
    public <R> R get(String path, String pathParam, int expectedStatusCode, Class<R> responseClass) {
        return apiCalls.doGet(requestSpec, expectedStatusCode, responseClass, path, pathParam);
    }

    @Step("Get Aircraft by Id={id}")
    public AircraftDto getById(long id) {
        return get(PATH_DETAILS, String.valueOf(id), SC_OK, AircraftDto.class);
    }

    @Step("Get Aircrafts by model = {modelName}")
    public List<AircraftDto> getAllByModelName(String modelName) {
        return apiCalls.doGet(requestSpec, SC_OK, new TypeRef<>() {}, PATH_MODELNAME, modelName);
    }

    @Step("Call to create an aircraft: {data}")
    public AircraftDto create(CreateAircraftDto data) throws JsonProcessingException {
        return apiCalls.doPost(requestSpec, SC_CREATED, AircraftDto.class, data, PATH);
    }

    @Step("Call to update aircraft {id}: {data}")
    public AircraftDto update(CreateAircraftDto data, long id) throws JsonProcessingException {
        return apiCalls.doPut(requestSpec, SC_CREATED, AircraftDto.class, data, PATH_DETAILS, id);
    }

    @Step("Call to partially update(patch) aircraft {id}: {patch}")
    public AircraftDto patch(JsonPatch patch, long id) throws JsonProcessingException {
        return apiCalls.doPut(requestSpec, SC_CREATED, AircraftDto.class, patch, PATH_DETAILS, id);
    }

    @Step("Call to delete aircraft {id}")
    public ApiResponse delete(long id) {
        return apiCalls.doDelete(requestSpec, SC_OK, ApiResponse.class, PATH_DETAILS, id);
    }

}
