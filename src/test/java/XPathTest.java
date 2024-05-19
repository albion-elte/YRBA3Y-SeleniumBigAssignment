import static org.junit.Assert.assertTrue;

import org.junit.*;
import java.net.URL;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import java.net.MalformedURLException;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;

public class XPathTest {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameTextboxLocator = By.xpath("//input[@id='username']");
    private By passwordTextboxLocator = By.xpath("//input[@id='password']");
    private By loginButtonLocator = By.xpath("//i[contains(text(), 'Submit')]");
    private By logoutButtonLocator = By.xpath("//i[contains(text(), 'Logout')]");
    private By successMessageLocator = By.xpath("//div[@class='post-title']");
    private By errorMessageLocator = By.xpath("//div[@id='error']");


    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    private WebElement waitVisibiltyAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    @Test
    public void SeleniumTest() {
        this.driver.get("https://practicetestautomation.com/practice-test-login/");

        //Success Login
        waitVisibiltyAndFindElement(usernameInputLocator).sendKeys("student");
        waitVisibiltyAndFindElement(passwordInputLocator).sendKeys("Password123");
        waitVisibiltyAndFindElement(loginButtonLocator).click();

        Assert.assertTrue(waitVisibiltyAndFindElement(successMessageLocator)
                .getText()
                .contains("Logged In Successfully"));

        //Logout
        waitVisibiltyAndFindElement(logoutButtonLocator).click();

        //Wrong Credentials
        waitVisibiltyAndFindElement(usernameInputLocator).sendKeys("wrongUsername");
        waitVisibiltyAndFindElement(passwordInputLocator).sendKeys("wrongPassword");
        waitVisibiltyAndFindElement(loginButtonLocator).click();

        Assert.assertTrue(waitVisibiltyAndFindElement(errorMessageLocator)
                .getText()
                .contains("Your username is invalid!"));
        //.contains("Your password is invalid!")); //if we send the wrong password only
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
