package com.saucedemo.utils;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

import io.restassured.response.Response;
import lombok.Data;

@Data
public class TestContext {
    // Page y BrowserContext para compartir entre Steps
    private Page page;
    private BrowserContext browserContext;

     // JWT Token y API Response para pruebas API
    private String authToken;
    private Response lastResponse;
    
    public TestContext() {
    }
}
