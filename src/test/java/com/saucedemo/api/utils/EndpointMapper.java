package com.saucedemo.api.utils;

import com.saucedemo.api.clients.ApiClient;
import com.saucedemo.api.clients.AuthClient;
import com.saucedemo.api.clients.ProductsClient;
import java.util.Map;

public class EndpointMapper {
    private static final Map<String, Class<? extends ApiClient>> ENDPOINT_MAP = Map.of(
        "/products", ProductsClient.class,
        "/login", (Class<? extends ApiClient>) AuthClient.class
    );

    public static Class<? extends ApiClient> getClientClass(String endpoint) {
        return ENDPOINT_MAP.get(endpoint);
    }
}
