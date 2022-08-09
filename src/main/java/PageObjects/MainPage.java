package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    @AndroidFindBy(accessibility = "Graphics")
    WebElement graphicsElement;

    @AndroidFindBy(accessibility = "Views")
    WebElement viewsElement;

    public MainPage(AppiumDriver driver) {
        super(driver);
    }

    public GraphicsPage clickGraphics() {
        clickElement(graphicsElement);
        return new GraphicsPage(driver);
    }

    public ViewsPage clickViews() {
        clickElement(viewsElement);
        return new ViewsPage(driver);
    }
}
