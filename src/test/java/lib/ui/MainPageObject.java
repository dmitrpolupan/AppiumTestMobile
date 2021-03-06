package lib.ui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.qameta.allure.Attachment;
import lib.Platform;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject
{
    protected RemoteWebDriver _driver;

    public MainPageObject(RemoteWebDriver driver)
    {
        _driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String errorMessage, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(_driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementClickable(String locator, String errorMessage, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(_driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void tryClickElementWithFewAttempts(String locator, String errorMessage, int amountOfAttempts)
    {
        int currentAttempt = 0;
        boolean needMoreAttempt = true;

        while (needMoreAttempt){
            try {
                this.waitForElementAndClick(locator, errorMessage, 1);
                needMoreAttempt = false;
            } catch (Exception e){
                if(currentAttempt > amountOfAttempts){
                    this.waitForElementAndClick(locator, errorMessage, 1);
                }
            }
            ++currentAttempt;
        }
    }

    public WebElement waitForElementPresent(String locator, String errorMessage)
    {
        return waitForElementPresent(locator, errorMessage, 5);
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(_driver, timeoutInSeconds);
        wait.withMessage(errorMessage);
        return  wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void assertElementHasText(String locator, String expectedText, String errorMessage)
    {
        WebElement element = waitForElementPresent(locator, "Cannot find element for test", 5);
        String elementText = Platform.getInstance().isMW() ? element.getAttribute("aria-label") : element.getAttribute("text");
        Assert.assertEquals(errorMessage, expectedText, elementText);
    }

    public int getCountOfWebElements(String locator, String errorMessage, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        List<WebElement> list = _driver.findElements(by);
        return list.size();
    }

    public void scrollWebPageUp(){
        if(Platform.getInstance().isMW()){
            JavascriptExecutor jsExec = (JavascriptExecutor) _driver;
            jsExec.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator){
        int elementLocationByY = this.waitForElementPresent(locator, "Cannot find element by locator", 10).getLocation().getY();
        if (Platform.getInstance().isMW()){
            JavascriptExecutor jsExec = (JavascriptExecutor) _driver;
            Object jsResult = jsExec.executeScript("return window.pageYOffset");
            elementLocationByY -= Integer.parseInt(jsResult.toString());
        }
        int screenSizeByWindow = _driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByWindow;
    }

    public void scrollWebPageTillElementNotVisible(String locator, String errorMessage, int maxSwipes){
        int alreadySwiped = 0;

        WebElement element = this.waitForElementPresent(locator, errorMessage);
        while (!this.isElementLocatedOnTheScreen(locator)){
            scrollWebPageUp();
            ++alreadySwiped;
            if(alreadySwiped > maxSwipes){
                Assert.assertTrue(errorMessage, element.isDisplayed());
            }
        }
    }

    public boolean isElementDisplayed(String locator)
    {
        By by = this.getLocatorByString(locator);
        boolean flag = true;
        try
        {
            _driver.findElement(by);
        }
        catch(Exception e)
        {
            flag = false;
        }

        return flag;
    }

    public boolean isElementPresent(String locator)
    {
        return getCountOfWebElements(locator, "Cannot find element", 5) > 0;
    }

    public List<String> getArticlesName(String locator, String errorMessage, long timeoutInSeconds)
    {
        By by = this.getLocatorByString(locator);
        waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        List<WebElement> list = _driver.findElements(by);
        List<String> namesList = new ArrayList<>();
        for(WebElement element : list)
        {
            if(Platform.getInstance().isMW()){
                namesList.add(element.getAttribute("title"));
            }
            else {
                namesList.add(element.getAttribute("text"));
            }
        }
        return namesList;
    };

    public void swipeUp(int timeOfSwipe)
    {
        if(_driver instanceof AppiumDriver){
            TouchAction action = new TouchAction((AppiumDriver)_driver);
            Dimension size = _driver.manage().window().getSize();
            int x = size.width / 2;
            int startY = (int) (size.height * 0.8);
            int endY = (int) (size.height * 0.2);

            action
                    .press(x, startY)
                    .waitAction(timeOfSwipe)
                    .moveTo(x, endY)
                    .release()
                    .perform();
        } else {
            System.out.println("Method backgroundApp() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpQuick()
    {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes)
    {
        By by = this.getLocatorByString(locator);
        int alreadySwipes = 0;
        while(_driver.findElements(by).size() == 0)
        {
            if(alreadySwipes > maxSwipes)
            {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwipes;
        }
    }

    public void swipeElementToLeft(String locator, String errorMessage)
    {
        if(_driver instanceof AppiumDriver){
            WebElement element = waitForElementPresent(
                    locator,
                    errorMessage,
                    5
            );
            int leftX = element.getLocation().getX();
            int rightX = leftX + element.getSize().getWidth();
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();
            int middleY = (upperY + lowerY) / 2;

            TouchAction action = new TouchAction((AppiumDriver)_driver);
            action
                    .press(rightX, middleY)
                    .waitAction(300)
                    .moveTo(leftX, middleY)
                    .release()
                    .perform();
        } else {
            System.out.println("Method swipeElementToLeft() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }
    }

    public void assertElementNotPresent(String locator, String errorMessage)
    {
        int amountOfElements = getCountOfWebElements(locator, errorMessage, 5);
        if(amountOfElements > 0)
        {
            String defaultMessage = "An element '" + locator + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeoutSeconds)
    {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutSeconds);
        return element.getAttribute(attribute);
    }

    private By getLocatorByString(String locatorWithType)
    {
        String[] expandedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = expandedLocator[0];
        String locator = expandedLocator[1];

        if(byType.equals("xpath"))
        {
            return By.xpath(locator);
        }
        else if(byType.equals("id"))
        {
            return By.id(locator);
        }
        else if(byType.equals("css"))
        {
            return By.cssSelector(locator);
        }
        else
        {
            throw new IllegalArgumentException("Cannot get type of locator. Locator - " + locator);
        }
    }

    public String takeScreenshot(String name)
    {
        TakesScreenshot ts = (TakesScreenshot) _driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/" + name + "_screenshot.png";
        try{
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken: " + path);
        } catch (Exception e){
            System.out.println("CAnnot take screenshot. Error: " + e.getMessage());
        }

        return path;
    }

    @Attachment
    public static byte[] screenshot(String path)
    {
        byte[] bytes = new byte[0];

        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Cannot get bytes from screenshot. Error: " + e.getMessage());
        }
        return bytes;
    }
}
