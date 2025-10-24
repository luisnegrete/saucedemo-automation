package com.saucedemo.api.clients;

import java.util.List;

import com.saucedemo.api.models.Product;
import com.saucedemo.api.utils.APIContext;

public abstract class ApiClient {
    protected static final String BASE_URI = "http://192.168.0.16:3000/";
    protected final APIContext context;
    
    public ApiClient(APIContext context) {
        this.context = context;
        this.context.setBaseUri(BASE_URI);
    }

    public abstract List<Product> getAllProductsWithoutToken();
    
}
