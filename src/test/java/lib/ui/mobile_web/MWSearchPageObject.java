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
        SEARCH_RESULT = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]"; //+
        NO_RESULTS_LABEL = "css:p.without-results"; //+
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary"; //+
        SEARCH_RESULT_ARTICLES_NAMES = "css:ul.page-list>li.page-summary"; //+
        SEARCH_RESULT_LOCATOR_FOR_TITLE_DESCRIPTION_TPL = "xpath://*[contains(@resource-id, 'page_list_item_title')][contains(@text, '{TITLE}')]//parent::*//*[contains(@resource-id, 'page_list_item_description')][contains(@text, '{DESCRIPTION}')]";
    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
