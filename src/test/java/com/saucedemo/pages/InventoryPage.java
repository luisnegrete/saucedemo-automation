package com.saucedemo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InventoryPage extends BasePage {
    // Locators with data-test (more stable than xpath)
    private final Locator header = page.locator("[data-test='header-container']");
    private final Locator sideMenu = page.locator(".bm-burger-button");
    private final Locator logoutOption = page.locator("[data-test='logout-sidebar-link']");

    public InventoryPage(Page page) {
        super(page);
    }

    // Method to validate the header is visible
    public boolean validateHeader() {
        return isElementVisible(header);
    }

    // Method to click on the side menu
    public void clickSideMenu() {
        clickElement(sideMenu);
    }

    // Method to click on the logout button
    public void clickLogoutButton() {
        clickElement(logoutOption);
    }
}
