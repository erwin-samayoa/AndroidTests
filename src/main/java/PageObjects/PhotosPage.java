package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PhotosPage extends MainPage{

    @AndroidFindBy(className = "android.widget.ImageView")
    List<WebElement> sourceElement;

    @AndroidFindBy(className = "android.widget.Gallery")
    WebElement destinationElement;

    public PhotosPage(AppiumDriver driver) {
        super(driver);
    }

    public void swipeLeft() {
        dragXtoYElement(sourceElement.get(1),destinationElement);
    }
}
