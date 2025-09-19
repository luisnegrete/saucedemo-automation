package com.saucedemo.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;
    
    // Locators con data-test (más estables que xpath)
    private final String usernameInput = "[data-test='username']";
    private final String passwordInput = "[data-test='password']";
    private final String loginButton = "[data-test='login-button']";
    private final String errorMessage = "[data-test='error']";
    
    public LoginPage(Page page) {
        this.page = page;
    }
    
    public void login(String username, String password) {
        page.fill(usernameInput, username);
        page.fill(passwordInput, password);
        page.click(loginButton);
    }

    public void fillUserName(String username) {
        page.fill(usernameInput, username);
    }

    public void fillPassword(String password) {
        page.fill(passwordInput, password);
    }

    public void clickLoginButton(){
        page.click(loginButton);
    }

    public boolean validateErrorMessage(){
        return page.isVisible(errorMessage);
    }

    public String getErrorMessage(){
        return page.locator(errorMessage).innerText();
    }

    public boolean validateLoginPage(){
        return page.isVisible(usernameInput);
    }
}