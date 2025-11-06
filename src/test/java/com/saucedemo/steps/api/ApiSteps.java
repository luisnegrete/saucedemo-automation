package com.saucedemo.steps.api;

import com.saucedemo.api.clients.APIClient;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiSteps {
    private int lastStatusCode;

    @When("I login with email {string} and password {string}")
    public void loginWithCredentials(String email, String password) {
        lastStatusCode = APIClient.login(email, password).getStatusCode();
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedCode) {
        assertThat(lastStatusCode).isEqualTo(expectedCode);
    }
}
