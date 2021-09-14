package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testcases.BaseTestClass;

public class Reporter extends TestListenerAdapter {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(new Date());// time stamp
		String reportName = "Extent-Report-" + timeStamp + ".html";

		htmlReporter = new ExtentHtmlReporter("./extent-reports/" + reportName);
		htmlReporter.loadXMLConfig("./extent-config.xml");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Hostname", "localhost");
		extent.setSystemInfo("Environemnt", "Live - Production");
		extent.setSystemInfo("QA Engineer", "Zabiullah");

		htmlReporter.config().setDocumentTitle("Amazon.in Test Project");
		htmlReporter.config().setReportName("Functional Automation Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
	}

	public void onTestStart(ITestResult tr) {
		test = extent.createTest(tr.getMethod().getDescription());
	}

	public void onTestSuccess(ITestResult tr) {
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult tr) {
		System.out.println("reporter : onTestFailure");

		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		BaseTestClass.captureScreenshot(tr.getName());

		String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + tr.getName() + ".png";
		File file = new File(screenshotPath);
		if (file.exists()) {
			try {
				test.info("Please find below screenshot...");
				test.addScreenCaptureFromPath(screenshotPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult tr) {
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
