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

public class PositiveTests {

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
        public void loginTest() {
                System.out.println("Starting loginTest");

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
                username.sendKeys("student");

                // enter password
                WebElement password = driver.findElement(By.name("password"));
                password.sendKeys("Password123");

                // click login button
                WebElement logInButton = driver.findElement(By.className("btn"));
                logInButton.click();

                sleep(3000);

                // verifications:
                // new url
                String expectedUrl = "https://practicetestautomation.com/practice-test-login/";
                String actualUrl = driver.getCurrentUrl();
                Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");

                // logout button is visible
                WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
                Assert.assertTrue(logOutButton.isDisplayed(), "Log Out button is not visible");

                // succesful login message
                // WebElement successMessage =
                // driver.findElement(By.cssSelector("#post-title"));
                WebElement successMessage = driver.findElement(By.xpath("//div[@id='post-title']"));
                String expectedMessage = "You logged into a secure area!";
                String actualMessage = successMessage.getText();
                // Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not
                // the same as expected");
                Assert.assertTrue(actualMessage.contains(expectedMessage),
                                "Actual message does not contain expected message.\nActual Message: " + actualMessage
                                                + "\nExpected Message: " + expectedMessage);

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
