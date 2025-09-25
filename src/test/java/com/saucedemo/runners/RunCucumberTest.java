package com.saucedemo.runners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.saucedemo.steps", "com.saucedemo.hooks"},
    plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "timeline:target/cucumber-parallel-report"
    },
    monochrome = true
)

public class RunCucumberTest {
    static {
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        System.setProperty("allure.results.directory", "target/allure-results-" + timestamp);
    }
}
