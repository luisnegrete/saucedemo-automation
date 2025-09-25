package com.saucedemo.hooks;

import java.io.ByteArrayInputStream;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Allure;

public class PlaywrightHooks {
    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();

    @Before
    public void scenarioSetUp() {
        playwright.set(Playwright.create());
        browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(100)));
        
        context.set(browser.get().newContext());
        page.set(context.get().newPage());

        page.get().navigate("https://www.saucedemo.com/");
    }

    @After
    public void tearDownScenario(io.cucumber.java.Scenario scenario) {
        if (scenario.isFailed() && page.get() != null) {
            byte[] screenshot = page.get().screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
        }
        if (context.get() != null) {
            context.get().close();
            context.remove();
        }
        if (browser.get() != null) {
            browser.get().close();
            browser.remove();
        }
        if (playwright.get() != null) {
            playwright.get().close();
            playwright.remove();
        }
        page.remove();
    }

    // :apuntando_hacia_la_derecha: Getter para usar la Page en Steps
    public static Page getPage() {
        return page.get();
    }
}
