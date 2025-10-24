package com.saucedemo.steps.api;

import com.saucedemo.api.clients.ProductsClient; // Cliente de Productos
import com.saucedemo.api.models.Product;
import com.saucedemo.api.utils.APIContext;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductSteps {
    private final APIContext context;
    private final ProductsClient productsClient; // El nuevo cliente
    
    // INYECCIÓN: Recibe la instancia única de APIContext
    public ProductSteps(APIContext context) {
        this.context = context;
        this.productsClient = new ProductsClient(context); // Inicializa el cliente, pasándole el Contexto
    }

    @When("the user gets all products on the stock")
    public void userGetsAllProducts() {
        // El cliente se encarga de la llamada HTTP y el mapeo.
        List<Product> productsList = productsClient.getAllProducts();
        
        // Guardamos la lista de POJOs en el contexto para la validación del siguiente Step
        context.set("productList", productsList);
        // La respuesta HTTP (código de estado) ya está guardada en context.getLastResponse() por el cliente.
    }

    @Then("user should see more than one product")
    public void userShouldSeeMoreThanOneProduct() {
        // 1. Validar el código de estado (primera validación crucial)
        Response response = context.getLastResponse();

        assertThat(response.getStatusCode())
            .as("El código de estado debe ser 200 OK")
            .isEqualTo(200);
        
        // 2. Obtener la lista de POJOs del contexto y validar el contenido.
        List<Product> productsList = context.get("productList");

        assertThat(productsList)
            .as("La lista de productos no debe ser nula")
            .isNotNull();
        
        assertThat(productsList.size())
            .as("La cantidad de productos debe ser mayor a uno")
            .isGreaterThan(1);
        
        // Opcional: Validar que los POJOs se mapearon correctamente
        assertThat(productsList.get(0).getId())
            .as("El primer producto debe tener un ID")
            .isNotNull();
    }

    
}






