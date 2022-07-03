Feature: Test Mod Functionality

Background: 
    Given a mod is logged in

# TC_03 Routing upon success/failure
Scenario Outline: Mod tries to manipulate accounts
    When a mod clicks on mod menu
    When a mod selects a user "<user_id>"
    And clicks to "<freeze_unfreeze>"
    When mod logs out and "<user_id>" logs in
    Then user is on "<landing_page>"

    Examples:
        | user_id   |   freeze_unfreeze |   landing_page    |
        | 1         |   true            |   Banned!         |
        | 1         |   false           |   Account         |
        | 3         |   true            |   Banned!         |
        | 3         |   false           |   Account         |
        | 1         |   false           |   Account         |