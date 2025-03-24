package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.AllureTestNg;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "stepDe",
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "json:target/cucumber-reports/cucumber.json",
        "tech.grasshopper.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"  //  Allure plugin for reporting
    }
)

@Feature("Add to Cart")  //Grouping feature in Allure reports
@Story("User adds product to cart")  //Defining the story in Allure reports
public class AddToCartRunner extends AbstractTestNGCucumberTests {
    private static ExtentReports extent;

    @BeforeClass
    public static void setupExtentReport() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReports/CucumberExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @AfterClass
    public static void tearDownExtentReport() {
        extent.flush();
    }
}
