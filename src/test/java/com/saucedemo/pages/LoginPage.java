package com.saucedemo.pages;

import org.slf4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginPage.class);

    // Locators con data-test (más estables que xpath)
    private final Locator usernameInput = page.locator("[data-test='username']");
    private final Locator passwordInput = page.locator("[data-test='password']");
    private final Locator loginButton = page.locator("[data-test='login-button']");
    private final Locator errorMessage = page.locator("[data-test='error']");

    public LoginPage(Page page) {
        super(page);
    }

    @Step("User is logged in with {string}, {string} credentials")
    public void login(String username, String password) {
        logger.info("Logging in with username: {} and password: {}", username, password);
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();
    }

    @Step("User enters the username as {string}")
    public void fillUserName(String username) {
        logger.info("Filling username field with: {}", username);
        usernameInput.fill(username);
    }

    @Step("User enters the password as {string}")
    public void fillPassword(String password) {
        logger.info("Filling password field with: {}", password);
        passwordInput.fill(password);
    }

    @Step("User clicks on the login button")
    public void clickLoginButton() {
        logger.info("Clicking on the login button");
        loginButton.click();
    }

    @Step("User should see error message")
    public boolean validateErrorMessage() {
        logger.info("Validating error message");

        return errorMessage.isVisible();
    }

    @Step("Get error message text")
    public String getErrorMessage() {
        logger.info("Getting error message text");

        return errorMessage.innerText();
    }

    @Step("User should see Login page")
    public boolean validateLoginPage() {
        logger.info("Validating login page elements");

        return usernameInput.isVisible() && passwordInput.isVisible() && loginButton.isVisible();
    }
}