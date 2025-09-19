package com.saucedemo.pages;

import com.microsoft.playwright.Page;

public class InventoryPage {
    private Page page;
    
    // Locators con data-test (más estables que xpath)
    private final String header = "[data-test='header-container']";
    private final String sideMenu = ".bm-burger-button";
    private final String logoutOption = "[data-test='logout-sidebar-link']";
    
    public InventoryPage(Page page) {
        this.page = page;
    }
    
    public boolean validateHeader(){
        return page.isVisible(header);
    }

    public void clickSideMenu(){
        page.click(sideMenu);
    }

    public void clickLogoutButton(){
        page.click(logoutOption);
    }
}
