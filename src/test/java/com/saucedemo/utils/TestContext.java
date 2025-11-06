package com.saucedemo.utils;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

import lombok.Data;

@Data
public class TestContext {
    // Page y BrowserContext para compartir entre Steps
    private Page page;
    private BrowserContext context;
    
    // API Token
    private String authToken;

    public TestContext() {
    }
}
