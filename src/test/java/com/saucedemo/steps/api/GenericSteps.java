package com.saucedemo.steps.api;

import com.saucedemo.api.clients.ApiClient;
import com.saucedemo.api.models.Product;
import com.saucedemo.api.utils.APIContext;
import com.saucedemo.api.utils.ClientFactory;

import io.cucumber.java.en.*;
import java.util.List;

public class GenericSteps {
    private final APIContext context;
    private ApiClient apiClient; // El nuevo cliente
    
    // INYECCIÓN: Recibe la instancia única de APIContext
    public GenericSteps(APIContext context) {
        this.context = context;
        
    }
    

    @When("The user performs a GET request to  {string} without a token")
    public void userGetsAllProductsWithoutToken(String service) {
        this.apiClient = (ApiClient)ClientFactory.createClientInstance(service);

        // El cliente se encarga de la llamada HTTP y el mapeo.
        List<Product> productsList = apiClient.getAllProductsWithoutToken();
        
        // Guardamos la lista de POJOs en el contexto para la validación del siguiente Step
        context.set("productList", productsList);
        // La respuesta HTTP (código de estado) ya está guardada en context.getLastResponse() por el cliente.
    }
    
}
