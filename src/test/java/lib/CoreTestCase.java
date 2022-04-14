package lib;
import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver _driver;
    protected boolean _isPortraitDefaultState = true;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        _driver = Platform.getInstance().getDriver();
        setupOrientation(_isPortraitDefaultState);
        this.openWikiWebPageForMobileWeb();
    }

    @Override
    protected void tearDown() throws Exception
    {
        _driver.quit();

        super.tearDown();
    }

    protected void changeOrientation(ScreenOrientation orientation)
    {
        if(_driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this._driver;
            driver.rotate(orientation);
        } else {
            System.out.println("Method changeOrientation() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void backgroundApp(int seconds)
    {
        if(_driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this._driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Method backgroundApp() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void openWikiWebPageForMobileWeb()
    {
        if(Platform.getInstance().isMW())
        {
            _driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform - " + Platform.getInstance().getPlatformVar());
        }
    }

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
}
