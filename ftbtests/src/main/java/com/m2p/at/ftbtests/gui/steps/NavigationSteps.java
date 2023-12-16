package com.m2p.at.ftbtests.gui.steps;

import com.m2p.at.ftbtests.config.TestDataConfig;
import com.m2p.at.ftbtests.gui.pages.HomePage;
import com.m2p.at.ftbtests.gui.pages.LoginPage;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

/**
 * Contains navigation related steps.
 */
@Slf4j
@Service
public class NavigationSteps {
    private final TestDataConfig testData;

    /**
     * A re-sable WebDriver.
     * Note, basically it could be a Spring bean but that might be hard for underwstanding by newcomers.
     * To be set by tests, see base test class(es).
     */
    @Setter
    @Getter
    private WebDriver driver;

    public NavigationSteps(TestDataConfig testData) {
        this.testData = testData;
    }

    @Step
    public HomePage openHome() {
        driver.get(testData.getApp().getHomePageUrl());

        return new HomePage(driver);
    }

    @Step
    public LoginPage openLogin() {
        log.info("Attempt to open the login page.");
        driver.get(testData.getApp().getLoginPageUrl());

        return new LoginPage(driver);
    }
}
