package stepDef;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import base.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private static WebDriver driver;

    @Before
    public void setup() {
        if (driver == null) {
        	driver = BaseClass.getDriver();
            driver.manage().window().maximize();
        }
    }

    @After
    public void tearDown() {
        // Do not close the browser after each scenario
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
