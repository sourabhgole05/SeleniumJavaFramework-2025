package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager {

	private static ExtentReports extent;
	private static ExtentTest test;

	 public static ExtentReports createInstance() {
	        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	        String reportPath = System.getProperty("user.dir") + "/reports/Test-Report-" + timeStamp + ".html";
	        
	        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle("Automation Test Report");
	        htmlReporter.config().setReportName("Test Execution Report");
	        
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        extent.setSystemInfo("OS", System.getProperty("os.name"));
	        extent.setSystemInfo("Environment", "QA");
	        
	        return extent;
	    }

		/*
		 * public static String captureScreenshot(WebDriver driver, String
		 * screenshotName) { try { TakesScreenshot ts = (TakesScreenshot) driver; File
		 * source = ts.getScreenshotAs(OutputType.FILE); String dest =
		 * System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
		 * File destination = new File(dest); FileUtils.copyFile(source, destination);
		 * return dest; } catch (Exception e) {
		 * System.out.println("Exception while taking screenshot: " + e.getMessage());
		 * return e.getMessage(); } }
		 */
	 
	 
	  // Method to capture screenshot and return as Base64
	    public static String captureScreenshotAsBase64(WebDriver driver) {
	        try {
	            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	        } catch (Exception e) {
	            System.out.println("Exception while taking screenshot: " + e.getMessage());
	            return null;
	        }
	    }

	    // Method to attach screenshot to Extent Report
	    public static void attachScreenshotToReport(WebDriver driver, ExtentTest test, String message) {
	        try {
	            String base64Screenshot = captureScreenshotAsBase64(driver);
	            if (base64Screenshot != null) {
	                test.info(message, 
	                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
	            } else {
	                test.warning("Failed to capture screenshot");
	            }
	        } catch (Exception e) {
	            test.warning("Failed to attach screenshot: " + e.getMessage());
	        }
	    }

	 // In ExtentReportManager.java
	    public static void attachScreenshotOnFailure(WebDriver driver, ExtentTest test, Throwable throwable) {
	        try {
	            String base64Screenshot = captureScreenshotAsBase64(driver);
	            if (base64Screenshot != null) {
	                test.fail(throwable, 
	                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
	            } else {
	                test.fail("Screenshot unavailable: " + throwable.getMessage());
	            }
	        } catch (Exception e) {
	            test.fail("Failed to capture screenshot: " + e.getMessage());
	        }
	    }
	 
		public static ExtentReports getReportsInstance() {
			// TODO Auto-generated method stub
			return null;
		}
	}