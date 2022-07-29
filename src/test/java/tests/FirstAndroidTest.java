package tests;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class FirstAndroidTest {
    //private AppiumDriver driver; //For general use
    private AndroidDriver driver; //For SMS receiving simulation

    //@BeforeTest
    public void setUp(String app, String... activity) throws MalformedURLException {
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


    }

    //uiautomationviewer requires PATH to contain: C:\Program Files\Android\Android Studio\jre\bin
    @Test
    public void testDemo1() throws MalformedURLException {
        setUp("ApiDemos-debug.apk");
        driver.findElement(AppiumBy.accessibilityId("Graphics")).click();
        //System.out.println("Response is: ");
        assertEquals(driver.findElement(AppiumBy.accessibilityId("Arcs")).getText(),"Arcs");
    }

    private void scrollDown() {
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

    }

    @Test
    public void testDemo2() throws MalformedURLException {
        setUp("ApiDemos-debug.apk");
        AppiumBy views = (AppiumBy) AppiumBy.accessibilityId("Views");
        AppiumBy lists = (AppiumBy) AppiumBy.accessibilityId("Lists");

        driver.findElement(views).click();

        scrollDown();
        //Missing a timer when the finger is going from up to down not touching the screen
        scrollDown();

        //Verifying if "Lists" element is present
        assertEquals(driver.findElement(lists).getText(),"Lists");

    }

    @Test
    public void testDemoDragAndDrop() throws MalformedURLException {
        setUp("ApiDemos-debug.apk");
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        int startingX = driver.findElement(AppiumBy.id("drag_dot_1")).getLocation().getX();
        int startingY = driver.findElement(AppiumBy.id("drag_dot_1")).getLocation().getY();

        int endingX = driver.findElement(AppiumBy.id("drag_dot_2")).getLocation().getX();
        int endingY = driver.findElement(AppiumBy.id("drag_dot_2")).getLocation().getY();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence sequence = new Sequence(finger,1);

        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),startingX,startingY));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(1500),PointerInput.Origin.viewport(),endingX,endingY));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        //Going back to the begining (just as a "sleep")
        //sequence.addAction(finger.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),startingX,startingY));

        driver.perform(Arrays.asList(sequence));
    }

    @Test
    public void testDemoMoveGallery() throws MalformedURLException {
        setUp("ApiDemos-debug.apk");
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        scrollDown();
        assertEquals(driver.findElement(AppiumBy.accessibilityId("Gallery")).getText(),"Gallery");
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();

        int startingX = driver.findElements(AppiumBy.className("android.widget.ImageView")).get(1).getLocation().getX();
        int startingY = driver.findElements(AppiumBy.className("android.widget.ImageView")).get(1).getLocation().getY();

        int endingX = driver.findElement(AppiumBy.className("android.widget.Gallery")).getLocation().getX() + 2;
        int endingY = driver.findElement(AppiumBy.className("android.widget.Gallery")).getLocation().getY() + 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        Sequence sequence = new Sequence(finger,1);

        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport(),startingX,startingY));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(1500),PointerInput.Origin.viewport(),endingX,endingY));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        //Going back to the begining (just as a "sleep")
        //sequence.addAction(finger.createPointerMove(Duration.ofMillis(1000),PointerInput.Origin.viewport(),startingX,startingY));

        driver.perform(Arrays.asList(sequence));
    }

    @Test
    public void secondCalcTest7x7() throws MalformedURLException {
        setUp("com.android.calculator2",".Calculator");
        driver.findElement(AppiumBy.id("digit_7")).click();
        driver.findElement(AppiumBy.id("op_mul")).click();
        driver.findElement(AppiumBy.id("digit_7")).click();

        assertEquals(driver.findElement(AppiumBy.id("result")).getText(),"49");
    }

    @Test
    public void testSMS() throws MalformedURLException {
        setUp("com.android.messaging",".ui.conversationlist.ConversationListActivity");

        String phoneNumber = "1235623571";
        String message = "hola1";

        driver.sendSMS(phoneNumber,message);
        assertEquals(driver.findElements(AppiumBy.id("conversation_snippet")).get(0).getText(),message);
    }

    @Test
    public void testPic() throws IOException {
        setUp("com.android.gallery3d",".app.Gallery");

        By appGeneralViewElement = AppiumBy.id("gl_root_view");
        By photoViewElement = AppiumBy.id("photopage_bottom_controls");

        File archivo = new File("C:\\Users\\User\\Downloads\\logo-TAU-gold-poweredby-applitools-467x105.png");
        driver.pushFile("/sdcard/Pictures/archivo.png",archivo);

        var wait = new WebDriverWait(driver,Duration.ofSeconds(20));

        wait.until(ExpectedConditions.presenceOfElementLocated(appGeneralViewElement)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(photoViewElement));



    }

    @Test
    public void testWhatsApp() throws MalformedURLException {

        setUp("com.whatsapp",".HomeActivity","11");

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
