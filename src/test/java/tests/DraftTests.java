package tests;

import dataProvider.MailDataProvider;
import dataProvider.TestData;
import org.testng.annotations.Test;

import static core.pages.LoginPageProvider.EMAIL;

public class DraftTests extends BaseTest {
    @Test(groups = {"LoginTest", "CreateDraft", "CheckDraft", "DeleteDraft"})
    public void loginInMail() {
        steps.checkThatLoginSucceed(EMAIL);
    }

    @Test(dataProviderClass = MailDataProvider.class, dataProvider = "getData",
            groups = {"CreateDraft", "CheckDraft", "DeleteDraft"})
    public void createDraft(TestData data) {
        steps.writeDraft(data.getReceiverEmail(), data.getSubject(), data.getMessage());
    }

    @Test(dataProviderClass = MailDataProvider.class, dataProvider = "getData",
            groups = {"CheckDraft"})
    public void checkDraftField(TestData data) {
        steps.checkDraftFile(data, data.getReceiverEmail());
    }

    @Test(dataProviderClass = MailDataProvider.class, dataProvider = "getData",
            groups = {"DeleteDraft"})
    public void deleteDraft(TestData data) {
        steps.deleteDraft(data.getReceiverEmail());
        steps.checkDraftDeletedSucceed(data.getReceiverEmail());
    }

    @Test
    public void logOut() {
        steps.logOut();
        steps.checkThatLogOutSucceed();
    }
}
