package com.automation.selenium_template.driver;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import com.automation.selenium_template.reports.ReportStatus;
import com.automation.selenium_template.reports.ReportingUtil;

public class DriverControllerV4 {
	
	private WebDriver webDriver;
	private Duration defaultExplicitWaitDuration;
	private static Logger logger = LoggerFactory.getLogger(DriverController.class);
	
	public DriverControllerV4(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}
	
	
	// getters and setters
	
	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public Duration getDefaultExplicitWaitDuration() {
		return defaultExplicitWaitDuration;
	}
	
	public void setDefaultExplicitWaitDuration(Duration defaultExplicitWaitDuration) {
		this.defaultExplicitWaitDuration = defaultExplicitWaitDuration;
	}
	
	
	// operations
	
	/**
	 * Load a new web page in the current browser window.
	 * @param stepDescription short step description
	 * @param url URL to load
	 * @return true if operation is successful otherwise false
	 */
	public boolean get(String stepDescription, String url) {
		if(StringUtils.isNotBlank(url)) {
			try {
				webDriver.get(url);
				//logging
				log(Level.INFO, stepDescription, "Successfully loaded url: {}", url);
				//reporting
				
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, String.format("Exception occured while loading url: %s", url), e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Blank url: {}", url);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Close the current window, quitting the browser if it's the last window currently open. 
	 * @param stepDescription short step description
	 * @return true if operation is successful otherwise false
	 */
	public boolean close(String stepDescription) {
		try {
			webDriver.close();
			//logging
			log(Level.INFO, stepDescription, "Successfully closed current window");
			//reporting
			
		}catch (Exception e) {
			//logging
			log(Level.ERROR, stepDescription, "Exception occured while closing current window", e);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Quits the driver, closing every associated window.
	 * @param stepDescription short step description
	 * @return true if operation is successful otherwise false
	 */
	public boolean quit(String stepDescription) {
		try {
			webDriver.quit();
			//logging
			log(Level.INFO, stepDescription, "Successfully quit web driver");
			//reporting
			
		}catch (Exception e) {
			//logging
			log(Level.ERROR, stepDescription, "Exception occured while quitting web driver", e);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Click on the web element found using the given 'locatorString' string which is visible and within viewport.
	 * @param stepDescription short step description
	 * @param locatorString the string to find the element with
	 * @return true if operation is successful otherwise false
	 */
	public boolean click(String stepDescription, String locatorString) {
		if(StringUtils.isNotBlank(locatorString)) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration);
			return performClickOperation(stepDescription, webElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Blank locator string: {}", locatorString);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Click on the web element found using the given 'by' locator which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @return true if operation is successful otherwise false
	 */
	public boolean click(String stepDescription, By by) {
		if(by != null) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration);
			return performClickOperation(stepDescription, webElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Click on the web element that is visible and within the view port.
	 * @param stepDescription short step description
	 * @param webElement the web element to click on
	 * @return true if operation is successful otherwise false
	 */
	public boolean click(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			webElement = waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration);
			return performClickOperation(stepDescription, webElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Click on the web element that is visible and within the view port.
	 * @param stepDescription short step description
	 * @param webElement the web element to click on
	 * @return true if operation is successful otherwise false
	 */
	private boolean performClickOperation(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			try {
				webElement.click();
				//logging
				log(Level.INFO, stepDescription, "Successfully clicked web element: {}", webElement);
				//reporting
				
				return true;
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occured while clicking web element", e);
				//reporting
				
			}
		}
		return false;
	}
	
	/**
	 * Click on the web element using {@link Actions} found using the given 'locatorString' string which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param locatorString the string to find the element with
	 * @return true if operation is successful otherwise false
	 */
	public boolean clickUsingActions(String stepDescription, String locatorString) {
		if(StringUtils.isNotBlank(locatorString)) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration);
			return performClickUsingActionsOperation(stepDescription, webElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Blank locator string: {}", locatorString);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Click on the web element using {@link Actions} found using the given 'by' locator which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @return true if operation is successful otherwise false
	 */
	public boolean clickUsingActions(String stepDescription, By by) {
		if(by != null) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration);
			return performClickUsingActionsOperation(stepDescription, webElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Click on the web element using {@link Actions} which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param webElement the web element to click on
	 * @return true if operation is successful otherwise false
	 */
	public boolean clickUsingActions(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			webElement = waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration);
			return performClickUsingActionsOperation(stepDescription, webElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Click on the web element using {@link Actions} which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param webElement the web element to click on
	 * @return true if operation is successful otherwise false
	 */
	private boolean performClickUsingActionsOperation(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			try {
				Actions actions = new Actions(webDriver);
				actions.moveToElement(webElement).click().build().perform();
				//logging
				log(Level.INFO, stepDescription, "Successfully clicked web element: {}", webElement);
				//reporting
				
				return true;
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occured while clicking web element", e);
				//reporting
				
			}
		}
		return false;
	}
	
	/**
	 * Click on the web element using {@link JavascriptExecutor} found using the given 'locatorString' string that is present on the DOM.
	 * @param stepDescription short step description
	 * @param locatorString the string to find the element with
	 * @return true if operation is successful otherwise false
	 */
	public boolean clickUsingJSExecutor(String stepDescription, String locatorString) {
		if(StringUtils.isNotBlank(locatorString)) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitDuration);
			return performClickUsingJSExecutorOperation(stepDescription, webElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Blank locator string: {}", locatorString);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Click on the first web using {@link JavascriptExecutor} element found using the given 'by' locator that is present on the DOM.
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @return true if operation is successful otherwise false
	 */
	public boolean clickUsingJSExecutor(String stepDescription, By by) {
		if(by != null) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitDuration);
			return performClickUsingJSExecutorOperation(stepDescription, webElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Click on the web element using {@link JavascriptExecutor} which is present on the DOM.
	 * @param stepDescription short step description
	 * @param webElement the web element to click on
	 * @return true if operation is successful otherwise false
	 */
	public boolean clickUsingJSExecutor(String stepDescription, WebElement webElement) {
		if(webElement != null) {
//			webElement = waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration);
			return performClickUsingJSExecutorOperation(stepDescription, webElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Click on the web element using {@link JavascriptExecutor} which is present on the DOM. 
	 * @param stepDescription short step description
	 * @param webElement the web element to click on
	 * @return true if operation is successful otherwise false
	 */
	private boolean performClickUsingJSExecutorOperation(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			try {
				JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
				javascriptExecutor.executeAsyncScript("arguments[0].click();", webElement);
				//logging
				log(Level.INFO, stepDescription, "Successfully clicked web element: {}", webElement);
				//reporting
				
				return true;
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occured while clicking web element", e);
				//reporting
				
			}
		}
		return false;
	}
	
	/**
	 * Method to simulate typing into the web element found using the given 'locatorString' string which is visible and within the viewport. This may also set the web element's value.
	 * @param stepDescription short step description
	 * @param locatorString the string to find the element with
	 * @param value character sequence to send to the web element
	 * @return true if operation is successful otherwise false
	 */
	public boolean sendKeys(String stepDescription, String locatorString, String value) {
		if(StringUtils.isNotBlank(locatorString) && StringUtils.isNotBlank(value)) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration);
			return performSendKeysOperation(stepDescription, webElement, value);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatoString: {}, value: {}", locatorString, value);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Method to simulate typing into the web element found using the given 'by' locator which is visible and within the viewport. This may also set the web element's value.
	 * @param stepDescription short step description
	 * @param by the locator to find the element with
	 * @param value character sequence to send to the web element
	 * @return true if operation is successful otherwise false
	 */
	public boolean sendKeys(String stepDescription, By by, String value) {
		if(by != null && StringUtils.isNotBlank(value)) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration);
			return performSendKeysOperation(stepDescription, webElement, value);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, value: {}", by, value);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Method to simulate typing into the web element which is visible and within the viewport. This may also set the web element's value.
	 * @param stepDescription short step description
	 * @param webElement the web element to send keys to
	 * @param value character sequence to send to the web element
	 * @return true if operation is successful otherwise false
	 */
	public boolean sendKeys(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && StringUtils.isNotBlank(value)) {
			webElement = waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration);
			return performSendKeysOperation(stepDescription, webElement, value);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, value: {}", webElement, value);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Method to simulate typing into the web element which is visible and within the viewport. This may also set the web element's value.
	 * @param stepDescription short step description
	 * @param webElement the web element to send keys to
	 * @param value character sequence to send to the web element
	 * @return true if operation is successful otherwise false
	 */
	private boolean performSendKeysOperation(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && StringUtils.isNotBlank(value)) {
			try {
				webElement.sendKeys(value);
				//logging
				log(Level.INFO, stepDescription, "Successfully sent keys: {} to web element: {}", value, webElement);
				//reporting
				
				return true;
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occured while sending keys to web element", e);
				//reporting
				
			}
		}
		return false;
	}
	
	/**
	 * Method to simulate clearing the text and then typing into the web element found using the given 'locatorString' string which is visible and within the viewport. This may also set the web element's value.
	 * @param stepDescription short step description
	 * @param locatorString the string to find the element with
	 * @param value character sequence to send to the web element
	 * @return true if operation is successful otherwise false
	 */
	public boolean clearAndSendKeys(String stepDescription, String locatorString, String value) {
		if(StringUtils.isNotBlank(locatorString) && StringUtils.isNotBlank(value)) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration);
			return performClearAndSendKeysOperation(stepDescription, webElement, value);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatoString: {}, value: {}", locatorString, value);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Method to simulate clearing the text and then typing into the web element found using the given 'by' locator which is visible and within the viewport. This may also set the web element's value.
	 * @param stepDescription short step description
	 * @param by the locator to find the element with
	 * @param value character sequence to send to the element
	 * @return true if operation is successful otherwise false
	 */
	public boolean clearAndSendKeys(String stepDescription, By by, String value) {
		if(by != null && StringUtils.isNotBlank(value)) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration);
			return performClearAndSendKeysOperation(stepDescription, webElement, value);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, value: {}", by, value);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Method to simulate clearing the text and then typing into the web element which is visible and within the viewport. This may also set the web element's value.
	 * @param stepDescription short step description
	 * @param webElement the web element to send keys to
	 * @param value character sequence to send to the element
	 * @return true if operation is successful otherwise false
	 */
	public boolean clearAndSendKeys(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && StringUtils.isNotBlank(value)) {
			webElement = waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration);
			return performClearAndSendKeysOperation(stepDescription, webElement, value);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, value: {}", webElement, value);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Method to simulate clearing the text and then typing into the web element which is visible and within the viewport. This may also set the web element's value.
	 * @param stepDescription short step description
	 * @param webElement the web element to send keys to
	 * @param value character sequence to send to the element
	 * @return true if operation is successful otherwise false
	 */
	private boolean performClearAndSendKeysOperation(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && StringUtils.isNotBlank(value)) {
			try {
				webElement.clear();
				webElement.sendKeys(value);
				//logging
				log(Level.INFO, stepDescription, "Successfully sent keys: {} to web element: {}", value, webElement);
				//reporting
				
				return true;
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occured while sending keys to web element", e);
				//reporting
				
			}
		}
		return false;
	}
	
	/**
	 * Select all options that display text matching the given 'visibleText' for the web element found using the given 'locatorString' string which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param locatorString the string to find the element with
	 * @param visibleText The visible text to match against
	 * @return true if operation is successful otherwise false
	 */
	public boolean selectByVisibleText(String stepDescription, String locatorString, String visibleText) {
		if(StringUtils.isNotBlank(locatorString) && StringUtils.isNotBlank(visibleText)) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration);
			return performSelectByVisibleTextOperation(stepDescription, webElement, visibleText);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatoString: {}, visibleText: {}", locatorString, visibleText);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Select all options that display text matching the given 'visibleText' string for the web element found using the given 'by' locator which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param by the locator to find the element with
	 * @param visibleText The visible text to match against
	 * @return true if operation is successful otherwise false
	 */
	public boolean selectByVisibleText(String stepDescription, By by, String visibleText) {
		if(by != null && StringUtils.isNotBlank(visibleText)) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration);
			return performSelectByVisibleTextOperation(stepDescription, webElement, visibleText);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, visibleText: {}", by, visibleText);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Select all options that display text matching the given 'visibleText' string for the web element which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param webElement the web element to select options for
	 * @param visibleText The visible text to match against
	 * @return true if operation is successful otherwise false
	 */
	public boolean selectByVisibleText(String stepDescription, WebElement webElement, String visibleText) {
		if(webElement != null && StringUtils.isNotBlank(visibleText)) {
			webElement = waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration);
			return performSelectByVisibleTextOperation(stepDescription, webElement, visibleText);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, visibleText: {}", webElement, visibleText);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Select all options that display text matching the given 'visibleText' string for the web element which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param webElement the web element to select options for
	 * @param visibleText The visible text to match against
	 * @return true if operation is successful otherwise false
	 */
	private boolean performSelectByVisibleTextOperation(String stepDescription, WebElement webElement, String visibleText) {
		if(webElement != null && StringUtils.isNotBlank(visibleText)) {
			try {
				Select select = new Select(webElement);
				select.selectByVisibleText(visibleText);
				//logging
				log(Level.INFO, stepDescription, "Successfully selected option: {} by visible text on web element", visibleText, webElement);
				//reporting
				
				return true;
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occured while selecting option by visible text", e);
				//reporting
				
			}
		}
		return false;
	}
	
	/**
	 * Select all options that have a value matching the given 'value' string for the web element found using the given 'locatorString' string which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param locatorString the string to find the element with
	 * @param value the value to match against
	 * @return true if operation is successful otherwise false
	 */
	public boolean selectByValue(String stepDescription, String locatorString, String value) {
		if(StringUtils.isNotBlank(locatorString) && StringUtils.isNotBlank(value)) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration);
			return performSelectByValueOperation(stepDescription, webElement, value);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatoString: {}, value: {}", locatorString, value);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Select all options that have a value matching the given 'value' string for the web element found using the given 'locatorString' string which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param by the locator to find the element with
	 * @param value the value to match against
	 * @return true if operation is successful otherwise false
	 */
	public boolean selectByValue(String stepDescription, By by, String value) {
		if(by != null && StringUtils.isNotBlank(value)) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration);
			return performSelectByValueOperation(stepDescription, webElement, value);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, value: {}", by, value);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Select all options that have a value matching the given 'value' string for the web element which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param webElement the web element to select options for
	 * @param value the value to match against
	 * @return true if operation is successful otherwise false
	 */
	public boolean selectByValue(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && StringUtils.isNotBlank(value)) {
			webElement = waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration);
			return performSelectByValueOperation(stepDescription, webElement, value);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, value: {}", webElement, value);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Select all options that have a value matching the given 'value' string for the web element which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param webElement the web element to select options for
	 * @param value the value to match against
	 * @return true if operation is successful otherwise false
	 */
	private boolean performSelectByValueOperation(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && StringUtils.isNotBlank(value)) {
			try {
				Select select = new Select(webElement);
				select.selectByValue(value);
				//logging
				log(Level.INFO, stepDescription, "Successfully selected option: {} by value on web element", value, webElement);
				//reporting
				
				return true;
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occured while selecting option by value", e);
				//reporting
				
			}
		}
		return false;
	}
	
	/**
	 * Method to drag and drop a web element which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param sourceLocatorString the string to find the source web element with
	 * @param targetLocatorString the string to find the target web element with
	 * @return true if operation is successful otherwise false
	 */
	public boolean dragAndDrop(String stepDescription, String sourceLocatorString, String targetLocatorString) {
		if(StringUtils.isNotBlank(sourceLocatorString) && StringUtils.isNotBlank(targetLocatorString)) {
			WebElement sourceWebElement = waitForVisibilityOfElement(stepDescription, sourceLocatorString, defaultExplicitWaitDuration);
			WebElement targetWebElement = waitForVisibilityOfElement(stepDescription, targetLocatorString, defaultExplicitWaitDuration);
			return performDragAndDropOperation(stepDescription, sourceWebElement, targetWebElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. sourceLocatorString: {}, targetLocatorString: {}", sourceLocatorString, targetLocatorString);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Method to drag and drop a web element which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param sourceBy the locator to find the source web element with
	 * @param targetBy the locator to find the target web element with
	 * @return true if operation is successful otherwise false
	 */
	public boolean dragAndDrop(String stepDescription, By sourceBy, By targetBy) {
		if(sourceBy != null && targetBy != null) {
			WebElement sourceWebElement = waitForVisibilityOfElement(stepDescription, sourceBy, defaultExplicitWaitDuration);
			WebElement targetWebElement = waitForVisibilityOfElement(stepDescription, targetBy, defaultExplicitWaitDuration);
			return performDragAndDropOperation(stepDescription, sourceWebElement, targetWebElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null. sourceBy: {}, targetBy: {}", sourceBy, targetBy);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Method to drag and drop a web element which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param sourceWebElement the web element to drag and drop
	 * @param targetWebElement the web element to drag and drop the given 'sourceWebElement' to
	 * @return true if operation is successful otherwise false
	 */
	public boolean dragAndDrop(String stepDescription, WebElement sourceWebElement, WebElement targetWebElement) {
		if(sourceWebElement != null && targetWebElement != null) {
			sourceWebElement = waitForVisibilityOfElement(stepDescription, sourceWebElement, defaultExplicitWaitDuration);
			targetWebElement = waitForVisibilityOfElement(stepDescription, targetWebElement, defaultExplicitWaitDuration);
			return performDragAndDropOperation(stepDescription, sourceWebElement, targetWebElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null. sourceWebElement: {}, targetWebElement: {}", sourceWebElement, targetWebElement);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Method to drag and drop a web element which is visible and within the viewport.
	 * @param stepDescription short step description
	 * @param sourceWebElement the web element to drag and drop
	 * @param targetWebElement the web element to drag and drop the given 'sourceWebElement' to
	 * @return true if operation is successful otherwise false
	 */
	private boolean performDragAndDropOperation(String stepDescription, WebElement sourceWebElement, WebElement targetWebElement) {
		if(sourceWebElement != null && targetWebElement != null) {
			try {
				Actions actions = new Actions(webDriver);
				actions.moveToElement(sourceWebElement).clickAndHold().moveToElement(targetWebElement).release().build().perform();
				//logging
				log(Level.INFO, stepDescription, "Successfully dragged and dropped web element: {}", sourceWebElement);
				//reporting
				
				return true;
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occured while dragging and dropping element", e);
				//reporting
				
			}
		}
		return false;
	}
	
	/**
	 * Get the value of the given attribute of the element which is present on the DOM.
	 * @param stepDescription short step description
	 * @param locatorString the string to find the web element with
	 * @param attribute attribute name of the web element
	 * @return The attribute/property's current value or null if the value is not set
	 */
	public String getAttribute(String stepDescription, String locatorString, String attribute) {
		if(StringUtils.isNotBlank(locatorString) && StringUtils.isNotBlank(attribute)) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitDuration);
			return performGetAttributeOperation(stepDescription, webElement, attribute);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatorString: {}, attribute: {}", locatorString, attribute);
			//reporting
			
		}
		return null;
	}
	
	/**
	 * Get the value of the given attribute of the element which is present on the DOM.
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @param attribute attribute name of the web element
	 * @return The attribute/property's current value or null if the value is not set
	 */
	public String getAttribute(String stepDescription, By by, String attribute) {
		if(by != null && StringUtils.isNotBlank(attribute)) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitDuration);
			return performGetAttributeOperation(stepDescription, webElement, attribute);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, attribute: {}", by, attribute);
			//reporting
			
		}
		return null;
	}
	
	/**
	 * Get the value of the given attribute of the element which is present on the DOM.
	 * @param stepDescription short step description
	 * @param webElement the web element to get the attribute of
	 * @param attribute attribute name of the web element
	 * @return The attribute/property's current value or null if the value is not set
	 */
	public String getAttribute(String stepDescription, WebElement webElement, String attribute) {
		if(webElement != null && StringUtils.isNotBlank(attribute)) {
			return performGetAttributeOperation(stepDescription, webElement, attribute);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, attribute: {}", webElement, attribute);
			//reporting
			
		}
		return null;
	}
	
	/**
	 * Get the value of the given attribute of the element which is present on the DOM.
	 * @param stepDescription short step description
	 * @param webElement the web element to get the attribute of
	 * @param attribute attribute name of the web element
	 * @return The attribute/property's current value or null if the value is not set
	 */
	private String performGetAttributeOperation(String stepDescription, WebElement webElement, String attribute) {
		if(webElement != null && StringUtils.isNotBlank(attribute)) {
			try {
				String value = webElement.getAttribute(attribute);
				//logging
				log(Level.INFO, stepDescription, "Successfully got attribute: {} of web element", attribute, webElement);
				//reporting
				
				return value;
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occured while getting attribute of web element", e);
				//reporting
				
			}
		}
		return null;
	}
	
	/**
	 * Return an opaque handle to this window that uniquely identifies it within this driver instance.This can be used to switch to this window at a later date 
	 * @param stepDescription short step description
	 * @return The current window handle
	 */
	public String getWindowHandle(String stepDescription) {
		try {
			String windowHandle = webDriver.getWindowHandle();
			//logging
			log(Level.INFO, stepDescription, "Successfully got window handle: {}", windowHandle);
			//reporting
			
			return windowHandle;
		}catch(Exception e) {
			//logging
			log(Level.ERROR, stepDescription, "Exception while getting window handle", e);
			//reporting
			
			return null;
		}
	}
	
	/**
	 * Return a set of window handles which can be used to iterate over all open windows.
	 * @param stepDescription short step description
	 * @return A set of window handles which can be used to iterate over all open windows
	 */
	public Set<String> getWindowHandles(String stepDescription) {
		try {
			Set<String> windowHandles = webDriver.getWindowHandles();
			//logging
			log(Level.INFO, stepDescription, "Successfully got window handles");
			//reporting
			
			return windowHandles;
		}catch(Exception e) {
			//logging
			log(Level.ERROR, stepDescription, "Exception while getting window handles", e);
			//reporting
			
			return Set.of();
		}
	}
	
	/**
	 * Get the title of the current page. 
	 * @param stepDescription short step description
	 * @return The title of the current page, with leading and trailing whitespace stripped, or null if one is not already set
	 */
	public String getTitle(String stepDescription) {
		try {
			String title = webDriver.getTitle();
			//logging
			log(Level.INFO, stepDescription, "Successfully got title: {}", title);
			//reporting
			
			return title;
		}catch(Exception e) {
			//logging
			log(Level.ERROR, stepDescription, "Exception while getting title", e);
			//reporting
			
			return null;
		}
	}
	
	/**
	 * Get a string representing the current URL that the browser is looking at.
	 * @param stepDescription short step description
	 * @return The URL of the page currently loaded in the browser
	 */
	public String getCurrentUrl(String stepDescription) {
		try {
			String url = webDriver.getCurrentUrl();
			//logging
			log(Level.INFO, stepDescription, "Successfully got current page url: {}", url);
			//reporting
			
			return url;
		}catch(Exception e) {
			//logging
			log(Level.ERROR, stepDescription, "Exception while getting current page url", e);
			//reporting
			
			return null;
		}
	}
	
	/**
     * Switch the focus of future commands for this driver to the window with the given name/handle.
     * @param stepDescription short step description
     * @param windowHandle The name or the window handle
     * @return true if the driver is focused on the new window otherwise false
     */
	public boolean switchToWindow(String stepDescription, String windowHandle) {
		if(StringUtils.isNotBlank(windowHandle)) {
			try {
				webDriver.switchTo().window(windowHandle);
				//logging
				log(Level.INFO, stepDescription, "Successfully switched to window");
				//reporting
				
				return true;
			}catch(Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception while switching to window", e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Blank window handle: {}", windowHandle);
			//reporting
			
		}
		return false;
	}
	
	/**
     * Creates a new browser window and switches the focus for future commands of this driver to the
     * new window.
     * @param stepDescription short step description
     * @param windowType The type of new browser window to be created. The created window is not
     *     guaranteed to be of the requested type; if the driver does not support the requested
     *     type, a new browser window will be created of whatever type the driver does support.
     * @return true if the driver is focused on the given window otherwise false
     */
	public boolean switchToNewWindow(String stepDescription, WindowType windowType) {
		if(windowType != null) {
			try {
				webDriver.switchTo().newWindow(windowType);
				//logging
				log(Level.INFO, stepDescription, "Successfully switched to new window");
				//reporting
				
				return true;
			}catch(Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception while switching to new window", e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null window type: {}", windowType);
			//reporting
			
		}
		return false;
	}
	
	
	// alert
	
	/**
	 * Get alert if present.
	 * @param stepDescription short step description
	 * @return true if alert is present otherwise false
	 */
	public boolean isAlertPresent(String stepDescription) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, defaultExplicitWaitDuration);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			//logging
			log(Level.INFO, stepDescription, "Successfully found alert: {}", alert);
			//reporting
			
			return alert != null;
		}catch(Exception e) {
			//logging
			log(Level.ERROR, stepDescription, "Exception while finding alert", e);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Switches to the currently active modal dialog for this particular driver instance.
	 * @param stepDescription short step description
	 * @return true if web driver successfully switches to the alert otherwise false
	 */
	public boolean switchToAlert(String stepDescription) {
		if(isAlertPresent(stepDescription)) {
			try {
				webDriver.switchTo().alert();
				//logging
				log(Level.INFO, stepDescription, "Successfully switched to alert");
				//reporting
				
				return true;
			}catch(Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception while switching to alert", e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Alert not found");
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Method to send keys to the alert.
	 * @param stepDescription short step description
	 * @param value keys to send to the alert
	 * @return true if operation was successful otherwise false
	 */
	public boolean sendKeysToAlert(String stepDescription, String value) {
		if(isAlertPresent(stepDescription)) {
			if(StringUtils.isNotBlank(value)) {
				try {
					Alert alert = webDriver.switchTo().alert();
					alert.sendKeys(value);
					//logging
					log(Level.INFO, stepDescription, "Successfully sent keys: {} to alert", value);
					//reporting
					
					return true;
				}catch(Exception e) {
					//logging
					log(Level.ERROR, stepDescription, "Exception while sending keys to alert", e);
					//reporting
					
				}
			}else {
				//logging
				log(Level.ERROR, stepDescription, "Blank value: {}", value);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Alert not present");
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Method to accept alert.
	 * @param stepDescription short step description
	 * @return true if operation was successful otherwise false
	 */
	public boolean acceptAlert(String stepDescription) {
		if(isAlertPresent(stepDescription)) {
			try {
				Alert alert = webDriver.switchTo().alert();
				alert.accept();
				//logging
				log(Level.INFO, stepDescription, "Successfully accepted alert");
				//reporting
				
				return true;
			}catch(Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception while accepting alert", e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Alert not present");
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Method to dismiss alert.
	 * @param stepDescription short step description
	 * @return true if operation was successful otherwise false
	 */
	public boolean dismissAlert(String stepDescription) {
		if(isAlertPresent(stepDescription)) {
			try {
				Alert alert = webDriver.switchTo().alert();
				alert.dismiss();
				//logging
				log(Level.INFO, stepDescription, "Successfully dismissed alert");
				//reporting
				
				return true;
			}catch(Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception while dismissing for alert", e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Alert not present");
			//reporting
			
		}
		return false;
	}
	
	
	//frame
	
	/**
	 * Select a frame by its (zero-based) index. Once the frame has been selected, all subsequent calls on the WebDriver interface are made to that frame. 
	 * @param stepDescription short step description
	 * @param index (zero-based) index
	 * @return true if driver is focused on the given frame otherwise false
	 */
	public boolean switchToIFrameByIndex(String stepDescription, int index) {
		if(index > 0) {
			try {
				webDriver.switchTo().frame(index);
				//logging
				log(Level.INFO, stepDescription, "Successfully switched to iframe with index: {}", index);
				//reporting
				
				return true;
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occured while switching to iframe", e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Invalid index: {}", index);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Select a frame by its name or ID. Frames located by matching name attributes are always given precedence over those matched by ID.
	 * @param stepDescription
	 * @param nameOrId the name of the frame window, the id of the frame or frame element, or the (zero-based) index
	 * @return true if driver is focused on the given frame otherwise false
	 */
	public boolean switchToIFrameByNameOrId(String stepDescription, String nameOrId) {
		if(StringUtils.isNotBlank(nameOrId)) {
			try {
				webDriver.switchTo().frame(nameOrId);
				//logging
				log(Level.INFO, stepDescription, "Successfully switched to iframe with name or id: {}", nameOrId);
				//reporting
				
				return true;
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occured while switching to iframe", e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Blank name of id: {}", nameOrId);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Select a frame using the locator to find the web element. 
	 * @param stepDescription short step description
	 * @param webElement The frame element to switch to
	 * @return true if the driver is focused on the given frame otherwise false
	 */
	public boolean switchToIFrame(String stepDescription, String locatorString) {
		if(StringUtils.isNotBlank(locatorString)) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitDuration);
			return performSwitchToIFrameOperation(stepDescription, webElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Blank locator string: {}", locatorString);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Select a frame using the locator to find the web element. 
	 * @param stepDescription short step description
	 * @param by The frame element locator to switch to
	 * @return true if the driver is focused on the given frame otherwise false
	 */
	public boolean switchToIFrame(String stepDescription, By by) {
		if(by != null) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitDuration);
			return performSwitchToIFrameOperation(stepDescription, webElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Select a frame using the located web element. 
	 * @param stepDescription short step description
	 * @param webElement The frame element to switch to
	 * @return true of the driver is focused on the given frame otherwise false
	 */
	public boolean switchToIFrame(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			return performSwitchToIFrameOperation(stepDescription, webElement);
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
			//reporting
			
		}
		return false;
	}
	
	/**
	 * Select a frame using the located web element. 
	 * @param stepDescription short step description
	 * @param webElement The frame element to switch to
	 * @return true if the driver is focused on the given frame otherwise false
	 */
	private boolean performSwitchToIFrameOperation(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			try {
				webDriver.switchTo().frame(webElement);
				//logging
				log(Level.INFO, stepDescription, "Successfully switched to iframe: {}", webElement);
				//reporting
				
				return true;
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occurred while switcing to iframe", e);
				//reporting
				
			}
		}
		return false;
	}
	
	/**
	 * Selects either the first frame on the page, or the main document when a page contains iframes. 
	 * @param stepDescription short step description
	 * @return true if driver is focused on the top window/first frame otherwise false
	 */
	public boolean switchToDefaultContent(String stepDescription) {
		try {
			webDriver.switchTo().defaultContent();
			//logging
			log(Level.INFO, stepDescription, "Successfully switched to default content");
			//reporting
			
			return true;
		}catch (Exception e) {
			//logging
			log(Level.ERROR, stepDescription, "Exception occurred while switching to default content", e);
			//reporting
			
		}
		return false;
	}
	
	// explicit waits
	
	// presence
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that the element is visible.
	 * @param stepDescription short step description
	 * @param locatorString the string to find the web element with
	 * @param duration the duration to wait for
	 * @return the web element once it is located
	 */
	public WebElement isElementPresent(String stepDescription, String locatorString, Duration duration) {
		return waitForPresenceOfElement(stepDescription, locatorString, duration, false);
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that the element is visible.
	 * <p>This method generates report if the element is not present on the DOM.</p>
	 * @param stepDescription short step description
	 * @param locatorString the string to find the web element with
	 * @param duration the duration to wait for
	 * @return the web element once it is located
	 */
	public WebElement waitForPresenceOfElement(String stepDescription, String locatorString, Duration duration) {
		return waitForPresenceOfElement(stepDescription, locatorString, duration, true);
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that the element is visible.
	 * <p>This method generates report if the element is not present on the DOM based on the value of 'generateReport'</p>
	 * @param stepDescription short step description
	 * @param locatorString the string to find the web element with
	 * @param duration the duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return the web element once it is located
	 */
	private WebElement waitForPresenceOfElement(String stepDescription, String locatorString, Duration duration, boolean generateReport) {
		if(StringUtils.isNotBlank(locatorString)) {
			List<By> locators = getLocators(locatorString);
			Optional<WebElement> webElementOptional = locators.stream()
					.map(locator -> waitForPresenceOfElement(stepDescription, locator, duration != null ? duration : defaultExplicitWaitDuration, false))
					.filter(webElement -> webElement != null)
					.findFirst();
			if(webElementOptional.isPresent()) {
				return webElementOptional.get();
			}else {
				//logging
				log(Level.ERROR, stepDescription, "No element present by locator string: {}", locatorString);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, null, "Blank locator string: {}", locatorString, duration);
			//reporting
			if(generateReport) {
				
			}
		}
		return null;
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that the element is visible.
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @param duration the duration to wait for
	 * @return the web element once it is located
	 */
	public WebElement isElementPresent(String stepDescription, By by, Duration duration) {
		return waitForPresenceOfElement(stepDescription, by, duration, false);
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that the element is visible.
	 * <p>This method generates report if the element is not present on the DOM.</p>
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @param duration the duration to wait for
	 * @return the web element once it is located
	 */
	public WebElement waitForPresenceOfElement(String stepDescription, By by, Duration duration) {
		return waitForPresenceOfElement(stepDescription, by, duration, true);
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page. This does not necessarily mean that the element is visible.
	 * <p>This method generates report if the element is not present on the DOM based on the value of 'generateReport'</p>
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @param duration the duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return the web element once it is located
	 */
	private WebElement waitForPresenceOfElement(String stepDescription, By by, Duration duration, boolean generateReport) {
		if(by != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.presenceOfElementLocated(by));
			}catch(Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occured while waiting for presence of element", e);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null locator: {}", by, duration);
			//reporting
			if(generateReport) {
				
			}
		}
		return null;
	}
	
	// visibility
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * @param stepDescription short step description
	 * @param locatorString the string to find the element with
	 * @param duration the duration to wait for
	 * @return the web element once it is located and visible
	 */
	public WebElement isElementVisible(String stepDescription, String locatorString, Duration duration) {
		return waitForVisibilityOfElement(stepDescription, locatorString, duration, false);
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * <p>This method generates report if the element is not visible on the DOM.</p>
	 * @param stepDescription short step description
	 * @param locatorString the string to find the element with
	 * @param duration the duration to wait for
	 * @return the web element once it is located and visible
	 */
	public WebElement waitForVisibilityOfElement(String stepDescription, String locatorString, Duration duration) {
		return waitForVisibilityOfElement(stepDescription, locatorString, duration, true);
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * <p>This method generates report if the element is not visible on the DOM based on the value of 'generateReport'</p>
	 * @param stepDescription short step description
	 * @param locatorString the string to find the element with
	 * @param duration the duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return the web element once it is located and visible
	 */
	private WebElement waitForVisibilityOfElement(String stepDescription, String locatorString, Duration duration, boolean generateReport) {
		if(StringUtils.isNotBlank(locatorString)) {
			Optional<WebElement> webElementOptional = getLocators(locatorString).stream()
					.map(locator -> waitForVisibilityOfElement(stepDescription, locator, duration != null ? duration : defaultExplicitWaitDuration, false))
					.filter(webElement -> webElement != null)
					.findFirst();
			if(webElementOptional.isPresent()) {
				return webElementOptional.get();
			}else {
				//logging
				log(Level.ERROR, stepDescription, "No visible element found by locator string: {}", locatorString);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Blank locator string: {}", locatorString);
			//reporting
			if(generateReport) {
				
			}
		}
		return null;
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * @param stepDescription short step description
	 * @param by the locator to find the element with
	 * @param duration the duration to wait for
	 * @return the web element once it is located and visible
	 */
	public WebElement isElementVisible(String stepDescription, By by, Duration duration) {
		return waitForVisibilityOfElement(stepDescription, by, duration, false);
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * <p>This method generates report if the element is not visible on the DOM.</p>
	 * @param stepDescription short step description
	 * @param by the locator to find the element with
	 * @param duration the duration to wait for
	 * @return the web element once it is located and visible
	 */
	public WebElement waitForVisibilityOfElement(String stepDescription, By by, Duration duration) {
		return waitForVisibilityOfElement(stepDescription, by, duration, true);
	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * <p>This method generates report if the element is not visible on the DOM based on the value of 'generateReport'</p>
	 * @param stepDescription short step description
	 * @param by the locator to find the element with
	 * @param duration the duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return the web element once it is located and visible
	 */
	private WebElement waitForVisibilityOfElement(String stepDescription, By by, Duration duration, boolean generateReport) {
		if(by != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			}catch(Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for visibility of element", e);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
			//reporting
			if(generateReport) {
				
			}
		}
		return null;
	}
	
	/**
	 * An expectation for checking that an element, known to be present on the DOM of a page, is visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * @param stepDescription short step description
	 * @param webElement the web element to check visibility of
	 * @param duration the duration to wait for
	 * @return the web element once it is located and visible
	 */
	public WebElement isElementVisible(String stepDescription, WebElement webElement, Duration duration) {
		return waitForVisibilityOfElement(stepDescription, webElement, duration, false);
	}
	
	/**
	 * An expectation for checking that an element, known to be present on the DOM of a page, is visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * <p>This method generates report if the element is not visible on the DOM.</p>
	 * @param stepDescription short step description
	 * @param webElement the web element to check visibility of
	 * @param duration the duration to wait for
	 * @return the web element once it is located and visible
	 */
	public WebElement waitForVisibilityOfElement(String stepDescription, WebElement webElement, Duration duration) {
		return waitForVisibilityOfElement(stepDescription, webElement, duration, true);
	}
	
	/**
	 * An expectation for checking that an element, known to be present on the DOM of a page, is visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * <p>This method generates report if the element is not visible on the DOM based on the value of 'generateReport'</p>
	 * @param stepDescription short step description
	 * @param webElement the web element to check visibility of
	 * @param duration the duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return the web element once it is located and visible
	 */
	private WebElement waitForVisibilityOfElement(String stepDescription, WebElement webElement, Duration duration, boolean generateReport) {
		if(webElement != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.visibilityOf(webElement));
			}catch(Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for invisibility of element", e);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
			//reporting
			if(generateReport) {
				
			}
		}
		return null;
	}
	
	// invisibility
	
	/**
	 * An expectation for checking that an element is either invisible or not present on the DOM.
	 * @param stepDescription short step description
	 * @param locatorString string to find the web element with
	 * @param duration duration to wait for
	 * @return true if the element is not displayed or the element doesn't exist or stale element
	 */
	public boolean isElementInvisible(String stepDescription, String locatorString, Duration duration) {
		return waitForInvisibilityOfElement(stepDescription, locatorString, duration, false);
	}
	
	/**
	 * An expectation for checking that an element is either invisible or not present on the DOM.
	 * <p>This method generates report if the element is visible on the DOM.</p>
	 * @param stepDescription short step description
	 * @param locatorString string to find the web element with
	 * @param duration duration to wait for
	 * @return true if the element is not displayed or the element doesn't exist or stale element
	 */
	public boolean waitForInvisibilityOfElement(String stepDescription, String locatorString, Duration duration) {
		return waitForInvisibilityOfElement(stepDescription, locatorString, duration, true);
	}
	
	/**
	 * An expectation for checking that an element is either invisible or not present on the DOM.
	 * <p>This method generates report if the element is visible on the DOM based on the value of 'generateReport'.</p>
	 * @param stepDescription short step description
	 * @param locatorString string to find the web element with
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true if the element is not displayed or the element doesn't exist or stale element
	 */
	private boolean waitForInvisibilityOfElement(String stepDescription, String locatorString, Duration duration, boolean generateReport) {
		if(StringUtils.isNotBlank(locatorString)) {
			Optional<By> locatorOptional = getLocators(locatorString).stream()
					.filter(locator -> waitForInvisibilityOfElement(stepDescription, locator, duration != null ? duration : defaultExplicitWaitDuration, false))
					.findFirst();
			if(locatorOptional.isEmpty()) {
				//logging
				log(Level.ERROR, stepDescription, "No invisible element found by locator string: {}", locatorString);
				//reporting
				if(generateReport) {
					
				}
			}
			return locatorOptional.isPresent();
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Blank locator string: {}", locatorString);
			//reporting
			if(generateReport) {
				
			}
		}
		return false;
	}
	
	/**
	 * An expectation for checking that an element is either invisible or not present on the DOM.
	 * @param stepDescription short step description
	 * @param by locator to find the web element with
	 * @param duration duration to wait for
	 * @return true if the element is not displayed or the element doesn't exist or stale element
	 */
	public boolean isElementInvisible(String stepDescription, By by, Duration duration) {
		return waitForInvisibilityOfElement(stepDescription, by, duration, false);
	}
	
	/**
	 * An expectation for checking that an element is either invisible or not present on the DOM.
	 * <p>This method generates report if the element is visible on the DOM.</p>
	 * @param stepDescription short step description
	 * @param by locator to find the web element with
	 * @param duration duration to wait for
	 * @return true if the element is not displayed or the element doesn't exist or stale element
	 */
	public boolean waitForInvisibilityOfElement(String stepDescription, By by, Duration duration) {
		return waitForInvisibilityOfElement(stepDescription, by, duration, true);
	}
	
	/**
	 * An expectation for checking that an element is either invisible or not present on the DOM.
	 * <p>This method generates report if the element is visible on the DOM based on the value of 'generateReport'.</p>
	 * @param stepDescription short step description
	 * @param by locator to find the web element with
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true if the element is not displayed or the element doesn't exist or stale element
	 */
	private boolean waitForInvisibilityOfElement(String stepDescription, By by, Duration duration, boolean generateReport) {
		if(by != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			}catch(Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for invisibility of element", e);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
			//reporting
			if(generateReport) {
				
			}
		}
		return false;
	}
	
	/**
	 * An expectation for checking that an element is either invisible or not present on the DOM.
	 * @param stepDescription short step description
	 * @param webElement the web element to check invisibility of
	 * @param duration duration to wait for
	 * @return true if the element is not displayed or the element doesn't exist or stale element
	 */
	public boolean isElementInvisible(String stepDescription, WebElement webElement, Duration duration) {
		return waitForInvisibilityOfElement(stepDescription, webElement, duration, false);
	}
	
	/**
	 * An expectation for checking that an element is either invisible or not present on the DOM.
	 * <p>This method generates report if the element is visible on the DOM.</p>
	 * @param stepDescription short step description
	 * @param webElement the web element to check invisibility of
	 * @param duration duration to wait for
	 * @return true if the element is not displayed or the element doesn't exist or stale element
	 */
	public boolean waitForInvisibilityOfElement(String stepDescription, WebElement webElement, Duration duration) {
		return waitForInvisibilityOfElement(stepDescription, webElement, duration, true);
	}
	
	/**
	 * An expectation for checking that an element is either invisible or not present on the DOM.
	 * <p>This method generates report if the element is visible on the DOM based on the value of 'generateReport'.</p>
	 * @param stepDescription short step description
	 * @param webElement the web element to check invisibility of
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true if the element is not displayed or the element doesn't exist or stale element
	 */
	private boolean waitForInvisibilityOfElement(String stepDescription, WebElement webElement, Duration duration, boolean generateReport) {
		if(webElement != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.invisibilityOf(webElement));
			}catch(Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for visibility of element", e);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
			//reporting
			if(generateReport) {
				
			}
		}
		return false;
	}
	
	// attribute to be
	
	/**
	 * An expectation for checking given WebElement has attribute with a specific value.
	 * @param stepDescription short step description
	 * @param locatorString the string to find the web element with
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute with the value
	 */
	public boolean isElementAttributeEqualTo(String stepDescription, String locatorString, String attribute, String value, Duration duration) {
		return waitForAttributeToBe(stepDescription, locatorString, attribute, value, duration, false);
	}
	
	/**
	 * An expectation for checking given WebElement has attribute with a specific value.
	 * <p>This method generates report if the element attribute does not equal to the given 'value'.</p>
	 * @param stepDescription short step description
	 * @param locatorString the string to find the web element with
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute with the value
	 */
	public boolean waitForAttributeToBe(String stepDescription, String locatorString, String attribute, String value, Duration duration) {
		return waitForAttributeToBe(stepDescription, locatorString, attribute, value, duration, true);
	}
	
	/**
	 * An expectation for checking given WebElement has attribute with a specific value.
	 * <p>This method generates report if the element attribute does not equal to the given 'value' based on the DOM based on the value of 'generateReport'.</p>
	 * @param stepDescription short step description
	 * @param locatorString the string to find the web element with
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute with the value
	 */
	private boolean waitForAttributeToBe(String stepDescription, String locatorString, String attribute, String value, Duration duration, boolean generateReport) {
		if(StringUtils.isNotBlank(locatorString) && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				Optional<By> locatorOptional = getLocators(locatorString).stream()
						.filter(locator -> waitForAttributeToBe(stepDescription, locator, attribute, value, duration != null ? duration : defaultExplicitWaitDuration, false))
						.findFirst();
				if(locatorOptional.isPresent()) {
					//logging
					log(Level.ERROR, stepDescription, "No element found with {} = {} by locator string: {}", attribute, value, locatorString);
				}
				locatorOptional.isPresent();
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to be {}", attribute, value), e);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatorString: {}, attribute: {}, value: {}", locatorString, attribute, value);
			//reporting
			if(generateReport) {
				
			}
		}
		return false;
	}
	
	/**
	 * An expectation for checking given WebElement has attribute with a specific value.
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute with the value
	 */
	public boolean isElementAttributeEqualTo(String stepDescription, By by, String attribute, String value, Duration duration) {
		return waitForAttributeToBe(stepDescription, by, attribute, value, duration, false);
	}
	
	/**
	 * An expectation for checking given WebElement has attribute with a specific value.
	 * <p>This method generates report if the element attribute does not equal to the given 'value'.</p>
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute with the value
	 */
	public boolean waitForAttributeToBe(String stepDescription, By by, String attribute, String value, Duration duration) {
		return waitForAttributeToBe(stepDescription, by, attribute, value, duration, true);
	}
	
	/**
	 * An expectation for checking given WebElement has attribute with a specific value.
	 * <p>This method generates report if the element attribute does not equal to the given 'value' based on the DOM based on the value of 'generateReport'.</p>
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute with the value
	 */
	private boolean waitForAttributeToBe(String stepDescription, By by, String attribute, String value, Duration duration, boolean generateReport) {
		if(by != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.attributeToBe(by, attribute, value));
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to be {}", attribute, value), e);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, attribute: {}, value: {}", by, attribute, value);
			//reporting
			if(generateReport) {
				
			}
		}
		return false;
	}
	
	/**
	 * An expectation for checking given WebElement has attribute with a specific value.
	 * @param stepDescription short step description
	 * @param webElement the web element to check attribute of
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute with the value
	 */
	public boolean isElementAttributeEqualTo(String stepDescription, WebElement webElement, String attribute, String value, Duration duration) {
		return waitForAttributeToBe(stepDescription, webElement, attribute, value, duration, false);
	}
	
	/**
	 * An expectation for checking given WebElement has attribute with a specific value.
	 * <p>This method generates report if the element attribute does not equal to the given 'value'.</p>
	 * @param stepDescription short step description
	 * @param webElement the web element to check attribute of
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute with the value
	 */
	public boolean waitForAttributeToBe(String stepDescription, WebElement webElement, String attribute, String value, Duration duration) {
		return waitForAttributeToBe(stepDescription, webElement, attribute, value, duration, true);
	}

	/**
	 * An expectation for checking given WebElement has attribute with a specific value.
	 * <p>This method generates report if the element attribute does not equal to the given 'value' based on the DOM based on the value of 'generateReport'.</p>
	 * @param stepDescription short step description
	 * @param webElement the web element to check attribute of
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute with the value
	 */
	private boolean waitForAttributeToBe(String stepDescription, WebElement webElement, String attribute, String value, Duration duration, boolean generateReport) {
		if(webElement != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.attributeToBe(webElement, attribute, value));
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to be {}", attribute, value), e);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, attribute: {}, value: {}", webElement, attribute, value);
			//reporting
			if(generateReport) {
				
			}
		}
		return false;
	}
	
	// attribute to contain
	
	/**
	 * An expectation for checking WebElement with given locator has attribute with a specific value.
	 * @param stepDescription short step description
	 * @param locatorString the string to find the web element with
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute which contains the value
	 */
	public boolean doesElementAttributeContain(String stepDescription, String locatorString, String attribute, String value, Duration duration) {
		return waitForAttributeToContain(stepDescription, locatorString, attribute, value, duration, false);
	}
	
	/**
	 * An expectation for checking WebElement with given locator has attribute with a specific value.
	 * <p>This method generates report if the element attribute does not contain the given 'value'.</p>
	 * @param stepDescription short step description
	 * @param locatorString the string to find the web element with
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute which contains the value
	 */
	public boolean waitForAttributeToContain(String stepDescription, String locatorString, String attribute, String value, Duration duration) {
		return waitForAttributeToContain(stepDescription, locatorString, attribute, value, duration, true);
	}
	
	/**
	 * An expectation for checking WebElement with given locator has attribute with a specific value.
	 * <p>This method generates report if the element attribute does not contain the given 'value' based on the DOM based on the value of 'generateReport'.</p>
	 * @param stepDescription short step description
	 * @param locatorString the string to find the web element with
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute which contains the value
	 */
	private boolean waitForAttributeToContain(String stepDescription, String locatorString, String attribute, String value, Duration duration, boolean generateReport) {
		if(StringUtils.isNotBlank(locatorString) && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				Optional<By> locatorOptional = getLocators(locatorString).stream()
						.filter(locator -> waitForAttributeToContain(stepDescription, locator, attribute, value, duration != null ? duration : defaultExplicitWaitDuration, false))
						.findFirst();
				if(locatorOptional.isPresent()) {
					//logging
					log(Level.ERROR, stepDescription, "No element found with {} = {} by locator string: {}", attribute, value, locatorString);
				}
				locatorOptional.isPresent();
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to be {}", attribute, value), e);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatorString: {}, attribute: {}, value: {}", locatorString, attribute, value);
			//reporting
			if(generateReport) {
				
			}
		}
		return false;
	}
	
	/**
	 * An expectation for checking WebElement with given locator has attribute with a specific value.
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute which contains the value
	 */
	public boolean doesElementAttributeContain(String stepDescription, By by, String attribute, String value, Duration duration) {
		return waitForAttributeToContain(stepDescription, by, attribute, value, duration, false);
	}
	
	/**
	 * An expectation for checking WebElement with given locator has attribute with a specific value.
	 * <p>This method generates report if the element attribute does not contain the given 'value'.</p>
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute which contains the value
	 */
	public boolean waitForAttributeToContain(String stepDescription, By by, String attribute, String value, Duration duration) {
		return waitForAttributeToContain(stepDescription, by, attribute, value, duration, true);
	}
	
	/**
	 * An expectation for checking WebElement with given locator has attribute with a specific value.
	 * <p>This method generates report if the element attribute does not contain the given 'value' based on the DOM based on the value of 'generateReport'.</p>
	 * @param stepDescription short step description
	 * @param by the locator to find the web element with
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute which contains the value
	 */
	private boolean waitForAttributeToContain(String stepDescription, By by, String attribute, String value, Duration duration, boolean generateReport) {
		if(by != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.attributeToBe(by, attribute, value));
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to be {}", attribute, value), e);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, attribute: {}, value: {}", by, attribute, value);
			//reporting
			if(generateReport) {
				
			}
		}
		return false;
	}
	
	/**
	 * An expectation for checking WebElement with given locator has attribute with a specific value.
	 * @param stepDescription short step description
	 * @param webElement the web element to check attribute of
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute which contains the value
	 */
	public boolean doesElementAttributeContain(String stepDescription, WebElement webElement, String attribute, String value, Duration duration) {
		return waitForAttributeToContain(stepDescription, webElement, attribute, value, duration, false);
	}
	
	/**
	 * An expectation for checking WebElement with given locator has attribute with a specific value.
	 * <p>This method generates report if the element attribute does not contain the given 'value'.</p>
	 * @param stepDescription short step description
	 * @param webElement the web element to check attribute of
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute which contains the value
	 */
	public boolean waitForAttributeToContain(String stepDescription, WebElement webElement, String attribute, String value, Duration duration) {
		return waitForAttributeToContain(stepDescription, webElement, attribute, value, duration, true);
	}
	
	/**
	 * An expectation for checking WebElement with given locator has attribute with a specific value.
	 * <p>This method generates report if the element attribute does not contain the given 'value' based on the DOM based on the value of 'generateReport'.</p>
	 * @param stepDescription short step description
	 * @param webElement the web element to check attribute of
	 * @param attribute attribute name of the web element
	 * @param value value to be present
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return true when element has css or html attribute which contains the value
	 */
	private boolean waitForAttributeToContain(String stepDescription, WebElement webElement, String attribute, String value, Duration duration, boolean generateReport) {
		if(webElement != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.attributeContains(webElement, attribute, value));
			}catch (Exception e) {
				//logging
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to contain {}", attribute, value), e);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, attribute: {}, value: {}", webElement, attribute, value);
			//reporting
			if(generateReport) {
				
			}
		}
		return false;
	}
	
	// custom condition
	
	/**
	 * Method to apply an explicit wait on a custom expected condition.
	 * @param <T> return type of the given 'expectedCondition'
	 * @param stepDescription short step description
	 * @param expectedCondition custom condition to perform explicit wait upon
	 * @param duration duration to wait for
	 * @param generateReport boolean value to determine if reports will be generated or not
	 * @return return value of the given 'expectedCondition'
	 */
	public <T> T waitForCustomCondition(String stepDescription, ExpectedCondition<T> expectedCondition, Duration duration, boolean generateReport) {
		if(expectedCondition != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(expectedCondition);
			}catch(Exception e) {
				//logging
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for custom expected condition", e);
				//reporting
				if(generateReport) {
					
				}
			}
		}else {
			//logging
			log(Level.ERROR, stepDescription, "Null custom expected condition: {}", expectedCondition);
			//reporting
			if(generateReport) {
				
			}
		}
		return null;
	}
	
	
	// find element
	
	/**
	 * Find the first WebElement using the given method.
	 * @param locatorString the string to find the web element with
	 * @return The first matching element on the current page
	 */
	public WebElement findElement(String locatorString) {
		if(StringUtils.isNotBlank(locatorString)) {
			List<By> locators = getLocators(locatorString);
			Optional<WebElement> webElementOptional = locators.stream()
					.map(locator -> tryFindingElement(locator))
					.filter(webElement -> webElement != null)
					.findFirst();
			if(webElementOptional.isPresent()) {
				return webElementOptional.get();
			}else {
				//logging
				log(Level.ERROR, null, "No web element found with locator string: {}", locatorString);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, null, "Blank locator string: {}", locatorString);
			//reporting
			
		}
		return null;
	}
	
	/**
	 * Find the first WebElement using the given method.
	 * @param by the locator to find the web element with
	 * @return The first matching element on the current page
	 */
	private WebElement tryFindingElement(By by) {
		if(by != null) {
			try {
				return webDriver.findElement(by);
			}catch(Exception e) {
				//logging
				log(Level.ERROR, null, "Exception occurred while finding web element", e);
			}
		}else {
			//logging
			log(Level.ERROR, null, "Null locator: {}", by);
		}
		return null;
	}
	
	/**
	 * Find the first WebElement using the given method.
	 * @param by the locator to find the web element with
	 * @return The first matching element on the current page
	 */
	public WebElement findElement(By by) {
		if(by != null) {
			try {
				return webDriver.findElement(by);
			}catch(Exception e) {
				//logging
				log(Level.ERROR, null, "Exception occurred while finding web element", e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, null, "Null locator: {}", by);
			//reporting
			
		}
		return null;
	}
	
	/**
	 * Find all elements within the current page using the given 'locatorString' locator.
	 * @param locatorString the string to find the web element with
	 * @return A list of all matching WebElements, or an empty list if nothing matches
	 */
	public List<WebElement> findElements(String locatorString) {
		if(StringUtils.isNotBlank(locatorString)) {
			List<By> locators = getLocators(locatorString);
			Optional<List<WebElement>> webElementListOptional = locators.stream()
					.map(locator -> tryFindingWebElements(locator))
					.filter(webElements -> !webElements.isEmpty())
					.findFirst();
			if(webElementListOptional.isPresent()) {
				return webElementListOptional.get();
			}else {
				//logging
				log(Level.ERROR, null, "No web elements found with locator string: {}", locatorString);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, null, "Blank locator string: {}", locatorString);
			//reporting
			
		}
		return List.of();
	}
	
	/**
	 * Find all elements within the current page using the given 'by' locator.
	 * @param by the locator to find the web element with
	 * @return A list of all matching WebElements, or an empty list if nothing matches
	 */
	private List<WebElement> tryFindingWebElements(By by) {
		if(by != null) {
			try {
				return webDriver.findElements(by);
			}catch(Exception e) {
				//logging
				log(Level.ERROR, null, "Exception occurred while finding web elements with locator", e);
			}
		}else {
			//logging
			log(Level.ERROR, null, "Null locator {}", by);
		}
		return List.of();
	}
	
	/**
	 * Find all elements within the current page using the given 'by' locator.
	 * @param by the locator to find the web element with
	 * @return A list of all matching WebElements, or an empty list if nothing matches
	 */
	public List<WebElement> findElements(By by) {
		if(by != null) {
			try {
				return webDriver.findElements(by);
			}catch(Exception e) {
				//logging
				log(Level.ERROR, null, "Exception occurred while finding web elements with locator", e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, null, "Null locator {}", by);
			//reporting
			
		}
		return List.of();
	}
	
	/**
	 * Find the first child web element of the given 'parentWebElement' web element using the given 'locatorString' string.
	 * @param parentWebElement parent web element from which to locate the child web element
	 * @param locatorString string to find the child web element with
	 * @return The first matching element on the current page
	 */
	public WebElement findChildWebElement(WebElement parentWebElement, String locatorString) {
		if(parentWebElement != null && StringUtils.isNotBlank(locatorString)) {
			List<By> locators = getLocators(locatorString);
			Optional<WebElement> webElementOptional = locators.stream()
					.map(locator -> tryFindingChildWebElement(parentWebElement, locator))
					.filter(webElement -> webElement != null)
					.findFirst();
			if(webElementOptional.isPresent()) {
				return webElementOptional.get();
			}else {
				//logging
				log(Level.ERROR, null, "No child web element found with locator string: {}", locatorString);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, null, "One or more of the required fields is null or blank. parentWebElement: {}, locatorString: {}", parentWebElement, locatorString);
			//reporting
			
		}
		return null;
	}
	
	/**
	 * Find the first child web element of the given 'parentWebElement' web element using the given 'by' locator
	 * @param parentWebElement parent web element from which to locate the child web element
	 * @param by locator to find the child web element with
	 * @return The first matching element on the current page
	 */
	private WebElement tryFindingChildWebElement(WebElement parentWebElement, By by) {
		if(parentWebElement != null && by != null) {
			try {
				return parentWebElement.findElement(by);
			}catch(Exception e) {
				//logging
				log(Level.ERROR, null, "Exception occurred while finding child web elements", e);
			}
		}else {
			//logging
			log(Level.ERROR, null, "One or more of the required fields is null. parentWebElement: {}, by: {}", parentWebElement, by);
		}
		return null;
	}
	
	/**
	 * Find the first child web element of the given 'parentWebElement' web element using the given 'by' locator
	 * @param parentWebElement parent web element from which to locate the child web element
	 * @param by locator to find the child web element with
	 * @return The first matching element on the current page
	 */
	public WebElement findChildWebElement(WebElement parentWebElement, By by) {
		if(parentWebElement != null && by != null) {
			try {
				return parentWebElement.findElement(by);
			}catch(Exception e) {
				//logging
				log(Level.ERROR, null, "Exception occurred while finding child web elements", e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, null, "One or more of the required fields is null. parentWebElement: {}, by: {}", parentWebElement, by);
			//reporting
			
		}
		return null;
	}
	
	/**
	 * Find list of child web elements of the given 'parentWebElement' web element using the given 'by' locator
	 * @param parentWebElement parent web element from which to locate the child web element
	 * @param by locator to find the child web element with
	 * @return The first matching element on the current page
	 */
	public List<WebElement> findChildWebElements(WebElement parentWebElement, String locatorString) {
		if(parentWebElement != null && StringUtils.isNotBlank(locatorString)) {
			List<By> locators = getLocators(locatorString);
			Optional<List<WebElement>> webElementListOptional = locators.stream()
					.map(locator -> tryFindingChildWebElements(parentWebElement, locator))
					.filter(webElements -> !webElements.isEmpty())
					.findFirst();
			if(webElementListOptional.isPresent()) {
				return webElementListOptional.get();
			}else {
				//logging
				log(Level.ERROR, null, "No child web elements found with locator string: {}", locatorString);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, null, "One or more of the required fields is null or blank. parentWebElement: {}, locatorString: {}", parentWebElement, locatorString);
			//reporting
			
		}
		return List.of();
	}
	
	/**
	 * Find list of child web elements of the given 'parentWebElement' web element using the given 'by' locator
	 * @param parentWebElement parent web element from which to locate the child web element
	 * @param by locator to find the child web element with
	 * @return The first matching element on the current page
	 */
	private List<WebElement> tryFindingChildWebElements(WebElement parentWebElement, By by) {
		if(parentWebElement != null && by != null) {
			try {
				return parentWebElement.findElements(by);
			}catch(Exception e) {
				//logging
				log(Level.ERROR, null, "Exception occurred while finding child web elements", e);
				
			}
		}else {
			//logging
			log(Level.ERROR, null, "One or more of the required fields is null. parentWebElement: {}, by: {}", parentWebElement, by);
		}
		return List.of();
	}
	
	/**
	 * Find list of child web elements of the given 'parentWebElement' web element using the given 'by' locator
	 * @param parentWebElement parent web element from which to locate the child web element
	 * @param by locator to find the child web element with
	 * @return The first matching element on the current page
	 */
	public List<WebElement> findChildWebElements(WebElement parentWebElement, By by) {
		if(parentWebElement != null && by != null) {
			try {
				return parentWebElement.findElements(by);
			}catch(Exception e) {
				//logging
				log(Level.ERROR, null, "Exception occurred while finding child web elements", e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, null, "One or more of the required fields is null. parentWebElement: {}, by: {}", parentWebElement, by);
			//reporting
			
		}
		return List.of();
	}
	
	
	// locator pairs
	
	/**
	 * Get list of locators from the given 'locatorString' string where each locator key and value pair is separated by ';'.
	 * <p>Example : {@code xpath~//div[@class='some-class'];xpath~//div[@id='some-id']}</p>
	 * @param locatorString string containing locator key and value pairs
	 * @return list of locators extracted from the given 'locatorString' string
	 */
	private List<By> getLocators(String locatorString) {
		if(StringUtils.isNotBlank(locatorString)) {
			List<String> locatorPairStrings = Arrays.asList(locatorString.split(";"));
			List<List<String>> locatorPairs = locatorPairStrings.stream()
					.map(locatorPairString -> extractLocatorPair(locatorPairString))
					.filter(locatorPairList -> locatorPairList.size() > 0)
					.toList();
			List<By> locators = locatorPairs.stream()
					.map(locatorPair -> getLocator(locatorPair.get(0), locatorPair.get(1)))
					.filter(locator -> locator != null)
					.toList();
			if(locators.isEmpty()) {
				//logging
				log(Level.ERROR, null, "No locators found with locator string: {}", locatorString);
				//reporting
				
			}
			return locators;
		}else {
			//logging
			log(Level.ERROR, null, "Blank locator string: {}", locatorString);
			//reporting
			
		}
		return List.of();
	}
	
	/**
	 * Method to convert the locator key and value into a {@link By} locator
	 * @param locatorKey locator key string
	 * @param locatorValue locator value string
	 * @return valid locator based on the locator key otherwise null
	 */
	private By getLocator(String locatorKey, String locatorValue) {
		if(StringUtils.isNotBlank(locatorKey) && StringUtils.isNotBlank(locatorValue)) {
			By byLocator = null;
			switch (locatorKey) {
			case "xpath":
				byLocator = By.xpath(locatorValue);
				break;
			case "className":
				byLocator = By.className(locatorValue);
				break;
			case "name":
				byLocator = By.name(locatorValue);
				break;
			case "id":
				byLocator = By.id(locatorValue);
				break;
			case "css":
				byLocator = By.cssSelector(locatorValue);
				break;
			case "tagName":
				byLocator = By.tagName(locatorValue);
				break;
			case "linkText":
				byLocator = By.linkText(locatorValue);
				break;
			case "partialLinkText":
				byLocator = By.linkText(locatorValue);
				break;
			default:
				//logging
				log(Level.ERROR, null, "Invalid locator key: {}", locatorKey);
				//reporting
				
				break;
			}
			return byLocator;
		}else {
			//logging
			log(Level.ERROR, null, "One or more required fields is blank. locatorKey: {}, locatorValue: {}", locatorKey, locatorValue);
			//reporting
			
			return null;
		}
	}
	
	/**
	 * Method to extract the locator key and value separated by '~'.
	 * <p>Example : {@code xpath~//div[@class='some-class']}</p>
	 * @param locatorPairString string containing the locator key and value separated by '~'
	 * @return list of string containing the locator key and value
	 */
	private List<String> extractLocatorPair(String locatorPairString) {
		if(StringUtils.isNotBlank(locatorPairString)) {
			try {
				return List.of(locatorPairString.split("~")[0], locatorPairString.split("~")[1]);
			}catch(Exception e) {
				//logging
				log(Level.ERROR, null, "Exception occurred while extracting locator pair", e);
				//reporting
				
			}
		}else {
			//logging
			log(Level.ERROR, null, "Blank locator pair string: {}", locatorPairString);
			//reporting
			
		}
		return List.of();
	}
	
	
	// logging
	
	private void log(Level level, String stepDescription, String message, Object... arguments) {
		message = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, message) : message;
		logger.atLevel(level).log(message, arguments);
	}
	
	
	// reporting
	
	private void reportInfo(ReportStatus reportStatus, String stepDescription, String message, File screenshotFile) {
		message = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, message) : message;
		if(screenshotFile != null) {
			ReportingUtil.logInfo(message, screenshotFile);
		}else {
			ReportingUtil.logInfo(message);
		}
	}
	
	private void reportException(ReportStatus reportStatus, String stepDescription, String message, Exception e, File screenshotFile) {
		message = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, message) : message;
		if(screenshotFile != null) {
			ReportingUtil.logException(message, e, screenshotFile);
		}else {
			ReportingUtil.logException(message, e);
		}
	}
	
}
