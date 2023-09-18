package com.m2p.at.ftbtests.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Configuration // Tell Spring to use this class as a configuration source
@EnableConfigurationProperties // Take values from the property files.
@ConfigurationProperties("data") // Take only properties under the "data" entry.
@Component // Mark the class as a Spring's component.
@Data // Generate getters/setters etc on-the-fly.
public class TestDataConfig {
    private TestAppData app;
    private TestUser admin;
    private TestUser agent;

    @Data
    public static class TestAppData {
        private String host;
        private int port;
        private TestGui gui;
        private TestApi api;

        // The methods below are just some syntactic sugar to simplify data access.
        public String getUrl() {
            return format("http://%s:%s", host, port);
        }
        public String getHomePageUrl() {
            return getUrl();
        }
        public String getLoginPageUrl() {
            return getUrl() + gui.getLogin();
        }

        public String getRestApiRootUrl() {
            return getUrl() + api.getRest().getRoot();
        }
        public String getRestApiUrl() {
            return getRestApiRootUrl() + api.getRest().getVersion();
        }
    }

    @Data
    public static class TestGui {
        private String login;
    }

    @Data
    public static class TestApi {
        private TestApiRest rest;
        // Note, some other API e.g. TestApiGraphQl could be placed here.
    }

    @Data
    public static class TestApiRest {
        private String root;
        private String version;
    }

    @Data
    public static class TestUser {
        private String login;
        private String password;
    }
}
