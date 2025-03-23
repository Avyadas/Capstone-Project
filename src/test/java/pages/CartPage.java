package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    By cartIcon = By.cssSelector(".showcart");
   // By viewCartLink = By.xpath("//a[contains(text(),'View and Edit Cart')]");
    By proceedToCheckoutBtn = By.xpath("//button[contains(text(),'Proceed to Checkout')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigate to Cart Page
    public void goToCart() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
            
        } catch (Exception e) {
            System.out.println("Failed to open cart page: " + e.getMessage());
        }
    }

    // Click Proceed to Checkout
    public void clickProceedToCheckout() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn)).click();
        } catch (Exception e) {
            System.out.println("Checkout button not found: " + e.getMessage());
        }
    }
}
