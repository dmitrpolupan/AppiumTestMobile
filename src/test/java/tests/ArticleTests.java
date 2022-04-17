package tests;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Compare displayed article name with expected one")
    @Description("Check that actual article title is displayed as expected one")
    @Step("Starting test testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    @Link("https://some_test_link_to_test.org")
    @Issue("ISSUE-12345")
    @Story("Added story tag to check")
    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .clickByArticleWithSubstring("Object-oriented programming language");

        String articleTitle = ArticlePageObject.getArticleTitle();

        //ArticlePageObject.takeScreenshot("article_page");

        Assert.assertEquals("Article header is not equal", "Java (programming language)", articleTitle);
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Swipe article to the footer")
    @Description("Swipe article to footer and check that it works")
    @Step("Starting test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
    @Issue("ISSUE-12345")
    @Story("Added story tag to check")
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Appium")
                .clickByArticleWithSubstring("Appium");

        ArticlePageObject.waitForTitleElement();

        ArticlePageObject.swipeToFooter();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Title element exists for opened article")
    @Description("Open article and check is title exist")
    @Step("Starting test testCheckTitleElementExist")
    @Severity(value = SeverityLevel.CRITICAL)
    @TmsLink("TMSLINK-12345")
    @Story("Added story tag to check")
    public void testCheckTitleElementExist()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .clickByArticleWithSubstring("Object-oriented programming language");

        //for testing
        //try
        //{
        //    Thread.sleep(1500);
        //}
        //catch(InterruptedException e)
        //{
        //}

        boolean isArticleTitlePresent = ArticlePageObject.isArticleTitlePresent();

        Assert.assertTrue("Article title has not been displayed at the moment of checking", isArticleTitlePresent);
    }
}
