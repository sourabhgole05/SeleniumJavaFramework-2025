package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest_S;
import pages.PracticeLoginPage;
import utils.ConfigManager;
import utils.Log;

public class NegativeLoginTest2 extends BaseTest_S {

	@Test
	public void validLoginTest() {

		ConfigManager config = ConfigManager.getInstance();

		// Get values from config
		String username = config.getProperty("invalid.username");
		String password = config.getProperty("valid.password");

		Log.info("Starting Login test.....");
		// test = ExtentReportManager.creatTest("Login Test");
		test = extent.createTest("Test Successful Login", "Verify user can login with valid credentials");
		test.info("Navigating url....");
		Log.info("navigating to url....");

		PracticeLoginPage loginPage = new PracticeLoginPage(driver);

		test.info("Enter Username");
		loginPage.enterUsername(username);
		Log.info("Enter Username");

		test.info("Enter Password");
		loginPage.enterPassword(password);
		Log.info("Enter Password");

		test.info("Click to Submit button");
		loginPage.clickSubmitBtn();
		Log.info("Click to Submit ");

		
		Assert.assertTrue(loginPage.isLoginErrorMsgDisplay(), "Login error msg is not displaying....Entered username correctly !!!"); 
		
//		String expectedUrl = "practicetestautomation.com/logged-in-successfully/";
//		String actualUrl = driver.getCurrentUrl();

		// Log the actual URL
//		test.info("Actual URL is: " + actualUrl);
//		test.info("Expected URL should contain: " + expectedUrl);

		// Validation with assert and fallback log
//		try {
//			Assert.assertTrue(actualUrl.contains(expectedUrl),
//					"URL Mismatch: expected to contain '" + expectedUrl + "', but got '" + actualUrl + "'");
//			test.pass("✅ URL validation passed.");

//		} catch (AssertionError e) {
//			test.fail("❌ URL validation failed. Details: " + e.getMessage());
//			throw e;
//		}

//		Assert.assertTrue(loginPage.isLogoutBtnDisplay());
	}
}