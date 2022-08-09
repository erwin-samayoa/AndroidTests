package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MessengerPage extends BasePage {

    @AndroidFindBy(id = "swipeableContent")
    List<WebElement> conversations;

    //By messageElement = AppiumBy.id("message_text");
    //By boxElement = AppiumBy.id("compose_message_text");

    public MessengerPage(AppiumDriver driver) {
        super(driver);
    }

    public void sendSMS(String number, String message) {
        AndroidDriver driverForSMS = (AndroidDriver) driver;
        driverForSMS.sendSMS(number,message);
    }

    /**
     *
     * @param messageNumber starts at 1 for first element
     */
    public String getMessageFrom(int messageNumber) {
        WebElement elementToProcess = locateSubElement(AppiumBy.id("conversation_snippet"),conversations.get(messageNumber-1));
        return getTextFromElement(elementToProcess);
    }

    /**
     *
     * @param messageNumber starts at 1 for first element
     */
    public MessagePage clickMessage(int messageNumber) {
        clickElement(conversations.get(messageNumber-1));
        return new MessagePage(driver);
    }

    public int findMessageFrom(String text) {
        int valueToReturn = 0;
        int i = 1;
        for(WebElement element: conversations) {
            WebElement elementToProcess = locateSubElement(AppiumBy.id("conversation_name"),element);
            if (getTextFromElement(elementToProcess).contains(text)) {
                valueToReturn = i;
            }
            i++;
        }
        return valueToReturn;
    }

}
