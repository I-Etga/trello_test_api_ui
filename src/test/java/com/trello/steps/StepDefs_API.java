package com.trello.steps;

import com.trello.pages.BoardsPage;
import com.trello.utility.BrowserUtilities;
import com.trello.utility.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

import static io.restassured.RestAssured.config;
import static io.restassured.config.ParamConfig.UpdateStrategy.REPLACE;
import static io.restassured.config.ParamConfig.paramConfig;
import static org.hamcrest.CoreMatchers.*;


public class StepDefs_API {

    BoardsPage boardsPage = new BoardsPage();


    private static final String BASE_URL = ConfigurationReader.getProperty("baseUri");
    private static final String API_KEY = ConfigurationReader.getProperty("api_key");
    private static final String TOKEN = ConfigurationReader.getProperty("token");
    static String boardId;
    static String boardName;
    static String listName;

    List<String> ListOnNewBoardUI;
    List<String> namesListApi;

    RequestSpecification givenPart;
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

            if (titleAttribute.contains(StepDefs_API.boardName)) {
                boardFound = true;
                break;
            }
        }

        Assert.assertTrue("No board with title containing the specified name found", boardFound);

    }


    @Given("User provides API key and token")
    public void user_provides_api_key_and_token() {
        givenPart = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("key", API_KEY)
                .queryParam("token", TOKEN);
    }

    @When("User creates a new Trello board via API with name {string}")
    public void user_creates_a_new_trello_board_api_with_name(String boardName) {

        response = givenPart.when()
                .queryParam("name", boardName)
                .post(BASE_URL + "boards/")
                .prettyPeek();

        boardId = response.then().extract().path("id");


        StepDefs_API.boardName = boardName;
        thenPart = response.then();


    }

    @Then("The Trello board should be created successfully via API")
    public void the_trello_board_should_be_created_successfully_via_api() {

        thenPart.statusCode(200)
                .body("name", equalTo(boardName))
                .body("id", notNullValue());
    }


    @When("User deletes the Trello board via API")
    public void user_deletes_the_trello_board_via_api() {
        response = givenPart.when()
                .delete(BASE_URL + "boards/" + boardId).prettyPeek();

        thenPart = response.then();
    }

    @Then("The Trello board should be deleted successfully via API")
    public void the_trello_board_should_be_deleted_successfully_via_api() {
        thenPart.statusCode(200).
                body("_value", nullValue());

    }

    @Then("User verifies through API if default lists exist on the board")
    public void user_verifies_through_api_default_if_lists_exist_on_the_board(List<String> lists) {
        response = givenPart
                .when()
                .get(BASE_URL + "boards/" + boardId + "/lists");

        JsonPath jsonPath = response.then().extract().jsonPath();

        namesListApi = jsonPath.getList("name");

        ListOnNewBoardUI = lists;
        Assert.assertTrue(namesListApi.containsAll(lists));


    }

    @When("User creates a new list named {string} on the board via API")
    public void user_creates_a_new_list_named_on_the_board_via_api(String listName) {

        response = givenPart
                .config(config().paramConfig(paramConfig().queryParamsUpdateStrategy(REPLACE))) //in order to overwrite query parameter
                .queryParam("name", listName)
                .post(BASE_URL + "boards/" + boardId + "/lists").prettyPeek();

        StepDefs_API.listName = listName;

        thenPart = response.then();

    }

    @Then("User verifies through API if the list exists on the board")
    public void user_verifies_through_api_if_the_list_exists_on_the_board() {
        response = givenPart
                .when()
                .get(BASE_URL + "boards/" + boardId + "/lists");

        JsonPath jsonPath = response.then().extract().jsonPath();

        namesListApi = jsonPath.getList("name");

        Assert.assertTrue(namesListApi.contains(listName));
    }

    @Then("User verifies through UI if a new list exists on the board")
    public void user_verifies_through_ui_if_a_new_list_exists_on_the_board() {

        BrowserUtilities.waitFor(3);
        for (WebElement eachList : boardsPage.listsNewBoard) {
            String list = eachList.getText();
            Assert.assertTrue(namesListApi.contains(list));
            BrowserUtilities.waitFor(1);
        }
    }


}