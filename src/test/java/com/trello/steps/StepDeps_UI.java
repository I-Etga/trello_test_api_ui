package com.trello.steps;

import com.trello.pages.BoardsPage;
import com.trello.pages.LoginPage;
import com.trello.utility.BrowserUtilities;
import com.trello.utility.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class StepDeps_UI {

    BoardsPage boardsPage = new BoardsPage();
    LoginPage loginPage = new LoginPage();

    static String name;
    private static final String USERNAME = ConfigurationReader.getProperty("username");
    private static final String PASSWORD = ConfigurationReader.getProperty("password");

    @Given("User logged in with valid credentials and is on the Trello home page")
    public void user_logged_in_with_valid_credentials_and_is_on_the_trello_home_page() {

        BrowserUtilities.waitFor(1);
        loginPage.username.sendKeys(USERNAME + Keys.ENTER);

        BrowserUtilities.waitFor(1);
        loginPage.password.sendKeys(PASSWORD + Keys.ENTER);

    }

    @When("User creates a new Trello board with name {string}")
    public void user_creates_a_new_trello_board_with_name(String name) {
        boardsPage.createBoardOrWorkspace.click();
        BrowserUtilities.waitFor(2);
        boardsPage.createBoardButton.click();
        BrowserUtilities.waitFor(2);
        boardsPage.createBoardInput.sendKeys(name);
        BrowserUtilities.waitFor(3);
        boardsPage.submitButton.click();
        BrowserUtilities.waitFor(3);
        StepDeps_UI.name = name;


    }

    @When("User deletes the Trello board")
    public void user_deletes_the_trello_board() {

        BrowserUtilities.waitFor(3);
        boardsPage.showMenu.click();
        BrowserUtilities.waitFor(3);
        boardsPage.closeBoard.click();
        BrowserUtilities.waitFor(3);
        boardsPage.closeBoard2.click();
        BrowserUtilities.waitFor(3);
        boardsPage.permanentlyDelete.click();
        BrowserUtilities.waitFor(3);
        boardsPage.confirmDelete.click();
    }

    @Then("User checks if the Trello board exists on the UI")
    public void user_checks_if_the_trello_board_exists_on_the_ui() {
        BrowserUtilities.waitFor(4);

        boolean boardFound = false;

        for (WebElement board : boardsPage.boards) {
            String titleAttribute = board.getAttribute("title");

            if (titleAttribute.contains(name)) {
                boardFound = true;
                break;
            }
        }

        if (boardFound) {
            Assertions.assertTrue(true, "Board with title containing the specified name exists");
        } else {
            Assertions.assertTrue(false, "No board with title containing the specified name found");
        }
    }

    @Then("The Trello board should be deleted successfully on the UI")
    public void the_trello_board_should_be_deleted_successfully_on_the_ui() throws InterruptedException {
        BrowserUtilities.waitFor(3);
        boardsPage.boardsButton.click();
        BrowserUtilities.waitFor(3);

        for (WebElement board : boardsPage.boards) {
            Assertions.assertFalse(board.getAttribute("title").contains(name));
        }
    }

}
