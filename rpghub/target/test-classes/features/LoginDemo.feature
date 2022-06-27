Feature: Test Login Functionality

Background: 
    Given a user is on the login page

Scenario: A user enters the correct credentials
    When a user enters the correct username and correct password
    And clicks on login button
    Then a user is navigated to the homepage