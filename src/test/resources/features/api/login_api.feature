Feature: API Authentication

  @api @login @successfulLogin
  Scenario: Successful login with valid credentials
    When I login with email "standard_user@sauce.com" and password "secret_sauce"
    Then the response status code should be 200

  @api @login @failedLogin
  Scenario: Login with invalid credentials
    When I login with email "wrong@example.com" and password "wrong"
    Then the response status code should be 401
