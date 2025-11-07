package com.saucedemo.steps.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.slf4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.cucumber.java.en.Then;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestContext;

public class LoginSteps {
    private final TestContext testContext;
    private final LoginPage loginPage;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginSteps.class);

    public LoginSteps(TestContext context) {
        testContext = context;
        this.loginPage = new LoginPage(testContext.getPage());
    }

    @Given("User is on login page")
    public void loadLoginPage() {
        // Ya se abre automáticamente en el Hook con la URL base
        logger.info("User is on login page");
        Allure.step("Navigated to login page at https://www.saucedemo.com/");
    }

    @Given("User is logged in with {string}, {string} credentials")
    public void logginAsCredentials(String username, String password) {
        logger.info("Logging in with username: {} and password: {}", username, password);
        Allure.step("Logged in with {} and {} credentials: " + username + "/" + password);

        this.loginPage.login(username, password);
    }

    @When("User enter the username as {string}")
    public void fillUserName(String username) {
        logger.info("Filling username field with: {}", username);
        Allure.step("Entered username: " + username);

        this.loginPage.fillUserName(username);
    }

    @When("User enter the password as {string}")
    public void fillPassword(String password) {
        logger.info("Filling password field with: {}", password);
        Allure.step("Entered password: " + password);

        this.loginPage.fillPassword(password);
    }

    @When("User click on the login button")
    public void clickLoginButton() {
        logger.info("Clicking on the login button");
        Allure.step("Clicked on the login button");

        this.loginPage.clickLoginButton();
    }

    @Then("User should see error message as {string}")
    public void validateLoginErrorMessage(String errorMessage) {
        logger.info("Validating login error message");
        Allure.step("Validated error message: " + errorMessage);

        boolean isVisible = this.loginPage.validateErrorMessage();
        assertTrue("The Error Message is not displayed correctly", isVisible);
        assertEquals(errorMessage, this.loginPage.getErrorMessage());
    }

    @Then("User should see Login page")
    public void validateLoginPage() {
        logger.info("Validating login page");
        Allure.step("Validated Login page is displayed correctly");

        boolean isVisible = this.loginPage.validateLoginPage();
        assertTrue("The Login screen was not displayed correctly", isVisible);
    }

}