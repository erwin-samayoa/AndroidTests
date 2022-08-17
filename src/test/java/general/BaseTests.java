package general;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTests extends AbstractTestNGCucumberTests {
    //private AppiumDriver driver; //For general use
    protected AndroidDriver driver; //For SMS receiving simulation, clipboard maybe

    //@BeforeTest
    protected AppiumDriver setUp(String app, String... activity) throws MalformedURLException {

        AppiumDriver driver; //For general use
        //private AndroidDriver driver; //For SMS receiving simulation, clipboard maybe

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName","Android");

        switch (activity.length) {
            case 0: //APK
                cap.setCapability("app",System.getProperty("user.dir") + "/apps/" + app);
                cap.setCapability("platformVersion","9.0"); //Emulator
                break;
            case 1: //App already installed (requires Activity)
                cap.setCapability("appPackage",app);
                cap.setCapability("appActivity",activity[0]);
                cap.setCapability("platformVersion","9.0");
                break;
            case 2:
            default: //App already installed and specific Android version
                cap.setCapability("appPackage",app);
                cap.setCapability("appActivity",activity[0]);
                cap.setCapability("platformVersion",activity[1]); //Emulator
                break;
        }

        cap.setCapability("noReset",true); //Beware not setting this to true if you use and app like whatsapp



        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),cap);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        return driver; //Still dont know how to isolate this "link"

    }

    //@AfterTest
    public void tearDown() {
        //I dont know how to manage this in PoM
        //Considering the setUp method (in this same class) instantiated the driver
        if (driver != null) {
            driver.quit();
        }
    }
}
