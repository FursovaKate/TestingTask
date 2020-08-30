package core.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverHelper {

    private static final String JS_AJAX_PROGRESS =
            "var userWindow = window;" +
                    "var docReady = window.document.readyState == 'complete';" +
                    "var isJqueryComplete = typeof(userWindow.jQuery) != 'function' || userWindow.jQuery.active == 0;" +
                    "var isPrototypeComplete = typeof(userWindow.Ajax) != 'function' || userWindow.Ajax.activeRequestCount == 0;" +
                    "var isDojoComplete = typeof(userWindow.dojo) != 'function' || userWindow.dojo.io.XMLHTTPTransport.inFlight.length == 0;" +
                    "var result = docReady && isJqueryComplete && isPrototypeComplete && isDojoComplete;" +
                    "return result;";
    /**
     * JavaScript code to check if all the animation completed
     */
    private static final String JS_ANIMATION_PROGRESS = "var anim = 0; var anim = $(':animated'); " +
                    "return anim.length == 0";
    private static final ExpectedCondition<Object> isAJAXCompleted = webDriver -> {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        return Boolean.parseBoolean(js.executeScript(JS_AJAX_PROGRESS).toString());
    };
    private static final ExpectedCondition<Object> isAnimated = webDriver -> {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        return Boolean.parseBoolean(js.executeScript(JS_ANIMATION_PROGRESS).toString());
    };

    private ExpectedCondition<Boolean> isVisible(final WebElement element) {
        return driver -> element.isDisplayed();
    }
    public DriverHelper(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriver driver;
    private final Integer TIME_OUT = 7000;
    private final Integer SLEEP = 20;

    public void waitForElementToBeClickable(WebElement element) {
        new WebDriverWait(driver, TIME_OUT).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementIsVisible(WebElement webElement) {
        new WebDriverWait(driver, TIME_OUT).until(isVisible(webElement));
    }

    public void clickViaJs(String cssSelector) {
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void waitForPageUpdate() {
        Wait<WebDriver> wait = new WebDriverWait(driver, TIME_OUT, SLEEP);
        wait.until(isAJAXCompleted);
        wait.until(isAnimated);
    }
}

