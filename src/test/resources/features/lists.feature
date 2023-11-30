Feature: Create list on the Board

  @tc4 @ui
  Scenario: User creates a Board and sees default list on that board
    Given User provides API key and token
    When User creates a new Trello board via API with name "Germany"
    And User logged in with valid credentials and is on the Trello home page
    Then User verifies through API if default lists exist on the board
      | To Do |
      | Doing |
      | Done  |
    And User clicks last created board
    And User verifies through UI if a new list exists on the board

  @tc5 @ui
  Scenario: User creates a Board and a list on that board
    Given User provides API key and token
    When User creates a new Trello board via API with name "Deutschland"
    And User creates a new list named "Kaiserslautern" on the board via API
    And User logged in with valid credentials and is on the Trello home page
    And User clicks last created board
    Then User verifies through API if the list exists on the board
    And User verifies through UI if a new list exists on the board