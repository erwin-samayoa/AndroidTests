package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ViewsPage extends BasePage {

    @AndroidFindBy(accessibility = "Drag and Drop")
    WebElement dragAndDropElement;

    @AndroidFindBy(accessibility = "Lists")
    WebElement listsElement;

    public ViewsPage(AppiumDriver driver) {
        super(driver);
    }

    public DragAndDropPage clickDragAndDropElement() {
        clickElement(dragAndDropElement);
        return new DragAndDropPage(driver);
    }

    private String getTextForElementByAccesibility(String accessibilityId) {
        //not using annotation-style to make it cleaner and reusable
        WebElement element = locateElement(AppiumBy.accessibilityId(accessibilityId));
        return element.getText();
    }

    private void clickElementByAccesibility(String accessibilityId) {
        //not using annotation-style to make it cleaner and reusable
        WebElement element = locateElement(AppiumBy.accessibilityId(accessibilityId));
        clickElement(element);
    }

    public String getTextForListsElement() {
        return getTextForElementByAccesibility("Lists");
    }

    public String getTextForGalleryElement() {
        return getTextForElementByAccesibility("Gallery");
    }

    public GalleryPage clickGalleryElement() {
        clickElementByAccesibility("Gallery");
        return new GalleryPage(driver);
    }
}
