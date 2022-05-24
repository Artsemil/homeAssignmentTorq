package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.PropertiesReader.getPropertyValue;

public abstract class AbstractPage {

    protected static final Logger LOGGER = LogManager.getLogger(AbstractPage.class.getName());
    private static final int EXPLICIT_WAIT = Integer.parseInt(getPropertyValue("EXPLICIT_WAIT"));

    protected WebDriver webDriver;
    private final Wait<WebDriver> wait;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, EXPLICIT_WAIT);
        PageFactory.initElements(webDriver, this);
    }

    protected void waitForElementEnabled(WebElement element) {
        new WebDriverWait(webDriver, EXPLICIT_WAIT).until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitUntilElementNotVisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            waitForElementEnabled(element);
        } catch (TimeoutException e) {
            LogManager.getLogger().error(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean isExpectedPage(String url) {
        LOGGER.debug("Start comparing current page with " + url);
        return webDriver.getCurrentUrl().equals(url);
    }

    public void refresh() {
        LOGGER.debug("Page " + webDriver.getCurrentUrl() + " was refreshed");
        webDriver.navigate().refresh();
    }
}
