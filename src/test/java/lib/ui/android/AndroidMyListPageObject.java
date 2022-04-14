package lib.ui.android;
import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListPageObject
{
    static
    {
        MY_LIST_NOT_NOW_BUTTON = "xpath://*[@text = 'NOT NOW']";
        FOLDER_BY_NAME_TPL = "xpath://*[@text = '{FOLDER_NAME}']";
        ARTICLE_TITLE_TPL = "xpath://*[@text = '{ARTICLE_TITLE}']";
    }

    public AndroidMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
