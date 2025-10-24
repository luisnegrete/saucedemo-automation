@orders
Feature: Order Management and Checkout Flow

  Background: Successful Login and Setup
    Given the user is authenticated with a valid JWT

  @skip
  Scenario: 1. Successful Order Creation (Checkout)
    Given the order payload is prepared with a valid "userId" and 2 "items"
    When the user performs a POST request to "/orders" with the stored token
    Then the response status code should be 201
    And save the value of the field "id" as session variable "orderId"

  @skip
  Scenario: 2. Retrieve Created Order
    Given the user creates an order and saves its ID as "existingOrderId"
    When the user performs a GET request to "/orders/{existingOrderId}" with the stored token
    Then the response status code should be 200
    And the response field "userId" should be the expected user ID

  @skip
  Scenario: 3. Fail to Create Order - Missing Items
    Given the order payload is prepared with a valid "userId" but an empty list of "items"
    When the user performs a POST request to "/orders" with the stored token
    Then the response status code should be 400
    And the response body should contain the error message "Datos de orden incompletos"
