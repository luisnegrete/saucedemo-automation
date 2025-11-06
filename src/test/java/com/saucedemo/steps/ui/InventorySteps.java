package com.saucedemo.steps.ui;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.utils.TestContext;

public class InventorySteps {
    private final TestContext testContext;
    private InventoryPage inventoryPage;

    public InventorySteps(TestContext context) {
        testContext = context;
        this.inventoryPage = new InventoryPage(testContext.getPage());
    }

    @Then("User should see inventory page")
    public void validateInventoryPage() {
        boolean isVisible = this.inventoryPage.validateHeader();
        assertTrue("The Inventary page is not displayed correctly", isVisible);
    }

    @When("User click on side menu")
    public void clickSideMenu() {
        this.inventoryPage.clickSideMenu();
    }

    @When("User click on Logout option")
    public void clickLogoutButton() {
        this.inventoryPage.clickLogoutButton();
    }
    
}
