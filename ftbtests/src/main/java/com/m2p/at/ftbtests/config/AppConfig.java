package com.m2p.at.ftbtests.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.qameta.allure.restassured.AllureRestAssured;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.Random;

/**
 * Provides commonly used beans.
 */
@Slf4j
@Configuration
public class AppConfig {
    @Bean
    public AllureRestAssured provideAllureRestAssured() {
        return new AllureRestAssured();
    }

    @Bean
    public Faker provideFaker() {
        return new Faker(Locale.ENGLISH);
    }

    @Bean
    public Random provideRandom() {
        return new Random();
    }

    @Bean(name="objectMapper")
    public ObjectMapper provideObjectMapper() {
        var om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        return om;
    }

}
