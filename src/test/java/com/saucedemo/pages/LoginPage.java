package com.saucedemo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

    // Locators con data-test (más estables que xpath)
    private final Locator usernameInput = page.locator("[data-test='username']");
    private final Locator passwordInput = page.locator("[data-test='password']");
    private final Locator loginButton = page.locator("[data-test='login-button']");
    private final Locator errorMessage = page.locator("[data-test='error']");

    public LoginPage(Page page) {
        super(page);
    }

    public void login(String username, String password) {
        fillInput(usernameInput, username);
        fillInput(passwordInput, password);

        clickElement(loginButton);
    }

    public void fillUserName(String username) {
        fillInput(usernameInput, username);
    }

    public void fillPassword(String password) {
        fillInput(passwordInput, password);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

    public boolean validateErrorMessage() {
        return isElementVisible(errorMessage);
    }

    public String getErrorMessage() {
        return getElementText(errorMessage);
    }

    public boolean validateLoginPage() {
        return isElementVisible(usernameInput) && isElementVisible(passwordInput) && isElementVisible(loginButton);
    }
}