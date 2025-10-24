package com.saucedemo.steps.ui;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.saucedemo.hooks.PlaywrightHooks;
import com.saucedemo.pages.InventoryPage;

public class InventorySteps {
    private InventoryPage inventoryPage;


    @Then("User should see inventory page")
    public void validateInventoryPage() {
        this.inventoryPage = new InventoryPage(PlaywrightHooks.getPage());
        
        boolean isVisible = this.inventoryPage.validateHeader();
        assertTrue("The Inventary page is not displayed correctly", isVisible);
    }

    @When("User click on side menu")
    public void clickSideMenu() {
        this.inventoryPage = new InventoryPage(PlaywrightHooks.getPage());
        this.inventoryPage.clickSideMenu();
    }

    @When("User click on Logout option")
    public void clickLogoutButton() {
        this.inventoryPage = new InventoryPage(PlaywrightHooks.getPage());
        this.inventoryPage.clickLogoutButton();
    }
    
}
