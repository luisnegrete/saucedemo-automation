package com.saucedemo.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/*", 
        glue = { 
                "com.saucedemo.steps.ui", 
                "com.saucedemo.steps.api",
                "com.saucedemo.hooks" }, 
        plugin = {
                "pretty",
                "rerun:target/rerun.txt",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "timeline:target/cucumber-parallel-report"
}, monochrome = true)

public class RunCucumberTest {
}
