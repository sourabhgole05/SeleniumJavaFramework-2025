package tests;

import org.testng.annotations.Test;

import base.BaseTest_S;
import pages.EmployeeReferPage;

public class EmployeeRefer extends BaseTest_S {

	

	@Test
	public void validEmployeeRefer() {
	
		EmployeeReferPage empRef = new EmployeeReferPage(driver);
		
		String password =  "Poojasg@1994";
		String jobID = "ATCI-4918797-S1863298";	
		
		test = extent.createTest("Test Successful Login", "Employee Referral Test Starting...!");
		
		empRef.loginRefUrl();
		
		empRef.enterEmail();
		
		empRef.clickNextBtn();
				
		empRef.enterPassword(password);
		
		empRef.clickNextBtn();
		
		empRef.approveSingRequestAccept();
		
		empRef.accentureLogo();
		
		empRef.clickToMenu();
		
		empRef.goToCareerMarketplace();
		
		empRef.goToSearchJobsForRefer();
		
		empRef.searchJobIDforRefer(jobID);
		
		captureScreenshot("Find Job ID");
	}
	
	
	

	
}

