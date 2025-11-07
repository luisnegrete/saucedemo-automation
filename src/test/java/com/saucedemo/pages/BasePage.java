package com.saucedemo.pages;

import org.slf4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import io.qameta.allure.Allure;

// Class base for future pages, currently empty but prepared for future expansion
public class BasePage {
    protected final Page page;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BasePage.class);

    // Dependency injection of Playwright Page through the constructor
    public BasePage(Page page) {
        this.page = page;
    }

    // Generic method to fill input fields, reusable in child pages
    protected void fillInput(Locator locator, String value) {
        logger.info("Filling {} field with: {}", locator, value);
        Allure.step("Filling field " + locator + " with: " + value);

        locator.fill(value);
    }

    // Generic method to click on an element, reusable in child pages
    protected void clickElement(Locator locator) {
        logger.info("Clicking on element: {}", locator);
        Allure.step("Clicking on element: " + locator);
        
        locator.click();
    }

    // Generic method to check if an element is visible, reusable in child pages
    protected boolean isElementVisible(Locator locator) {
        logger.info("Checking visibility of element: {}. Visible: {}", locator, locator.isVisible());
        Allure.step("Checking visibility of element: " + locator + ". Visible: " + locator.isVisible());

        return locator.isVisible();
    }

    // Generic method to get the text of an element, reusable in child pages
    protected String getElementText(Locator locator) {
        logger.info("Getting text of element: {}. Inner text: {}", locator, locator.innerText());
        Allure.step("Getting text of element: " + locator + ". Inner text: " + locator.innerText());

        return locator.innerText();
    }
}