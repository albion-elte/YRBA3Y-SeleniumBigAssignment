import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LocatorTests extends Base {

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
    @Parameters({"locator"})
    public void locatorTest(String locatorString) {
        log.info("Starting locatorTest for " + locatorString);
        openPage();
        By locatorBy = getBy(locatorString);

        WebElement emailInputField = driver.findElement(locatorBy);
        log.info("Element located");
        log.info("Element is displayed: " + emailInputField.isDisplayed());
    }


    private By getBy(String locator) {
        switch (locator) {
            // E-mail field
            case "relativeXPath":
                return By.xpath("//input[@id='email']");

            case "absoluteXPath":
                return By.xpath("/html/body/div/div/div/form/div/div/input");

            case "id":
                return By.id("email");

            case "css":
                return By.cssSelector("input");

            case "name":
                return By.name("email");

            case "tag":
                return By.tagName("input");

            // 	Retrieve password button
            case "className":
                return By.className("radius");

            // 	Elemental Selenium link
            case "linkText":
                return By.linkText("Elemental Selenium");

            case "partialLinkText":
                return By.partialLinkText("Elemental");

            default:
                return null;
        }
    }


    private void openPage() {
        // open the page
        String url = "https://practicetestautomation.com/practice-test-login/";
        driver.get(url);
        log.info("Page is opened");
    }

}
