package com.saucedemo.steps.ui;

import static org.junit.Assert.assertTrue;

import org.slf4j.Logger;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;

import com.saucedemo.pages.InventoryPage;
import com.saucedemo.utils.TestContext;

public class InventorySteps {
    private final TestContext testContext;
    private InventoryPage inventoryPage;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(InventorySteps.class);

    public InventorySteps(TestContext context) {
        testContext = context;
        this.inventoryPage = new InventoryPage(testContext.getPage());
    }

    @Then("User should see inventory page")
    public void validateInventoryPage() {
        logger.info("Validating inventory page");
        Allure.step("Validated Inventory page is displayed correctly");

        boolean isVisible = this.inventoryPage.validateHeader();
        assertTrue("The Inventory page is not displayed correctly", isVisible);
    }

    @When("User click on side menu")
    public void clickSideMenu() {
        logger.info("Clicking on side menu");
        Allure.step("Clicked on side menu");

        this.inventoryPage.clickSideMenu();
    }

    @When("User click on Logout option")
    public void clickLogoutButton() {
        logger.info("Clicking on Logout option");
        Allure.step("Clicked on Logout option");

        this.inventoryPage.clickLogoutButton();
    }

}
