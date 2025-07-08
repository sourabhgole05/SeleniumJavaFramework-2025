package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Log;

public class LoginPage {

	private WebDriver driver;

	By userNameTextBox = By.id("Email");
	By passwordTextBox = By.id("Password");
	By loginBtn = By.xpath("//button[@type='submit']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUserName(String username) {
		driver.findElement(userNameTextBox).clear();
		driver.findElement(userNameTextBox).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys(password);
	}

	public void clickLogin() {
		Log.info("Clicking Login button..");
		driver.findElement(loginBtn).click();
	}

}
