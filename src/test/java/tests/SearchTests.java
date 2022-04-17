package tests;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import javafx.print.PageLayout;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for searching")
public class SearchTests extends CoreTestCase
{
    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Searching results")
    @Description("Check that search result is displayed")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Canceling search results")
    @Description("Check that search result is canceled after canceling")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .waitForCancelButtonToAppear()
                .clickCancelButton()
                .waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Correct amount results are displayed")
    @Description("Check that correct amount of results are displaying after search")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);

        String searchValue = "Linkin Park Diskography";

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine(searchValue);

        int amountOfFoundArticles = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue("We didn't find results", amountOfFoundArticles > 0);
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Correct page if user performs empty search")
    @Description("Check that correct page and label are displayed after user performs search which returns no result")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);

        String searchValue = "tefgnfgfst";

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine(searchValue)
                .waitForEmptyResultsLabel()
                .assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Search field has informational text")
    @Description("Checking that Search field has expected informational text")
    @Step("Starting test testElementHasText")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testElementHasText()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);

        SearchPageObject.clickSkipButton();

        if(Platform.getInstance().isMW()){
            SearchPageObject.initSearchInput();
        }

        SearchPageObject.asserElementHasText("Search Wikipedia");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Canceling results works correctly")
    @Description("Checking that results are displayed after searching and disappear after cancelling")
    @Step("Starting test testCancelSearchResults")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCancelSearchResults()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Selenium");

        int countOfArticles = SearchPageObject.getSearchResultCount();

        Assert.assertTrue("Search results is not displayed after search", countOfArticles > 0);

        SearchPageObject
                .clickCancelButton()
                .waitCloseButtonIsDisappeared();

        boolean isResultDisplayed = SearchPageObject.isSearchResultDisplayed();

        Assert.assertFalse("Search result is still displayed after clear search field", isResultDisplayed);
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Each item has search word")
    @Description("Checking that each item in search result contains search word")
    @Step("Starting test testEachItemContainsSearchValue")
    @Severity(value = SeverityLevel.NORMAL)
    public void testEachItemContainsSearchValue()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);

        String searchValue = "Java";

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine(searchValue);

        boolean isAllNamesContainSearchValue = SearchPageObject.isResultItemsContainsSearchValue(searchValue);

        Assert.assertTrue("Not all result line contains '" + searchValue + "' search value ", isAllNamesContainSearchValue);
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Items have expected title and description")
    @Description("Checking that first thee items from search results have expected title and description")
    @Step("Starting test testResultItemsContainsSpecifiedTitlesAndDescriptions")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testResultItemsContainsSpecifiedTitlesAndDescriptions()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);

        String query = "java";
        String title1 = "Java";
        String description1 = "Island in Southeast Asia";
        String title2 = "JavaScript";
        String description2 = "High-level programming language";
        String title3 = "Java (programming language)";
        String description3 = "Object-oriented programming language";

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine(query)
                .waitForElementByTitleAndDescription(title1, description1)
                .waitForElementByTitleAndDescription(title2, description2)
                .waitForElementByTitleAndDescription(title3, description3);
    }
}
