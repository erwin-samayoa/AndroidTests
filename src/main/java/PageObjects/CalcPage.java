package PageObjects;

import general.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class CalcPage extends BasePage {

    @AndroidFindBy(id = "digit_7")
    WebElement digit7Element;

    @AndroidFindBy(id = "op_mul")
    WebElement multiplicationElement;

    @AndroidFindBy(id = "result")
    WebElement resultElement;

    public CalcPage(AppiumDriver driver) {
        super(driver);
    }

    public void clickDigit7() {
        clickElement(digit7Element);
    }

    public void clickMultiplication() {
        clickElement(multiplicationElement);
    }

    public String getResult() {
        return getTextFromElement(resultElement);
    }
}
