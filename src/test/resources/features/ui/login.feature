@login @auth @security @fullRegression
Feature: User Authentication test

  @US0101-01 @smoke
  Scenario: @US0101-01_Login success
    Given User is on login page
    When User enter the username as "standard_user"
    And User enter the password as "secret_sauce"
    And User click on the login button
    Then User should see inventory page

  @smoke
  Scenario Outline: <TC_ID>_Login failed
    Given User is on login page
    When User enter the username as "<USERNAME>"
    And User enter the password as "<PASSWORD>"
    And User click on the login button
    Then User should see error message as "<EXPECTED_MESSAGE>"

    Examples:
      | TC_ID      | USERNAME        | PASSWORD     | EXPECTED_MESSAGE                                                          |
      | @US0102-01 | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | @US0102-02 | wrong_user      | secret_sauce | Epic sadface: Username and password do not match any user in this service |

  @US0103-01 @smoke
  Scenario: @US0103-01_User Logout and return login page
    Given User is logged in with "standard_user", "secret_sauce" credentials
    When User click on side menu
    And User click on Logout option
    Then User should see Login page
