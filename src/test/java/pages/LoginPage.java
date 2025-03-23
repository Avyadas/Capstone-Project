package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScreenshotUtil;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
       // this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By emailField = By.id("email");
    private By passwordField = By.id("pass");
    private By signInButton = By.id("send2");

    // Login Method
    public void login(String email, String password) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(signInButton).click();
        
    }

    // Handle Alert & Screenshot
    public void handleAlert(String expectedMessage, String screenshotName) {
        try {
        	ScreenshotUtil.takeScreenshot(driver, screenshotName);
            Alert alert = driver.switchTo().alert();
            String actualMessage = alert.getText();
            if (actualMessage.equals(expectedMessage)) {
                System.out.println("Alert Verified: " + actualMessage);
            } else {
                System.out.println("Alert Mismatch! Expected: " + expectedMessage + ", but got: " + actualMessage);
            }

            alert.accept();
        } catch (Exception e) {
            System.out.println("Alert not found or failed to handle.");
        }
    }
}
