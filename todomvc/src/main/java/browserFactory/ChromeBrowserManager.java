package browserFactory;

import org.openqa.selenium.chrome.ChromeDriver;

import static utils.PropertiesReader.getPropertyValue;

public class ChromeBrowserManager extends BrowserManager {

    private static final String CHROME_DRIVER_PATH = getPropertyValue("CHROME_DRIVER_PATH");

    @Override
    protected void createDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        LOGGER.debug("ChromeDriver was created");
    }

    @Override
    public void quitDriver() {
        super.quitDriver();
        LOGGER.debug("ChromeDriver was quited");
    }

    @Override
    protected void setupDriver() {
        super.setupDriver();
        LOGGER.debug("ChromeDriver was configured");
    }
}
