package core.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.security.PublicKey;

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

    @FindBy(xpath = "//a[contains(@aria-label, 'Выйти из аккаунта')]")
    WebElement logOutButton;

    @FindBy(xpath = "//span[contains(text(),'Log in')]/..")
    WebElement loginButtonIndexPage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setEmail(String email) {
        setEmailField.sendKeys(email);
    }

    public void submitButtonClick() {
        driverHelper.waitForPageUpdate();
        submitLoginButton.click();
    }

    public void setPasswordField(String pass) {
        setPasswordField.sendKeys(pass);
        driverHelper.waitForPageUpdate();
    }

    public void userIconClick() {
        driverHelper.waitForPageUpdate();
        driverHelper.waitForElementIsVisible(userIcon);
        userIcon.click();
    }

    public String getUserAccountName() {
        return userName.getText();
    }

    public void logOutButtonClick() {
        driverHelper.waitForPageUpdate();
        logOutButton.click();
        driverHelper.waitForElementIsVisible(loginButtonIndexPage);
    }

    public boolean isLoginFormVisible() {
        driverHelper.waitForElementIsVisible(loginButtonIndexPage);
        return loginButtonIndexPage.isDisplayed();
    }
}
