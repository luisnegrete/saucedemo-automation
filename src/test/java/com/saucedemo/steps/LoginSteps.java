package com.saucedemo.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.hooks.PlaywrightHooks;

public class LoginSteps {
    private LoginPage loginPage;


    @Given("User is on login page")
    public void loadLoginPage() {
        // Ya se abre automáticamente en el Hook con la URL base
        this.loginPage = new LoginPage(PlaywrightHooks.getPage());
    }

    @Given("User is logged in with {string}, {string} credentials")
    public void logginAsCredentials(String username, String password) {
        this.loginPage = new LoginPage(PlaywrightHooks.getPage());
        this.loginPage.login(username, password);
    }

    @When("User enter the username as {string}")
    public void fillUserName(String username) {
        this.loginPage = new LoginPage(PlaywrightHooks.getPage());
        this.loginPage.fillUserName(username);
    }

    @When("User enter the password as {string}")
    public void fillPassword(String password) {
        this.loginPage = new LoginPage(PlaywrightHooks.getPage());
        this.loginPage.fillPassword(password);
    }
    
    @When("User click on the login button")
    public void clickLoginButton() {
        this.loginPage = new LoginPage(PlaywrightHooks.getPage());
        this.loginPage.clickLoginButton();
    }

    @Then("User should see error message as {string}")
    public void validateLoginErrorMessage(String errorMessage) {
        boolean isVisible = this.loginPage.validateErrorMessage();
        assertTrue("The Error Message is not displayed correctly", isVisible);
        assertEquals(errorMessage, this.loginPage.getErrorMessage());
    }

    @Then("User should see Login page")
    public void validateLoginPage() {
        boolean isVisible = this.loginPage.validateLoginPage();
        assertTrue("The Login screen was not displayed correctly", isVisible);
    }
    
}