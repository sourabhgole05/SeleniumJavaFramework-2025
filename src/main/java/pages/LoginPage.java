package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Log;

public class LoginPage {

	private WebDriver driver;

	@FindBy(id = "Email")
	WebElement userNameTextbox;

	@FindBy(id = "Password")
	WebElement passwordTextbox;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginButton;

	// private By userNameTextBox = By.id("Email");
	// private By passwordTextBox = By.id("Password");
	// private By loginBtn = By.xpath("//button[@type='submit']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUserName(String username) {

		userNameTextbox.clear();
		userNameTextbox.sendKeys(username);

		// driver.findElement(userNameTextBox).clear();
		// driver.findElement(userNameTextBox).sendKeys(username);
	}

	public void enterPassword(String password) {

		passwordTextbox.clear();
		passwordTextbox.sendKeys(password);

		// driver.findElement(passwordTextBox).clear();
		// driver.findElement(passwordTextBox).sendKeys(password);
	}

	public void clickLogin() {
		Log.info("Clicking Login button..");

		loginButton.click();
		// driver.findElement(loginBtn).click();
	}

}
