package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WhatsAppMainPage extends BasePage {

    @AndroidFindBy(accessibility = "Buscar")
    WebElement searchElement;

    @AndroidFindBy(id = "search_input" )
    WebElement searchBoxElement;

    @AndroidFindBy(id = "conversations_row_contact_name")
    List<WebElement> conversations;

    public WhatsAppMainPage(AppiumDriver driver) {
        super(driver);
    }

    private void searchMode() {
        clickElement(searchElement);
    }

    public void search(String text) {
        searchMode();
        setText(searchBoxElement,text);
    }

    /**
     *
     * @param text is the contact to search in list of conversations
     * @return starts at 1
     */
    public int findMessageFrom(String text) {
        int valueToReturn = 0;
        int i = 1;
        //Locating contact from list (even filtered driver detects full list)
        for(WebElement element: conversations) {

            if (element.getText().toLowerCase().contains(text.toLowerCase())) {
                valueToReturn = i;
                System.out.println(element.getText());
                break;
            }
            i++;
        }
        return valueToReturn;
    }

    /**
     *
     * @param messageNumber starts at 1 for first element
     */
    public WhatsMessagePage clickMessage(int messageNumber) {
        clickElement(conversations.get(messageNumber-1));
        return new WhatsMessagePage(driver);
    }

}
