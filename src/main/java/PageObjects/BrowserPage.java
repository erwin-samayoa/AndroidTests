package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BrowserPage extends BasePage {

    private AndroidDriver driverForAndroid = null;

    @AndroidFindBy(id = "url_field")
    WebElement urlTextElement;

    @AndroidFindBy(accessibility = "Load URL")
    WebElement loadUrlButton;

    public BrowserPage(AppiumDriver driver) {
        super(driver);
    }

    public void navigate(String url) {
        setText(urlTextElement,url);
        clickElement(loadUrlButton);
    }

    private void enterWeb() {
        //Switching context to webview one
        driverForAndroid = changeContext("webview_shell");
    }

    private void exitWeb() {
        //Going back to original context
        changeContext("NATIVE_APP");
    }

    public void clickWebLink(String textOfLink) {
        enterWeb();
        if (driverForAndroid != null) {
            //Because of specific Android features it seems it needs a whole set of upper methods in BasePage to work with the AndroidDriver instead of AppiumDriver
            //driverForAndroid.findElement(By.linkText(textOfLink)).click(); //Not suported on guthub/reactivecircus
            driverForAndroid.findElement(By.xpath("//a[contains(.,'" + textOfLink +"')]")).click();
        }
        exitWeb();
    }
}
