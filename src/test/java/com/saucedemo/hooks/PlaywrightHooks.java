package com.saucedemo.hooks;

import java.io.ByteArrayInputStream;

import com.microsoft.playwright.*;
import com.saucedemo.utils.TestContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Allure;

public class PlaywrightHooks {
    private final TestContext testContext;
    private Playwright playwright;
    private Browser browser;

    // Inyección de TestContext por Cucumber
    public PlaywrightHooks(TestContext context) {
        this.testContext = context;
    }

    @Before
    public void scenarioSetUp() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(true)
                .setSlowMo(100));
        testContext.setContext(browser.newContext());
        testContext.setPage(testContext.getContext().newPage());

        testContext.getPage().navigate("https://www.saucedemo.com/");
    }

    @After
    public void tearDownScenario(io.cucumber.java.Scenario scenario) {
        if (scenario.isFailed() && testContext.getPage() != null) {
            byte[] screenshot = testContext.getPage().screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
        }

        if(testContext.getPage() != null) {
            testContext.getPage().close();
        }

        if (testContext.getContext() != null) {
            testContext.getContext().close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}
