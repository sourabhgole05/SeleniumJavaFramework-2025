package base;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigManager;
import utils.ExtentReportManager;

public class BaseTest_S {
    protected WebDriver driver;
    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    protected ExtentTest test;
    protected static ExtentReports extent;
    protected static ConfigManager config = ConfigManager.getInstance();

    // Timeout configurations
    private static final Duration IMPLICIT_WAIT = Duration.ofSeconds(10);
    private static final Duration PAGE_LOAD_TIMEOUT = Duration.ofSeconds(30);
    private static final Duration EXPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(30);
    private static final Duration EXPLICIT_POLLING_INTERVAL = Duration.ofMillis(500);
    
    
    @BeforeSuite
    public void setupSuite() {
        extent = ExtentReportManager.createInstance();
    }
    
    @AfterSuite
    public void teardownReport() {
        if (extent != null) {
            extent.flush();
        }
    }
 
    // Default browser if not specified
    private static final String DEFAULT_BROWSER = "chrome";
    
    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {  // Add @Optional with default value
        initializeDriver(browser);
        configureDriver();
        setImplicitWait();
        threadLocalDriver.set(driver);
        
        // Navigate to URL from properties file
        String url = config.getProperty("base.url");
        if (url != null && !url.isEmpty()) {
            driver.get(url);
        } else {
            // Fallback URL if not specified in properties
            driver.get("https://practicetestautomation.com");
        }
    }
    
    private void initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-notifications");
                options.addArguments("--incognito");
                
             // 🔒 Disable password manager & automation banners
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false); // disable Chrome Credential Service
                prefs.put("profile.password_manager_enabled", false); // disable password manager
                prefs.put("profile.default_content_setting_values.notifications", 2); // block notifications

                options.setExperimentalOption("prefs", prefs);
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); // hide "Chrome is being controlled..."
                options.setExperimentalOption("useAutomationExtension", false); // turn off automation extension

                
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    private void configureDriver() {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT);
        driver.manage().timeouts().scriptTimeout(PAGE_LOAD_TIMEOUT);
    }

    private void setImplicitWait() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT);
    }

    // Explicit Wait Methods
    public WebDriverWait getWait() {
        return new WebDriverWait(driver, EXPLICIT_WAIT_TIMEOUT, EXPLICIT_POLLING_INTERVAL);
    }

    public FluentWait<WebDriver> getFluentWait() {
        return new FluentWait<>(driver)
            .withTimeout(EXPLICIT_WAIT_TIMEOUT)
            .pollingEvery(EXPLICIT_POLLING_INTERVAL)
            .ignoring(Exception.class);
    }

    // Common explicit wait conditions
    public void waitForElementToBeClickable(WebElement locator) {
        getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToBeVisible(WebElement locator) {
        getWait().until(ExpectedConditions.visibilityOf(locator));
    }

    public void waitForUrlToContain(String urlFraction) {
        getWait().until(ExpectedConditions.urlContains(urlFraction));
    }

    public boolean isElementPresent(WebElement locator) {
        try {
            getWait().until(ExpectedConditions.visibilityOf(locator));
            return true;
        } catch ( NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }
    
   


    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                // Use the Base64 screenshot method
                ExtentReportManager.attachScreenshotOnFailure(driver, test, result.getThrowable());
                test.log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.log(Status.PASS, "Test Passed");
            } else {
                test.log(Status.SKIP, "Test Skipped: " + result.getThrowable().getMessage());
            }
        } catch (Exception e) {
            System.out.println("Exception in tearDown: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
                threadLocalDriver.remove();
            }
        }
    }
    
    /**
     * Takes a screenshot and attaches it to the report with a custom message
     * @param message The message to display with the screenshot
     */
    protected void captureScreenshot(String message) {
        ExtentReportManager.attachScreenshotToReport(driver, test, message);
    }
    
    
    
    public WebDriver getDriver() {
        return threadLocalDriver.get() != null ? threadLocalDriver.get() : driver;
    }
}