package steps;

import core.pages.DraftFilePage;
import core.pages.IndexPage;
import core.pages.LoginPage;
import core.pages.MainMailPage;
import dataProvider.TestData;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class MailSteps {
    private IndexPage indexPage;
    private LoginPage loginPage;
    private MainMailPage mainMailPage;
    private DraftFilePage draftFilePage;

    public MailSteps(WebDriver driver) {
        this.indexPage = new IndexPage(driver);
        this.loginPage = new LoginPage(driver);
        this.mainMailPage = new MainMailPage(driver);
        this.draftFilePage = new DraftFilePage(driver);
    }

    public void openIndexPage(String url) {
        indexPage.open(url);
    }

    public void logIn(String email, String pass) {
        indexPage.clickOnTheLogInButton();
        loginPage.setEmail(email);
        loginPage.submitButtonClick();
        loginPage.setPasswordField(pass);
        loginPage.submitButtonClick();
    }

    public void writeDraft(String email, String subject, String message) {
        mainMailPage.writeLetterButtonClick();
        assertEquals(mainMailPage.isWriteLetterFormVisible(), true);
        mainMailPage.setReceiverField(email);
        mainMailPage.setSubjectField(subject);
        mainMailPage.setBodyField(message);
        mainMailPage.closeLetterForm();
        mainMailPage.goInDraftFile();
    }

    public void checkDraftFile(TestData data, String email) {
        mainMailPage.goInDraftFile();
        draftFilePage.openDraft(email);
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(draftFilePage.getReceiverEmailDraftField(), data.getReceiverEmail());
        sa.assertEquals(draftFilePage.getSubjectDraftFieldText(), data.getSubject());
        sa.assertEquals(draftFilePage.getMessageBodyDraftField(), data.getMessage());
        sa.assertAll();
    }

    public void checkThatLoginSucceed(String name) {
        loginPage.userIconClick();
        assertEquals(loginPage.getUserAccountName(), name);
    }

    public void deleteDraft(String name) {
        mainMailPage.goInDraftFile();
        draftFilePage.draftCheckboxClick(name);
        draftFilePage.deleteButtonClick();
    }

    public void logOut() {
        loginPage.userIconClick();
        loginPage.logOutButtonClick();
    }

    public void checkThatLogOutSucceed() {
        assertEquals(loginPage.isLoginFormVisible(), true);
    }

    public void checkDraftDeletedSucceed(String name) {
        assertEquals(draftFilePage.isDraftDisplayed(name), false);
    }
}
