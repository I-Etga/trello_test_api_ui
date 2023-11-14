Feature: Trello Board Operations

  @ui @tc1
  Scenario Outline: Create and Delete Trello Board
    And User logged in with valid credentials and is on the Trello home page
    When User creates a new Trello board with name "<name>"
    Then User checks if the Trello board exists on the UI
    When User deletes the Trello board
    Then The Trello board should be deleted successfully on the UI
    Examples:
      | name      |
      | TestData1 |
      | TestData2 |


  @ui @tc2
  Scenario: Create and Delete Trello Board via API and UI
    When User creates a new Trello board via API with name "API Test Trello"
    Then The Trello board should be created successfully via API
    And User logged in with valid credentials and is on the Trello home page
    And User checks if the board exists on the UI


  @tc3
  Scenario: Create and Delete Trello Board via API
    When User creates a new Trello board via API with name "API Test Trello"
    Then The Trello board should be created successfully via API
    When User deletes the Trello board via API
    Then The Trello board should be deleted successfully via API
