package uitest;

import browserFactory.BrowserManager;
import browserFactory.BrowserManagerFactory;
import browserFactory.BrowserType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;
import pages.TodoVMCPage;

import static utils.PropertiesReader.getPropertyValue;

public abstract class BaseTest {

    private WebDriver webDriver;
    protected TodoVMCPage todoVMCPage;
    private BrowserManager manager;
    private BrowserType browserType = BrowserType.valueOf(getPropertyValue("BROWSER"));

    @BeforeClass
    public void loadState() {
        manager = BrowserManagerFactory.getManager(browserType);
        webDriver = manager.getDriver();
        todoVMCPage = new TodoVMCPage(webDriver).open();
        webDriver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod() {
        todoVMCPage.selectAllTasks();
    }

    @AfterClass
    public void afterClass() {
        manager.quitDriver();
    }
}
