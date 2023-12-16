package com.m2p.at.ftbtests.gui.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Slf4j
public class LoginPage extends BasePage {
    private final By usernameBy = By.id("txtUsername");
    private final By passwordBy = By.id("txtPassword");
    private final By submitBy = By.id("btnSubmit");
    private final By cancelBy = By.xpath("//div/a[@href='/' and contains(@class, 'btn')]");
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Login as a valid user.
     *
     * @return HomePage instance.
     */
    @Step //TODO: Insecure! The password appears on the Allure report, need to hide somehow.
    public HomePage login(String userName, String password) {
        log.info("Attempt to login in as {}", userName);
        driver.findElement(usernameBy).sendKeys(userName);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(submitBy).click();

        return new HomePage(driver);
    }
}
