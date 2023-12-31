package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class Setup implements ITestListener {
    public static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    public void onStart(ITestContext context) {
        String fileName = ExtentReportsManager.getReportNameWithTimeStamp();
        String reportsPath = System.getProperty("user.dir") + "\\reports\\" + fileName;
        extentReports = ExtentReportsManager.createReport(reportsPath,"Test API Automation Report","Test ExecutionReport");
    }
    public void onFinish(ITestContext context) {
        if(extentReports != null)
            extentReports.flush();
    }

    public void onTestStart(ITestResult result) {
        //Commenting below code for executing PetsTestScenarios.java class..After executing we can un comment below code
        /*ExtentTest extentTest = extentReports.createTest("Test Name:" + " " + result.getTestClass().getName() + " - " + result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        extentTestThreadLocal.set(extentTest);*/
    }
    public void onTestFailure(ITestResult result) {
        ExtentReportsManager.logFailDetails(result.getThrowable().getMessage());
        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
        stackTrace = stackTrace.replaceAll(",","<br>");
        String formattedPrintTrace = "<details>\n" +
                "  <summary>Click Here to see log message</summary>\n" +
                "  "+stackTrace+"\n" +
                "</details>\n";
        ExtentReportsManager.logExceptionDetails(formattedPrintTrace);
    }
}
