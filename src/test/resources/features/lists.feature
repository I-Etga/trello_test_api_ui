Feature: Create list on the Board
  As a user, I want the capability to create a new list on a board in order to organize tasks or items effectively.

  @tc4
  Scenario: User creates a Board and sees default list on that board
    Given User provides API key and token
    When User creates a new Trello board via API with name "Germany"
    #And User logged in with valid credentials and is on the Trello home page
    Then User verifies through API if default lists exist on the board
      | To Do |
      | Doing |
      | Done  |
    #And User verifies through UI if a new list exists on the board
    And User deletes the Trello board via API

  @tc5
  Scenario: User creates a Board and a list on that board
    Given User provides API key and token
    When User creates a new Trello board via API with name "Deutschland"
    And User creates a new list named "Kaiserslautern" on the board via API
    #And User logged in with valid credentials and is on the Trello home page
    Then User verifies through API if the list exists on the board
    #And User verifies through UI if a new list exists on the board
    And User deletes the Trello board via API