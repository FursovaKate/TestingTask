package core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class DraftFilePage extends AbstractPage {

    @FindBy(css = ".mail-MessageSnippet-FromText")
    List<WebElement> drafts;

    @FindBy(css = "input.composeTextField.ComposeSubject-TextField")
    WebElement subjectDraftField;

    @FindBy(css = ".ComposeYabble-Text")
    WebElement receiverEmailDraftField;

    @FindBy(css = ".cke_inner.cke_reset")
    WebElement messageBodyDraftField;

    @FindBy(css = "._nb-checkbox-flag")
    List<WebElement> draftCheckbox;

    @FindBy(xpath = "//div[contains(@title, 'Удалить (Delete)')]")
    WebElement deleteButton;

    @FindBy(css = ".mail-MessageSnippet-Wrapper")
    List<WebElement> allDraftInfo;

    private final String CHECKBOX_SELECTOR = "._nb-checkbox-flag";
    private final String TITLE_SELECTOR = ".mail-MessageSnippet-FromText";
    private final String ATTRIBUTE = "value";

    public DraftFilePage(WebDriver driver) {
        super(driver);
    }

    public void openDraft(String email) {
        driverHelper.waitForVisibilityOfAllElements(".mail-MessageSnippet-FromText");
        for (WebElement draft : drafts) {
            if (draft.getText().equals(email)) {
                draft.click();
            }
        }
    }

    public String getSubjectDraftFieldText() {
        driverHelper.waitForElementIsVisible(subjectDraftField);
        return subjectDraftField.getAttribute(ATTRIBUTE);
    }

    public String getReceiverEmailDraftField() {
        driverHelper.waitForElementIsVisible(receiverEmailDraftField);
        return receiverEmailDraftField.getText();
    }

    public String getMessageBodyDraftField() {
        driverHelper.waitForElementIsVisible(messageBodyDraftField);
        return messageBodyDraftField.getText();
    }

    public void draftCheckboxClick(String name) {
        for (WebElement info : allDraftInfo) {
            WebElement checkbox = info.findElement(By.cssSelector(CHECKBOX_SELECTOR));
            WebElement title = info.findElement(By.cssSelector(TITLE_SELECTOR));
            if (title.getText().equals(name)) {
                checkbox.click();
            }
        }
    }

    public void deleteButtonClick() {
        deleteButton.click();
    }
}
