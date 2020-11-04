package com.launcher;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/features/BringgTestScenario.feature", plugin = {"pretty",
        "html:target/cucumber/BringgTestScenario.html",
        "rerun:target/cucumber/BringgTestScenario.txt",
        "json:target/cucumber/BringgTestScenario.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue = {"com.definitions"})
public class BringgTestScenarioLauncher extends AbstractTestNGCucumberTests {
}
