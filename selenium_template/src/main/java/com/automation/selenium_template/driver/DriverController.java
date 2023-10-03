package com.automation.selenium_template.driver;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class DriverController {

	private WebDriver webDriver;
	private Duration defaultExplicitWaitDuration;
	private static Logger logger = LoggerFactory.getLogger(DriverController.class);
	
	public DriverController(WebDriver webDriver) {
		super();
		this.webDriver = webDriver;
	}
	
	public WebElement waitForPresenceOfElement(String stepDescription, String locatorString, Duration duration) {
		List<By> locators = getLocators(locatorString);
		Optional<WebElement> webElementOptional = locators.stream()
				.map(locator -> tryToWaitForPresenceOfElement(locator, duration))
				.filter(webElement -> webElement != null)
				.findFirst();
		System.out.println("here");
		if(webElementOptional.isPresent()) {
			return webElementOptional.get();
		}else {
			logger.error("No element present with locator string: {}", locatorString);
			log(Level.ERROR, stepDescription, "No element present with locator string", locatorString);
			return null;
		}
	}
	
	private WebElement tryToWaitForPresenceOfElement(By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.presenceOfElementLocated(by));
		}catch(Exception e) {
			logger.error("Exception occured while waiting for presence of element", e);
			return null;
		}
	}
	
	public WebElement waitForPresenceOfElement(String stepDescription, By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.presenceOfElementLocated(by));
		}catch(Exception e) {
			logger.error("Exception occured while waiting for presence of element", e);
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
			logger.error("No visible element found with locator string: {}", locatorString);
			return null;
		}
	}
	
	private WebElement tryToWaitForVisibilityOfElement(By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		}catch(Exception e) {
			logger.error("Exception occured while waiting for visibility of element", e);
			return null;
		}
	}
	
	public WebElement waitForVisibilityOfElement(String stepDescription, By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		}catch(Exception e) {
			logger.error("Exception occured while waiting for visibility of element", e);
			return null;
		}
	}
	
	public WebElement waitForVisibilityOfElement(String stepDescription, WebElement webElement, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.visibilityOf(webElement));
		}catch(Exception e) {
			logger.error("Exception occured while waiting for visibility of element", e);
			return null;
		}
	}
	
	public List<WebElement> waitForVisibilityOfAllElements(String stepDescription, String locatorString, Duration duration) {
		List<By> locators = getLocators(locatorString);
		Optional<List<WebElement>> webElementsOptional = locators.stream().map(locator -> tryToWaitForVisibilityOfAllElements(locator, duration)).filter(webElements -> !webElements.isEmpty()).findFirst();
		if(webElementsOptional.isPresent()) {
			return webElementsOptional.get();
		}else {
			logger.error("No visible elements found with locator string: {}", locatorString);
			return List.of();
		}
	}
	
	private List<WebElement> tryToWaitForVisibilityOfAllElements(By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		}catch(Exception e) {
			logger.error("Exception occured while waiting for visibility of element", e);
			return null;
		}
	}
	
	public List<WebElement> waitForVisibilityOfAllElements(String stepDescription, By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		}catch(Exception e) {
			logger.error("Exception occured while waiting for visibility of element", e);
			return null;
		}
	}
	
	public List<WebElement> waitForVisibilityOfAllElements(String stepDescription, List<WebElement> webElements, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
		}catch(Exception e) {
			logger.error("Exception occured while waiting for visibility of all elements", e);
			return null;
		}
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, String locatorString, Duration duration) {
		List<By> locators = getLocators(locatorString);
		boolean invisibleElementFlag = locators.stream().anyMatch(locator -> tryToWaitForInvisibilityOfElement(locator, duration));
		if(!invisibleElementFlag) {
			logger.error("No invisble element found with locator string: {}", locatorString);
		}
		return invisibleElementFlag;
	}
	
	private boolean tryToWaitForInvisibilityOfElement(By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}catch(Exception e) {
			logger.error("Exception occured while waiting for invisibility of element", e);
			return false;
		}
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, By by, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}catch(Exception e) {
			logger.error("Exception occured while waiting for invisibility of element", e);
			return false;
		}
	}
	
	public boolean waitForInvisibilityOfElement(String stepDescription, WebElement webElement, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(ExpectedConditions.invisibilityOf(webElement));
		}catch(Exception e) {
			logger.error("Exception occured while waiting for invisibility of element", e);
			return false;
		}
	}
	
	public <T>T waitForCustomCondition(String stepDescription, ExpectedCondition<T> expectedCondition, Duration duration) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, duration);
			return wait.until(expectedCondition);
		}catch(Exception e) {
			logger.error("Exception occured while waiting for custom condition", e);
			return null;
		}
	}
	
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
			logger.error("Exception occured while find elements: {}", e.getMessage());
		}
		return List.of();
	}
	
	private List<WebElement> tryFindingWebElements(By by) {
		try {
			return webDriver.findElements(by);
		}catch(Exception e) {
			logger.error("Exception occured while find elements: {}", e.getMessage());
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
			logger.error("Exception occured while processing locator pair string: {}", e.getMessage());
		}
		return List.of();
	}
	
	private void log(Level level, String stepDescription, String message, Object... arguments) {
		message = StringUtils.isNoneBlank(stepDescription) ? String.format("%s %s", stepDescription, message) : message;
		logger.atLevel(level).log(message, arguments);
	}
	
}
