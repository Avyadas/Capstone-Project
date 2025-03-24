package testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import com.aventstack.extentreports.Status;
import utils.ScreenshotUtil;

public class CartEditTest extends BaseClass {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        invokeBrowser();
        driver = getDriver();

        // ✅ Ensure Extent Report is initialized
       // extent = getExtentReportInstance();
        test = extent.createTest("View and Edit Cart Test");
        test.log(Status.INFO, "Test Initialized: View and Edit Cart");
    }

    @Test(priority = 1)
    public void viewAndEditCart() {
        try {
            driver.get("https://magento.softwaretestingboard.com/");
            test.log(Status.INFO, "Navigated to Magento Store");

            driver.findElement(By.linkText("Sign In")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("avya.borra@gmail.com");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass"))).sendKeys("Avya1516");
            wait.until(ExpectedConditions.elementToBeClickable(By.id("send2"))).click();
            test.log(Status.PASS, "Logged in Successfully");

            // ✅ Click on Cart Icon (Ensure It's Clickable)
            WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'action showcart')]")));
            cartIcon.click();
            test.log(Status.INFO, "Clicked on Cart Icon");

            // ✅ Ensure Cart Dropdown is Fully Loaded Before Clicking "View and Edit Cart"
            WebElement viewCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='View and Edit Cart']")));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", viewCart);
            test.log(Status.PASS, "Clicked 'View and Edit Cart' via JavaScriptExecutor");

            // ✅ Wait for URL to Change (Confirm Page Loaded)
            wait.until(ExpectedConditions.urlContains("checkout/cart"));
            test.log(Status.PASS, "Shopping Cart Page Loaded");

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to Open 'View and Edit Cart': " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void updateQuantity() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // ✅ Ensure Shopping Cart Table is Loaded Before Interacting
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart.table-wrapper")));

            // ✅ Change Quantity
            WebElement quantityBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title='Qty']")));
            quantityBox.clear();
            quantityBox.sendKeys("2");
            test.log(Status.INFO, "Changed product quantity to 2");

            // ✅ Click Update Cart
            WebElement updateCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Update Shopping Cart']")));
            updateCartBtn.click();
            test.log(Status.PASS, "Cart Updated Successfully");
         

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to Update Cart: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void removeProduct() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // ✅ Ensure Shopping Cart Table is Loaded Before Removing Product
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart.table-wrapper")));

            // ✅ Click Remove Product
            WebElement removeItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'action-delete')]")));
            removeItem.click();
            test.log(Status.INFO, "Clicked Remove Product");
            JavascriptExecutor js1 = (JavascriptExecutor) driver;
            js1.executeScript("window.scrollBy(0, 300);");
            Thread.sleep(2000);
            // ✅ Capture Screenshot
            ScreenshotUtil.takeScreenshot(driver, "Updated_Cart");
            test.log(Status.INFO, "Screenshot Captured: Updated Cart");
           

        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to Remove Product: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDownTest() {
        try {
            if (extent != null) {
                extent.flush();
            }
            if (driver != null) {
                driver.quit();
            }
            test.log(Status.INFO, "Browser Closed");
        } catch (Exception e) {
            test.log(Status.FAIL, "Error while closing resources: " + e.getMessage());
        }
    }
}
