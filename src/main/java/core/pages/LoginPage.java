package core.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @FindBy(id = "passp-field-login")
    WebElement setEmailField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitLoginButton;

    @FindBy(id = "passp-field-passwd")
    WebElement setPasswordField;

    @FindBy(css = ".user-pic.user-pic_has-plus_.user-account__pic")
    WebElement userIcon;

    @FindBy(css = ".user-account__subname")
    WebElement userName;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setEmail(String email) {
        setEmailField.sendKeys(email);
    }

    public void submitButtonClick() {
        submitLoginButton.click();
    }

    public void setPasswordField(String pass) {
        setPasswordField.sendKeys(pass);
    }

    public void userIconClick() {
        driverHelper.waitForElementIsVisible(userIcon);
        userIcon.click();
    }

    public String getUserAccountName() {
        return userName.getText();
    }
}
