package com.saucedemo.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase modelo para mapear la respuesta JSON del servicio POST /login.
 * Ejemplo de respuesta: {"token": "eyJhbGciOiJIUzI1NiI..."}
 */
@JsonIgnoreProperties(ignoreUnknown = true) // Ignora campos desconocidos en el JSON
public class TokenSession {
    // Utiliza @JsonProperty si el nombre del campo JSON difiere del nombre de la variable Java.
    // En este caso, el campo JSON es "token" y la variable Java es "token".
    @JsonProperty("token")
    private String token;
    // Constructor vacío es necesario para el deserializador de Jackson
    public TokenSession() {
    }

    // Constructor con argumentos (Opcional, pero recomendado)
    public TokenSession(String token) {
        this.token = token;
    }

    // Getter para acceder al token
    public String getToken() {
        return token;
    }

    // Setter (Opcional, pero se recomienda si el objeto necesita ser mutado)
    public void setToken(String token) {
        this.token = token;
    }
    
    @Override
    public String toString() {
        return "TokenResponse{" +
                "token='" + token.substring(0, 15) + "...' (Truncated)" + // Truncamos para no mostrar el token completo en logs
                '}';
    }
}