package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticeLoginPage {

	private WebDriver driver;


	@FindBy(id = "username")
	WebElement userNameTextbox;

	@FindBy(id = "password")
	WebElement passwordTextbox;

	@FindBy(xpath = "//button[@id='submit']")
	WebElement submitBtn;
	
	@FindBy(linkText = "Log out")
	WebElement logoutBtn;
	
	@FindBy(id = "error")
	WebElement errorLoginMsg;

	public PracticeLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String username) {
		userNameTextbox.clear();
		userNameTextbox.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordTextbox.clear();
		passwordTextbox.sendKeys(password);
	}

	public void clickSubmitBtn() {
		submitBtn.click();
	}
	
	public boolean isLogoutBtnDisplay() {
			return logoutBtn.isDisplayed();
	}
	
	public boolean isLoginErrorMsgDisplay() {
		
		return errorLoginMsg.isDisplayed();
	}

}
