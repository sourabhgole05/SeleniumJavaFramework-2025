package tests;




import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import base.BaseTest_S;
import pages.PracticeLoginPage;
import utils.Log;

public class PracticeLoginTest extends BaseTest_S {

	@Test
	public void validLoginTest() {

		Log.info("Starting Login test.....");
	//	test = ExtentReportManager.creatTest("Login Test");
		test = extent.createTest("Test Successful Login", "Verify user can login with valid credentials");
		test.info("Navigating url....");

		PracticeLoginPage loginPage = new PracticeLoginPage(driver);

		test.info("Enter Username");
		loginPage.enterUsername("student");
		
		test.info("Enter Password");
		loginPage.enterPassword("Password123");

		test.info("Click to Submit button");
		loginPage.clickSubmitBtn();
		
		String expectedUrl  =  "practicetestautomation1.com/logged-in-successfully/";
		String actualUrl = driver.getCurrentUrl();
		

	    // Log the actual URL
	    test.info("Actual URL is: " + actualUrl);
	    test.info("Expected URL should contain: " + expectedUrl);

	    // Validation with assert and fallback log
	    try {
	        Assert.assertTrue(actualUrl.contains(expectedUrl), 
	            "URL Mismatch: expected to contain '" + expectedUrl + "', but got '" + actualUrl + "'");
	        test.pass("✅ URL validation passed.");
	    } catch (AssertionError e) {
	        test.fail("❌ URL validation failed. Details: " + e.getMessage());
	        throw e; // rethrow to mark test failed
	    }
	}
}