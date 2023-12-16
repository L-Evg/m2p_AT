package com.m2p.at.ftbtests.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By usernameBy = By.id("spUsername");
    private final By btnLogoutBy = By.id("btnLogout");
    private final By elLoginBy = By.id("aLogin");

    // Valid for admin user only. //TODO(*): Refactor to extract separate POs: BaseHome, AdminHome, AgentHome, CustomerHome
    private final By linkAirportsBy = By.id("aAirports");
    private final By linkFlightsBy = By.id("aFlights");

    //TODO: Add the rest of the fields.

    // Valid for agent user only.
    //TODO: Add the rest of the fields.

    // Valid for customer only.
    // Valid for agent user only.

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Common section.
    public boolean isSomeoneLoggedIn() {
        return driver.findElement(usernameBy).isDisplayed();
    }

    public String getCurrentUserName() {
        return driver.findElement(usernameBy).getText();
    }

    // Admin-related section.
    public boolean isAirportsLinkVisible() {
        return driver.findElement(linkAirportsBy).isDisplayed();
    }

    public boolean isFlightsLinkVisible() {
        return driver.findElement(linkFlightsBy).isDisplayed();
    }
}
