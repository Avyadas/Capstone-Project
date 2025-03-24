package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ScreenshotUtil;

public class RegistrationPage {
    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    // Register user with correct locators
    public void registerUser(String fName, String lName, String email, String pass) {
        driver.findElement(By.id("firstname")).sendKeys(fName);
        driver.findElement(By.id("lastname")).sendKeys(lName);
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.id("password-confirmation")).sendKeys(pass);
        driver.findElement(By.xpath("//button[@title='Create an Account']")).click();
    }


    public void handleAlert(String expectedMsg, String screenshotName) {
        try {
            Alert alert = driver.switchTo().alert();
            ScreenshotUtil.takeScreenshot(driver, screenshotName);
            if (alert.getText().equals(expectedMsg)) {
            	alert.accept();
            }
           
        } catch (Exception e) {
            System.out.println("No alert found.");
        }
    }
}
