package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class GalleryPage extends BasePage {

    @AndroidFindBy(accessibility = "1. Photos")
    WebElement photosElement;

    public GalleryPage(AppiumDriver driver) {
        super(driver);
    }

    public PhotosPage clickPhotos() {
        clickElement(photosElement);
        return new PhotosPage(driver);
    }
}
