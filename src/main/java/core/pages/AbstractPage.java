package core.pages;

import core.webdriver.DriverHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public abstract class AbstractPage {
    protected WebDriver driver;

    protected DriverHelper driverHelper;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.driverHelper = new DriverHelper(driver);
    }
}
