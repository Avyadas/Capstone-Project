package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;

    // Locators
    By proceedToCheckoutBtn = By.xpath("//button[contains(text(),'Proceed to Checkout')]");
    By AddressBtn = By.xpath("//button[@class='action action-show-popup']");
    By firstName = By.name("firstname");
    By lastName = By.name("lastname");
    By streetAddress = By.name("street[0]");
    By city = By.name("city");
    By state = By.name("region_id");
    By zipCode = By.name("postcode");
    By country = By.name("country_id");
    By phone = By.name("telephone");
    By shipHereButton = By.xpath("//span[normalize-space()='Ship here']");
    By freeShipping = By.xpath("//tbody/tr[1]/td[1]");
    By nextButton = By.xpath("//span[normalize-space()='Next']");
    By placeOrderButton = By.xpath("//button[@title='Place Order']");
    By orderNumber = By.cssSelector("a[class='order-number'] strong");
    By signoutBtn=By.xpath("//div[@class='panel header']//button[@type='button']");
    By logoutButton = By.linkText("Sign Out");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }
 //Click "Proceed to Checkout"
    public void clickProceedToCheckout() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn)).click();
        } catch (Exception e) {
            System.out.println("Proceed to Checkout button not found: " + e.getMessage());
        }
    }

    //Fill Shipping Details
    public void enterShippingDetails(String fName, String lName, String address, String cityName, String stateName, String zip, String countryName, String phoneNum) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkout-loader")));
    	try {
            driver.findElement(firstName).clear();
            driver.findElement(firstName).sendKeys(fName);
            driver.findElement(lastName).clear();
            driver.findElement(lastName).sendKeys(lName);
            driver.findElement(streetAddress).sendKeys(address);
            driver.findElement(city).sendKeys(cityName);
            selectFromDropdown(state, stateName);
            driver.findElement(zipCode).sendKeys(zip);
            selectFromDropdown(country, countryName);
            driver.findElement(phone).sendKeys(phoneNum);
        } catch (Exception e) {
            System.out.println("Failed to enter shipping details: " + e.getMessage());
        }
    }
 //Click "Ship Here"
    public void clickShipHere() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(shipHereButton)).click();
        } catch (Exception e) {
            System.out.println("Ship Here button not found: " + e.getMessage());
        }
    }

    // Select Free Shipping Option
    public void selectFreeShipping() {
        try {
        	 JavascriptExecutor js = (JavascriptExecutor) driver;
             js.executeScript("window.scrollBy(0, 500)");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(freeShipping)).click();
        } catch (Exception e) {
            System.out.println("Free shipping option not found: " + e.getMessage());
        }
    }

    // Click Next Button (Payment Step) to continue to payment
    public void clickNext() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        } catch (Exception e) {
            System.out.println("Failed to click Next button: " + e.getMessage());
        }
    }

    // Placing order by clicking Place Order
    public void placeOrder() {
        try {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading-mask")));

            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait1.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderButton);
        } catch (Exception e) {
            System.out.println("Failed to place order: " + e.getMessage());
        }
    }

    // Capture Order Number
    public String getOrderNumber() {
        try {
        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumber)).getText();
        } catch (Exception e) {
            System.out.println("Order number not found: " + e.getMessage());
            return "N/A";
        }
    }

    // Logout from Account
    public void logout() {
        try {
        	
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(signoutBtn)).click();
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        } catch (Exception e) {
            System.out.println("Logout failed: " + e.getMessage());
        }
    }

    // Helper Method: Select Dropdown
    private void selectFromDropdown(By locator, String value) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(value);
    }
}
