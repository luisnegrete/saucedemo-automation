package com.saucedemo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InventoryPage extends BasePage{
    
    // Locators con data-test (más estables que xpath)
    private final Locator header = page.locator("[data-test='header-container']");
    private final Locator sideMenu = page.locator(".bm-burger-button");
    private final Locator logoutOption = page.locator("[data-test='logout-sidebar-link']");

    public InventoryPage(Page page) {
        super(page);
    }
    
    public boolean validateHeader(){
        return header.isVisible();
    }

    public void clickSideMenu(){
        sideMenu.click();
    }

    public void clickLogoutButton(){
        logoutOption.click();
    }
}
