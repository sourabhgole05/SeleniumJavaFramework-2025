package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	private static ExtentReports extent;
	private static ExtentTest test;

	public static ExtentReports getReportsInstance() {
		if (extent == null) {

			String timestamp = new SimpleDateFormat("yyyy-mm-dd_HH-mm-ss").format(new Date());
			String reportPath = "reports/ExtentReport_" + timestamp + ".html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
			reporter.config().setDocumentTitle("Automation Test Report");
			reporter.config().setReportName("Test Execution Report");

			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}

		return extent;
	}

	public static ExtentTest creatTest(String testName) {

		test = getReportsInstance().createTest(testName);
		return test;

	}

	public static String captureSreeenshot(WebDriver driver, String screenshotName) {

		try {

			File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			String path = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";

			FileUtils.copyFile(scr, new File(path));
			return path;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
