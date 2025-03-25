package extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Taylor");
        }
        return extent;
    }
}
