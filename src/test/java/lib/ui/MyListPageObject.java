package lib.ui;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObject extends MainPageObject
{
    protected static String
            MY_LIST_NOT_NOW_BUTTON,
            FOLDER_BY_NAME_TPL,
            ARTICLE_TITLE_TPL,
            REMOVE_FROM_SAVE_BUTTON;

    public MyListPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public MyListPageObject clickNotNowButton()
    {
        if(Platform.getInstance().isAndroid()){
            this.waitForElementAndClick(MY_LIST_NOT_NOW_BUTTON,"Cannot find 'NOT NOW' button",5);
        } else {
            System.out.println("Method clickNotNowButton() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }
        return this;
    }

    public MyListPageObject openSavedArticles(String substring)
    {
        if(Platform.getInstance().isAndroid()){
            String folderNameXpath = getFolderNameXpath(substring);
            this.waitForElementAndClick(folderNameXpath,"Cannot find Saved list",5);
            this.waitForElementPresent(folderNameXpath,"Cannot find saved article",10);
        } else {
            System.out.println("Method openSavedArticles() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }

        return this;
    }

    public MyListPageObject swipeByArticleToDelete(String articleTitle)
    {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleTitleXpath = getArticleTitleXpath(articleTitle);

        if(Platform.getInstance().isMW()) {
            String removeLocator = getRemoveButtonByTitle(articleTitle);
            this.waitForElementAndClick(removeLocator, "Cannot find and click remove button", 5);
            this.waitForElementNotPresent(removeLocator, "Saved icon is not disappeared", 5);
            _driver.navigate().refresh();
        } else {
            this.swipeElementToLeft(articleTitleXpath,"Cannot Swipe element");
            this.waitForArticleToDisappearByTitle(articleTitle);
        }

        return this;
    }

    public MyListPageObject waitForArticleToAppearByTitle(String articleTitle)
    {
        String articleTitleXpath = getArticleTitleXpath(articleTitle);
        this.waitForElementPresent(articleTitleXpath,"Cannot delete saved article",10);
        return this;
    }

    public MyListPageObject waitForArticleToDisappearByTitle(String articleTitle)
    {
        String articleTitleXpath = getArticleTitleXpath(articleTitle);
        this.waitForElementNotPresent(articleTitleXpath, "Cannot delete saved article",10);
        return this;
    }

    public void openArticleByTitle(String title)
    {
        String atricleTitle = getArticleTitleXpath(title);
        this.waitForElementPresent(atricleTitle,"Cannot find saved article - " + title,10);
        this.waitForElementAndClick(atricleTitle,"Cannot find saved article - " + title,10);
    }

    /*TEMPLATE METHODS*/
    private static String getFolderNameXpath(String folderName)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    private static String getArticleTitleXpath(String articleTitle)
    {
        return ARTICLE_TITLE_TPL.replace("{ARTICLE_TITLE}", articleTitle);
    }

    private static String getRemoveButtonByTitle(String articleTitle)
    {
        return REMOVE_FROM_SAVE_BUTTON.replace("{ARTICLE_TITLE}", articleTitle);
    }
    /*TEMPLATE METHODS*/
}
