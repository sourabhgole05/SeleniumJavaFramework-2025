package pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseTest_S;

public class EmployeeReferPage extends BaseTest_S {

	private WebDriver driver;

	String url = "https://wd103.myworkday.com/wday/authgwy/accenture/login.html";

	@FindBy(xpath = "//div[contains(text(),'Accenture Employee')]")
	WebElement accentureEmployee;

	@FindBy(xpath = "//input[@type='email']")
	WebElement email;

	@FindBy(xpath = "//input[@type='password']")
	WebElement password;

	@FindBy(xpath = "//input[@id='idSIButton9']")
	WebElement nextBtn;

	@FindBy(xpath = "//div[@id='idDiv_SAOTCAS_Title']")
	WebElement approveSingRequest;

	@FindBy(xpath = "//img[@role='presentation']")
	WebElement accentureLogo;

	@FindBy(css = "[data-part='message']")
	WebElement welComeMsg;

	@FindBy(css = "[title='Global Navigation']")
	WebElement globalNavigationMenu;

	@FindBy(css = "[aria-label='Careers Marketplace']")
	WebElement careersMarketplace;

	@FindBy(css = "[aria-label='Search Jobs & Apply/Refer']")
	WebElement searchJobsForRefer;

	@FindBy(css = "[placeholder='search']")
	WebElement searchBox;

	@FindBy(xpath = "//span[@title='Search']")
	WebElement searchBtn;

	@FindBy(xpath = "//div[@aria-label='Search Results']//div[@role='link']")
	WebElement searchResult;

	@FindBy(css = "[data-automation-id='compositeHeader']")
	WebElement filteredJobID;

	@FindBy(css = "[data-automation-id='pageHeaderTitleText']")
	WebElement jobPostingDetails;

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
		waitForElementToBeVisible(welComeMsg);
		waitForElementToBeClickable(globalNavigationMenu);
		globalNavigationMenu.click();
	}

	public void goToCareerMarketplace() {
		waitForElementToBeVisible(careersMarketplace);
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

	public boolean verifyValidJOBID() {
		waitForElementToBeVisible(searchResult);
		waitForElementToBeClickable(searchResult);
		return searchResult.isDisplayed();
	}

	public void clickToJobRefer() {

		if (verifyValidJOBID() == true) {
			waitForElementToBeVisible(searchResult);
			waitForElementToBeClickable(searchResult);

			int attempts = 0;
			while (attempts < 3) {
				try {

					waitForElementToBeVisible(searchResult);
					waitForElementToBeClickable(searchResult);
					searchResult.click();
					System.out.println("✅ Clicked on Job ID successfully.");
					break;
				} catch (StaleElementReferenceException e) {
					System.out.println(
							"⚠️ StaleElementReferenceException caught, retrying... Attempt: " + (attempts + 1));
					attempts++;
				} catch (Exception e) {
					throw new RuntimeException("❌ Failed to click on filteredJobID after retries.", e);
				}
			}
		} else {
			System.out.println("❌ JOB-ID is invalid, please try with another JOB-ID.");
		}
	}

	/*
	 * public void clickToJob_Refer() { // Step 1: Check if element is present
	 * 
	 * if (isElementPresent(searchResult)) {
	 * 
	 * // Step 2: Try clicking with retry in case of stale element int attempts = 0;
	 * while (attempts < 3) { try { waitForElementToBeVisible(filteredJobID);
	 * waitForElementToBeClickable(filteredJobID); filteredJobID.click();
	 * System.out.println("✅ Clicked on Job ID successfully."); break; } catch
	 * (StaleElementReferenceException e) { System.out.println(
	 * "⚠️ StaleElementReferenceException caught, retrying... Attempt: " + (attempts
	 * + 1)); attempts++; } catch (Exception e) { throw new
	 * RuntimeException("❌ Failed to click on filteredJobID after retries.", e); } }
	 * 
	 * } else {
	 * System.out.println("❌ JOB-ID is invalid, please try with another JOB-ID.");
	 * return; }
	 * 
	 * }
	 */

	public void verifyViewJobPostingPageloaded() {
		waitForElementToBeVisible(jobPostingDetails);
		return;
	}

}
