package lib.ui.factories;
import lib.Platform;
import lib.ui.IOS.IOSNavigationUIPageObject;
import lib.ui.NavigationUIPageObject;
import lib.ui.android.AndroidNavigationUIPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIPageObjectFactory
{
    public static NavigationUIPageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidNavigationUIPageObject(driver);
        } else {
            return new IOSNavigationUIPageObject(driver);
        }
    }
}
