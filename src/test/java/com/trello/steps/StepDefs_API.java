package com.trello.steps;

import com.trello.pages.BoardsPage;
import com.trello.utility.BrowserUtilities;
import com.trello.utility.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.*;


public class StepDefs_API {

    BoardsPage boardsPage = new BoardsPage();
    static String name;


    private static final String BASE_URL = ConfigurationReader.getProperty("baseUri");
    private static final String API_KEY = ConfigurationReader.getProperty("api_key");
    private static final String TOKEN = ConfigurationReader.getProperty("token");
    static String boardId;

    Response response;
    ValidatableResponse thenPart;


    @Then("User checks if the board exists on the UI")
    public void user_checks_if_the_board_exists_on_the_ui() {
        BrowserUtilities.waitFor(2);
        boardsPage.boardsButton.click();
        BrowserUtilities.waitFor(2);

        boardsPage.showMoreButton.click();
        BrowserUtilities.waitFor(2);

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


    @When("User creates a new Trello board via API with name {string}")
    public void user_creates_a_new_trello_board_api_with_name(String name) {
        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("name", name)
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .post(BASE_URL + "boards/").prettyPeek();

        boardId = response.then().extract().path("id");

        StepDefs_API.name = name;
        thenPart = response.then();

    }

    @Then("The Trello board should be created successfully via API")
    public void the_trello_board_should_be_created_successfully_via_api() {

        thenPart.statusCode(200)
                .body("name", equalTo(name))
                .body("id", notNullValue());
    }


    @When("User deletes the Trello board via API")
    public void user_deletes_the_trello_board_via_api() {
        response = RestAssured.given()
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN)
                .delete(BASE_URL + "boards/" + boardId).prettyPeek();

        thenPart = response.then();
    }

    @Then("The Trello board should be deleted successfully via API")
    public void the_trello_board_should_be_deleted_successfully_via_api() {
        thenPart.statusCode(200).
                body("_value", nullValue());

    }

}
