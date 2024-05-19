import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AxesTests extends Base {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void axesTest() {
        log.info("Starting axes test");
        openPage();
        String email = "albion.elte@gmail.com";

        // Get row for specific email
        WebElement row = driver.findElement(By.xpath("//*[@id='table1']//td[text()='" + email + "']/parent::*"));

        // Print text from this row
        log.info("Row: " + row.getText());

        // Click on delete button for this row

        // One way
        WebElement deleteButton = driver.findElement(By.xpath("//*[@id='table1']//td[text()='" + email
                + "']/parent::*/child::td/a[@href='#delete']"));
        deleteButton.click();

        // Another way
        // incorrect
        row.findElement(By.xpath("//child::td/a[@href='#delete']"));

        // helper
        List<WebElement> list1 = row.findElements(By.xpath("//child::td/a[@href='#delete']"));
        log.info("List1 size: " + list1.size());

        // correct
        row.findElement(By.xpath("./child::td/a[@href='#delete']"));

        // helper
        List<WebElement> list2 = row.findElements(By.xpath("./child::td/a[@href='#delete']"));
        log.info("List2 size: " + list2.size());

        // also correct
        row.findElement(By.xpath("./descendant::a[@href='#delete']"));

        // helper
        List<WebElement> list3 = row.findElements(By.xpath("./descendant::a[@href='#delete']"));
        log.info("List3 size: " + list3.size());

    }


    private void openPage() {
        // open the page
        String url = "https://practicetestautomation.com/practice-test-login/";
        driver.get(url);
        log.info("Page is opened");
    }

}
