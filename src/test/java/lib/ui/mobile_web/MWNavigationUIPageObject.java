package lib.ui.mobile_web;
import lib.ui.NavigationUIPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUIPageObject extends NavigationUIPageObject
{
    static
    {
        MY_SAVED_ARTICLES_LINK = "css:a[data-event-name='menu.unStar']"; //*
        BACK_BUTTON = "xpath:test";
        RETURN_PREVIOUS_PAGE = "css:p#centralauth-backlink-section"; //*
        OPEN_NAVIGATION = "css:label#mw-mf-main-menu-button"; //*
    }

    public MWNavigationUIPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
