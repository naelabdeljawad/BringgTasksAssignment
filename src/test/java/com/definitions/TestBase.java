package com.definitions;

import com.WebDriverFactory;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter.getCurrentStep;

public class TestBase {
    public WebDriver chromeDriver;

    protected TestBase() {
        createDriver();
    }

    public WebDriver createDriver() {
        try {
            getCurrentStep().log(Status.INFO, "Creating chrome driver...");
            this.chromeDriver = WebDriverFactory.createChromeDriver();
            getCurrentStep().log(Status.INFO,"Chrome driver is created!");
            return this.chromeDriver;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeDriver() {
        try {
            WebDriverFactory.closeDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
