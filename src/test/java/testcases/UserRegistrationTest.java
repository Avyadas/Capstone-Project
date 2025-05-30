package testcases;

import base.BaseClass;
import org.testng.annotations.*;
import pages.RegistrationPage;
import utils.ScreenshotUtil;

import com.aventstack.extentreports.Status;

public class UserRegistrationTest extends BaseClass {
    static RegistrationPage registrationPage;

    @BeforeClass
    public void setUp() {
        setupExtentReport();
        test = extent.createTest("User Registration Test");
        invokeBrowser();
        openRegistrationPage();
        registrationPage = new RegistrationPage(driver);
    }

    @Test(priority = 1)
    public void registerWithInvalidDetails() throws InterruptedException {
        test.log(Status.INFO, "Testing registration with invalid details.");
        //Registration with invalid details
        registrationPage.registerUser(prop.getProperty("invalid_first_name"),
                                      prop.getProperty("invalid_last_name"),
                                      prop.getProperty("invalid_email"),
                                      prop.getProperty("invalid_password"));

        Thread.sleep(5000);
        ScreenshotUtil.takeScreenshot(driver, "InvalidDetails");
        test.log(Status.PASS, "Invalid Registration Attempt.");
        driver.navigate().refresh();
    }

    @Test(priority = 2, dependsOnMethods = "registerWithInvalidDetails")
    public void registerWithValidDetails() throws InterruptedException {
        test.log(Status.INFO, "Testing registration with valid details.");
        //Registration with valid details
        registrationPage.registerUser(prop.getProperty("valid_first_name"),
                                      prop.getProperty("valid_last_name"),
                                      prop.getProperty("valid_email"),
                                      prop.getProperty("valid_password"));

        Thread.sleep(5000);
        ScreenshotUtil.takeScreenshot(driver, "ValidDetails");
        test.log(Status.PASS, "Registration successful.");
        openLoginPage();
    }

    @AfterClass
    public void tearDownTest() {
    	 openLoginPage();
        //tearDown();
    }
}
