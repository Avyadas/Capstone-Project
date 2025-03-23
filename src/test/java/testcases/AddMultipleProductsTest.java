package testcases;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseClass;
import utils.ScreenshotUtil;


public class AddMultipleProductsTest extends BaseClass {
	WebDriver driver;
	 @BeforeClass
	    public void setUp() {
	        invokeBrowser();  // Use browser from data.properties
	        driver = getDriver();
	        test = extent.createTest("Add MultipleProducts To Cart Test ");
	    }
	 @Test(priority = 1)
	    public void addmen() throws InterruptedException {
		 driver.get("https://magento.softwaretestingboard.com/");
         driver.findElement(By.linkText("Sign In")).click();
         Thread.sleep(2000);
         driver.findElement(By.id("email")).sendKeys("avya.borra@gmail.com");
         Thread.sleep(2000);
         driver.findElement(By.id("pass")).sendKeys("Avya1516");
         Thread.sleep(2000);
         driver.findElement(By.id("send2")).click();
         Thread.sleep(2000);
//         test.log(Status.INFO, "Navigating to Cart");
//         test.log(Status.PASS, "Procedd to checkout successfully");
        
         driver.findElement(By.linkText("Men")).click();
         driver.findElement(By.linkText("Bottoms")).click();
         JavascriptExecutor js = (JavascriptExecutor) driver;
          js.executeScript("window.scrollBy(0, 300);");
           driver.findElement(By.linkText("Orestes Fitness Short")).click();
         Thread.sleep(3000);
   		JavascriptExecutor js1 = (JavascriptExecutor) driver;
           js1.executeScript("window.scrollBy(0, 300);");
           //Selects XL Size
   		driver.findElement(By.xpath("//div[@id='option-label-size-143-item-175']")).click();
   		Thread.sleep(3000);
   		//Selects Gray color
   		driver.findElement(By.xpath("//div[@id='option-label-color-93-item-49']")).click();
   		 // Set Quantity to 3
           WebElement quantityInput1 = driver.findElement(By.id("qty"));
           quantityInput1.clear();
           quantityInput1.sendKeys("3");
           Thread.sleep(1000);
   		//Adds products to the cart
   		driver.findElement(By.xpath("//button[@id='product-addtocart-button']")).click();
   		Thread.sleep(3000);
   		JavascriptExecutor js2 = (JavascriptExecutor) driver;
           js2.executeScript("window.scrollBy(0, -300);");
   		 // Take a screenshot
           ScreenshotUtil.takeScreenshot(driver, "Men_Added_Success");
           driver.navigate().to("https://magento.softwaretestingboard.com");
	 }
	 @Test(priority = 1)
	    public void addwatch() throws InterruptedException {
      driver.findElement(By.linkText("Gear")).click();
      driver.findElement(By.linkText("Watches")).click();
      JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.scrollBy(0, 300);");
//
        driver.findElement(By.linkText("Aim Analog Watch")).click();
      Thread.sleep(3000);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0, 300);");
		 // Set Quantity to 3
        WebElement quantityInput = driver.findElement(By.id("qty"));
        quantityInput.clear();
        quantityInput.sendKeys("2");
        Thread.sleep(1000);
		//Adds products to the cart
		driver.findElement(By.xpath("//button[@id='product-addtocart-button']")).click();
		Thread.sleep(3000);
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0, -300);");
		 // Take a screenshot
        ScreenshotUtil.takeScreenshot(driver, "Watch_Added_Success");
        driver.navigate().to("https://magento.softwaretestingboard.com");
	 }
	 @Test(priority=1)
	 public void addshirt() throws InterruptedException {
	 driver.findElement(By.linkText("Women")).click();
     driver.findElement(By.linkText("Hoodies & Sweatshirts")).click();
     JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("window.scrollBy(0, 300);");
       driver.findElement(By.linkText("Phoebe Zipper Sweatshirt")).click();
     Thread.sleep(3000);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
       js1.executeScript("window.scrollBy(0, 300);");
       //Selects XL Size
		driver.findElement(By.xpath("//div[@id='option-label-size-143-item-170']")).click();
		Thread.sleep(3000);
		//Selects Gray color
		driver.findElement(By.xpath("//div[@id='option-label-color-93-item-52']")).click();
		 // Set Quantity to 1
       WebElement quantityInput = driver.findElement(By.id("qty"));
       quantityInput.clear();
       quantityInput.sendKeys("1");
       Thread.sleep(1000);
     //Adds products to the cart
     		driver.findElement(By.xpath("//button[@id='product-addtocart-button']")).click();
     		Thread.sleep(3000);
     		JavascriptExecutor js2 = (JavascriptExecutor) driver;
             js2.executeScript("window.scrollBy(0, -300);");
     		 // Take a screenshot
             ScreenshotUtil.takeScreenshot(driver, "Shirt_Added_Success");
	 }
	 @AfterClass
	    public void tearDownTest() {
	    	 tearDown();
	        extent.flush();
	 }
	
}