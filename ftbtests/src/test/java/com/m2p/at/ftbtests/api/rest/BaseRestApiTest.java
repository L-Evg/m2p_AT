package com.m2p.at.ftbtests.api.rest;

import com.m2p.at.ftbtests.BaseTest;
import com.m2p.at.ftbtests.api.rest.v0.steps.AircraftSteps;
import com.m2p.at.ftbtests.data.ModelTransformer;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.basic;
import static io.restassured.RestAssured.given;

@Slf4j
public class BaseRestApiTest extends BaseTest {
    @Autowired
    protected AllureRestAssured allureRestAssured;

    @Autowired
    protected ModelTransformer converter;

    @Autowired
    protected Faker faker;

    @Autowired
    protected Random rnd;

    @BeforeClass(alwaysRun = true, description = "Do precondition steps which are common for all tests.")
    public void doBasicSuitePreconditionSteps() {
        RestAssured.config = RestAssured.config().logConfig(LogConfig
                .logConfig()
                .enablePrettyPrinting(true)
                .enableLoggingOfRequestAndResponseIfValidationFails()
                .blacklistHeader(HttpHeaders.AUTHORIZATION));

        RestAssured.authentication = basic(testData.getAdmin().getLogin(), testData.getAdmin().getPassword());
    }

    protected RequestSpecification buildReqSpec() {
        var headers = new HashMap<>(Map.of(
                HttpHeaders.CONTENT_TYPE, "application/json",
                "X-CUSTOM", "" // Just an example of custom header usage
                ));

        return given().baseUri(testData.getApp().getRestApiUrl())
                .filter(allureRestAssured)
                .log().all()
                .headers(headers);
    }
}
