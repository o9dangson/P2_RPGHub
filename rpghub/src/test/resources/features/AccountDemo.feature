Feature: Test Account Page Functionality

Background: 
    Given a user is logged in and on the Account page

    Scenario: User creates a new listing
        When user clicks Create Listing button
        When user inputs a list name
        When user inputs a dungeon name
        When user inputs max party size
        When user clicks Submit button
        Then there should be a new listing 

    Scenario: User uses filter listing
        When user clicks Filter Listing button
        When user chooses a category to filter by
        When user inputs a specific filter
        When user clicks Filter button
        Then the listing should be shown

    Scenario: User selects a listing to view
        When user clicks Select Listing button
        When user clicks View Selected Listing button
        Then user should be shown listing in listing page

    Scenario: User removes selected listing
        When user clicks Select Listing button
        When user clicks Remove Selected Listing button
        Then the selected listing should be removed 