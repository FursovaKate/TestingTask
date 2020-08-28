package core.webdriver;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DriverHelper {
    public DriverHelper(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;
    private final Integer TIME_OUT = 7000;
    private final Integer SLEEP = 20;

    public void waitForElementToBeClickable(WebElement element) {
        new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForJSComplete() {
        new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.jsReturnsValue("complete"));
    }

    public void waitForListOfElementsToBeVisible(String selector, int numberOfElementsInListMoreThan) {
        new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(selector), numberOfElementsInListMoreThan));
    }

    public void waitForElementIsVisible(WebElement webElement) {
        new WebDriverWait(driver, TIME_OUT).until(isVisible(webElement));
    }

    private ExpectedCondition<Boolean> isVisible(final WebElement element) {
        return driver -> element.isDisplayed();
    }

    public void clickViaJs(String cssSelector) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void waitForVisibilityOfAllElements(String element) {
        Wait<WebDriver> wait = new WebDriverWait(driver,  TIME_OUT, SLEEP);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(element)));
        driver.manage().timeouts().setScriptTimeout(TIME_OUT, TimeUnit.MILLISECONDS);
    }
}

