Feature: Tests listing page Functionality

Background:
Given User is logged in 
When user clicks select listing button
When user clicks view selected listing button


Scenario: User joins a listing
When user clicks join listing button
When user clicks a role
When user inputs a note
Then user will be added to the list

Scenario: user leader accepts
When group leader selects user
And group leader clicks Accept button
Then user status will change to Accepted

Scenario: user leader rejects user
When group leader selects user
And group leader clicks Reject button
Then user status will change to Rejected

Scenario: user leader kicks user
When group leader selects user
And group leader clicks Kick button
Then user will be deleted from list

Scenario: User leaves a listing
When user clicks leave group button
Then user will be removed from listing

