package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;

public class MultimediaPage extends BasePage {

    @AndroidFindBy(id = "gl_root_view")
    WebElement generalViewElement;

    @AndroidFindBy(id = "photopage_bottom_controls")
    WebElement photoViewElement;

    public MultimediaPage(AppiumDriver driver) {
        super(driver);
    }

    public void uploadMediaFile(String file) throws IOException {
        File archivo = new File("C:\\Users\\User\\Downloads\\logo-TAU-gold-poweredby-applitools-467x105.png");
        uploadFile("/sdcard/Pictures/archivo.png",archivo);


    }
}
