package com.saucedemo.hooks;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;

import com.microsoft.playwright.*;
import com.saucedemo.utils.TestContext;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class PlaywrightHooks {
    private final TestContext testContext;
    private Playwright playwright;
    private Browser browser;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(PlaywrightHooks.class);

    // Inyección de TestContext por Cucumber
    public PlaywrightHooks(TestContext context) {
        this.testContext = context;
    }

    @Before
    public void scenarioSetUp() {
        logger.info("Setting up Playwright for all scenarios");
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(true)
                .setSlowMo(100));
        testContext.setContext(browser.newContext());
        testContext.setPage(testContext.getContext().newPage());

        testContext.getPage().navigate("https://www.saucedemo.com/");
    }

    @After
    public void tearDownScenario(io.cucumber.java.Scenario scenario) throws Exception {
        logger.info("Tearing down Playwright after all scenarios: {}", scenario.getName());
        if (scenario.isFailed() && testContext.getPage() != null) {
            byte[] screenshot = testContext.getPage().screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
        }

        byte[] logFile = Files.readAllBytes(Paths.get("logs/test-execution.log"));
        attachLogFile(logFile);

        if (testContext.getPage() != null) {
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

    @Attachment(value = "Test Execution Log", type = "text/plain")
    public byte[] attachLogFile(byte[] logFile) {
        return logFile;
    }
}