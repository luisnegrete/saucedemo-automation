@products
Feature: Product Management and CRUD Operations

  Background: Successful Login and Setup
    # Se recomienda usar un Hook o un paso de Login pre-ejecutado para esta Background.
    Given User login using API with email "standard_user@sauce.com" and password "secret_sauce" credentials

  @US-API0101-01
  Scenario: 1. Access Denied without Token
    When The user performs a GET request to  "/products" without a token
    Then the response status code should be 401

  @US-API0101-02
  Scenario: 2. Get All Products (Happy Path)
    When the user performs a GET request to "/products" with the stored token
    Then the response status code should be 200
    And the response body should be a list of "Product" models
    And the product list should contain more than 1 item

  @US-API0101-03
  Scenario: 3. Create New Product
    Given the product payload is set with name "Automation Widget" and price 99.99
    When the user performs a POST request to "/products" with the stored token
    Then the response status code should be 201
    And save the value of the field "id" as session variable "newProductId"
    And the response field "name" should equal "Automation Widget"

  @US-API0101-04
  Scenario: 4. Fail to Create Product due to Invalid Price
    Given the product payload is set with name "Invalid Product" and price "not_a_number"
    When the user performs a POST request to "/products" with the stored token
    Then the response status code should be 400
    And the response body should contain the error message "El precio debe ser un número positivo"

  @US-API0101-05
  Scenario: 5. Update Product Price (PATCH)
    Given the user creates a product and saves its ID as "updateId"
    When the user performs a PATCH request to "/products/{updateId}" with the stored token and payload {"price": 149.99}
    Then the response status code should be 200
    And the response field "price" should equal 149.99

  @US-API0101-06
  Scenario: 6. Delete Product
    Given the user creates a product and saves its ID as "deleteId"
    When the user performs a DELETE request to "/products/{deleteId}" with the stored token
    Then the response status code should be 200
    And a subsequent GET request to "/products/{deleteId}" should return 404 Not Found
