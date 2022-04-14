package lib.ui;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObject extends MainPageObject
{
    protected static String
            TITLE,
            SAVED_ICON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            MY_LIST_FOLDER_NAME,
            FOOTER_ELEMENT;

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 5);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        if(Platform.getInstance().isAndroid()){
            return titleElement.getAttribute("text");
        } else if(Platform.getInstance().isMW()){
            return titleElement.getText();
        } else {
            return "Method getArticleTitle() does nothing for Platform - " + Platform.getInstance().getPlatformVar();
        }
    }

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

    public ArticlePageObject clickSavedIconAndClickOverlay()
    {
        this.waitForElementAndClick(SAVED_ICON,"Cannot find button to click button for saving article",5);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,"Cannot find link to init saving article process",5);
        return this;
    }

    public void addArticleToMyList(String savedName)
    {
        this.clickSavedIconAndClickOverlay();
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, savedName,"Cannot find input field for entering text for article",5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON,"Cannot find Ok button to save article",5);
    }

    public ArticlePageObject moveToSpecifiedFolder(String folderName)
    {
        String folderNameFromListXpath = getMyListFolderNameXpath(folderName);
        this.waitForElementAndClick(folderNameFromListXpath,"Cannot find link to init saving article process",5);
        return this;
    }

    public ArticlePageObject moveToListWithSavedArticles()
    {
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,"Cannot find link to init saving article process",5);
        return this;
    }

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
