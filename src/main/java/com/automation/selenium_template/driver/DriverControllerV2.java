package com.automation.selenium_template.driver;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.selenium_template.reports.ReportStatus;

public class DriverControllerV2 {

	private WebDriver webDriver;
	
	private Duration defaultExplicitWaitDuration;
	
	private static Logger logger = LoggerFactory.getLogger(DriverControllerV2.class);

	public DriverControllerV2(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}
	
	public boolean clearAndSendKeys(String stepDescription, String locatorString, String value) {
		if(waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration)) {
			return clearAndSendKeys(stepDescription, getLocators(locatorString).get(0), value);
		}
		return false;
	}
	
	public boolean clearAndSendKeys(String stepDescription, By by, String value) {
		if(waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return clearAndSendKeys(stepDescription, webElement, value);
			}
		}
		return false;
	}
	
	public boolean clearAndSendKeys(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration)) {
			try {
				webElement.clear();
				webElement.sendKeys(value);
				return true;
			}catch(Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean sendKeys(String stepDescription, String locatorString, String value) {
		if(waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration)) {
			return sendKeys(stepDescription, getLocators(locatorString).get(0), value);
		}
		return false;
	}
	
	public boolean sendKeys(String stepDescription, By by, String value) {
		if(waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return sendKeys(stepDescription, webElement, value);
			}
		}
		return false;
	}
	
	public boolean sendKeys(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration)) {
			try {
				webElement.sendKeys(value);
				return true;
			}catch(Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean click(String stepDescription, String locatorString) {
		if(waitForClickabilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration)) {
			return click(stepDescription, getLocators(locatorString).get(0));
		}
		return false;
	}
	
	public boolean click(String stepDescription, By by) {
		if(waitForClickabilityOfElement(stepDescription, by, defaultExplicitWaitDuration)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return click(stepDescription, webElement);
			}
		}
		return false;
	}
	
	public boolean click(String stepDescription, WebElement webElement) {
		if(webElement != null && waitForClickabilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration)) {
			try {
				webElement.click();
				return true;
			}catch(Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean clickUsingActions(String stepDescription, String locatorString) {
		if(waitForClickabilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration)) {
			return clickUsingActions(stepDescription, getLocators(locatorString).get(0));
		}
		return false;
	}
	
	public boolean clickUsingActions(String stepDescription, By by) {
		if(waitForClickabilityOfElement(stepDescription, by, defaultExplicitWaitDuration)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return clickUsingActions(stepDescription, webElement);
			}
		}
		return false;
	}
	
	public boolean clickUsingActions(String stepDescription, WebElement webElement) {
		if(webElement != null && waitForClickabilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration)) {
			try {
				Actions actions = new Actions(webDriver);
				actions.moveToElement(webElement).click().build().perform();
				return true;
			}catch(Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean clickUsingJSExecutor(String stepDescription, String locatorString) {
		if(waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitDuration)) {
			return clickUsingJSExecutor(stepDescription, getLocators(locatorString).get(0));
		}
		return false;
	}
	
	public boolean clickUsingJSExecutor(String stepDescription, By by) {
		if(waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitDuration)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return clickUsingJSExecutor(stepDescription, webElement);
			}
		}
		return false;
	}
	
	public boolean clickUsingJSExecutor(String stepDescription, WebElement webElement) {
		if(webElement != null) {
			try {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
				jsExecutor.executeScript("arguments[0].click();", webElement);
				return true;
			}catch(Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean selectByVisibleText(String stepDescription, String locatorString, String visibleText) {
		if(waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration)) {
			return selectByVisibleText(stepDescription, getLocators(locatorString).get(0), visibleText);
		}
		return false;
	}
	
	public boolean selectByVisibleText(String stepDescription, By by, String visibleText) {
		if(waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return selectByVisibleText(stepDescription, webElement, visibleText);
			}
		}
		return false;
	}
	
	public boolean selectByVisibleText(String stepDescription, WebElement webElement, String visibleText) {
		if(webElement != null && waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration)) {
			try {
				Select select = new Select(webElement);
				select.selectByVisibleText(visibleText);
				return true;
			}catch(Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean selectByValue(String stepDescription, String locatorString, String value) {
		if(waitForVisibilityOfElement(stepDescription, locatorString, defaultExplicitWaitDuration)) {
			return selectByValue(stepDescription, getLocators(locatorString).get(0), value);
		}
		return false;
	}
	
	public boolean selectByValue(String stepDescription, By by, String value) {
		if(waitForVisibilityOfElement(stepDescription, by, defaultExplicitWaitDuration)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				return selectByValue(stepDescription, webElement, value);
			}
		}
		return false;
	}
	
	public boolean selectByValue(String stepDescription, WebElement webElement, String value) {
		if(webElement != null && waitForVisibilityOfElement(stepDescription, webElement, defaultExplicitWaitDuration)) {
			try {
				Select select = new Select(webElement);
				select.selectByValue(value);
				return true;
			}catch(Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean dragAndDrop(String stepDescription, String sourceLocatorString, String targetLocatorString) {
		if(waitForVisibilityOfElement(stepDescription, sourceLocatorString, defaultExplicitWaitDuration)
				&& waitForVisibilityOfElement(stepDescription, targetLocatorString, defaultExplicitWaitDuration)) {
			return dragAndDrop(stepDescription, getLocators(sourceLocatorString).get(0), getLocators(targetLocatorString).get(0));
		}
		return false;
	}
	
	public boolean dragAndDrop(String stepDescription, By sourceBy, By targetBy) {
		if(waitForVisibilityOfElement(stepDescription, sourceBy, defaultExplicitWaitDuration)
				&& waitForVisibilityOfElement(stepDescription, targetBy, defaultExplicitWaitDuration)) {
			return dragAndDrop(stepDescription, findElement(sourceBy), findElement(targetBy));
		}
		return false;
	}
	
	public boolean dragAndDrop(String stepDescription, WebElement sourceWebElement, WebElement targetWebElement) {
		if(waitForVisibilityOfElement(stepDescription, sourceWebElement, defaultExplicitWaitDuration) 
				&& waitForVisibilityOfElement(stepDescription, targetWebElement, defaultExplicitWaitDuration)) {
			try {
				Actions actions = new Actions(webDriver);
				actions.moveToElement(sourceWebElement)
				.clickAndHold(sourceWebElement)
				.moveToElement(targetWebElement)
				.release(targetWebElement)
				.build()
				.perform();
				return true;
			}catch(Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean waitForClickabilityOfElement(String stepDescription, String locatorString, Duration timeout) {
		return getLocators(locatorString).stream()
				.allMatch(locator -> tryToWaitForClickabilityOfElement(stepDescription, locator, timeout));
	}
	
	private boolean tryToWaitForClickabilityOfElement(String stepDescription, By by, Duration timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, timeout);
			return wait.until(ExpectedConditions.elementToBeClickable(by)) != null;
		}catch(Exception e) {
			
		}
		return false;
	}
	
	public boolean waitForClickabilityOfElement(String stepDescription, By by, Duration timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, timeout);
			return wait.until(ExpectedConditions.elementToBeClickable(by)) != null;
		}catch(Exception e) {
			
		}
		return false;
	}
	
	public boolean waitForClickabilityOfElement(String stepDescription, WebElement webElement, Duration timeout) {
		if(webElement != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, timeout);
				return wait.until(ExpectedConditions.elementToBeClickable(webElement)) != null;
			}catch(Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean waitForPresenceOfElement(String stepDescription, String locatorString, Duration timeout) {
		return getLocators(locatorString).stream()
				.allMatch(locator -> tryToWaitForPresenceOfElements(stepDescription, locator, timeout));
	}
	
	private boolean tryToWaitForPresenceOfElements(String stepDescription, By by, Duration timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, timeout);
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)) != null;
		}catch(Exception e) {
			logger.error("Exception occured while waiting for presence of element: {}", e);
		}
		return false;
	}
	
	public boolean waitForPresenceOfElement(String stepDescription, By by, Duration timeout) {
		if(by != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, timeout);
				return wait.until(ExpectedConditions.presenceOfElementLocated(by)) != null;
			}catch(Exception e) {
				logger.error("Exception occured while waiting for presence of element: {}", e);
			}
		}
		logger.error("null locator");
		return false;
	}
	
	public boolean waitForVisibilityOfElement(String stepDescription, String locatorString, Duration timeout) {
//		return getLocators(locatorString).stream()
//				.allMatch(locator -> tryToWaitForVisibilityOfElement(stepDescription, locator, timeout));
		
		// return true if any of the locator passes the visibility check
		List<By> locators = getLocators(locatorString).stream()
				.filter(locator -> tryToWaitForVisibilityOfElement(stepDescription, locator, timeout))
				.toList();
		return locators.size() > 0 ? true : false;
	}
	
	private boolean tryToWaitForVisibilityOfElement(String stepDescription, By by, Duration timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, timeout);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(by)) != null;
		}catch(Exception e) {
			logger.error("Exception occured while waiting for visibility of element : {}", e);
		}
		return false;
	}
	
	public boolean waitForVisibilityOfElement(String stepDescription, By by, Duration timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, timeout);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(by)) != null;
		}catch(Exception e) {
			
		}
		return false;
	}
	
	public boolean waitForVisibilityOfElement(String stepDescription, WebElement webElement, Duration timeout) {
		if(webElement != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, timeout);
				return wait.until(ExpectedConditions.visibilityOf(webElement)) != null;
			}catch(Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, String locatorString, Duration timeout) {
		return getLocators(locatorString).stream()
				.allMatch(locator -> tryToWaitForInvisibilityOfElement(stepDescription, locator, timeout));
	}
	
	private boolean tryToWaitForInvisibilityOfElement(String stepDescription, By by, Duration timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, timeout);
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}catch(Exception e) {
			
		}
		return false;
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, By by, Duration timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, timeout);
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}catch(Exception e) {
			
		}
		return false;
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, WebElement webElement, Duration timeout) {
		if(webElement != null) {
			try {
				WebDriverWait wait = new WebDriverWait(webDriver, timeout);
				return wait.until(ExpectedConditions.invisibilityOf(webElement));
			}catch(Exception e) {
				
			}
		}
		return false;
	}
	
	public boolean scrollToElement(String stepDescription, String locatorString, String scrollOptions) {
		if(waitForPresenceOfElement(stepDescription, locatorString, defaultExplicitWaitDuration)) {
			return scrollToElement(stepDescription, getLocators(locatorString).get(0), scrollOptions);
		}
		return false;
	}
	
	public boolean scrollToElement(String stepDescription, By by, String scrollOptions) {
		if(by != null && waitForPresenceOfElement(stepDescription, by, defaultExplicitWaitDuration)) {
			WebElement webElement = findElement(by);
			if(webElement != null) {
				try {
					JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
					String script = String.format("arguments[0].scrollIntoView(%s);", scrollOptions);
					javascriptExecutor.executeScript(script, webElement);
					return true;
				}catch(Exception e) {
					logger.error("Exception occured while scrolling to element: {}", e);
				}
			}
		}
		return false;
	}
	
	// find elements
	
	public WebElement findElement(String locatorString) {
		List<WebElement> webElements = findElements(locatorString);
		return webElements.size() > 0 ? webElements.get(0) : null;
	}
	
	public WebElement findElement(By by) {
		List<WebElement> webElements = findElements(by);
		return webElements.size() > 0 ? webElements.get(0) : null;
	}
	
	public List<WebElement> findElements(String locatorString) {
		List<WebElement> webElements = Arrays.asList();
		List<By> locators = getLocators(locatorString);
		if(locators.size() > 0) {
			try {
				webElements = locators.stream()
						.map(locator -> tryFindingWebElements(locator))
						.filter(webElementList -> webElementList.size() > 0)
						.findFirst()
						.get();
			}catch(Exception e) {
				logger.error("No web elements found with locator string: {}", locatorString);
			}
		}
		return webElements;
	}
	
	public List<WebElement> findElements(By by) {
		try {
			return webDriver.findElements(by);
		}catch(Exception e) {
			logger.error("Exception occured while find elements: {}", e);
		}
		return List.of();
	}
	
	private List<WebElement> tryFindingWebElements(By by) {
		try {
			return webDriver.findElements(by);
		}catch(Exception e) {
			logger.error("Exception occured while find elements: {}", e);
		}
		return List.of();
	}
	
	// locator strings
	
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
		if(locators.size() == 0) {
			logger.error("No locators found with locator string: {}", locatorString);
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
			logger.error("Invalid locator key: {}", locatorKey);
			break;
		}
		return byLocator;
	}
	
	private List<String> extractLocatorPair(String locatorPairString) {
		try {
			return List.of(locatorPairString.split("~")[0], locatorPairString.split("~")[1]);
		}catch(Exception e) {
			//logging and reporting
			logger.error("Exception occured while processing locator pair string: {}", e);
		}
		return List.of();
	}
	
	// reporting
	private void report(ReportStatus reportStatus, String stepDescription, String message) {
		
	}
	
	private void report(ReportStatus reportStatus, String stepDescription, String message, Exception e) {
		
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
	
	
	
}
