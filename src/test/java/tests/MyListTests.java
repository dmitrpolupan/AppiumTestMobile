package tests;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for MyList")
public class MyListTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value = "MyList"), @Feature(value = "Search"), @Feature(value = "Authorization"), @Feature(value = "Article")})
    @DisplayName("Save first articles to My List")
    @Description("Check that first articles from result saves to MyList")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(_driver);
        NavigationUIPageObject NavigationUI = NavigationUIPageObjectFactory.get(_driver);
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .clickByArticleWithSubstring("Object-oriented programming language");

        String articleName = "Java (programming language)";
        String savedName = "Learning article";

        ArticlePageObject.waitForTitleElement();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(savedName);
        } else {
            ArticlePageObject.addArticleToMySaved();
            AuthorizationPageObject authorization = new AuthorizationPageObject(_driver);
            authorization
                    .clickAuthButton()
                    .enterLoginData("Dmtestdm", "123456789a1")
                    .submitForm();

            NavigationUI.clickBackPreviousPage();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login.",
                    articleName,
                    ArticlePageObject.getArticleTitle());
        }

        NavigationUI
                .clickBackButton()
                .clickBackButton()
                .openNavigation()
                .clickMySavedArticles();

        MyListPageObject
                .clickNotNowButton()
                .openSavedArticles(savedName)
                .swipeByArticleToDelete(articleName);
    }

    @Test
    @Features(value = {@Feature(value = "MyList"), @Feature(value = "Search"), @Feature(value = "Authorization"), @Feature(value = "Article")})
    @DisplayName("Save two articles and removing one from My List")
    @Description("Check that two articles can be saved to MyList and one from them can be removed")
    @Step("Starting test testSavingTwoArticles")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSavingTwoArticles()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(_driver);
        NavigationUIPageObject NavigationUI = NavigationUIPageObjectFactory.get(_driver);
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(_driver);

        String searchValue = "Java";

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine(searchValue);

        String articleName1 = "JavaScript";
        String articleName2 = "Java (programming language)";
        String folderForSaving = "Learning articles folder";

        SearchPageObject.clickByArticleWithSubstring(articleName1);

        ArticlePageObject.waitForTitleElement();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(folderForSaving);

            NavigationUI.clickBackButton();

            SearchPageObject.clickByArticleWithSubstring(articleName2);

            ArticlePageObject.waitForTitleElement();

            ArticlePageObject
                    .clickSavedIconAndClickOverlay()
                    .moveToSpecifiedFolder(folderForSaving)
                    .moveToListWithSavedArticles();

            MyListPageObject
                    .swipeByArticleToDelete(articleName2)
                    .openArticleByTitle(articleName1);

            String actualTitleName = ArticlePageObject.getArticleTitle();

            Assert.assertEquals("Actual article name is not equal to expected one", articleName1, actualTitleName);
        } else if (Platform.getInstance().isMW()){
            ArticlePageObject.addArticleToMySaved();
            AuthorizationPageObject authorization = new AuthorizationPageObject(_driver);
            authorization
                    .clickAuthButton()
                    .enterLoginData("Dmtestdm", "123456789a1")
                    .submitForm();

            NavigationUI.clickBackPreviousPage();

            ArticlePageObject.waitForTitleElement();

            Assert.assertEquals("We are not on the same page after login.",
                    articleName1,
                    ArticlePageObject.getArticleTitle());

            SearchPageObject
                    .clickSkipButton()
                    .initSearchInput()
                    .typeSearchLine(searchValue)
                    .clickByArticleWithSubstring(articleName2);;

            ArticlePageObject.waitForTitleElement();

            ArticlePageObject.addArticleToMySaved();

            NavigationUI
                    .openNavigation()
                    .clickMySavedArticles();

            MyListPageObject.waitForArticleToAppearByTitle(articleName1);
            MyListPageObject.waitForArticleToAppearByTitle(articleName2);
            MyListPageObject.swipeByArticleToDelete(articleName1);
            MyListPageObject.waitForArticleToDisappearByTitle(articleName1);
            MyListPageObject.waitForArticleToAppearByTitle(articleName2);
        } else {
            System.out.println("Only Android and WebMobile are supported");
        }
    }
}
