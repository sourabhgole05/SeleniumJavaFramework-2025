package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

	@Test
	public void testValidLogin() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);

		loginPage.enterPassword("admin@yourstore.com");
		loginPage.enterPassword("admin");
		loginPage.clickLogin();

		Thread.sleep(5000);
		
		System.out.println("Title of the Page is : " + driver.getTitle());
		
		Assert.assertEquals(driver.getTitle(), "Just a moment...");
		

	}
}
