package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrangeHRMLoginTest {

	private WebDriver driver;

	By usernameORG = By.name("username");
	By passwordORG = By.name("password");

	By loginBtn = By.xpath("//button[@type='submit']");

	public OrangeHRMLoginTest(WebDriver driver) {
		this.driver = driver;
		}

	public void enterUserName(String username) {

		driver.findElement(usernameORG).sendKeys(username);
		}

	public void enterPassword(String password) {

		driver.findElement(passwordORG).sendKeys(password);
		}

	public void clickLogin() {

		driver.findElement(loginBtn).click();
		}

}
