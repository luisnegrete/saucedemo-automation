package com.saucedemo.api.utils;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase de Contexto para compartir datos de estado (State)
 * entre diferentes Step Definitions durante la ejecución de un Scenario.
 *
 * Utiliza Inyección de Dependencias (DI) de Cucumber para asegurar
 * que la misma instancia sea usada por todos los Steps.
 */
public class APIContext {
    private String baseUri;
    // 1. Almacenamiento del Token JWT
    private String authToken;
    
    public String getBaseUri() {
        return baseUri;
    }
    
    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    // 2. Almacenamiento de la última respuesta de la API (para validación)
    private Response lastResponse;

    // 3. Almacenamiento genérico (útil para guardar IDs, datos, etc.)
    private final Map<String, Object> scenarioData;

    public APIContext() {
        this.scenarioData = new HashMap<>();
    }
    
    // --- Métodos para el Token JWT ---
    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    // --- Métodos para la Última Respuesta ---
    public Response getLastResponse() {
        return lastResponse;
    }

    public void setLastResponse(Response lastResponse) {
        this.lastResponse = lastResponse;
    }

    // --- Métodos para Almacenamiento Genérico ---
    /**
     * Guarda un valor para una clave específica en el contexto del escenario.
     * @param key Nombre de la variable.
     * @param value Objeto a guardar.
     */
    public void set(String key, Object value) {
        scenarioData.put(key, value);
    }
    
    /**
     * Obtiene un valor del contexto del escenario por su clave.
     * @param key Nombre de la variable.
     * @param <T> El tipo de dato esperado.
     * @return El objeto guardado o null.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) scenarioData.get(key);
    }
}
