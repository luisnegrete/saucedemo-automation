package com.saucedemo.steps.ui;

import org.slf4j.Logger;

import com.microsoft.playwright.Page;
import com.saucedemo.utils.TestContext;

// Generates the corresponding comments in English
public class BaseUISteps {
    // Context for the test
    protected final TestContext testContext;

    // Playwright page instance
    protected final Page page;

    // Logger for logging information
    protected static final Logger logger = org.slf4j.LoggerFactory.getLogger(BaseUISteps.class);

    // Constructor to initialize the test context and page
    public BaseUISteps(TestContext context) {
        testContext = context;
        this.page = testContext.getPage();
    }
}
