package lib.ui;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIPageObject extends MainPageObject
{
    protected static String
        MY_SAVED_ARTICLES_LINK,
        BACK_BUTTON;

    public NavigationUIPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void clickMySavedArticles()
    {
        this.waitForElementAndClick(MY_SAVED_ARTICLES_LINK,"Cannot find Saved button in the bottom menu",5);
    }

    public NavigationUIPageObject clickBackButton()
    {
        this.waitForElementAndClick(BACK_BUTTON,"Cannot find Back button in the first time",5);
        return this;
    }
}
