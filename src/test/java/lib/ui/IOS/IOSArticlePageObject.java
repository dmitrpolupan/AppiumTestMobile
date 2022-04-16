package lib.ui.IOS;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "xpath:test";
        OPTION_ADD_TO_MY_LIST_BUTTON = "xpath:test";
        ADD_TO_MY_LIST_OVERLAY = "xpath:test";
        MY_LIST_NAME_INPUT = "xpath:test";
        MY_LIST_OK_BUTTON = "xpath:test";
        MY_LIST_FOLDER_NAME = "xpath:test";
        FOOTER_ELEMENT = "xpath:test";
    }

    public IOSArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
