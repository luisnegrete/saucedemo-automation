@auth @login @security @fullRegression
Feature: API Authentication and Token Management

  @apiTest @US-API0101-01 @smoke
  Scenario: 1. Successful Login and Token Generation
    Given User login using API with email "standard_user@sauce.com" and password "secret_sauce" credentials
    Then The response status code should be 200
    And The response body should contain the field "token"

  @US-API0101-02
  Scenario: 2. Login with Invalid Credentials
    Given User login using API with email "invalid@user.com" and password "wrong_pass" credentials
    Then The response status code should be 401
    And The response body field "message" should equal "Invalid credentials"

  @US-API0101-03
  Scenario: 3. Login with Blocked User (Simulating Sauce Demo)
    Given User login using API with email "locked_out_user@sauce.com" and password "secret_sauce" credentials
    Then The response status code should be 403
    And The response body field "message" should equal "User has been locked out."
