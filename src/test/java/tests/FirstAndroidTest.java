package tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;

public class FirstAndroidTest {
    private AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName","Android");
        cap.setCapability("platformVersion","9.0");
        //cap.setCapability("app",System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        cap.setCapability("appPackage","com.android.calculator2");
        cap.setCapability("appActivity",".Calculator");



        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),cap);


    }

    //uiautomationviewer requires PATH to contain: C:\Program Files\Android\Android Studio\jre\bin
    @Test
    public void firstApiTest() {
        driver.findElement(AppiumBy.accessibilityId("Graphics")).click();
        System.out.println("Response is: ");
        assertEquals(driver.findElement(AppiumBy.accessibilityId("Arcs")).getText(),"Arcs");
    }

    @Test
    public void secondCalcTest7x7() {
        driver.findElement(AppiumBy.id("digit_7")).click();
        driver.findElement(AppiumBy.id("op_mul")).click();
        driver.findElement(AppiumBy.id("digit_7")).click();

        assertEquals(driver.findElement(AppiumBy.id("result")).getText(),"49");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
