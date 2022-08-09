package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import org.openqa.selenium.WebElement;

public class DragAndDropPage extends BasePage {


    public DragAndDropPage(AppiumDriver driver) {
        super(driver);
    }

    public void dragXElementToY(int x, int y) {
        String sourceElementString = "drag_dot_" + x;
        String destinationElementString = "drag_dot_" + y;

        WebElement sourceElement = locateElement(AppiumBy.id(sourceElementString));
        WebElement destinationElement = locateElement(AppiumBy.id(destinationElementString));

        dragXtoYElement(sourceElement,destinationElement);
    }
}
