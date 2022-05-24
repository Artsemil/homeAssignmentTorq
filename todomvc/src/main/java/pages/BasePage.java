package pages;

import org.openqa.selenium.WebDriver;

public class BasePage extends AbstractPage {

    private static final String URL = "https://zalmoxisus.github.io/examples/todomvc/";

    public BasePage(WebDriver webDriver) {
        super(webDriver);
    }

    public BasePage open() {
        webDriver.get(URL);
        LOGGER.debug("Page " + URL + " was opened");
        return this;
    }

}
