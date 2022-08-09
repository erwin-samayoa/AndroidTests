package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MessagePage extends BasePage {

    @AndroidFindBy(accessibility = "More options")
    WebElement moreOptionsElement;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete']")
    WebElement deleteOptionElement;

    @AndroidFindBy(id = "message_text")
    WebElement messageElement;

    @AndroidFindBy(id = "compose_message_text")
    WebElement messageToSendElement;

    public MessagePage(AppiumDriver driver) {
        super(driver);
    }

    public void clickMoreOptions() {
        clickElement(moreOptionsElement);
    }

    public void clickDeleteOption() {
        clickElement(deleteOptionElement);
    }

    public void confirmDeletion() {
        acceptDialog();
    }

    public String getMessage() {
        return getTextFromElement(messageElement);
    }

    public String getMessageToSend() {
        return getTextFromElement(messageToSendElement);
    }

    public void setMessageToSend(String message) {
        setText(messageToSendElement,message);
    }

    public void clearMessageToSend() {
        clearText(messageToSendElement);
    }


}
