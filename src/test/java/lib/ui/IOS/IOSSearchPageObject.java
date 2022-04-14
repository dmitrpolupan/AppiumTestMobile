package lib.ui.IOS;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject
{
    static
    {
        SKIP_BUTTON = "xpath:test";
        SEARCH_INPUT_FIELD = "xpath:test";
        SEARCH_INIT_ELEMENT = "xpath:test";
        SEARCH_RESULT_LOCATOR = "xpath:test";
        SEARCH_INPUT = "xpath:test";
        SEARCH_CANCEL_BUTTON = "xpath:test";
        SEARCH_RESULT = "xpath:test";
        NO_RESULTS_LABEL = "xpath:test";
        SEARCH_RESULT_ELEMENT = "xpath:test";
        SEARCH_RESULT_ARTICLES_NAMES = "xpath:test";
        SEARCH_RESULT_LOCATOR_FOR_TITLE_DESCRIPTION_TPL = "xpath:test";
    }

    public IOSSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
