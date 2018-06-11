import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebDriverLogger extends AbstractWebDriverEventListener {
    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("WebDriver navigated to '" + url + "'");
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        try {
            System.out.println("WebDriver search element - " + by);
        } catch (NullPointerException ex) {
        }
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println("WebDriver click on element - " + element.getText().trim());
    }
}

