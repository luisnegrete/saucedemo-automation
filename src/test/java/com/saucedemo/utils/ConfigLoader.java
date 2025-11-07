package com.saucedemo.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigLoader {
    // Cargar variables de entorno desde el archivo .env
    private static final Dotenv dotenv = Dotenv.load();

    private static String get(String key) {
        return dotenv.get(key);
    }

    // Métodos específicos para obtener configuraciones comunes
    public static String getApiBaseUrl() {
        return get("API_BASE_URL");
    }
    public static String getUiBaseUrl() {
        return get("UI_BASE_URL");
    }
    public static String getStandardUser() {
        return get("STANDARD_USER");
    }
    public static String getStandardPassword() {
        return get("STANDARD_PASSWORD");
    }
}
