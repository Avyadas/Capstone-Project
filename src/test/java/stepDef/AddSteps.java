package stepDef;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ExcelReader;
import utils.ScreenshotUtil;


public class AddSteps {
	WebDriverWait wait;
    WebDriver driver = new FirefoxDriver();

    @Given("User logs in with valid credentials")
    public void user_logs_in() {
        driver.get("https://magento.softwaretestingboard.com/");
        driver.findElement(By.linkText("Sign In")).click();

        // Retrieve login data from Excel
        String[] loginData = ExcelReader.getLoginData();
        driver.findElement(By.id("email")).sendKeys(loginData[0]);
        driver.findElement(By.id("pass")).sendKeys(loginData[1]);
        driver.findElement(By.id("send2")).click();
      
        Assert.assertTrue(driver.findElement(By.cssSelector(".greet.welcome")).isDisplayed());
    }

    @When("User selects Women -> Tops > Antonia Racer Tank -> XL -> Purple")
    public  void user_selects_fixed_category() throws InterruptedException {
        // Navigate to "Women" -> "Tops"
      driver.findElement(By.linkText("Women")).click();
      driver.findElement(By.linkText("Tops")).click();
      JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.scrollBy(0, 300);");
        // Select "Antonia Racer Tank"
        driver.findElement(By.linkText("Antonia Racer Tank")).click();
      Thread.sleep(3000);
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0, 300);");
        //Selects XL Size
		driver.findElement(By.xpath("//div[@id='option-label-size-143-item-170']")).click();
		Thread.sleep(3000);
		//Selects Purple color
		driver.findElement(By.xpath("//div[@id='option-label-color-93-item-57']")).click();
		 // Set Quantity to 3
        WebElement quantityInput = driver.findElement(By.id("qty"));
        quantityInput.clear();
        quantityInput.sendKeys("3");
		//Adds products to the cart
		driver.findElement(By.xpath("//button[@id='product-addtocart-button']")).click();
		Thread.sleep(3000);
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0, -300);");
		 // Take a screenshot
        ScreenshotUtil.takeScreenshot(driver, "Item_Added_Success");
    }

    @Then("Item should be added to cart successfully")
    public void item_added_successfully() {
        driver.quit();
    }
    
}
