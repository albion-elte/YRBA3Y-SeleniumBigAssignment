import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class NegativeTests {

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
    public void incorrectUsernameTest() {
        System.out.println("Starting incorrectUsernameTest");

        // sleep for 3 seconds
        sleep(3000);

        // maximize browser window
        driver.manage().window().maximize();

        // open test page
        String url = "https://practicetestautomation.com/practice-test-login/";
        driver.get(url);
        System.out.println("Page is opened.");

        // enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("incorrectUsername");

        // enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Password123");

        // click login button
        WebElement logInButton = driver.findElement(By.className("btn"));
        logInButton.click();

        sleep(3000);

        // Verifications
        WebElement errorMessage = driver.findElement(By.id("post-title"));
        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = errorMessage.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Actual error message does not contain expected. \nActual: "
                        + actualErrorMessage + "\nExpected: "
                        + expectedErrorMessage);

        // Close browser
        driver.quit();
    }

    private void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
