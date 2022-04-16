package lib.ui.mobile_web;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject
{
    static
    {
        SEARCH_INPUT_FIELD = "css:form>input.search"; //+
        SEARCH_INIT_ELEMENT = "css:button#searchIcon"; //+
        SEARCH_RESULT_LOCATOR = "css:ul.page-list>li.page-summary";
        SEARCH_INPUT = "css:form>input[type=search]"; //+
        SEARCH_CANCEL_BUTTON = "css:div>button.cancel"; //+
        SEARCH_RESULT = "xpath://li[contains(@class, 'page-summary')]//*[contains(@data-title, '{SUBSTRING}') or contains(text(), '{SUBSTRING}')]";
        NO_RESULTS_LABEL = "css:p.without-results"; //+
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary"; //+
        SEARCH_RESULT_ARTICLES_NAMES = "css:ul.page-list>li.page-summary"; //+
        SEARCH_RESULT_LOCATOR_FOR_TITLE_DESCRIPTION_TPL = "xpath://li[contains(@class, 'page-summary')]//a[@data-title='{TITLE}']//div[text() = '{DESCRIPTION}']"; //*
    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
