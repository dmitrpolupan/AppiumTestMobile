package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject
{
    static
    {
        MY_LIST_NOT_NOW_BUTTON = "xpath://*[@text = 'NOT NOW']";
        FOLDER_BY_NAME_TPL = "xpath://*[@text = '{FOLDER_NAME}']";
        ARTICLE_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{ARTICLE_TITLE}')]";
        REMOVE_FROM_SAVE_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), 'Java (programming language)')]/../../a[contains(@class, 'watched')]";
    }

    public MWMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
