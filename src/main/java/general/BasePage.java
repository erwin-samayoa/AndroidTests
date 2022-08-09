package general;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Set;


public class BasePage {
    protected AppiumDriver driver;
    public static final Duration TIMEOUT = Duration.ofSeconds(10);

    //Constructor

    public BasePage(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
        this.driver = driver;
    }

    //Private methods

    private void waitForClick(WebElement elementToWaitFor) {
        WebDriverWait wait = new WebDriverWait(driver,TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(elementToWaitFor));
    }

    private void waitForClick(By element) {
        WebDriverWait wait = new WebDriverWait(driver,TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    private void waitForElement(WebElement elementToWaitFor) {
        WebDriverWait wait = new WebDriverWait(driver,TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
    }

    private void waitForElement(By elementToWaitFor) {
        WebDriverWait wait = new WebDriverWait(driver,TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(elementToWaitFor));
    }

    private void waitForSubElement(By elementToWaitFor, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver,TIMEOUT);
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, elementToWaitFor));
    }

    //Protected methods

    protected void clickElement(WebElement elementToClick) {
        waitForClick(elementToClick);
        elementToClick.click();
    }

    protected void clickElement(By element) {
        waitForClick(element);
        driver.findElement(element).click();
    }

    protected void clearText(WebElement element) {
        waitForElement(element);
        element.clear();
    }

    protected String getTextFromElement(WebElement elementToGet) {
        waitForElement(elementToGet);
        return elementToGet.getText();
    }

    protected void dragXtoYElement(WebElement elementX, WebElement elementY) {
        int startingX = elementX.getLocation().getX();
        int startingY = elementX.getLocation().getY();

        int endingX = elementY.getLocation().getX();
        int endingY = elementY.getLocation().getY();

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

    protected WebElement locateElement(By element) {
        //When constructing locators on the fly
        //This is to ensure the wait on the element before find
        waitForElement(element);
        return driver.findElement(element);

    }

    protected WebElement locateSubElement(By subElement, WebElement element) {
        //When constructing locators on the fly
        //This is to ensure the wait on the element before find
        waitForSubElement(subElement,element);
        return element.findElement(subElement);

    }

    protected void acceptDialog() {
        driver.switchTo().alert().accept();
    }

    protected void uploadFile(String s, File file) throws IOException {
        AndroidDriver driverForUpload = (AndroidDriver) driver;
        driverForUpload.pushFile("/sdcard/Pictures/archivo.png",file);
    }

    protected void setText(WebElement element, String text) {
        waitForElement(element);
        element.sendKeys(text);
    }

    protected AndroidDriver changeContext(String targetContext) {

        AndroidDriver driverForProcessing = (AndroidDriver) driver;
        Set<String> contexts = driverForProcessing.getContextHandles();

        //System.out.println(driver.getContext()); //is NATIVE_APP

        for (String context: contexts) {
            if (context.contains(targetContext)) {
                driverForProcessing.context(context);
            }
            //System.out.println(context);
        }


        return driverForProcessing;
    }

    //Public methods

    public void scrollDown() {
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

    public void setTextToClipboard(String text) {
        AndroidDriver driverForClipboard = (AndroidDriver) driver;
        driverForClipboard.setClipboardText(text);
    }

    public String getTextFromClipboard() {
        AndroidDriver driverForClipboard = (AndroidDriver) driver;
        return driverForClipboard.getClipboardText();
    }

}
