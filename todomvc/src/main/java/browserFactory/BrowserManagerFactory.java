package browserFactory;

public class BrowserManagerFactory {

    public static BrowserManager getManager(BrowserType name) {
        BrowserManager browserManager;
        switch (name) {
            case CHROME:
                browserManager = new ChromeBrowserManager();
                break;
            case CHROME_DOCKER:
                browserManager = new ChromeDockerBrowserManager();
                break;
            default:
                throw new IllegalArgumentException("Incorrect browser type, should be CHROME, CHROME_DOCKER");
        }
        return browserManager;
    }
}
