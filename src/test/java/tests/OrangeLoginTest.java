package tests;


import java.util.Timer;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.OrangeHRMLoginTest;
import utils.Log;

public class OrangeLoginTest extends BaseTest{

	 @Test
	public void ORGLoginTest() throws InterruptedException {
		
		OrangeHRMLoginTest orgHRM =  new OrangeHRMLoginTest(driver);
		
		Log.info("Open URL");
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		Log.info("wait for load a page");
		Thread.sleep(5000);

		Log.info("enter user name : xxxx");
		orgHRM.enterUserName("Admin");
		Log.info("enter password : xxxx");
		orgHRM.enterPassword("admin123");
		Log.info("click to login");
		orgHRM.clickLogin();
		
		Assert.assertEquals(driver.getTitle(),"OrangeHRM");
		
	}
	
	
	
}
