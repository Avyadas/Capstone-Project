package runner;

import base.BaseClass;
import io.cucumber.core.cli.Main;
import org.testng.annotations.Test;

public class RunCucumberTest extends BaseClass {

    @Test(priority = 3)
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
            Main.run(cucumberOptions);
            test.pass("Add to Cart executed successfully");
        } catch (Exception e) {
            test.fail("Add to Cart failed: " + e.getMessage());
        }
    }
}
