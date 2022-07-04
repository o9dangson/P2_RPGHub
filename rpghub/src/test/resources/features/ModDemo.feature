Feature: Test Mod Functionality

Background: 
    Given a mod is logged in

#TC_13
Scenario: Mod tries to freeze account
    When a mod clicks on mod menu
    When a mod selects a user
    And clicks to ban
    When mod logs out and user logs in
    Then banned user is on the ban page

Scenario: Mod tries to unfreeze account
    When a mod clicks on mod menu
    When a mod selects a user
    And clicks to unban
    When mod logs out and user logs in
    Then unbanned user is on the account page

#Scenario: Mod tries to manipulate listing that isn't theirs
#    When mod logs out and user logs in
#    When a user creates a listing
#    When a user logs out and mod logs in
#    When mod removes listing
#    Then mod sees listing is gone

Scenario: Mod tries to manipulate listing and entry that isn't theirs
    When mod logs out and user logs in
    When a user creates a listing
    When they navigate to listing
    When a user creates an entry
    When a user logs out and mod logs in
    When they navigate to listing
    When mod selects entry
    When mod opens update menu
    When mod accepts entry
    When mod selects entry
    When mod rejects entry
    When mod selects entry
    When mod kicks entry
    Then mod sees entry is gone