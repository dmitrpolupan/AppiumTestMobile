package lib;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.util.Properties;

public class CoreTestCase{

    protected RemoteWebDriver _driver;
    protected boolean _isPortraitDefaultState = true;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception
    {
        _driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        setupOrientation(_isPortraitDefaultState);
        this.openWikiWebPageForMobileWeb();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown() throws Exception
    {
        _driver.quit();
    }

    @Step("Change Orientation")
    protected void changeOrientation(ScreenOrientation orientation)
    {
        if(_driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this._driver;
            driver.rotate(orientation);
        } else {
            System.out.println("Method changeOrientation() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Move app to background")
    protected void backgroundApp(int seconds)
    {
        if(_driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this._driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Method backgroundApp() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Open wiki WebPage for Mobile Web (this method does nothing for Android and iOS)")
    protected void openWikiWebPageForMobileWeb()
    {
        if(Platform.getInstance().isMW())
        {
            _driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Set orientation")
    private void setupOrientation(boolean isPortrait)
    {
        if(!Platform.getInstance().isMW()){
            if(isPortrait)
            {
                this.changeOrientation(ScreenOrientation.PORTRAIT);
            }
            else
            {
                this.changeOrientation(ScreenOrientation.LANDSCAPE);
            }
        }
    }

    private void createAllurePropertyFile()
    {
        String path = System.getProperty("allure.results.directory");
        try{
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See details on allure github section environment");
            fos.close();
        } catch (Exception e){
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}
