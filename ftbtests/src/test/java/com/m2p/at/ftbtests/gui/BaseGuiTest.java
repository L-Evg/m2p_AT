package com.m2p.at.ftbtests.gui;

import com.m2p.at.ftbtests.BaseTest;
import com.m2p.at.ftbtests.config.TestDataConfig;
import com.m2p.at.ftbtests.gui.steps.NavigationSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * A bae class for all GUI/Selenium-based tests.
 */
@Slf4j
public class BaseGuiTest extends BaseTest {
    @Autowired
    protected NavigationSteps nav;

    private WebDriver driver; // [WARNING] Potential issues in case of parallel execution.

    @BeforeClass(alwaysRun = true)
    public void setupClass() {
        log.info("Setting up the class.");
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod(alwaysRun = true)
    public void setupTest() {
        log.info("Setting up the test.");
        // Launch the browser before each test.
        driver = new FirefoxDriver();
        // driver = new ChromeDriver(); // Could be ChromeDriver(); if you like.
        // Browser may need additional configuration. You could do that here.
        nav.setDriver(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void teardownTest() {
        // Close the browser after each test.
        driver.quit();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
