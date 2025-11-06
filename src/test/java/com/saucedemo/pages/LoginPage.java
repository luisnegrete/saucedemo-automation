package com.saucedemo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

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
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();
    }

    public void fillUserName(String username) {
        usernameInput.fill(username);
    }

    public void fillPassword(String password) {
        passwordInput.fill(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public boolean validateErrorMessage(){
        return errorMessage.isVisible();
    }

    public String getErrorMessage(){
        return errorMessage.innerText();
    }

    public boolean validateLoginPage(){
        return usernameInput.isVisible() && passwordInput.isVisible() && loginButton.isVisible();
    }
}