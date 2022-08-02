package tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserTests {
    private AppiumDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        //needed in my device without chrome

        //caps.setCapability("appPackage","org.chromium.webview_shell");
        //caps.setCapability("appActivity","WebViewBrowserActivity");


        caps.setCapability("platformVersion","9.0");

        //Requires to start appium with something like this
        //appium --chromedriver-executable C:\Users\User\chromedriver.exe
        //Seems to also require a device with chrome
        caps.setCapability("browserName","Chrome"); //Android default is Browser

        caps.setCapability("noReset",true); //Beware not setting this to true if you use and app like whatsapp

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);



    }

    @Test
    public void testBrowser1() {
        driver.get("https://testpages.herokuapp.com/styled/find-by-playground-test.html");
        driver.findElement(By.linkText("jump to para 4")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
