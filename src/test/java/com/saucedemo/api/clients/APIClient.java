package com.saucedemo.api.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

import com.saucedemo.utils.ConfigLoader;

public class APIClient {
    private static final String BASE_URL = ConfigLoader.getApiBaseUrl();
    private static String authToken;

    static {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static void setAuthToken(String token) {
        authToken = token;
    }

    public static Response post(String endpoint, Object body) {
        return buildRequest()
                .body(body)
                .post(endpoint);
    }

    public static Response get(String endpoint) {
        return buildRequest().get(endpoint);
    }

    public static Response delete(String endpoint) {
        return buildRequest().delete(endpoint);
    }

    public static Response login(String email, String password) {
        return post("/login", Map.of(
            "email", email,
            "password", password
        ));
    }

    private static RequestSpecification buildRequest() {
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        if (authToken != null && !authToken.isEmpty()) {
            request.header("Authorization", "Bearer " + authToken);
        }

        return request;
    }
}
