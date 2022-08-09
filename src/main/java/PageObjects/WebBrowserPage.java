package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WebBrowserPage extends BasePage {

    //Not extending BasePage because it possibly needs another set of functions

    public WebBrowserPage(AppiumDriver driver) {
        super(driver);
    }

    public void navigate(String url) {
        driver.get(url);
    }

    public void clickWebLink(String textOfLink) {
            driver.findElement(By.linkText("jump to para 4")).click();
    }

}
