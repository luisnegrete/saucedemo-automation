package com.saucedemo.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal; // Recomendado para manejar dinero con precisión

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private BigDecimal price; // Usamos BigDecimal para evitar problemas de precisión flotante

    @JsonProperty("inventory")
    private Integer inventory;

    // Constructor vacío (necesario para el deserializador de Jackson)
    public Product() {
    }

    // Getters
    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public Integer getInventory() { return inventory; }
    
    // Setters (Opcional, pero bueno para la mutabilidad)
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setInventory(Integer inventory) { this.inventory = inventory; }
    
    // toString para logging (útil)
    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + '}';
    }
}









