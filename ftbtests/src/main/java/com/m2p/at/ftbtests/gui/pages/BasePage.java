package com.m2p.at.ftbtests.gui.pages;

import org.openqa.selenium.WebDriver;

/**
 * A base for all page objects.
 */
public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
