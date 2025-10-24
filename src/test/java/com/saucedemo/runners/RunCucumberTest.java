package com.saucedemo.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.saucedemo.steps, com.saucedemo.hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, rerun:target/rerun.txt, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm, timeline:target/cucumber-parallel-report")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "${cucumber.filter.tags}")
public class RunCucumberTest {
}
