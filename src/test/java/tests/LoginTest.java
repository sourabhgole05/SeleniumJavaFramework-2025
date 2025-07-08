package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {

	@Test
	public void testValidLogin() throws InterruptedException {

		Log.info("Starting Login test.....");
		test = ExtentReportManager.creatTest("Login Test");

		test.info("Navigating url....");
		LoginPage loginPage = new LoginPage(driver);

		Log.info("Adding credentials");
		test.info("Adding redentials");
		loginPage.enterPassword("admin@yourstore.com");
		loginPage.enterPassword("admin");
		loginPage.clickLogin();
		test.info("Clicking login button");

		Thread.sleep(2500);

		System.out.println("Title of the Page is : " + driver.getTitle());
		Log.info("Verifying Page Title.....");
		test.info("verifying page title");
		Assert.assertEquals(driver.getTitle(), "Just a moment....");
		test.pass("Login Successfully");

	}
	
	@Test
	public void testInvalidLogin() throws InterruptedException {

		Log.info("Starting Login test.....");
		test = ExtentReportManager.creatTest("Login Test");

		test.info("Navigating url....");
		LoginPage loginPage = new LoginPage(driver);

		Log.info("Adding credentials");
		test.info("Adding redentials");
		loginPage.enterPassword("admin@yourstore1.com");
		loginPage.enterPassword("admin");
		loginPage.clickLogin();
		test.info("Clicking login button");

		Thread.sleep(2500);

		System.out.println("Title of the Page is : " + driver.getTitle());
		Log.info("Verifying Page Title.....");
		test.info("verifying page title");
		Assert.assertEquals(driver.getTitle(), "Just a moment....");
		test.pass("Login Successfully");

	}
}
