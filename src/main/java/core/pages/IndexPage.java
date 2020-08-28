package core.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class IndexPage extends AbstractPage {

    @FindBy(xpath = "//span[contains(text(),'Log in')]/..")
    WebElement logInButton;

    public IndexPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public void clickOnTheLogInButton() {
        driverHelper.waitForElementToBeClickable(logInButton);
        logInButton.click();
    }
}
