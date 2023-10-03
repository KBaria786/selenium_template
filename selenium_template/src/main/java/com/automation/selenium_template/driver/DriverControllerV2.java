package com.automation.selenium_template.driver;

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

public class DriverControllerV2 {
	
	private WebDriver webDriver;
	private Duration defaultExplicitWaitDuration;
	private static Logger logger = LoggerFactory.getLogger(DriverController.class);
	
	public DriverControllerV2(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}
	
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
	
	/**
	 * Load a new web page in the current browser window
	 */
	public boolean get(String stepDescription, String url) {
		if(StringUtils.isNotEmpty(url)) {
			try {
				webDriver.get(url);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully loaded new web page: {}", url);
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occurred while loading new web page: {} {}", url, e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Invalid url: {}", url);
		}
		return false;
	}
	
	/**
	 * Close the current window, quitting the browser if it's the last window currently open. 
	 */
	public boolean close(String stepDescription) {
		try {
			webDriver.close();
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfuly closed current window");
			return true;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception occurred while closing current window: {}", e);
		}
		return false;
	}
	
	/**
	 * Quits the driver, closing every associated window. 
	 */
	public boolean quit(String stepDescription) {
		try {
			webDriver.quit();
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfuly quit web driver");
			return true;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception occurred while quiting web driver: {}", e);
		}
		return false;
	}
	
