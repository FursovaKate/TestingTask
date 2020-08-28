package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import steps.MailSteps;

import java.util.concurrent.TimeUnit;

import static core.pages.LoginPageProvider.EMAIL;
import static core.pages.LoginPageProvider.PASSWORD;

public class BaseTest {
    protected WebDriver driver;
    protected static final String URL = "https://mail.yandex.com/?noretpath=1";
    protected MailSteps steps;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        steps = new MailSteps(driver);
        steps.openIndexPage(URL);
        steps.logIn(EMAIL, PASSWORD);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}
