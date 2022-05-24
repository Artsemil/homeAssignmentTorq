package browserFactory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static utils.PropertiesReader.getPropertyValue;

public class ChromeDockerBrowserManager extends BrowserManager {

    private static final String PORT = getPropertyValue("PORT");

    @Override
    protected void createDriver() {
        ChromeOptions options = new ChromeOptions();
        try {
            driver = new RemoteWebDriver(new URL("http:localhost:" + PORT + "/wd/hub"),
                    options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        LOGGER.debug("Docker ChromeDriver was created");
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
