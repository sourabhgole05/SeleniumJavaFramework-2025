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
	
}
