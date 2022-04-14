package lib.ui.factories;
import lib.Platform;
import lib.ui.IOS.IOSMyListPageObject;
import lib.ui.MyListPageObject;
import lib.ui.android.AndroidMyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObjectFactory
{
    public static MyListPageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidMyListPageObject(driver);
        } else {
            return new IOSMyListPageObject(driver);
        }
    }
}
