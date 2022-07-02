Feature: Test Login Functionality

Background: 
    Given a user is on the login page

# TC_03 Routing upon success/failure
Scenario: A user enters the correct credentials
    When a user enters the correct username and correct password
    And clicks on login button
    Then a user is navigated to the account page

Scenario: A user enters the incorrect credentials
    When a user enters the incorrect username and password
    And clicks on login button
    Then a user is navigated to the homepage

Scenario: A user logs in correctly but is banned
    When a user enters frozen login information
    And clicks on login button
    Then a user will be navigated to a page telling them they are banned

## TC_04 Tests direct html routing
#Scenario Outline: A user tries to directly access routes
#    When a user is logged in "<logged_in>"
#    And a user types in the url "<url_path>"
#    Then they should be at "<location>"
#
#    Examples:
#        |   logged_in   |   url_path                                |   location    |
#        |   false       |   http://localhost:9090/                  |   Homepage    |
#        |   false       |   http://localhost:9090/account           |   Homepage    |
#        |   false       |   http://localhost:9090/listing/manage    |   Homepage    |
#        |   true        |   http://localhost:9090/                  |   Account     |
#        |   true        |   http://localhost:9090/account           |   Account     |
#        |   true        |   http://localhost:9090/listing/manage    |   Listing     |