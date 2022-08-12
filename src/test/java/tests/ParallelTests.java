package tests;

import PageObjects.MessagePage;
import PageObjects.MessengerPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tools.JsonReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ParallelTests {

    //Doing set up here to not alter CaseTests class
    protected ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();


    protected AndroidDriver setUp(String appiumPort, String udid, String wdaPort) throws MalformedURLException {

        ThreadLocal<DesiredCapabilities> cap = new ThreadLocal<>();
        cap.set(new DesiredCapabilities());
        cap.get().setCapability("platformName","Android");

        cap.get().setCapability("appPackage","com.android.messaging");
        cap.get().setCapability("appActivity",".ui.conversationlist.ConversationListActivity");
        cap.get().setCapability("platformVersion","9.0");
        cap.get().setCapability("udid",udid);
        //There are more capabilities to consider (for Android and for iOS)


        cap.get().setCapability("noReset",true); //Beware not setting this to true if you use and app like whatsapp



        driver.set(new AndroidDriver(new URL("http://localhost:" + appiumPort + "/wd/hub"),cap.get()));
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        return driver.get();

    }

    //@AfterTest
    public void tearDown() {
        //I dont know how to manage this in PoM
        //Considering the setUp method (in this same class) instantiated the driver
        if (driver.get() != null) {
            driver.get().quit();
        }
    }

    @DataProvider(name = "sms data")
    public Object[][] passData() throws IOException, ParseException {
        return JsonReader.getJsonData(System.getProperty("user.dir") + "/data/SMSData.json");
    }

    @Test(dataProvider = "sms data")
    public void testSMS1(String phoneNumber, String message) throws MalformedURLException {

        //PoM
        MessengerPage messenger = new MessengerPage(setUp("10001","emulator-5554","11001"));


        messenger.sendSMS(phoneNumber,message);

        //Unconditional wait for the app and message to appear
        //Before asserting
        messenger.waitSeconds(10);


        assertEquals(messenger.getMessageFrom(1),message);
        MessagePage messagePage = messenger.clickMessage(1);

        messagePage.clickMoreOptions();

        messagePage.clickDeleteOption();

        messagePage.confirmDeletion();

        tearDown(); //Cleaning up here to avoid weir behaviour if managed by testng @AfterTest
    }

    @Test(dataProvider = "sms data")
    public void testSMS2(String phoneNumber, String message) throws MalformedURLException {

        //PoM
        MessengerPage messenger = new MessengerPage(setUp("10002","emulator-5556","11001"));


        messenger.sendSMS(phoneNumber,message);

        //Unconditional wait for the app and message to appear
        //Before asserting
        messenger.waitSeconds(10);

        assertEquals(messenger.getMessageFrom(1),message);
        MessagePage messagePage = messenger.clickMessage(1);

        messagePage.clickMoreOptions();

        messagePage.clickDeleteOption();

        messagePage.confirmDeletion();

        tearDown(); //Cleaning up here to avoid weir behaviour if managed by testng @AfterTest
    }

}
