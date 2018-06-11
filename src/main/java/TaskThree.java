import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.ArrayList;

public class TaskThree extends MyWebDriver {

    private static final String CATEGORY_NAME = RandomString.make(10);

    public static void main(String[] args) {

        WebDriver driver = getConfiguredDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");

       WebElement field1 = driver.findElement(By.id("email"));
        field1.sendKeys("webinar.test@gmail.com");

        WebElement field2 = driver.findElement(By.id("passwd"));
        field2.sendKeys("Xcg7299bnSmMuRLp9ITw");

        WebElement button = driver.findElement(By.name("submitLogin"));
        button.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.id("subtab-AdminCatalog")));

        WebElement catalog = driver.findElement(By.id("subtab-AdminCatalog"));

        Actions builder = new Actions(driver);
        builder.moveToElement(catalog).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("subtab-AdminCategories")));
        WebElement category = driver.findElement(By.id("subtab-AdminCategories"));
        category.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#page-header-desc-category-new_category")));

        WebElement addCategory = driver.findElement(By.cssSelector("#page-header-desc-category-new_category"));
        addCategory.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name_1")));
        WebElement cat_name = driver.findElement(By.id("name_1"));


        cat_name.sendKeys(CATEGORY_NAME);
        WebElement cat_submit = driver.findElement(By.id("category_form_submit_btn"));
        cat_submit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > div:nth-child(4) > div")));

        WebElement name_filter = driver.findElement(By.cssSelector("#table-category > thead > tr:nth-child(1) > th:nth-child(3) > span > a:nth-child(2) > i"));
        name_filter.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#form-category > div")));

        WebElement table = driver.findElement(By.cssSelector("#form-category > div"));
        List<WebElement> tab_elements = table.findElements(By.xpath(".//tr/td[3]"));

        ArrayList<String> cat_names = new ArrayList<String>();

        boolean found = false;

        for (WebElement el : tab_elements) {
            if (el.getText().trim().contains(CATEGORY_NAME)) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Category '" + CATEGORY_NAME + "' is not created");
        } else {
            System.out.println("Category '" + CATEGORY_NAME + "' is created");
        }
    }

    private static EventFiringWebDriver getConfiguredDriver() {
        WebDriver driver = initDriver();

        EventFiringWebDriver webDriver = new EventFiringWebDriver(driver);
        webDriver.register(new WebDriverLogger());
        return webDriver;
    }
}
