package com.saucedemo.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "@target/rerun.txt",
    glue = {"com.saucedemo.steps", "com.saucedemo.hooks"},
    plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "timeline:target/cucumber-parallel-report"
    },
    monochrome = true
)
public class RunFailedTests {
}
