package uitest;

import browserFactory.BrowserManager;
import browserFactory.BrowserManagerFactory;
import browserFactory.BrowserType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.BasePage;

import static utils.PropertiesReader.getPropertyValue;

public abstract class BaseTest {

    private WebDriver webDriver;
    private BasePage basePage;
    private BrowserManager manager;
    private BrowserType browserType = BrowserType.valueOf(getPropertyValue("BROWSER"));

    @BeforeClass
    public void loadState() {
        manager = BrowserManagerFactory.getManager(browserType);
        webDriver = manager.getDriver();
        basePage = new BasePage(webDriver).open();
        webDriver.manage().window().maximize();
    }

    @AfterClass
    public void afterClass() {
        manager.quitDriver();
    }
}
