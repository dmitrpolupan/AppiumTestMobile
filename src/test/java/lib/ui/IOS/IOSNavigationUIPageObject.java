package lib.ui.IOS;
import lib.ui.NavigationUIPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUIPageObject extends NavigationUIPageObject
{
    static
    {
        MY_SAVED_ARTICLES_LINK = "xpath:test";
        BACK_BUTTON = "xpath:test";
    }

    public IOSNavigationUIPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
