package testcases;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.LoginPage;
//import runner.AddToCartRunner;
import utils.ExcelUtil;
import com.aventstack.extentreports.Status;

public class LoginPageTest extends BaseClass {
    WebDriver driver;
    LoginPage loginPage;
    @Parameters("browser")
    @BeforeClass
   
    public void setup( String browser) {
        setupExtentReport();
        driver = BaseClass.driver;
        loginPage = new LoginPage(driver);
        test = extent.createTest("Login Page Test");
    }

    @Test(dataProvider = "LoginData" ,dependsOnMethods= "testcases.UserRegistrationTest.registerWithValidDetails")
    public void testLogin(String email, String password, String status) {
        test.log(Status.INFO, "Attempting login with email: " + email);
        loginPage.login(email, password);

        if (status.equalsIgnoreCase("Invalid")) {
            test.log(Status.PASS, "Invalid Login Attempt");
            loginPage.handleAlert("Invalid details ! Try again", "InvalidLogin");
        } else {
            test.log(Status.PASS, "Successful Login");
            loginPage.handleAlert("Successfully login", "HomePage");
//           
        }
        
    }

    @DataProvider(name = "LoginData")
    public Object[][] getData() {
        return ExcelUtil.getExcelData("C:\\Users\\avyab\\eclipse-workspace\\Luma\\LoginData.xlsx", "Sheet1");
    }

    @AfterClass
    public void tearDown() {
    	 driver.get("https://magento.softwaretestingboard.com/");
        extent.flush();
    }
}
