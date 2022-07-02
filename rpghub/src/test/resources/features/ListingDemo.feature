Feature: Tests listing page Functionality

Background:
Given User is logged in 
When user clicks select listing button
When user clicks view selected listing button


Scenario: User joins a listing
When user clicks join listing button
Then user will be added to the list

Scenario: User leaves a listing
When user clicks leave group button
Then user will be removed from listing

