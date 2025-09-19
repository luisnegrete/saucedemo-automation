package com.saucedemo.hooks;

import java.io.ByteArrayInputStream;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Allure;

public class PlaywrightHooks {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    @Before
    public static void scenarioSetUp() {
        // Inicializar Playwright y abrir navegador solo una veza
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(true)
                .setSlowMo(100));

        // Crear un contexto y página nuevos por escenario
        context = browser.newContext();
        page = context.newPage();

        page.navigate("https://www.saucedemo.com/");
    }

    @After
    public void tearDownScenario(io.cucumber.java.Scenario scenario) {
        if (scenario.isFailed() && page != null) {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
        }
        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    // :apuntando_hacia_la_derecha: Getter para usar la Page en Steps
    public static Page getPage() {
        return page;
    }
}