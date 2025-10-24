package com.saucedemo.api.clients;

import com.saucedemo.api.models.Product;
import com.saucedemo.api.models.TokenSession;
import com.saucedemo.api.utils.APIContext;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

/**
 * Cliente de API dedicado a gestionar las interacciones de autenticación
 * con el servicio /login del JSON Server Mock.
 */
public class AuthClient extends ApiClient {
    public AuthClient(APIContext context) {
        super(context);
    }

    /**
     * Realiza una petición POST al endpoint /login para obtener un token JWT.
     *
     * @param email Email del usuario para el login.
     * @param password Contraseña del usuario.
     * @return Objeto TokenResponse que contiene el token JWT.
     */
    public TokenSession postLogin(String email, String password) {
        // 1. Crear el cuerpo de la petición (Payload)
        // Usamos Map para construir el JSON de forma sencilla.
        Map<String, String> credentials = Map.of(
                "email", email,
                "password", password
        );

        // 2. Ejecutar la petición usando Rest Assured
        Response response = RestAssured.given()
                .baseUri(context.getBaseUri())
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .body(credentials)
                .log().all()
            .when()
                .post("/login")
            .then()
                .log().all()
                .extract().response();
                
        // 3. Validar el estado de la respuesta y mapear el cuerpo
        // Validamos explícitamente el código 200/201 (aunque esto lo manejarán los Steps de Cucumber)
        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Fallo en el Login. Código de estado: " + response.getStatusCode() + ". Cuerpo: " + response.asString());
        }
        
        // Deserializar (mapear) el cuerpo JSON a la clase TokenResponse
        return response.as(TokenSession.class);
    }

    @Override
    public List<Product> getAllProductsWithoutToken() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllProductsWithoutToken'");
    }
}
