package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseTest_S;

public class EmployeeReferPage extends BaseTest_S {

	private WebDriver driver;
	
	String url = "https://wd103.myworkday.com/wday/authgwy/accenture/login.html";

	
	@FindBy(xpath= "//div[contains(text(),'Accenture Employee')]")
	WebElement accentureEmployee ;
	
	@FindBy(xpath ="//input[@type='email']")
	WebElement email;
	
	@FindBy(xpath ="//input[@type='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='idSIButton9']")
	WebElement nextBtn;
	
	@FindBy(xpath="//div[@id='idDiv_SAOTCAS_Title']")
	WebElement approveSingRequest; 
	
	@FindBy(xpath="//img[@role='presentation']")
	WebElement accentureLogo;
	
	@FindBy(css = "[title='Global Navigation']")
	WebElement globalNavigationMenu;
	
	@FindBy(css="[aria-label='Careers Marketplace']")
	WebElement careersMarketplace;
	
	@FindBy(css="[aria-label='Search Jobs & Apply/Refer']")
	WebElement searchJobsForRefer;
	
	@FindBy(css="[placeholder='search']")
	WebElement searchBox;
	
	@FindBy(xpath="//span[@title='Search']")
	WebElement searchBtn;
	
	public EmployeeReferPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loginRefUrl() {
		driver.get(url);
	}
	
	public void enterEmail() {
		accentureEmployee.click();
		email.sendKeys("sourabh.gole@accenture.com");
	}
	
	public void enterPassword(String pwsd) {
		waitForElementToBeVisible(password);
		password.sendKeys(pwsd);
	}
	
	public void clickNextBtn() {
		nextBtn.click();
	}
	
	public void approveSingRequestAccept() {
		waitForElementToBeVisible(approveSingRequest);
	}
	
	public void accentureLogo() {
		 waitForElementToBeVisible(accentureLogo);
		accentureLogo.isDisplayed();
	}
	
	public void clickToMenu() {
		waitForElementToBeClickable(globalNavigationMenu);
		globalNavigationMenu.click();
	}
	
	public void goToCareerMarketplace() {
		waitForElementToBeClickable(careersMarketplace);
		careersMarketplace.click();
	}
	
	public void goToSearchJobsForRefer() {
		waitForElementToBeClickable(searchJobsForRefer);
		searchJobsForRefer.click();
	}
	
	public void searchJobIDforRefer(String jobID) {
		waitForElementToBeClickable(searchBox);
		searchBox.sendKeys(jobID);
		searchBtn.click();
	}
	
	
	
	
}
