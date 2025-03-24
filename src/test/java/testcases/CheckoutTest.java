package testcases;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import base.BaseClass;
import pages.CartPage;
import pages.CheckoutPage;
import utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CheckoutTest extends BaseClass {
    WebDriver driver;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        invokeBrowser();  // Launch browser from data.properties
        driver = getDriver();
        driver.get("https://magento.softwaretestingboard.com/");
        test = extent.createTest("Checkout Test", "Verify checkout functionality");
    }

    @Test(priority = 1)
    public void checkoutProcess() throws InterruptedException {
        driver.findElement(By.linkText("Sign In")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("email")).sendKeys("avya.borra@gmail.com");
        Thread.sleep(2000);
        driver.findElement(By.id("pass")).sendKeys("Avya1516");
        Thread.sleep(2000);
        driver.findElement(By.id("send2")).click();
        Thread.sleep(2000);
        try {
            test.log(Status.INFO, "Navigating to Cart");
            CartPage cart = new CartPage(driver);
            Thread.sleep(5000);
            cart.goToCart();
            test.log(Status.PASS, "Navigated to cart successfully");
            Thread.sleep(3000);

            test.log(Status.INFO, "Proceeding to Checkout");
            CheckoutPage checkout = new CheckoutPage(driver);
            checkout.clickProceedToCheckout();
            test.log(Status.PASS, "Procedd to checkout successfully");
            Thread.sleep(3000);

            test.log(Status.INFO, "Filling Shipping Details");
            checkout.enterShippingDetails("Avya", "Das", "123 Main St", "New York", "New York", "10001", "United States", "1234567890");
            checkout.clickShipHere();
            test.log(Status.PASS, "Filled Details successfully");
            Thread.sleep(3000);

            test.log(Status.INFO, "Selecting Free Shipping ($0.00)");
            checkout.selectFreeShipping();
            test.log(Status.PASS, "Selects shipping successfully");
            Thread.sleep(2000);

            test.log(Status.INFO, "Proceeding to Payment");
            checkout.clickNext();
            test.log(Status.PASS, "Payment done successfully");
            Thread.sleep(3000);

            test.log(Status.INFO, "Placing the Order");
            checkout.placeOrder();
            test.log(Status.PASS, "Placed Order successfully");
            Thread.sleep(5000);

            test.log(Status.INFO, "Capturing Order Number");
            String orderNumber = checkout.getOrderNumber();
            System.out.println("Order Number: " + orderNumber);
            test.log(Status.PASS, "Order placed successfully: " + orderNumber);

            ScreenshotUtil.takeScreenshot(driver, "Order_Success");
            test.log(Status.INFO, "Screenshot captured after successful order");

            test.log(Status.INFO, "Logging out");
            checkout.logout();
            test.log(Status.PASS, "Logged out successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        test.log(Status.INFO, "Browser closed");
        extent.flush();
    }
}
