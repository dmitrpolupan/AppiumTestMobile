package tests;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;

@Epic("Tests for application conditions")
public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value = "ApplicationConditions"), @Feature(value = "Search")})
    @DisplayName("Changing screen orientation on result page")
    @Description("Check that result page displays correctly after screen orientation was changed")
    @Step("Starting test testChangeScreenOrientationOnScreenResults")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testChangeScreenOrientationOnScreenResults()
    {
        if(Platform.getInstance().isMW()){
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(_driver);

        String searchValue = "Java";

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine(searchValue)
                .clickByArticleWithSubstring("Object-oriented programming language");


        String titleBeforeRotation = ArticlePageObject.getArticleTitle();

        this.changeOrientation(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals("Article title have been changes after screen rotation", titleBeforeRotation, titleAfterRotation);

        this.changeOrientation(ScreenOrientation.PORTRAIT);

        String titleAfterSecondRotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals("Article title have been changes after the second screen rotation", titleBeforeRotation, titleAfterSecondRotation);
    }

    @Test
    @Features(value = {@Feature(value = "ApplicationConditions"), @Feature(value = "Search")})
    @DisplayName("Search result displays after background")
    @Description("Check that result displays correctly after app goes in background and returned back")
    @Step("Starting test testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckSearchArticleInBackground()
    {
        if(Platform.getInstance().isMW()){
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .waitForSearchResult("Object-oriented programming language");

        this.backgroundApp(3);

        //SearchPageObject.waitForSearchResult("Object-oriented programming language"); //it looks that wikipedia has bug and results are not restored
    }

    @Test
    @Features(value = {@Feature(value = "ApplicationConditions"), @Feature(value = "Search")})
    @DisplayName("Search result displays after screen rotation")
    @Description("Check that result displays correctly after screen rotation")
    @Step("Starting test testRotation")
    @Severity(value = SeverityLevel.NORMAL)
    public void testRotation()
    {
        if(Platform.getInstance().isMW()){
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .waitForSearchResult("Object-oriented programming language");

        this.changeOrientation(ScreenOrientation.LANDSCAPE);

        SearchPageObject.waitForSearchResult("Java");

        this.changeOrientation(ScreenOrientation.PORTRAIT);

        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
