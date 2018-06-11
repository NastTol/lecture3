import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

class MyWebDriver {
    static EventFiringWebDriver getConfiguredDriver() {
        WebDriver driver = initDriver();

        EventFiringWebDriver webDriver = new EventFiringWebDriver(driver);
        webDriver.register(new WebDriverLogger());
        return webDriver;
    }

    private static WebDriver initDriver() {
        String myDriver = null;
        myDriver = System.getProperty("webdriver");
        if (myDriver != null) {
            return defineDriver(myDriver);
        } else {
            System.setProperty("webdriver.chrome.driver", TaskThree.class.getResource("chromedriver.exe").getPath());
            return new ChromeDriver();
        }
    }

    private static WebDriver defineDriver(String myDriver) {
        WebDriver driver;

        if (myDriver.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", TaskThree.class.getResource("geckodriver.exe").getPath());
            driver = new FirefoxDriver();
        } else if (myDriver.equals("ie")) {
            System.setProperty("webdriver.ie.driver", TaskThree.class.getResource("edgedriver.exe").getPath());
            driver = new InternetExplorerDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", TaskThree.class.getResource("chromedriver.exe").getPath());
            driver = new ChromeDriver();
        }
        return driver;
    }
}
