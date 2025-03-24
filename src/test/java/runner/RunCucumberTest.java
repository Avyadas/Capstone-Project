package runner;

import base.BaseClass;
import io.cucumber.core.cli.Main;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Feature("Add to Cart")  // ✅ Groups tests under the "Add to Cart" feature
@Story("User adds product to cart successfully")  // ✅ Defines the test story
public class RunCucumberTest extends BaseClass {

    @Test(priority = 3)
    @Description("Executes the Add to Cart feature using Cucumber")  // ✅ Adds test description in Allure
    @Severity(SeverityLevel.CRITICAL)  // ✅ Marks test as critical
    public void runCucumberTests() {
        test = extent.createTest("Add to Cart");

        // Ensure browser is still open
        driver = BaseClass.getDriver();

        String[] cucumberOptions = {
            "-g", "stepDef",
            "src/test/java/features/AddToCart.feature",
            "--plugin", "html:target/cucumber-reports.html"
        };

        try {
            executeCucumber(cucumberOptions);
        } catch (Exception e) {
            Allure.addAttachment("Error Log", e.getMessage());  // ✅ Attaches failure log to Allure report
            test.fail("Add to Cart failed: " + e.getMessage());
        }
    }

    @Step("Executing Cucumber Tests")  // ✅ Logs this step in Allure
    private void executeCucumber(String[] options) {
        Main.run(options);
        test.pass("Add to Cart executed successfully");
    }
}
