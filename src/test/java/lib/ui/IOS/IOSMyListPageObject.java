package lib.ui.IOS;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListPageObject extends MyListPageObject
{
    static
    {
        MY_LIST_NOT_NOW_BUTTON = "xpath:test";
        FOLDER_BY_NAME_TPL = "xpath:test";
        ARTICLE_TITLE_TPL = "xpath:test";
    }

    public IOSMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
