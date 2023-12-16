package com.m2p.at.ftbtests.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2p.at.ftbtests.api.rest.v0.model.AircraftDto;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * A wrapper for RestAssured, provides API call specific re-usable methods.
 */
@Slf4j
@Service
public class RestApiCallSteps {

    private final ObjectMapper objectMapper;

    @Autowired
    public RestApiCallSteps(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Does GET REST API call .
     *
     * @param path          Resource path like /address/byId
     * @param params        Query params string. A proper, more technical way would be to use a Map + ResAssured's .queryParams but
     *                      this way seems more human-readable for now.
     * @param responseClass Deserialization target.
     * @return Deserialized response.
     */
    @Step("Send HTTP-GET. /{path} {params}")
    @Attachment("HTTP-GET's response.")
    public <R> R doGet(RequestSpecification reqSpec, String path, String params,
                       int expectedStatus, Class<R> responseClass) {
        log.info("Sending GET for: {}{}. Expected: {}", path, params, expectedStatus);
        return given()
                .spec(reqSpec)
                .get(path + params)
                .then().log().all()
                .and()
                .statusCode(expectedStatus)
                .extract().response().as(responseClass);
    }

    @Step("Send HTTP-GET. /{path} ? {queryParamMap}")
    @Attachment("HTTP-GET's response.")
    public <R> R doGet(RequestSpecification reqSpec, String path, Map<String, ?> queryParamMap,
                       int expectedStatus, Class<R> responseClass) {
        log.info("Sending GET for: {} QueryParams -> {}. Expected: {}", path, queryParamMap, expectedStatus);
        return given()
                .spec(reqSpec)
                .queryParams(queryParamMap)
                .get(path)
                .then().log().all()
                .and()
                .statusCode(expectedStatus)
                .extract().response().as(responseClass);
    }

    @Step("Send HTTP-GET /{path} {pathParams}.")
    @Attachment("HTTP-GET's response.")
    public <R> R doGet(RequestSpecification reqSpec, int expectedStatus, Class<R> responseClass, String path, Object... pathParams) {
        log.info("Sending GET for: {}({}) ", path, Arrays.toString(pathParams));
        return given()
                .spec(reqSpec)
                .get(path, pathParams)
                .then().log().all()
                .and()
                .statusCode(expectedStatus)
                .extract().response().as(responseClass);
    }

    @Step("Send HTTP-GET /{path} {pathParams}.")
    @Attachment("HTTP-GET's response.")
    public <R> R doGet(RequestSpecification reqSpec, int expectedStatus,
                       TypeRef<R> responseTypeRef,
                       String path, Object... pathParams) {
        log.info("Sending GET for: {}({}) ", path, Arrays.toString(pathParams));
        return given()
                .spec(reqSpec)
                .get(path, pathParams)
                .then().log().all()
                .and()
                .statusCode(expectedStatus)
                .extract().response().as(responseTypeRef);
    }

    @Step("Send HTTP-GET. /{path} ? {queryParamMap}")
    @Attachment("HTTP-GET's response.")
    public String doGet(RequestSpecification reqSpec, String path, Map<String, ?> queryParamMap, int expectedStatus) {
        log.info("Sending GET for: {} QueryParams -> {} ", path, queryParamMap);
        return given()
                .spec(reqSpec)
                .queryParams(queryParamMap)
                .get(path)
                .then().log().all()
                .and()
                .statusCode(expectedStatus)
                .extract().response().asString();
    }

    @Step("Send HTTP-GET. /{path} {pathParams} ? {queryParamMap}")
    @Attachment("HTTP-GET's response.")
    public <R> R doGet(RequestSpecification reqSpec, String path, Map<String, ?> queryParamMap,
                       int expectedStatus, Class<R> responseClass, Object... pathParams) {
        log.info("Sending GET for: {} PathParams -> {} QueryParams -> {} ", path, pathParams, queryParamMap);
        return given()
                .spec(reqSpec)
                .queryParams(queryParamMap)
                .get(path, pathParams)
                .then().log().all()
                .and()
                .statusCode(expectedStatus)
                .extract().response().as(responseClass);
    }

    @Step("Send HTTP-POST. /{path} {pathParams}. Expecting {expectedStatus}.")
    @Attachment("HTTP-POST's response.")
    public <R> R doPost(RequestSpecification reqSpec, int expectedStatus, Class<R> responseClass,
                        String body, String path, Object... pathParams) {
        log.info("Sending POST for: {} {} | Body = {}", path, Arrays.toString(pathParams), body);
        return given()
                .spec(reqSpec)
                .body(body)
                .post(path, pathParams)
                .then().log().all()
                .and()
                .statusCode(expectedStatus)
                .extract().response().as(responseClass);
    }

    public <R> R doPost(RequestSpecification reqSpec, int expectedStatus, Class<R> responseClass,
                        Object body, String path, Object... pathParams) throws JsonProcessingException {
        return doPost(reqSpec, expectedStatus, responseClass,
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body),
                path, pathParams);
    }

    @Step("Send HTTP-POST, ignore response status. /{path} {pathParams}")
    @Attachment("HTTP-POST's response.")
    public <R> R doPostIgnoreStatus(RequestSpecification reqSpec, Class<R> responseClass,
                                    Object body, String path, Object... pathParams) throws JsonProcessingException {
        log.info("Sending POST for: {} {} | Body={}", path, Arrays.toString(pathParams), body);
        return given()
                .spec(reqSpec)
                .body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body))
                .post(path, pathParams)
                .then().log().all()
                .and()
                .extract().response().as(responseClass);
    }

    @Step("Send HTTP-POST, ignore response status. /{path} {pathParams}")
    @Attachment("HTTP-POST's response.")
    public String doPostIgnoreStatus(RequestSpecification reqSpec,
                                     Object body, String path, Object... pathParams) throws JsonProcessingException {
        log.info("Sending POST for: {} {} | Body={}", path, Arrays.toString(pathParams), body);
        return given()
                .spec(reqSpec)
                .body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body))
                .post(path, pathParams)
                .then().log().all()
                .and()
                .extract().response().asString();
    }

    public <R> R doPut(RequestSpecification reqSpec, int expectedStatus, Class<R> responseClass,
                        Object body, String path, Object... pathParams) throws JsonProcessingException {
        log.info("Sending PUT for: {} {} | Body = {}", path, Arrays.toString(pathParams), body);
        return given()
                .spec(reqSpec)
                .body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body))
                .put(path, pathParams)
                .then().log().all()
                .and()
                .statusCode(expectedStatus)
                .extract().response().as(responseClass);
    }


    public <R> R doPatch(RequestSpecification reqSpec, int expectedStatus, Class<R> responseClass,
                        Object body, String path, Object... pathParams) throws JsonProcessingException {
        log.info("Sending PATCH for: {} {} | Body = {}", path, Arrays.toString(pathParams), body);
        return given()
                .spec(reqSpec)
                .body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body))
                .patch(path, pathParams)
                .then().log().all()
                .and()
                .statusCode(expectedStatus)
                .extract().response().as(responseClass);
    }

    @Step("Send HTTP-DELETE /{path} {pathParams}.")
    @Attachment("HTTP-DELETE's response.")
    public <R> R doDelete(RequestSpecification reqSpec, int expectedStatus, Class<R> responseClass, String path, Object... pathParams) {
        log.info("Sending DELETE for: {} {} ", path, Arrays.toString(pathParams));
        return given()
                .spec(reqSpec)
                .delete(path, pathParams)
                .then().log().all()
                .and()
                .statusCode(expectedStatus)
                .extract().response().as(responseClass);
    }
}
