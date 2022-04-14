package lib.ui.android;
import lib.ui.NavigationUIPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUIPageObject extends NavigationUIPageObject
{
    static
    {
        MY_SAVED_ARTICLES_LINK = "xpath://android.widget.FrameLayout[@content-desc=\"Saved\"]/android.widget.ImageView";
        BACK_BUTTON = "xpath://*[contains(@class, 'ImageButton')]";
    }

    public AndroidNavigationUIPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