	/**
	 * Method to simulate typing or setting value of a web element
	 */
	public boolean sendKeys(String stepDescription, String locatorString, String value) {
		if(StringUtils.isNotBlank(locatorString) && StringUtils.isNotBlank(value)) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration);
			return performSendKeys(stepDescription, webElement, value);
		}else {
			log(Level.ERROR, stepDescription, "Invalid locator string: {} or Invalid value: {}", locatorString, value);
		}
		return false;
	}
	
	/**
	 * Method to simulate typing or setting value of a web element
	 */
	public boolean sendKeys(String stepDescription, By by, String value) {
		if(by != null && StringUtils.isNotBlank(value)) {
			WebElement webElement = waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration);
			return performSendKeys(stepDescription, webElement, value);
		}else {
			log(Level.ERROR, stepDescription, "Null locator: {} or Invalid value: {}", by, value);
		}
		return false;
	}
	
	/**
	 * Method to simulate typing or setting value of a web element
	 */
	public boolean sendKeys(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && StringUtils.isNotBlank(value)) {
			return performSendKeys(stepDescription, waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration), value);
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {} or Invalid value: {}", webElement, value);
		}
		return false;
	}
	
	/**
	 * Method to simulate typing or setting value of a web element
	 */
	private boolean performSendKeys(String stepDescription, WebElement webElement, String value) {
		if(webElement != null) {
			try {
				webElement.sendKeys(value);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully sent keys: {} to web element: {}", value, webElement);
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while sending keys to web element: {}", e);
			}
		}
		return false;
	}
	
	public boolean clearAndSendKeys(String stepDescription, String locatorString, String value) {
		return performClearAndSendKeys(stepDescription, waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration), value);
	}
	
	public boolean clearAndSendKeys(String stepDescription, By by, String value) {
		return performClearAndSendKeys(stepDescription, waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration), value);
	}
	
	public boolean clearAndSendKeys(String stepDescription, WebElement webElement, String value) {
		return performClearAndSendKeys(stepDescription, waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration), value);
	}
	
	private boolean performClearAndSendKeys(String stepDescription, WebElement webElement, String value) {
		if(webElement != null) {
			try {
				webElement.clear();
				webElement.sendKeys(value);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully cleared and sent keys: {} to web element: {}", value, webElement);
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while clearing and sending keys to web element: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
		}
		return false;
	}
	
	public boolean click(String stepDescription, String locatorString) {
		return performClick(stepDescription, waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration));
	}
	
	public boolean click(String stepDescription, By by) {
		return performClick(stepDescription, waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration));
	}
	
	public boolean click(String stepDescription, WebElement webElement) {
		return performClick(stepDescription, waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration));
	}
	
	private boolean performClick(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			try {
				webElement.click();
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully clicked web element: {}", webElement);
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while clicking web element: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
		}
		return false;
	}
	
	public boolean clickUsingActions(String stepDescription, String locatorString) {
		return performClickUsingActions(stepDescription, waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration));
	}
	
	public boolean clickUsingActions(String stepDescription, By by) {
		return performClickUsingActions(stepDescription, waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration));
	}
	
	public boolean clickUsingActions(String stepDescription, WebElement webElement) {
		return performClickUsingActions(stepDescription, waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration));
	}
	
	private boolean performClickUsingActions(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			try {
				Actions actions = new Actions(webDriver);
				actions.moveToElement(webElement).click().build().perform();
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully clicked web element: {}", webElement);
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while clicking web element: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
		}
		return false;
	}
	
	public boolean clickUsingJSExecutor(String stepDescription, String locatorString) {
		return clickUsingJSExecutor(stepDescription, waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitDuration));
	}
	
	public boolean clickUsingJSExecutor(String stepDescription, By by) {
		return clickUsingJSExecutor(stepDescription, waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitDuration));
	}
	
	public boolean clickUsingJSExecutor(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			try {
				JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
				javascriptExecutor.executeScript("arguments[0].click();", webElement);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully clicked web element: {}", webElement);
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while clicking web element: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
		}
		return false;
	}
	
	public boolean selectByVisibleText(String stepDescription, String locatorString, String visibleText) {
		return performSelectByVisibleText(stepDescription, waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration), visibleText);
	}
	
	public boolean selectByVisibleText(String stepDescription, By by, String visibleText) {
		return performSelectByVisibleText(stepDescription, waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration), visibleText);
	}
	
	public boolean selectByVisibleText(String stepDescription, WebElement webElement, String visibleText) {
		return performSelectByVisibleText(stepDescription, waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration), visibleText);
	}
	
	private boolean performSelectByVisibleText(String stepDescription, WebElement webElement, String visibleText) {
		if(webElement != null) {
			try {
				Select select = new Select(webElement);
				select.selectByVisibleText(visibleText);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully selected option: {} by visible text on web element: {}", visibleText, webElement);
				return true;
			}catch (Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while selecting by visible text on web element: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
		}
		return false;
	}
	
	public boolean selectByValue(String stepDescription, String locatorString, String value) {
		return performSelectByValue(stepDescription, waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration), value);
	}
	
	public boolean selectByValue(String stepDescription, By by, String value) {
		return performSelectByValue(stepDescription, waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration), value);
	}
	
	public boolean selectByValue(String stepDescription, WebElement webElement, String value) {
		return performSelectByValue(stepDescription, waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration), value);
	}
	
	private boolean performSelectByValue(String stepDescription, WebElement webElement, String value) {
		if(webElement != null) {
			try {
				Select select = new Select(webElement);
				select.selectByValue(value);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully selected option by value: {} on web element: {}", value, webElement);
				return true;
			}catch (Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while selecting option by value on web element: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
		}
		return false;
	}
	
	public boolean dragAndDrop(String stepDescription, String sourceLocatorString, String targetLocatorString) {
		WebElement sourceWebElement = waitForVisibilityOfElement(stepDescription, sourceLocatorString, defaultExplicitWaitDuration);
		WebElement targetWebElement = waitForVisibilityOfElement(stepDescription, targetLocatorString, defaultExplicitWaitDuration);
		return performDragAndDrop(stepDescription, sourceWebElement, targetWebElement);
	}
	
	public boolean dragAndDrop(String stepDescription, By sourceLocator, By targetLocator, String value) {
		WebElement sourceWebElement = waitForVisibilityOfElement(stepDescription, sourceLocator, defaultExplicitWaitDuration);
		WebElement targetWebElement = waitForVisibilityOfElement(stepDescription, targetLocator, defaultExplicitWaitDuration);
		return performDragAndDrop(stepDescription, sourceWebElement, targetWebElement);
	}
	
	public boolean dragAndDrop(String stepDescription, WebElement sourceWebElement, WebElement targetWebElement) {
		sourceWebElement = waitForVisibilityOfElement(stepDescription, sourceWebElement, defaultExplicitWaitDuration);
		targetWebElement = waitForVisibilityOfElement(stepDescription, targetWebElement, defaultExplicitWaitDuration);
		return performDragAndDrop(stepDescription, sourceWebElement, targetWebElement);
	}
	
	private boolean performDragAndDrop(String stepDescription, WebElement sourceWebElement, WebElement targetWebElement) {
		if(sourceWebElement != null && targetWebElement != null) {
			try {
				Actions actions = new Actions(webDriver);
				actions.moveToElement(sourceWebElement).clickAndHold().moveToElement(targetWebElement).release().build().perform();
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully dragged and dropped element: {}", sourceWebElement);
				return true;
			}catch (Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occurred while dragging and dropping element: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null source or target web elements: {}, {}", sourceWebElement, targetWebElement);
		}
		return false;
	}
	
	public boolean scrollIntoView(String stepDescription, String locatorString, String scrollOptions) {
		return scrollIntoView(stepDescription, waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitDuration), scrollOptions);
	}
	
	public boolean scrollIntoView(String stepDescription, By by, String scrollOptions) {
		return scrollIntoView(stepDescription, waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitDuration), scrollOptions);
	}
	
	public boolean scrollIntoView(String stepDescription, WebElement webElement, String scrollOptions) {
		if(webElement != null) {
			try {
				JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
				String script = String.format("arguments[0].scrollIntoView(%s);", scrollOptions);
				javascriptExecutor.executeScript(script, webElement);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully scrolled to element: {}", webElement);
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while scrolling to element: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
		}
		return false;
	}
	
	public String getAttributeValue(String stepDescription, String locatorString, String attribute) {
		return getAttributeValue(stepDescription, waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitDuration), attribute);
	}
	
	public String getAttributeValue(String stepDescription, By by, String attribute) {
		return getAttributeValue(stepDescription, waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitDuration), attribute);
	}
	
	public String getAttributeValue(String stepDescription, WebElement webElement, String attribute) {
		if(webElement != null && StringUtils.isNotBlank(attribute)) {
			try {
				String attributeValue = webElement.getAttribute(attribute);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully got attribute value: {} = {}", attribute, attributeValue);
				return attributeValue;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while getting attribute value: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element or invalid attribute: {}, {}", webElement, attribute);
		}
		return null;
	}
	
	public String getWindowHandle(String stepDescription) {
		try {
			String windowHandle = webDriver.getWindowHandle();
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfully got window handle");
			return windowHandle;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while getting window handle: {}", e);
			return null;
		}
	}
	
	public Set<String> getWindowHandles(String stepDescription) {
		try {
			Set<String> windowHandles = webDriver.getWindowHandles();
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfully got window handles");
			return windowHandles;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while getting window handles: {}", e);
			return Set.of();
		}
	}
	
	public String getTitle(String stepDescription) {
		try {
			String title = webDriver.getTitle();
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfully got title: {}", title);
			return title;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while getting title: {}", e);
			return null;
		}
	}
	
	public boolean switchToIFrame(String stepDescription, int frameIndex) {
		if(frameIndex >= 0) {
			try {
				webDriver.switchTo().frame(frameIndex);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully switched to iframe");
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while switching to iframe: {}", e);
			}
			
		}else {
			log(Level.ERROR, stepDescription, "Invalid frame index: {}", frameIndex);
		}
		return false;
	}
	
	public boolean switchToIFrame(String stepDescription, String frameNameOrId) {
		if(StringUtils.isNotBlank(frameNameOrId)) {
			try {
				webDriver.switchTo().frame(frameNameOrId);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully switched to iframe");
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while switching to iframe: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Invalid frame name or id: {}", frameNameOrId);
		}
		return false;
	}
	
	public boolean switchToIFrameUsingLocatorString(String stepDescription, String locatorString) {
		return switchToIFrame(stepDescription, waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitDuration));
	}
	
	public boolean switchToIFrame(String stepDescription, By by) {
		return switchToIFrame(stepDescription, waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitDuration));
	}
	
	public boolean switchToIFrame(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			try {
				webDriver.switchTo().frame(webElement);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully switched to iframe");
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while switching to iframe: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {}", webElement);
		}
		return false;
	}
	
	public boolean switchToWindow(String stepDescription, String windowHandle) {
		if(StringUtils.isNotBlank(windowHandle)) {
			try {
				webDriver.switchTo().window(windowHandle);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully switched to window");
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while switching to window: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Invalid window handle: {}", windowHandle);
		}
		return false;
	}
	
	public boolean switchToNewWindow(String stepDescription, WindowType windowType) {
		if(windowType != null) {
			try {
				webDriver.switchTo().newWindow(windowType);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully switched to new window");
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while switching to new window: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null window type: {}", windowType);
		}
		return false;
	}
	
	public boolean switchToDefaultContent(String stepDescription) {
		try {
			webDriver.switchTo().defaultContent();
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfully switched to default content");
			return true;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while switching to default content: {}", e);
		}
		return false;
	}
	
	public Alert switchToAlert(String stepDescription) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, defaultExplicitWaitDuration);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			//logging and reporting
			log(Level.INFO, stepDescription, "Successfully switched to alert");
			return alert;
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while switching to alert: {}", e);
			return null;
		}
	}
	
	public boolean sendKeysToAlert(String stepDescription, String value) {
		Alert alert = switchToAlert(stepDescription);
		if(alert != null) {
			try {
				alert.sendKeys(value);
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully sent keys to alert");
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while sending keys to alert: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null alert: {}", alert);
		}
		return false;
	}
	
	public boolean acceptAlert(String stepDescription) {
		Alert alert = switchToAlert(stepDescription);
		if(alert != null) {
			try {
				alert.accept();
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully accepted alert");
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while accepting for alert: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null alert: {}", alert);
		}
		return false;
	}
	
	public boolean dismissAlert(String stepDescription) {
		Alert alert = switchToAlert(stepDescription);
		if(alert != null) {
			try {
				alert.dismiss();
				//logging and reporting
				log(Level.INFO, stepDescription, "Successfully dismissed alert");
				return true;
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception while dismissing for alert: {}", e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null alert: {}", alert);
		}
		return false;
	}
	
	public WebElement waitForPresenceOfElement(String stepDescription, String locatorString, Duration duration) {
		List<By> locators = getLocators(locatorString);
		Optional<WebElement> webElementOptional = locators.stream()
				.map(locator -> tryToWaitForPresenceOfElement(locator, duration))
				.filter(webElement -> webElement != null)
				.findFirst();
		if(webElementOptional.isPresent()) {
			return webElementOptional.get();
		}else {
			//logging and reporting
			log(Level.ERROR, stepDescription, "No element present by locator string: {}", locatorString);
			return null;
		}
	}
	
	private WebElement tryToWaitForPresenceOfElement(By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.presenceOfElementLocated(by));
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, null, "Exception while waiting for presence of element located by: {} {}", by, e);
			return null;
		}
	}
	
	public WebElement waitForPresenceOfElement(String stepDescription, By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.presenceOfElementLocated(by));
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while waiting for presence of element located by: {} {}", by, e);
			return null;
		}
	}
	
	public WebElement waitForVisibilityOfElement(String stepDescription, String locatorString, Duration duration) {
		List<By> locators = getLocators(locatorString);
		Optional<WebElement> webElementOptional = locators.stream()
				.map(locator -> tryToWaitForVisibilityOfElement(locator, duration))
				.filter(webElement -> webElement != null)
				.findFirst();
		if(webElementOptional.isPresent()) {
			return webElementOptional.get();
		}else {
			//logging and reporting
			log(Level.ERROR, stepDescription, "No visible element found with locator string: {}", locatorString);
			return null;
		}
	}
	
	private WebElement tryToWaitForVisibilityOfElement(By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, null, "Exception while waiting for invisbility of element located by: {} {}", by, e);
			return null;
		}
	}
	
	public WebElement waitForVisibilityOfElement(String stepDescription, By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while waiting for visbility of element located by: {} {}", by, e);
			return null;
		}
	}
	
	public WebElement waitForVisibilityOfElement(String stepDescription, WebElement webElement, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.visibilityOf(webElement));
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while waiting for visibility of element: {} {}", webElement, e);
			return null;
		}
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, String locatorString, Duration duration) {
		boolean invisibilityFlag = getLocators(locatorString).stream().allMatch(locator -> tryToWaitForInvisibilityOfElement(stepDescription, locator, duration));
		if(!invisibilityFlag) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "No invisible element found with locator string: {}", locatorString);
		}
		return invisibilityFlag;
	}
	
	public boolean tryToWaitForInvisibilityOfElement(String stepDescription,By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while waiting for invisbility of element located by: {} {}", by, e);
			return false;
		}
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while waiting for invisbility of element located by: {} {}", by, e);
			return false;
		}
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, WebElement webElement, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.invisibilityOf(webElement));
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception while waiting for invisbility of element: {} {}", webElement, e);
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean waitForAttributeToBe(String stepDescription, String locatorString, String attrubte, String attributeValue, Duration duration) {
		return waitForAttributeToBe(stepDescription, findElement(locatorString), attrubte, attributeValue, duration);
	}
	
	public boolean waitForAttributeToBe(String stepDescription, By by, String attribute, String attributeValue, Duration duration) {
		if(by != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(attributeValue)) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, by, duration);
			if(webElement != null) {
				return waitForAttributeToBe(stepDescription, webElement, attribute, attributeValue, duration);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null locator: {} or Invalid attribute: {} / attribute value: {}", by, attribute, attributeValue);
		}
		return false;
	}
	
	public boolean waitForAttributeToBe(String stepDescription, WebElement webElement, String attribute, String attributeValue, Duration duration) {
		if(webElement != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(attributeValue)) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration);
				return wait.until(ExpectedConditions.attributeToBe(webElement, attribute, attributeValue));
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for {} to be {}: {}", attribute, attributeValue, e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {} or Invalid attribute: {} / attribute value: {}", webElement, attribute, attributeValue);
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	public boolean waitForAttributeToContain(String stepDescription, String locatorString, String attribute, String attributeValue, Duration duration) {
		if(StringUtils.isNotBlank(locatorString) && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(attributeValue)) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, locatorString, duration);
			if(webElement != null) {
				return waitForAttributeToContain(stepDescription, webElement, attribute, attributeValue, duration);
			}
		}else {
			log(Level.ERROR, stepDescription, "Invalid locator string: {} or Invalid attribute: {} / attribute value: {}", locatorString, attribute, attributeValue);
		}
		return false;
	}
	
	public boolean waitForAttributeToContain(String stepDescription, By by, String attribute, String attributeValue, Duration duration) {
		if(by != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(attributeValue)) {
			WebElement webElement = waitForPresenceOfElement(stepDescription, by, duration);
			if(webElement != null) {
				return waitForAttributeToContain(stepDescription, webElement, attribute, attributeValue, duration);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null locator: {} or Invalid attribute: {} / attribute value: {}", by, attribute, attributeValue);
		}
		return false;
	}
	
	public boolean waitForAttributeToContain(String stepDescription, WebElement webElement, String attribute, String attributeValue, Duration duration) {
		if(webElement != null && StringUtils.isNotBlank(attribute) && StringUtils.isNotBlank(attributeValue)) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, duration);
				return wait.until(ExpectedConditions.attributeContains(webElement, attribute, attributeValue));
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, stepDescription, "Exception occurred while waiting for {} to contain {}: {}", attribute, attributeValue, e);
			}
		}else {
			log(Level.ERROR, stepDescription, "Null web element: {} or Invalid attribute: {} / attribute value: {}", webElement, attribute, attributeValue);
		}
		return false;
	}
	
	public <T> T waitForCustomCondition(String stepDescription, ExpectedCondition<T> expectedCondition, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(expectedCondition);
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, stepDescription, "Exception occurred while waiting for custom condition: {}", e);
			return null;
		}
	}
	
	public WebElement findElement(String locatorString) {
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
			return null;
		}
	}
	
	public WebElement findElement(By by) {
		try {
			return webDriver.findElement(by);
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, null, "Exception occurred while finding web element: {}", e);
		}
		return null;
	}
	
	public List<WebElement> findElements(String locatorString) {
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
			return List.of();
		}
	}
	
	private List<WebElement> tryFindingWebElements(By by) {
		try {
			return webDriver.findElements(by);
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, null, "Exception occurred while finding web elements: {}", e);
		}
		return List.of();
	}
	
	public List<WebElement> findElements(By by) {
		try {
			return webDriver.findElements(by);
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, null, "Exception occurred while finding web elements with locator: {}", e);
		}
		return List.of();
	}
	
	public WebElement findChildWebElement(WebElement parentWebElement, String locatorString) {
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
			return null;
		}
	}
	
	public WebElement findChildWebElement(WebElement parentWebElement, By by) {
		if(parentWebElement != null && by != null) {
			try {
				return parentWebElement.findElement(by);
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, null, "Exception occurred while finding child web elements: {}", e);
			}
		}else {
			log(Level.ERROR, null, "null parent web element or locator {}, {}", parentWebElement, by);
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
			log(Level.ERROR, null, "Null parent web element or invalid locator string {}, {}", parentWebElement, locatorString);
		}
		return List.of();
	}
	
	private List<WebElement> tryFindingChildWebElements(WebElement parentWebElement, By by) {
		if(parentWebElement != null && by != null) {
			try {
				return parentWebElement.findElements(by);
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, null, "Exception occurred while finding child web elements: {}", e);
			}
		}else {
			log(Level.ERROR, null, "Null parent web element or locator {}, {}", parentWebElement, by);
		}
		return List.of();
	}
	
	public List<WebElement> findChildWebElements(WebElement parentWebElement, By by) {
		if(parentWebElement != null && by != null) {
			try {
				return parentWebElement.findElements(by);
			}catch(Exception e) {
				//logging and reporting
				log(Level.ERROR, null, "Exception occurred while finding child web elements: {}", e);
			}
		}else {
			log(Level.ERROR, null, "Null parent web element or locator {}, {}", parentWebElement, by);
		}
		return List.of();
	}
	
	private List<By> getLocators(String locatorString) {
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
	}
	
	private By getLocator(String locatorKey, String locatorValue) {
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
	}
	
	private List<String> extractLocatorPair(String locatorPairString) {
		try {
			return List.of(locatorPairString.split("~")[0], locatorPairString.split("~")[1]);
		}catch(Exception e) {
			//logging and reporting
			log(Level.ERROR, null, "Exception occurred while extracting locator pair: {}", e);
		}
		return List.of();
	}
	
	private void log(Level level, String stepDescription, String message, Object... arguments) {
		message = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, message) : message;
		logger.atLevel(level).log(message, arguments);
	}
	
}
