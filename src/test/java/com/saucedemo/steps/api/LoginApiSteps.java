package com.saucedemo.steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.restassured.response.Response;

import com.saucedemo.api.clients.AuthClient;
import com.saucedemo.api.models.TokenSession;
import com.saucedemo.api.utils.APIContext;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;

public class LoginApiSteps {
    // Inyección del contexto: Cucumber crea y gestiona la instancia por escenario
    private final APIContext context;
    private final AuthClient authClient;

    public LoginApiSteps(APIContext context){
        this.context = context;
        this.authClient = new AuthClient(context); //Inicializa el cliente
    }
   
    @Given("User login using API with email {string} and password {string} credentials")
    public void performLoginAndSaveToken(String email, String password) {
        // Suponiendo que las credenciales están fijas o se cargan de otro lugar
        TokenSession tokenResponse = authClient.postLogin(email, password);

        // Guardar el token en tu contexto/sesión para usarlo en peticiones futuras
        context.setAuthToken(tokenResponse.getToken());
    }

    @Then("The response status code should be {int}")
    public void validateLoginStatusCodeResponse(Integer code){

        // 1. Obteniendo la respuesta del servicio anterior
        Response response = context.getLastResponse();

        assertThat(response.getStatusCode())
            .as("El código de estado debe ser:" + code)
            .isEqualTo(code);
    }

    @Then("The response body should contain the field {string}")
    public void validateFieldExistence(String fieldName) {
        // 1. Al obtener el valor del campo se realizan las asserciones adecuadas
        getFieldValueFromResponse(fieldName);
    }

    
    @Then("The response body field {string} should equal {string}")
    public void validateFieldExistenceAndValue(String fieldName, String expectedValue) {
        // 1. Obtener el valor del campo
        String fieldValue = getFieldValueFromResponse(fieldName);

        // 2. Realizar la aserción usando AssertJ
        SoftAssertions softly = new SoftAssertions();
        
        // A. Validar que el valor del campo de la respuesta sea igual a la esperada
        softly.assertThat(fieldValue)
              .as("El valor de la respuesta debe de ser igual al esperado")
              .isEqualTo(expectedValue);

        // Finalizar las aserciones (lanza excepción si alguna falló)
        softly.assertAll();
    }

    private String getFieldValueFromResponse(String fieldName){
        // 1. Obtener la última respuesta del contexto
        Response response = context.getLastResponse();

        // 2. Realizar la aserción usando AssertJ
        SoftAssertions softly = new SoftAssertions();

        // A. Validar que la respuesta no sea nula (prevención de errores)
        softly.assertThat(response)
              .as("La respuesta HTTP no debe ser nula")
              .isNotNull();
        
        // Usamos .jsonPath().getString() que devuelve null si el campo no existe
        String fieldValue = response.jsonPath().getString(fieldName);

        softly.assertThat(fieldValue)
              .as("El campo '" + fieldName + "' debe existir en el cuerpo de la respuesta")
              .isNotNull();
        
        // C. Validar que el valor del token no esté vacío ni sea solo espacios en blanco
        softly.assertThat(fieldValue)
              .as("El valor del campo '" + fieldName + "' (token) no debe estar vacío")
              .isNotEmpty()
              .isNotBlank();
        
        // Finalizar las aserciones (lanza excepción si alguna falló)
        softly.assertAll();

        return fieldValue;

    }

}
