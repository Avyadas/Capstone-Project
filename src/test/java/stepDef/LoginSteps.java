package stepDef;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginSteps {
    WebDriver driver = Hooks.getDriver(); // Reuse existing WebDriver instance

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
    	
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
    }

    @When("User enters valid credentials")
    public void user_enters_valid_credentials() {
       
    }
}
