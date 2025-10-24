package com.saucedemo.hooks;

import com.saucedemo.api.utils.APIContext;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.restassured.RestAssured;

/**
 * Hooks específicos para pruebas de API.
 * Se ejecutan antes y después de cada escenario de API.
 */
public class ApiHooks {
    private static final String BASE_URI = "http://192.168.0.16:3000";

    // Inyección de Dependencias: Esto asegura que el mismo APIContext
    // sea compartido con las clases de Steps (LoginApiSteps, ProductSteps).
    private final APIContext context;

    public ApiHooks(APIContext context) {
        this.context = context;
    }

    /**
     * Se ejecuta antes de cada escenario.
     * 1. Configura la BASE_URI para Rest Assured.
     * 2. Inicializa variables comunes del contexto.
     */
    @Before("@apiTest") // Aplica solo a escenarios etiquetados con @apiTest (opcional, pero recomendado)
    public void setupApiEnvironment() {
        // Establecer la URL base para todas las peticiones de Rest Assured
        RestAssured.baseURI = BASE_URI;
        System.out.println("Configuración de API: BASE_URI establecida en " + BASE_URI);
        // Opcional: Limpiar o inicializar el contexto al inicio de cada escenario
        context.setAuthToken(null);
        context.setLastResponse(null);
    }

    /**
     * Se ejecuta después de cada escenario.
     * Restaura la configuración de Rest Assured (útil si hay hooks de UI).
     */
    @After("@apiTest")
    public void cleanupApiEnvironment() {
        RestAssured.reset();
    }
}