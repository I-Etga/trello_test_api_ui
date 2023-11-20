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

    @FindBy(xpath = "//button[@data-testid='header-create-menu-button']")
    public WebElement createBoardOrWorkspace;

    @FindBy(xpath = "//button[@data-testid='header-create-board-button']")
    public WebElement createBoardButton;

    @FindBy(xpath = "/html/body/div[3]/div/section/div/form/div[1]/label/input")
    public WebElement createBoardInput;

    @FindBy(xpath = "//button[@data-testid='create-board-submit-button']")
    public WebElement submitButton;
    @FindBy(xpath = "//*[@id='content']/div/div[1]/div[1]/div/div/span[2]/button[2]")
    public WebElement showMenu;

    @FindBy(xpath = "//*[@id='content']/div/div[2]/div/div/div[2]/div/ul/li[17]/a")
    public WebElement closeBoard;

    @FindBy(xpath = "//*[@id='chrome-container']/div[4]/div/div[2]/div/div/div/input")
    public WebElement closeBoard2;

    @FindBy(xpath = "//*[@id='content']/div/div/div/div/div/div[2]/button")
    public WebElement permanentlyDelete;

    @FindBy(xpath = "/html/body/div[3]/div/section/div/button")
    public WebElement confirmDelete;

    @FindBy(xpath = "//*[@id='content']/div/div[2]/div/div/div/div/div/div/div/div[2]/div/div[1]/div[2]/a[1]")
    public WebElement boardsButton;

    @FindBy(xpath = "//*[@id='popover-boundary']/div/nav/div[1]/div/div/div[2]/div/div[3]/ul/div[2]/li/a")
    public List<WebElement> boards;

    @FindBy(xpath = "//*[@id='popover-boundary']/div/nav/div[1]/div/div/div[2]/div/div[3]/ul/button")
    public WebElement showMoreButton;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div/div/div/div/div/div/div/div[2]/div/div[2]/ul/li[1]/a")
    public WebElement lastCreatedBoard;

    @FindBy(xpath = "//div[@class='mKJWg6W_CLHoiO']/h2")
    public List<WebElement> listsNewBoard;

}
