package base;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import extent.ExtentManager;

public class BaseClass {
    public static WebDriver driver;
    public static Properties prop;
    public static ExtentReports extent;
    public static ExtentTest test;

    public BaseClass() {
        try {
            prop = new Properties();
            FileInputStream file = new FileInputStream("C:\\Users\\avyab\\eclipse-workspace\\Luma\\src\\test\\resources\\data.properties");
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file!");
        }
    }

    // ✅ Initialize the single Extent Report instance
    public static void setupExtentReport() {
        extent = ExtentManager.getInstance();  // Use the singleton Extent Report
    }

    public static void invokeBrowser(String browser) {
        if (browser == null || browser.isEmpty()) {
            browser = prop.getProperty("browser");
        }
        launchBrowser(browser);
    }

    public static void invokeBrowser() {
        String browser = prop.getProperty("browser");
        launchBrowser(browser);
    }

    protected static WebDriver launchBrowser(String browser) {
        try {
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            }
            driver.manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("WebDriver initialization failed.");
        }
		return driver;
    }

    public static void openRegistrationPage() {
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
    }

    public static void openLoginPage() {
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
    }

    // ✅ Flush Extent Report **only once after all tests are completed**
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            launchBrowser("browser");  // Ensure driver is initialized if not already
        }
        return driver;
    }

	
	}

