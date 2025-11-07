package com.saucedemo.steps.api;

import com.saucedemo.api.clients.APIClient;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

import org.slf4j.Logger;

public class ApiSteps {
    private int lastStatusCode;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ApiSteps.class);

    @When("I login with email {string} and password {string}")
    public void loginWithCredentials(String email, String password) {
        logger.info("Attempting to login with email: {} and password: {}", email, password);
        Allure.step("Attempting login with email: " + email);

        lastStatusCode = APIClient.login(email, password).getStatusCode();
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedCode) {
        logger.info("Verifying that the last status code {} equals expected code {}", lastStatusCode, expectedCode);
        Allure.step("Verifying that the last status code " + lastStatusCode + " equals expected code " + expectedCode);

        assertThat(lastStatusCode).isEqualTo(expectedCode);
    }
}
