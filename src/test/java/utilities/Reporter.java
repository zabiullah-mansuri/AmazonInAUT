package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

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

		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		ITestContext context = tr.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		Logger log4jLogger = (Logger) context.getAttribute("log4jLogger");

		String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + tr.getName() + ".png";
		File source, target = null;
		try {
			source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			target = new File(screenshotPath);
			FileUtils.copyFile(source, target);
			log4jLogger.info("Screenshot has been captured and saved to folder.");
		} catch (ClassCastException e) {
			log4jLogger.error("Couldn't take screenshot in HTML Unit Browser.");
		} catch (IOException e) {
			log4jLogger.error(e.getMessage());
		}

		if (target.exists()) {
			try {
				test.info("Please find below screenshot...");
				test.addScreenCaptureFromPath(screenshotPath);
				log4jLogger.info("Screenshot has been attached in extent-report.");
			} catch (IOException e) {
				log4jLogger.error(e.getMessage());
			}
		} else {
			log4jLogger.error("Couldn't find screenshot for attachment.");
		}

	}

	public void onTestSkipped(ITestResult tr) {
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
