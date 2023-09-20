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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class DriverControllerV3 {
	
	private WebDriver webDriver;
	private Duration defaultExplicitWaitDuration;
	private static Logger logger = LoggerFactory.getLogger(DriverController.class);
	
	public DriverControllerV3(WebDriver webDriver) {
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
				log(Level.INFO, stepDescription, "Successfully loaded url: {}", url);
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occured while loading url", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Blank url: {}", url);
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
			log(Level.INFO, stepDescription, "Successfully closed current window");
		}catch (Exception e) {
			log(Level.ERROR, stepDescription, "Exception occured while closing current window", e);
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
			log(Level.INFO, stepDescription, "Successfully quit web driver");
		}catch (Exception e) {
			log(Level.ERROR, stepDescription, "Exception occured while quitting web driver", e);
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
			log(Level.ERROR, stepDescription, "Blank locator string: {}", locatorString);
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
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
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
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
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
				log(Level.INFO, stepDescription, "Successfully clicked web element: {}", webElement);
				return true;
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occured while clicking web element", e);
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
			log(Level.ERROR, stepDescription, "Blank locator string: {}", locatorString);
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
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
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
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
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
				log(Level.INFO, stepDescription, "Successfully clicked web element: {}", webElement);
				return true;
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occured while clicking web element", e);
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
			log(Level.ERROR, stepDescription, "Blank locator string: {}", locatorString);
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
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
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
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
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
				log(Level.INFO, stepDescription, "Successfully clicked web element: {}", webElement);
				return true;
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occured while clicking web element", e);
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
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatoString: {}, value: {}", locatorString, value);
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
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, value: {}", by, value);
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
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, value: {}", webElement, value);
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
				log(Level.INFO, stepDescription, "Successfully sent keys: {} to web element: {}", value, webElement);
				return true;
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occured while sending keys to web element", e);
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
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatoString: {}, value: {}", locatorString, value);
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
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, value: {}", by, value);
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
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, value: {}", webElement, value);
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
				log(Level.INFO, stepDescription, "Successfully sent keys: {} to web element: {}", value, webElement);
				return true;
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occured while sending keys to web element", e);
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
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatoString: {}, visibleText: {}", locatorString, visibleText);
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
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, visibleText: {}", by, visibleText);
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
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, visibleText: {}", webElement, visibleText);
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
				log(Level.INFO, stepDescription, "Successfully selected option: {} by visible text on web element", visibleText, webElement);
				return true;
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occured while selecting option by visible text", e);
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
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatoString: {}, value: {}", locatorString, value);
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
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, value: {}", by, value);
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
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, value: {}", webElement, value);
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
				log(Level.INFO, stepDescription, "Successfully selected option: {} by value on web element", value, webElement);
				return true;
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occured while selecting option by value", e);
			}
		}
		return false;
	}
	
	//
	public boolean dragAndDrop(String stepDescription, String sourceLocatorString, String targetLocatorString, String value) {
		if(StringUtils.isNotBlank(sourceLocatorString) && StringUtils.isNotBlank(targetLocatorString)) {
			WebElement sourceWebElement = waitForVisibilityOfElement(stepDescription, sourceLocatorString, defaultExplicitWaitDuration);
			WebElement targetWebElement = waitForVisibilityOfElement(stepDescription, targetLocatorString, defaultExplicitWaitDuration);
			return performDragAndDropOperation(stepDescription, sourceWebElement, targetWebElement);
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. sourceLocatorString: {}, targetLocatorString: {}", sourceLocatorString, targetLocatorString);
		}
		return false;
	}
	
	public boolean dragAndDrop(String stepDescription, By sourceBy, By targetBy, String value) {
		if(sourceBy != null && targetBy != null) {
			WebElement sourceWebElement = waitForVisibilityOfElement(stepDescription, sourceBy, defaultExplicitWaitDuration);
			WebElement targetWebElement = waitForVisibilityOfElement(stepDescription, targetBy, defaultExplicitWaitDuration);
			return performDragAndDropOperation(stepDescription, sourceWebElement, targetWebElement);
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is null. sourceBy: {}, targetBy: {}", sourceBy, targetBy);
		}
		return false;
	}
	
	public boolean dragAndDrop(String stepDescription, WebElement sourceWebElement, WebElement targetWebElement, String value) {
		if(sourceWebElement != null && targetWebElement != null) {
			sourceWebElement = waitForVisibilityOfElement(stepDescription, sourceWebElement, defaultExplicitWaitDuration);
			targetWebElement = waitForVisibilityOfElement(stepDescription, targetWebElement, defaultExplicitWaitDuration);
			return performDragAndDropOperation(stepDescription, sourceWebElement, targetWebElement);
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is null. sourceWebElement: {}, targetWebElement: {}", sourceWebElement, targetWebElement);
		}
		return false;
	}
	
	private boolean performDragAndDropOperation(String stepDescription, WebElement sourceWebElement, WebElement targetWebElement) {
		if(sourceWebElement != null && targetWebElement != null) {
			try {
				Actions actions = new Actions(webDriver);
				actions.moveToElement(sourceWebElement).clickAndHold().moveToElement(targetWebElement).release().build().perform();
				log(Level.INFO, stepDescription, "Successfully dragged and dropped web element: {}", sourceWebElement);
				return true;
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occured while dragging and dropping element", e);
			}
		}
		return false;
	}
	
	//
	public String getAttribute(String stepDescription, String locatorString, String attribute) {
		if(StringUtils.isNotBlank(locatorString) && StringUtils.isNotBlank(attribute)) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitDuration);
			return performGetAttributeOperation(stepDescription, webElement, attribute);
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatorString: {}, attribute: {}", locatorString, attribute);
		}
		return null;
	}
	
	public String getAttribute(String stepDescription, By by, String attribute) {
		if(by != null && StringUtils.isNotBlank(attribute)) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitDuration);
			return performGetAttributeOperation(stepDescription, webElement, attribute);
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, attribute: {}", by, attribute);
		}
		return null;
	}
	
	public String getAttribute(String stepDescription, WebElement webElement, String attribute) {
		if(webElement != null && StringUtils.isNotBlank(attribute)) {
			return performGetAttributeOperation(stepDescription, webElement, attribute);
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, attribute: {}", webElement, attribute);
		}
		return null;
	}
	
	private String performGetAttributeOperation(String stepDescription, WebElement webElement, String attribute) {
		if(webElement != null && StringUtils.isNotBlank(attribute)) {
			try {
				String value = webElement.getAttribute(attribute);
				log(Level.INFO, stepDescription, "Successfully got attribute: {} of web element", attribute, webElement);
				return value;
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occured while getting attribute of web element", e);
			}
		}
		return null;
	}
	
	//
	public String getWindowHandle(String stepDescription) {
		try {
			String windowHandle = webDriver.getWindowHandle();
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfully got window handle: {}", windowHandle);
			return windowHandle;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while getting window handle", e);
			return null;
		}
	}
	
	//
	public Set<String> getWindowHandles(String stepDescription) {
		try {
			Set<String> windowHandles = webDriver.getWindowHandles();
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfully got window handles");
			return windowHandles;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while getting window handles", e);
			return Set.of();
		}
	}
	
	//
	public String getTitle(String stepDescription) {
		try {
			String title = webDriver.getTitle();
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfully got title: {}", title);
			return title;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while getting title", e);
			return null;
		}
	}
	
	//
	public String getCurrentUrl(String stepDescription) {
		try {
			String url = webDriver.getCurrentUrl();
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfully got current page url: {}", url);
			return url;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while getting current page url", e);
			return null;
		}
	}
	
	//
	public boolean switchToWindow(String stepDescription, String windowHandle) {
		if(StringUtils.isNotBlank(windowHandle)) {
			try {
				webDriver.switchTo().window(windowHandle);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully switched to window");
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while switching to window", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Blank window handle: {}", windowHandle);
		}
		return false;
	}
	
	//
	public boolean switchToNewWindow(String stepDescription, WindowType windowType) {
		if(windowType != null) {
			try {
				webDriver.switchTo().newWindow(windowType);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully switched to new window");
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while switching to new window", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null window type: {}", windowType);
		}
		return false;
	}
	
	//
	public Alert switchToAlert(String stepDescription) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, defaultExplicitWaitDuration);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfully switched to alert: {}", alert);
			return alert;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while switching to alert", e);
		}
		return null;
	}
	
	public boolean sendKeysToAlert(String stepDescription, String value) {
		if(StringUtils.isNotBlank(value)) {
			try {
				Alert alert = switchToAlert(stepDescription);
				alert.sendKeys(value);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully sent keys: {} to alert: {}", value, alert);
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while sending keys to alert", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Blank value: {}", value);
		}
		return false;
	}
	
	public boolean acceptAlert(String stepDescription) {
		try {
			Alert alert = switchToAlert(stepDescription);
			alert.accept();
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfully accepted alert");
			return true;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while accepting for alert", e);
		}
		return false;
	}
	
	public boolean dismissAlert(String stepDescription) {
		try {
			Alert alert = switchToAlert(stepDescription);
			alert.dismiss();
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfully dismissed alert");
			return true;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while dismissing for alert", e);
		}
		return false;
	}
	
	//
	public boolean switchToIFrameByIndex(String stepDescription, int index) {
		if(index > 0) {
			try {
				webDriver.switchTo().frame(index);
				log(Level.INFO, stepDescription, "Successfully switched to iframe with index: {}", index);
				return true;
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occured while switching to iframe", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Invalid index: {}", index);
		}
		return false;
	}
	
	public boolean switchToIFrameByNameOrId(String stepDescription, String nameOrId) {
		if(StringUtils.isNotBlank(nameOrId)) {
			try {
				webDriver.switchTo().frame(nameOrId);
				log(Level.INFO, stepDescription, "Successfully switched to iframe with name or id: {}", nameOrId);
				return true;
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occured while switching to iframe", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Blank name of id: {}", nameOrId);
		}
		return false;
	}
	
	public boolean switchToIFrame(String stepDescription, String locatorString) {
		if(StringUtils.isNotBlank(locatorString)) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitDuration);
			return performSwitchToIFrameOperation(stepDescription, webElement);
		}else {
			log(Level.ERROR, stepDescription, "Blank locator string: {}", locatorString);
		}
		return false;
	}
	
	public boolean switchToIFrame(String stepDescription, By by) {
		if(by != null) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitDuration);
			return performSwitchToIFrameOperation(stepDescription, webElement);
		}else {
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
		}
		return false;
	}
	
	public boolean switchToIFrame(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			return performSwitchToIFrameOperation(stepDescription, webElement);
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
		}
		return false;
	}
	
	private boolean performSwitchToIFrameOperation(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			try {
				webDriver.switchTo().frame(webElement);
				log(Level.INFO, stepDescription, "Successfully switched to iframe: {}", webElement);
				return true;
			}catch (Exception e) {
				log(Level.ERROR, stepDescription, "Exception occurred while switcing to iframe", e);
			}
		}
		return false;
	}
	
	//
	public boolean switchToDefaultContent(String stepDescription) {
		try {
			webDriver.switchTo().defaultContent();
			log(Level.INFO, stepDescription, "Successfully switched to default content");
			return true;
		}catch (Exception e) {
			log(Level.ERROR, stepDescription, "Exception occurred while switching to default content", e);
		}
		return false;
	}
	
	
	// explicit waits
	
	public WebElement waitForPresenceOfElement(String stepDescription, String locatorString, Duration duration) {
		if(StringUtils.isNotBlank(locatorString)) {
			List<By> locators = getLocators(locatorString);
			Optional<WebElement> webElementOptional = locators.stream()
					.map(locator -> tryToWaitForPresenceOfElement(stepDescription, locator, duration != null ? duration : defaultExplicitWaitDuration))
					.filter(webElement -> webElement != null)
					.findFirst();
			if(webElementOptional.isPresent()) {
				return webElementOptional.get();
			}else {
				//logging and reporting
				log(Level.ERROR, stepDescription, "No element present by locator string: {}", locatorString);
			}
		}else {
			log(Level.ERROR, null, "Blank locator string: {}", locatorString, duration);
		}
		return null;
	}
	
	private WebElement tryToWaitForPresenceOfElement(String stepDescription, By by, Duration duration) {
		if(by != null && duration != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.presenceOfElementLocated(by));
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occured while waiting for presence of element {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
		}
		return null;
	}
	
	public WebElement waitForPresenceOfElement(String stepDescription, By by, Duration duration) {
		if(by != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.presenceOfElementLocated(by));
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occured while waiting for presence of element", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null locator: {}", by, duration);
		}
		return null;
	}
	
	public WebElement waitForVisibilityOfElement(String stepDescription, String locatorString, Duration duration) {
		if(StringUtils.isNotBlank(locatorString)) {
			Optional<WebElement> webElementOptional = getLocators(locatorString).stream()
					.map(locator -> tryWaitForVisibilityOfElement(stepDescription, locator, duration != null ? duration : defaultExplicitWaitDuration))
					.filter(webElement -> webElement != null)
					.findFirst();
			if(webElementOptional.isPresent()) {
				return webElementOptional.get();
			}else {
				//logging and reporting
				log(Level.ERROR, stepDescription, "No visible element found by locator string: {}", locatorString);
			}
		}else {
			log(Level.ERROR, stepDescription, "Blank locator string: {}", locatorString);
		}
		return null;
	}
	
	private WebElement tryWaitForVisibilityOfElement(String stepDescription, By by, Duration duration) {
		if(by != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration);
				return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for visibility of element {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
		}
		return null;
	}
	
	public WebElement waitForVisibilityOfElement(String stepDescription, By by, Duration duration) {
		if(by != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for visibility of element", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
		}
		return null;
	}
	
	public WebElement waitForVisibilityOfElement(String stepDescription, WebElement webElement, Duration duration) {
		if(webElement != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.visibilityOf(webElement));
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for invisibility of element", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
		}
		return null;
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, String locatorString, Duration duration) {
		if(StringUtils.isNotBlank(locatorString)) {
			Optional<By> locatorOptional = getLocators(locatorString).stream()
					.filter(locator -> tryWaitForInvisibilityOfElement(stepDescription, locator, duration != null ? duration : defaultExplicitWaitDuration))
					.findFirst();
			if(locatorOptional.isEmpty()) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "No invisible element found by locator string: {}", locatorString);
			}
			return locatorOptional.isPresent();
		}else {
			log(Level.ERROR, stepDescription, "Blank locator string: {}", locatorString);
		}
		return false;
	}
	
	private boolean tryWaitForInvisibilityOfElement(String stepDescription, By by, Duration duration) {
		if(by != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration);
				return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for invisibility of element {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
		}
		return false;
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, By by, Duration duration) {
		if(by != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for invisibility of element", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null locator: {}", by);
		}
		return false;
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, WebElement webElement, Duration duration) {
		if(webElement != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.invisibilityOf(webElement));
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for visibility of element", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
		}
		return false;
	}
	
	public boolean waitForAttributeToBe(String stepDescription, String locatorString, String attribute, String value, Duration duration) {
		if(StringUtils.isNotBlank(locatorString) && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				Optional<By> locatorOptional = getLocators(locatorString).stream()
						.filter(locator -> tryToWaitForAttributeToBe(stepDescription, locator, attribute, value, duration != null ? duration : defaultExplicitWaitDuration))
						.findFirst();
				if(locatorOptional.isPresent()) {
					//logging and reporting
					log(Level.ERROR, stepDescription, "No element found with {} = {} by locator string: {}", attribute, value, locatorString);
				}
				locatorOptional.isPresent();
			}catch (Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to be {}", attribute, value), e);
			}
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatorString: {}, attribute: {}, value: {}", locatorString, attribute, value);
		}
		return false;
	}
	
	private boolean tryToWaitForAttributeToBe(String stepDescription, By by, String attribute, String value, Duration duration) {
		if(by != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.attributeToBe(by, attribute, value));
			}catch (Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to be {}", attribute, value), e);
			}
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, attribute: {}, value: {}", by, attribute, value);
		}
		return false;
	}
	
	public boolean waitForAttributeToBe(String stepDescription, By by, String attribute, String value, Duration duration) {
		if(by != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.attributeToBe(by, attribute, value));
			}catch (Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to be {}", attribute, value), e);
			}
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, attribute: {}, value: {}", by, attribute, value);
		}
		return false;
	}

	public boolean waitForAttributeToBe(String stepDescription, WebElement webElement, String attribute, String value, Duration duration) {
		if(webElement != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.attributeToBe(webElement, attribute, value));
			}catch (Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to be {}", attribute, value), e);
			}
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, attribute: {}, value: {}", webElement, attribute, value);
		}
		return false;
	}
	
	public boolean waitForAttributeToContain(String stepDescription, String locatorString, String attribute, String value, Duration duration) {
		if(StringUtils.isNotBlank(locatorString) && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				Optional<By> locatorOptional = getLocators(locatorString).stream()
						.filter(locator -> tryToWaitForAttributeToContain(stepDescription, locator, attribute, value, duration != null ? duration : defaultExplicitWaitDuration))
						.findFirst();
				if(locatorOptional.isPresent()) {
					//logging and reporting
					log(Level.ERROR, stepDescription, "No element found with {} = {} by locator string: {}", attribute, value, locatorString);
				}
				locatorOptional.isPresent();
			}catch (Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to be {}", attribute, value), e);
			}
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is blank. locatorString: {}, attribute: {}, value: {}", locatorString, attribute, value);
		}
		return false;
	}
	
	private boolean tryToWaitForAttributeToContain(String stepDescription, By by, String attribute, String value, Duration duration) {
		if(by != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.attributeToBe(by, attribute, value));
			}catch (Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to be {}", attribute, value), e);
			}
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, attribute: {}, value: {}", by, attribute, value);
		}
		return false;
	}
	
	public boolean waitForAttributeToContain(String stepDescription, By by, String attribute, String value, Duration duration) {
		if(by != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.attributeToBe(by, attribute, value));
			}catch (Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to be {}", attribute, value), e);
			}
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. by: {}, attribute: {}, value: {}", by, attribute, value);
		}
		return false;
	}
	
	public boolean waitForAttributeToContain(String stepDescription, WebElement webElement, String attribute, String value, Duration duration) {
		if(webElement != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(value)) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(ExpectedConditions.attributeContains(webElement, attribute, value));
			}catch (Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, String.format("Exception occurred while waiting for attribute {} to contain {}", attribute, value), e);
			}
		}else {
			log(Level.ERROR, stepDescription, "One or more of the required fields is null or blank. webElement: {}, attribute: {}, value: {}", webElement, attribute, value);
		}
		return false;
	}
	
	public <T> T waitForCustomCondition(String stepDescription, ExpectedCondition<T> expectedCondition, Duration duration) {
		if(expectedCondition != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration != null ? duration : defaultExplicitWaitDuration);
				return wait.until(expectedCondition);
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for custom expected condition", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null custom expected condition: {}", expectedCondition);
		}
		return null;
	}

	// find elements
	
	public WebElement findElement(String locatorString) {
		if(StringUtils.isNotBlank(locatorString)) {
			List<By> locators = getLocators(locatorString);
			Optional<WebElement> webElementOptional = locators.stream()
					.map(locator -> findElement(locator))
					.filter(webElement -> webElement != null)
					.findFirst();
			if(webElementOptional.isPresent()) {
				return webElementOptional.get();
			}else {
				//logging and reporting
				log(Level.ERROR, null, "No web element found with locator string: {}", locatorString);
			}
		}else {
			log(Level.ERROR, null, "Blank locator string: {}", locatorString);
		}
		return null;
	}
	
	public WebElement findElement(By by) {
		if(by != null) {
			try {
				return webDriver.findElement(by);
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, null, "Exception occurred while finding web element", e);
			}
		}else {
			log(Level.ERROR, null, "Null locator: {}", by);
		}
		return null;
	}
	
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
				//logging and reporting
				log(Level.ERROR, null, "No web elements found with locator string: {}", locatorString);
			}
		}else {
			log(Level.ERROR, null, "Blank locator string: {}", locatorString);
		}
		return List.of();
	}
	
	private List<WebElement> tryFindingWebElements(By by) {
		if(by != null) {
			try {
				return webDriver.findElements(by);
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, null, "Exception occurred while finding web elements with locator", e);
			}
		}else {
			log(Level.ERROR, null, "Null locator {}", by);
		}
		return List.of();
	}
	
	public List<WebElement> findElements(By by) {
		if(by != null) {
			try {
				return webDriver.findElements(by);
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, null, "Exception occurred while finding web elements with locator", e);
			}
		}else {
			log(Level.ERROR, null, "Null locator {}", by);
		}
		return List.of();
	}
	
	public WebElement findChildWebElement(WebElement parentWebElement, String locatorString) {
		if(parentWebElement != null && StringUtils.isNotBlank(locatorString)) {
			List<By> locators = getLocators(locatorString);
			Optional<WebElement> webElementOptional = locators.stream()
					.map(locator -> findChildWebElement(parentWebElement, locator))
					.filter(webElement -> webElement != null)
					.findFirst();
			if(webElementOptional.isPresent()) {
				return webElementOptional.get();
			}else {
				//logging and reporting
				log(Level.ERROR, null, "No child web element found with locator string: {}", locatorString);
			}
		}else {
			log(Level.ERROR, null, "One or more of the required fields is null or blank. parentWebElement: {}, locatorString: {}", parentWebElement, locatorString);
		}
		return null;
	}
	
	public WebElement findChildWebElement(WebElement parentWebElement, By by) {
		if(parentWebElement != null && by != null) {
			try {
				return parentWebElement.findElement(by);
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, null, "Exception occurred while finding child web elements", e);
			}
		}else {
			log(Level.ERROR, null, "One or more of the required fields is null. parentWebElement: {}, by: {}", parentWebElement, by);
		}
		return null;
	}
	
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
				//logging and reporting
				log(Level.ERROR, null, "No child web elements found with locator string: {}", locatorString);
			}
		}else {
			log(Level.ERROR, null, "One or more of the required fields is null or blank. parentWebElement: {}, locatorString: {}", parentWebElement, locatorString);
		}
		return List.of();
	}
	
	private List<WebElement> tryFindingChildWebElements(WebElement parentWebElement, By by) {
		if(parentWebElement != null && by != null) {
			try {
				return parentWebElement.findElements(by);
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, null, "Exception occurred while finding child web elements", e);
			}
		}else {
			log(Level.ERROR, null, "One or more of the required fields is null. parentWebElement: {}, by: {}", parentWebElement, by);
		}
		return List.of();
	}
	
	public List<WebElement> findChildWebElements(WebElement parentWebElement, By by) {
		if(parentWebElement != null && by != null) {
			try {
				return parentWebElement.findElements(by);
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, null, "Exception occurred while finding child web elements", e);
			}
		}else {
			log(Level.ERROR, null, "One or more of the required fields is null. parentWebElement: {}, by: {}", parentWebElement, by);
		}
		return List.of();
	}
	
	
	// screenshot
	
	public File getScreenshot() {
		return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
	}
	
	
	// locator string
	
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
				//logging and reporting
				log(Level.ERROR, null, "No locators found with locator string: {}", locatorString);
			}
			return locators;
		}else {
			log(Level.ERROR, null, "Blank locator string: {}", locatorString);
			return List.of();
		}
	}
	
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
				//logging and reporting
				log(Level.ERROR, null, "Invalid locator key: {}", locatorKey);
				break;
			}
			return byLocator;
		}else {
			log(Level.ERROR, null, "One or more required fields is blank. locatorKey: {}, locatorValue: {}", locatorKey, locatorValue);
			return null;
		}
	}
	
	private List<String> extractLocatorPair(String locatorPairString) {
		if(StringUtils.isNotBlank(locatorPairString)) {
			try {
				return List.of(locatorPairString.split("~")[0], locatorPairString.split("~")[1]);
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, null, "Exception occurred while extracting locator pair", e);
			}
		}else {
			log(Level.ERROR, null, "Blank locator pair string: {}", locatorPairString);
		}
		return List.of();
	}
	
	private void log(Level level, String stepDescription, String message, Object... arguments) {
		message = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, message) : message;
		logger.atLevel(level).log(message, arguments);
	}
}
