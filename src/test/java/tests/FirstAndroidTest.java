package tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class FirstAndroidTest {
    private AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName","Android");

        //cap.setCapability("platformVersion","9.0");
        //cap.setCapability("app",System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        //cap.setCapability("appPackage","com.android.calculator2");
        //cap.setCapability("appActivity",".Calculator");


        cap.setCapability("platformVersion","11");
        cap.setCapability("appPackage","com.whatsapp");
        cap.setCapability("appActivity",".HomeActivity");

        cap.setCapability("noReset",true); //Beware not setting this to true if you use and app like whatsapp



        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),cap);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


    }

    //uiautomationviewer requires PATH to contain: C:\Program Files\Android\Android Studio\jre\bin
    @Test
    public void firstApiTest() {
        driver.findElement(AppiumBy.accessibilityId("Graphics")).click();
        System.out.println("Response is: ");
        assertEquals(driver.findElement(AppiumBy.accessibilityId("Arcs")).getText(),"Arcs");
    }

    @Test
    public void secondApiTest() {
        AppiumBy views = (AppiumBy) AppiumBy.accessibilityId("Views");
        AppiumBy lists = (AppiumBy) AppiumBy.accessibilityId("Lists");

        driver.findElement(views).click();

        Dimension windowSize = driver.manage().window().getSize();
        int dragSource = (int) (windowSize.getHeight() * 0.8);
        int dragDestination = (int) (windowSize.getHeight() * 0.2);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");

        //Preparing sequence to execute
        Sequence sequence = new Sequence(finger,1);

        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),0,dragSource));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(500),PointerInput.Origin.viewport(),0,dragDestination));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Executing sequence two times (only one is not enough for the assert)
        driver.perform(Arrays.asList(sequence));
        //Missing a timer when the finger is going from up to down not touching the screen
        driver.perform(Arrays.asList(sequence));

        //Verifying if "Lists" element is present
        assertEquals(driver.findElement(lists).getText(),"Lists");

    }

    @Test
    public void secondCalcTest7x7() {
        driver.findElement(AppiumBy.id("digit_7")).click();
        driver.findElement(AppiumBy.id("op_mul")).click();
        driver.findElement(AppiumBy.id("digit_7")).click();

        assertEquals(driver.findElement(AppiumBy.id("result")).getText(),"49");
    }

    @Test
    public void testWhatsApp() {
        //Not yet working
        String contactToSend = "yyy";
        driver.findElement(AppiumBy.accessibilityId("Buscar")).click();
        driver.findElement(AppiumBy.id("search_input")).sendKeys(contactToSend);
        WebElement contact = null;

        List<WebElement> elements = driver.findElements(AppiumBy.id("conversations_row_contact_name"));

        //Locating contact from list (even filtered driver detects full list)
        for(WebElement element: elements) {

            if (element.getText().toLowerCase().contains(contactToSend.toLowerCase())) {
                contact = element;
                System.out.println(element.getText());
                break;
            }
        }

        if (contact != null) {

                contact.click();

            for(int i = 0; i<10;i++) {
                driver.findElement(AppiumBy.id("com.whatsapp:id/entry")).sendKeys("Prueba " + contactToSend + " es chotito " + (i+1));

                driver.findElement(AppiumBy.accessibilityId("Enviar")).click();

            }


        }

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
