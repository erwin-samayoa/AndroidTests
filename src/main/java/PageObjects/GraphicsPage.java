package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class GraphicsPage extends BasePage {
    public GraphicsPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(accessibility = "Arcs")
    WebElement arcsElement;

    public String getTextForArcsElement() {
        String returnString = getTextFromElement(arcsElement);
        return returnString;
    }
}
