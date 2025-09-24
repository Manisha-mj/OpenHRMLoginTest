package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        // Launch Chrome
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open the OrangeHRM demo site
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @Test
    public void loginTest() {
        // Wait until username field is visible and enter username
        WebElement username = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("username"))
        );
        username.sendKeys("Admin");

        // Wait until password field is visible and enter password
        WebElement password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("password"))
        );
        password.sendKeys("admin123");

        // Wait until login button is clickable and click it
        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))
        );
        loginButton.click();

        // Verify successful login by checking current URL
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        wait.until(ExpectedConditions.urlContains("dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));

        System.out.println("Login Successful!");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.close();
        }
    }
}