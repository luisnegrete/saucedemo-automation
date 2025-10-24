package com.saucedemo.api.clients;

import com.saucedemo.api.models.Product;
import com.saucedemo.api.utils.APIContext;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;

/**
 * Cliente de API dedicado a gestionar las interacciones con el recurso /products.
 * Requiere el token JWT para la autenticación.
 */
public class ProductsClient extends ApiClient{

    private APIContext context;
    
    // Inyección del contexto para obtener el token
    public ProductsClient(APIContext context) {
        super(context);
    }

    /**
     * Realiza una petición GET a /products para obtener todo el catálogo.
     *
     * @return Una lista de objetos Product mapeados desde la respuesta JSON.
     */
    public List<Product> getAllProductsWithoutToken() {
        // 1. Ejecutar la petición HTTP con Rest Assured
        Response response = RestAssured.given()
                .when()
                .get("/products");

        // 2. Guardar la respuesta completa en el Contexto para validaciones posteriores (código de estado)
        context.setLastResponse(response);
        
        // 3. Deserializar el cuerpo a una lista de POJOs
        // Nota: Rest Assured requiere el uso de response.as(List.class) y un truco de tipo para listas.
        // La forma más limpia es usar JsonPath para listas directamente.
        if (response.getStatusCode() == 200) {
            return response.jsonPath().getList("$", Product.class); // Mapea el array raíz ($) a List<Product>
        } else {
            // Si el estado no es 200, devolvemos una lista vacía y los Steps validarán el error.
            return List.of();
        }
    }

    /**
     * Realiza una petición GET a /products para obtener todo el catálogo.
     *
     * @return Una lista de objetos Product mapeados desde la respuesta JSON.
     */
    public List<Product> getAllProducts() {
        String token = context.getAuthToken();
    
        if (token == null || token.isEmpty()) {
             throw new IllegalStateException("El token JWT no está disponible en el contexto. Ejecute el login primero.");
        }

        // 1. Ejecutar la petición HTTP con Rest Assured
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token) // Adjunta el token
                .when()
                .get("/products");

        // 2. Guardar la respuesta completa en el Contexto para validaciones posteriores (código de estado)
        context.setLastResponse(response);
        
        // 3. Deserializar el cuerpo a una lista de POJOs
        // Nota: Rest Assured requiere el uso de response.as(List.class) y un truco de tipo para listas.
        // La forma más limpia es usar JsonPath para listas directamente.
        if (response.getStatusCode() == 200) {
            return response.jsonPath().getList("$", Product.class); // Mapea el array raíz ($) a List<Product>
        } else {
            // Si el estado no es 200, devolvemos una lista vacía y los Steps validarán el error.
            return List.of();
        }
    }
}









