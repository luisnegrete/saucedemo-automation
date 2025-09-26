package com.saucedemo.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "${cucumber.features}", glue = { "com.saucedemo.steps", "com.saucedemo.hooks" }, plugin = {
        "pretty",
        "rerun:target/rerun.txt",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "timeline:target/cucumber-parallel-report"
}, monochrome = true)

public class RunCucumberTest {
}
