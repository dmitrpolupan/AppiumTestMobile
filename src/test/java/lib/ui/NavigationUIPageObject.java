package lib.ui;
import lib.Platform;
import org.apache.xerces.util.SynchronizedSymbolTable;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIPageObject extends MainPageObject
{
    protected static String
        MY_SAVED_ARTICLES_LINK,
        BACK_BUTTON,
        RETURN_PREVIOUS_PAGE,
        OPEN_NAVIGATION;

    public NavigationUIPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public NavigationUIPageObject clickMySavedArticles()
    {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(MY_SAVED_ARTICLES_LINK, "Cannot find saved articles item", 7);
        } else {
            this.waitForElementAndClick(MY_SAVED_ARTICLES_LINK,"Cannot find Saved button in the bottom menu",5);
        }
        return this;
    }

    public NavigationUIPageObject clickBackButton()
    {
        if(!Platform.getInstance().isMW()){
            this.waitForElementAndClick(BACK_BUTTON,"Cannot find Back button in the first time",5);
        }
        return this;
    }

    public NavigationUIPageObject clickBackPreviousPage(){
        if(Platform.getInstance().isMW()){
            this.waitForElementPresent(RETURN_PREVIOUS_PAGE, "Cannot find Back previous page link", 5);
            this.waitForElementAndClick(RETURN_PREVIOUS_PAGE, "Cannot find and click Back previous page link", 5);
        }
        return this;
    }

    public NavigationUIPageObject openNavigation()
    {
        if(Platform.getInstance().isMW()){
            this.waitForElementClickable(OPEN_NAVIGATION, "Open Navigation button is not clickable", 5);
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click Open Navigation", 5);
        } else {
            System.out.println("Method openNavigation() does nothing for Platform - " + Platform.getInstance().getPlatformVar());
        }
        return this;
    }
}
