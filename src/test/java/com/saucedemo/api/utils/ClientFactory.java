package com.saucedemo.api.utils;

public class ClientFactory {
    public static Object createClientInstance(String endpoint) {

        Class<?> clazz = EndpointMapper.getClientClass(endpoint);
        
        if (clazz != null) {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error creando instancia para: " + endpoint, e);
            }
        }
        throw new IllegalArgumentException("No existe clase para el endpoint: " + endpoint);
    }
}