package core.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainMailPage extends AbstractPage {

    @FindBy(className = "mail-ComposeButton-Text")
    WebElement writeLetterButton;

    @FindBy(className = "composeReact__inner")
    WebElement writeLetterForm;

    @FindBy(css = ".composeYabbles")
    WebElement receiverField;

    @FindBy(xpath = "//input[@name='subject']")
    WebElement subjectField;

    @FindBy(xpath = "//div[@placeholder='Напишите что-нибудь']")
    WebElement bodyField;

    @FindBy(css = ".controlButtons__btn.controlButtons__btn--close")
    WebElement closeLetterFormButton;

    @FindBy(xpath = "//a[contains(@href,'#draft')]")
    WebElement draftFile;

    private static final String CLOSE_FORM_SELECTOR = ".controlButtons__btn.controlButtons__btn--close";


    public MainMailPage(WebDriver driver) {
        super(driver);
    }

    public void writeLetterButtonClick() {
        writeLetterButton.click();
    }

    public boolean isWriteLetterFormVisible() {
        return writeLetterForm.isDisplayed();
    }

    public void setReceiverField(String email) {
        receiverField.sendKeys(email);
    }

    public void setSubjectField(String subject) {
        subjectField.sendKeys(subject);
    }

    public void setBodyField(String body) {
        bodyField.sendKeys(body);
    }

    public void closeLetterForm() {
        driverHelper.clickViaJs(CLOSE_FORM_SELECTOR);
    }

    public void goInDraftFile() {
        driverHelper.waitForElementToBeClickable(draftFile);
        draftFile.click();
    }
}
