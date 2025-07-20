package tests;

import org.testng.annotations.Test;

import base.BaseTest_S;
import pages.EmployeeReferPage;

public class EmployeeRefer extends BaseTest_S {

	

	@Test
	public void validEmployeeRefer() {
	
		EmployeeReferPage empRef = new EmployeeReferPage(driver);
		
		String password =  "Poojasg@1994";
		
		test = extent.createTest("Test Successful Login", "Employee Referral Test Starting...!");
		
		empRef.loginRefUrl();
		
		empRef.enterEmail();
		
		empRef.clickNextBtn();
		
				
		empRef.enterPassword(password);
		
		empRef.clickNextBtn();
		
	}
	
	
	

	
}

