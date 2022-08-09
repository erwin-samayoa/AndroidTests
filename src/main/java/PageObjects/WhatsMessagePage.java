package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class WhatsMessagePage extends BasePage {

    @AndroidFindBy(id = "entry")
    WebElement messageBoxElement;

    @AndroidFindBy(accessibility = "Enviar")
    WebElement sendButtonElement;

    public WhatsMessagePage(AppiumDriver driver) {
        super(driver);
    }

    public void clickSendButton() {
        clickElement(sendButtonElement);
    }

    public void sendMessage(String text) {
        setText(messageBoxElement,text);
        clickSendButton();
    }
}
