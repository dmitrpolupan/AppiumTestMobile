package lib.ui.mobile_web;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject
{
    static
    {
        TITLE = "css:#content h1"; //+
        OPTION_ADD_TO_MY_LIST_BUTTON = "css:#page-actions a#ca-watch.watch-this-article"; //+
        ADD_TO_MY_LIST_OVERLAY = "id:snackbar_action";
        MY_LIST_NAME_INPUT = "id:text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']";
        MY_LIST_FOLDER_NAME = "xpath://*[@resource-id='org.wikipedia:id/lists_container']//*[@text='{FOLDER_NAME}']";
        FOOTER_ELEMENT = "css:footer.mw-footer"; //+
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions [title='Remove this page from your watchlist']";
    }

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
