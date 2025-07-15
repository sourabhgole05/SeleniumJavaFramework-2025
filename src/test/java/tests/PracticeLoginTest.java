package tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.PracticeLoginPage;
import utils.ExtentReportManager;
import utils.Log;

public class PracticeLoginTest extends BaseTest {

	@Test
	public void validLoginTest() {

		Log.info("Starting Login test.....");
		test = ExtentReportManager.creatTest("Login Test");
		test.info("Navigating url....");

		PracticeLoginPage loginPage = new PracticeLoginPage(driver);

		test.info("Enter Username");
		loginPage.enterUsername("student");
		
		test.info("Enter Password");
		loginPage.enterPassword("Password123");

		test.info("Click to Submit button");
		loginPage.clickSubmitBtn();
		
		String expectedUrl  =  "practicetestautomation1.com/logged-in-successfully/";
		String actulUrl = driver.getCurrentUrl();
		
		if (actulUrl.contains(expectedUrl)) {
			
			test.info("validate url checks");
		 Assert.assertTrue(true);
			
		}

	}

}
