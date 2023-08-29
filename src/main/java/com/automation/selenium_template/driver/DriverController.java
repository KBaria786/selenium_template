package com.automation.selenium_template.driver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverController {
	
	private Logger logger = LoggerFactory.getLogger(DriverController.class);

	private WebDriver driver;
	
	private Duration defaultImplicitWaitDuration;
	private Duration defaultExplicitWaitDuration;
	
	/**
	 * Create a DriverController object
	 * 
	 * @param driver the web driver
	 */
	public DriverController(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	/**
	 * Get the WebDriver object
	 * 
	 * @return WebDriver the web driver object
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Get the default implicit wait duration
	 * 
	 * @return Duration the default implicit wait duration
	 */
	public Duration getDefaultImplicitWaitDuration() {
		return defaultImplicitWaitDuration;
	}

	/**
	 * Set the default implicit wait duration
	 * 
	 * @param defaultImplicitWaitDuration the default implicit wait duration
	 */
	public void setDefaultImplicitWaitDuration(Duration defaultImplicitWaitDuration) {
		this.defaultImplicitWaitDuration = defaultImplicitWaitDuration;
	}

	/**
	 * Get the default explicit wait duration
	 * 
	 * @return Duration the default explicit wait duration
	 */
	public Duration getDefaultExplicitWaitDuration() {
		return defaultExplicitWaitDuration;
	}

	/**
	 * Set the default explicit wait duration
	 * 
	 * @param defaultExplicitWaitDuration the default explicit wait duration
	 */
	public void setDefaultExplicitWaitDuration(Duration defaultExplicitWaitDuration) {
		this.defaultExplicitWaitDuration = defaultExplicitWaitDuration;
	}

	/**
	 * Click on specified web element
	 * <p>An explicit wait of default duration is performed for the element to be clickable before clicking</p>
	 * 
	 * @param webElement the web element to click on
	 * @return true if click operation is successful, otherwise false
	 */
	public boolean click(WebElement webElement) {
		boolean operationFlag = false;
		if(waitForElementToBeClickable(webElement)) {
			try {
				webElement.click();
				operationFlag = true;
				logger.info("click operation successful on element: {}", webElement);
			}catch(Exception e) {
				logger.error("click operation unsuccessful on element: {}", webElement);
				logger.error("click operation error: {}", e.getMessage());
				e.printStackTrace();
			}
		}else {
			logger.error("click operation unsuccessful on element: {}", webElement);
		}
		return operationFlag;
	}
	
	/**
	 * Click on web element located by the By locator
	 * <p>An explicit wait of default duration is performed for the element to be clickable before clicking</p>
	 * 
	 * @param byLocator the By locator for the web element
	 * @return true if click operation is successful, otherwise false
	 */
	public boolean click(By byLocator) {
		boolean operationFlag = false;
		WebElement webElement = findElement(byLocator);
		if(webElement != null) {
			operationFlag = click(webElement);
		}else {
			logger.error("click operation unsuccessful on element: {}", webElement);
		}
		return operationFlag;
	}
	
	/**
	 * Click on specified web element using Actions
	 * <p>An explicit wait of default duration is performed for the element to be clickable before clicking</p>
	 * 
	 * @param webElement the web element to click on
	 * @return true if click operation is successful, otherwise false
	 */
	public boolean clickUsingActions(WebElement webElement) {
		boolean operationFlag = false;
		Actions actions = new Actions(driver);
		if(waitForElementToBeClickable(webElement)) {
			try {
				actions.moveToElement(webElement).click().build().perform();
				operationFlag = true;
				logger.info("click operation using actions successful on element: {}", webElement);
			}catch(Exception e) {
				logger.error("click operation using actions unsuccessful on element: {}: ", webElement);
				logger.error("click operation using actions error: {}", e.getMessage());
				e.printStackTrace();
			}
		}else {
			logger.error("click operation using actions unsuccessful on element: {}", webElement);
		}
		return operationFlag;
	}
	
	/**
	 * Click on web element located by the By locator using Actions
	 * <p>An explicit wait of default duration is performed for the element to be clickable before clicking</p>
	 * 
	 * @param byLocator the By locator for the web element
	 * @return true if click operation is successful, otherwise false
	 */
	public boolean clickUsingActions(By byLocator) {
		boolean operationFlag = false;
		WebElement webElement = findElement(byLocator);
		if(webElement != null) {
			operationFlag = clickUsingActions(webElement);
		}else {
			logger.error("click operation using actions unsuccessful on element: {}", webElement);
		}
		return operationFlag;
	}
	
	/**
	 * Click on web element located by the By locator using Javascript Executor
	 * <p>An explicit wait of default duration is performed for the element to be clickable before clicking</p>
	 * 
	 * @param webElement the web element to click on
	 * @return true if click operation is successful, otherwise false
	 */
	public boolean clickUsingJSExecutor(WebElement webElement) {
		boolean operationFlag = false;
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		try {
			jsExecutor.executeScript("arguments[0].click();", webElement);
			operationFlag = true;
			logger.error("click operation using javascript executor unsuccessful on element: {}", webElement);
		}catch(Exception e) {
			logger.error("click operation using javascript executor unsuccessful on element: {}", webElement);
			logger.error("click operation using javascript executor error: {}", e.getMessage());
			e.printStackTrace();
		}
		return operationFlag;
	}
	
	/**
	 * Enter text in specified web element
	 * <p>An explicit wait of default duration is performed for the element to be visible before entering text</p>
	 * 
	 * @param webElement the web element to enter text in
	 * @return true if enter text operation is successful, otherwise false
	 */
	public boolean enterText(WebElement webElement, String text) {
		boolean operationFlag = false;
		if(waitForElementToBeVisible(webElement)) {
			webElement.sendKeys(text);
			operationFlag = true;
			logger.info("enter text operation successful on element: {}", webElement);
		}else {
			logger.error("enter text operation unsuccessful on element: {}", webElement);
		}
		return operationFlag;
	}
	
	/**
	 * Enter text in specified web element
	 * <p>An explicit wait of default duration is performed for the element to be visible before entering text</p>
	 * 
	 * @param byLocator the By locator for the web element
	 * @return true if enter text operation is successful, otherwise false
	 */
	public boolean enterText(By byLocator, String text) {
		boolean operationFlag = false;
		WebElement webElement = findElement(byLocator);
		if(webElement != null) {
			if(waitForElementToBeVisible(webElement)) {
				webElement.sendKeys(text);
				operationFlag = true;
				logger.info("enter text operation successful on element: {}", webElement);
			}
		}else {
			logger.error("enter text operation unsuccessful on element: {}", webElement);
		}
		return operationFlag;
	}
	
	/**
	 * Wait for the element to be visible and clickable on the DOM for default explicit wait duration
	 * 
	 * @param byLocator the By locator for the web element
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeClickable(By ByLocator) {
		WebElement webElement = findElement(ByLocator);
		if(webElement != null) {
			return waitForElementToBeClickable(webElement, defaultExplicitWaitDuration);
		}
		return false;
	}
	
	/**
	 * Wait for the element to be visible and clickable on the DOM for the specified explicit wait duration
	 * 
	 * @param byLocator the By locator for the web element
	 * @param explicitWaitDuration the explicit wait duration 
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeClickable(By byLocator, Duration explicitWaitDuration) {
		WebElement webElement = findElement(byLocator);
		if(webElement != null) {
			return waitForElementToBeClickable(webElement, explicitWaitDuration);
		}
		return false;
	}
	
	/**
	 * Wait for the element to be visible and clickable on the DOM for default explicit wait duration
	 * 
	 * @param webElement the web element
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeClickable(WebElement webElement) {
		return waitForElementToBeClickable(webElement, defaultExplicitWaitDuration);
	}
	
	/**
	 * Wait for the element to be visible and clickable on the DOM for the specified explicit wait duration
	 * 
	 * @param webElement the web element
	 * @param explicitWaitDuration the explicit wait duration 
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeClickable(WebElement webElement, Duration explicitWaitDuration) {
		boolean isClickable = false;
		nullifyImplicitWait();
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, explicitWaitDuration);
			webElement = wait.until(ExpectedConditions.elementToBeClickable(webElement));
			isClickable = true;
			logger.debug("element: {} is clickable", webElement);
		}catch(TimeoutException e) {
			logger.error("element: {} is not clickable", webElement);
			e.printStackTrace();
		}
		
		setDefaultImplicitWait();
		return isClickable;
	}
	
	/**
	 * Wait for the element to be visible on the DOM for default explicit wait duration
	 * 
	 * @param byLocator the By locator for the web element
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeVisible(By byLocator) {
		WebElement webElement = findElement(byLocator);
		if(webElement != null) {
			return waitForElementToBeVisible(webElement, defaultExplicitWaitDuration);
		}
		return false;
	}
	
	/**
	 * Wait for the element to be visible on the DOM for the specified explicit wait duration
	 * 
	 * @param byLocator the By locator for the web element
	 * @param explicitWaitDuration the explicit wait duration 
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeVisible(By byLocator, Duration explicitWaitDuration) {
		WebElement webElement = findElement(byLocator);
		if(webElement != null) {
			return waitForElementToBeVisible(webElement, explicitWaitDuration);
		}
		return false;
	}
	
	/**
	 * Wait for the element to be visible on the DOM for default explicit wait duration
	 * 
	 * @param webElement the web element
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeVisible(WebElement webElement) {
		return waitForElementToBeVisible(webElement, defaultExplicitWaitDuration);
	}
	
	/**
	 * Wait for the element to be visible on the DOM for the specified explicit wait duration
	 * 
	 * @param webElement the web element
	 * @param explicitWaitDuration the explicit wait duration 
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeVisible(WebElement webElement, Duration explicitWaitDuration) {
		boolean isVisible = false;
		nullifyImplicitWait();
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, explicitWaitDuration);
			webElement = wait.until(ExpectedConditions.visibilityOf(webElement));
			isVisible = true;
			logger.debug("element: {} is visible", webElement);
		}catch(TimeoutException e) {
			logger.error("element: {} is not visible", webElement);
			e.printStackTrace();
		}
		
		setDefaultImplicitWait();
		return isVisible;
	}
	
	/**
	 * Find the first web element using the given By locator
	 * 
	 * @param byLocator the By locator for the web element
	 * @return the web element once it is located
	 */
	public WebElement findElement(By byLocator) {
		WebElement webElement = null;
		try {
			webElement = driver.findElement(byLocator);
			logger.info("successfully found element: {}", webElement);
		}catch(NoSuchElementException e) {
			logger.error("error while finding element: {}", e.getMessage());
			e.printStackTrace();
		}
		return webElement;
	}
	
	/**
	 * Sets the web driver implicit wait duration to zero seconds
	 */
	public void nullifyImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ZERO);
	}
	
	/**
	 * Sets the web driver implicit wait duration to default implicit wait duration
	 */
	public void setDefaultImplicitWait() {
		driver.manage().timeouts().implicitlyWait(defaultImplicitWaitDuration);
	}
	
	/**
	 * Sets the web driver implicit wait duration to specified duration
	 * 
	 * @param duration the duration to wait
	 */
	public void setImplicitWait(Duration duration) {
		driver.manage().timeouts().implicitlyWait(duration);
	}
	
}
