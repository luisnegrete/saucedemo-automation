package com.saucedemo.pages;

import com.microsoft.playwright.Page;

// Clase base para futuras páginas, actualmente vacía pero preparada para expansión futura
public class BasePage {
    protected final Page page;

    // Inyección de dependencia de Playwright Page a través del constructor
    public BasePage(Page page) {
        this.page = page;
    }
    
}