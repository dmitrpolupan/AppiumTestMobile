package lib.ui.factories;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.IOS.IOSArticlePageObject;
import lib.ui.android.AndroidArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory
{
    public static ArticlePageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);
        } else {
            return new IOSArticlePageObject(driver);
        }
    }
}