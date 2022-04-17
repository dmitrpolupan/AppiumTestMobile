package lib.ui;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObject extends MainPageObject
{
    protected static String
            TITLE,
            OPTION_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            MY_LIST_FOLDER_NAME,
            FOOTER_ELEMENT;

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Waiting for title element appear")
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 5);
    }

    @Step("Getting article title name")
    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        screenshot((this.takeScreenshot("article_title")));
        if(Platform.getInstance().isAndroid()){
            return titleElement.getAttribute("text");
        } else if(Platform.getInstance().isMW()){
            return titleElement.getText();
        } else {
            return "Method getArticleTitle() does nothing for Platform - " + Platform.getInstance().getPlatformVar();
        }
    }

    @Step("Swiping to the page footer")
    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else if(Platform.getInstance().isMW()){
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            System.out.println("Method swipeToFooter() does nothis for platform - " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Clicking on Saved icon and clicking on a overlay")
    public ArticlePageObject clickSavedIconAndClickOverlay()
    {
        this.addArticleToMySaved();
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,"Cannot find link to init saving article process",5);
        return this;
    }

    @Step("Adding article to the my list")
    public void addArticleToMyList(String savedName)
    {
        this.clickSavedIconAndClickOverlay();
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, savedName,"Cannot find input field for entering text for article",5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON,"Cannot find Ok button to save article",5);
    }

    @Step("Adding article to the my saved")
    public void addArticleToMySaved()
    {
        if(Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,"Cannot find button to click button for saving article",5);
    }

    @Step("Removing article from saved if it was added")
    public void removeArticleFromSavedIfItAdded()
    {
        if(this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    5);
            this.waitForElementNotPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Saved icon is not changed",
                    5);
        }
        this.waitForElementPresent(
                OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find button to add an article to saved list after removing it from this list before");
    }

    @Step("Moving to a specific folder")
    public ArticlePageObject moveToSpecifiedFolder(String folderName)
    {
        String folderNameFromListXpath = getMyListFolderNameXpath(folderName);
        this.waitForElementAndClick(folderNameFromListXpath,"Cannot find link to init saving article process",5);
        return this;
    }

    @Step("Moving to list with saved articles")
    public ArticlePageObject moveToListWithSavedArticles()
    {
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,"Cannot find link to init saving article process",5);
        return this;
    }

    @Step("Checking is article title presented")
    public boolean isArticleTitlePresent()
    {
        return this.isElementDisplayed(TITLE);
    }

    /*TEMPLATE METHODS*/
    private static String getTitleElement(String substring)
    {
        return TITLE.replace("{SUBSTRING}", substring);
    }

    private static String getMyListFolderNameXpath(String folderName)
    {
        return MY_LIST_FOLDER_NAME.replace("{FOLDER_NAME}", folderName);
    }
    /*TEMPLATE METHODS*/
}
