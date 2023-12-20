package com.trello.pages;

import com.trello.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BoardsPage {

    public BoardsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@aria-label='Create board or Workspace']")
    public WebElement createBoardOrWorkspace;

    @FindBy(xpath = "//button[@data-testid='header-create-board-button']")
    public WebElement createBoardButton;

    @FindBy(xpath = "//input[@data-testid = 'create-board-title-input']")
    public WebElement createBoardInput;

    @FindBy(xpath = "//button[@data-testid = 'create-board-submit-button']")
    public WebElement submitButton;
    @FindBy(xpath = "//button[@aria-label='Show menu']")
    public WebElement showMenu;

    @FindBy(xpath = "//a[@class='board-menu-navigation-item-link board-menu-navigation-item-link-v2 js-close-board']")
    public WebElement closeBoard;

    @FindBy(xpath = "//input[@class='js-confirm full nch-button nch-button--danger']")
    public WebElement closeBoard2;

    @FindBy(xpath = "//button[@data-testid='close-board-delete-board-button']")
    public WebElement permanentlyDelete;

    @FindBy(xpath = "//button[@data-testid='close-board-delete-board-confirm-button']")
    public WebElement confirmDelete;

    @FindBy(xpath = "//a[@data-testid='home-team-boards-tab']")
    public WebElement boardsButton;

    @FindBy(xpath = "//div[@class='jv7QDCKI8FPToj']/li/a")
    public List<WebElement> boards;

    @FindBy(xpath = "//*[@id='popover-boundary']/div/nav/div[1]/div/div/div[2]/div/div[3]/ul/button")
    public WebElement showMoreButton;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div/div/div/div/div/div/div/div[2]/div/div[2]/ul/li[1]/a")
    public WebElement lastCreatedBoard;

    @FindBy(xpath = "//div[@class='mKJWg6W_CLHoiO']/h2")
    public List<WebElement> listsNewBoard;

}
